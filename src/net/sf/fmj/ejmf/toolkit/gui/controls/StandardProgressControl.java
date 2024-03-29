/*
 * Copyright (C) 2009, Edmundo Albuquerque de Souza e Silva.
 *
 * This file may be distributed under the terms of the Q Public License
 * as defined by Trolltech AS of Norway and appearing in the file
 * LICENSE.QPL included in the packaging of this file.
 *
 * THIS FILE IS PROVIDED AS IS WITH NO WARRANTY OF ANY KIND, INCLUDING
 * THE WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE.  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL,
 * INDIRECT OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING
 * FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 * NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION
 * WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 */

/*
 * Copyright (C) 2009, Edmundo Albuquerque de Souza e Silva.
 *
 * This file may be distributed under the terms of the Q Public License
 * as defined by Trolltech AS of Norway and appearing in the file
 * LICENSE.QPL included in the packaging of this file.
 *
 * THIS FILE IS PROVIDED AS IS WITH NO WARRANTY OF ANY KIND, INCLUDING
 * THE WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE.  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL,
 * INDIRECT OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING
 * FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 * NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION
 * WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 */

package net.sf.fmj.ejmf.toolkit.gui.controls;

import java.awt.Component;
import java.util.EventListener;

import javax.media.Controller;
import javax.media.ControllerErrorEvent;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.Duration;
import javax.media.MediaTimeSetEvent;
import javax.media.PrefetchCompleteEvent;
import javax.media.RestartingEvent;
import javax.media.StartEvent;
import javax.media.StopEvent;
import javax.media.Time;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sf.fmj.ejmf.toolkit.util.SourcedTimer;
import net.sf.fmj.ejmf.toolkit.util.SourcedTimerEvent;
import net.sf.fmj.ejmf.toolkit.util.SourcedTimerListener;
import net.sf.fmj.ejmf.toolkit.util.TimeSource;

/**
* Progress slider for StandardControlPanel.
* This control maintains an internal time which 
* 'ticks'. At each tick, StandardProgressControl maps
* media time into a slider value and updates the progress slider.
* <p>
* The timer is provided by ejmf.toolkit.util.SourcedTimer.
* StandardProgressControl receieves 'ticks' by virtue of 
* being a SourcedTimerListener.
*/

public class StandardProgressControl extends ChangeListenerControl 
			implements ControllerListener,
				   TimeSource,
				   SourcedTimerListener {

    // Once prefetched, set to false.
    private boolean		firstPrefetch = true;

    private SourcedTimer        controlTimer;
   
    // Duration of Controller. 
    private long		duration;
 
    // Timer will fire every TIMER_TICK milliseconds
    final private static int TIMER_TICK = 250;

    public StandardProgressControl(Skin skin, Controller controller) {
	this(skin);
	setController(controller);
    }

    public StandardProgressControl(Skin skin) {
    	super(skin);
    }

	/**	
	* Create ProgressSlider
	* @see net.sf.fmj.ejmf.toolkit.gui.controls.skins.ejmf.ProgressSlider
	*/
    @Override
    protected Component createControlComponent(Skin skin) {
	return skin.createProgressSlider();
    }

	/**
	* Augments setController by adding itself as
	* as ControllerListener on the Controller and forcing
	* operational state to false. Availability of progress	
	* bar is determined only after Controller is prefetch
	* and duration is determinate.
	*/
    protected void setControllerHook(Controller controller) {
	setOperational(false);
	getController().addControllerListener(this);
	
	if (controller.getState() >= Controller.Prefetched)
	{	init();	// KAL: added to handle case where controller starts before our controllerUpdate handler gets registered.
	}
    }

    private void init() {
	Time d = getController().getDuration();
	// If duration is unknown or unbounded, slider
        // will not be operational.
	boolean flg = 
        	d != Duration.DURATION_UNBOUNDED &&
         	d != Duration.DURATION_UNKNOWN;

	// We have some know duration, is it zero?
	if (flg) {
	    duration = d.getNanoseconds();
	    flg = (duration != 0L);
	}
	setOperational(flg);
	// Duration is known and non-zero, all is well...
	if (flg) {
	    // Setup timer
	    controlTimer = new SourcedTimer(this, TIMER_TICK);
  	    controlTimer.addSourcedTimerListener(this);

	    Time mTime = getController().getMediaTime();
	    long mediaTime = mTime.getNanoseconds();

	    setValue(mediaTime);
	    
	    if (getController().getState() == Controller.Started)
	    	controlTimer.start();	// this handles the case where it is already started before we get here, in which case controllerUpdate will never get called with the initial state
	    
        } 
    }

	/**
	* Create ChangeListener. Tracks user movement	
	* of progress slider and update media time		
	* accordingly.
	*/
    protected EventListener createControlListener() {
	return new ChangeListener() {
 	    public void stateChanged(ChangeEvent e) {
     	    	ProgressBar s = (ProgressBar) e.getSource();
     	    	int value = s.getValue();
     	    	long mediaNanos = (long)((value * duration) /
                      (s.getMaximum() - s.getMinimum()));

     	    	// Intel JMF: Setting media time will update video frame
     	    	// Sun JMF: Video does not re-render until controller is restarted
	 	Controller controller = getController();
		int priorState = controller.getState();
		if (priorState == Controller.Started) {
		    controller.stop();
	        }

     	    	controller.setMediaTime(new Time(mediaNanos));

		if (priorState == Controller.Started) {
		    Time now = controller.getTimeBase().getTime();
		    controller.syncStart(now);
		}
     	    }
        };
    }

    /** 
      * Position slider based on mediaTime
      */
    public void setValue(long mediaTime) {
        ProgressBar bar;
	if (!isOperational()) {
	    return;
        }
	bar = (ProgressBar) getControlComponent();
 	long diff = bar.getMaximum() - bar.getMinimum();
 	// Translate time to slider value
 	int value = (int) ((diff * mediaTime) / duration);

 	SwingUtilities.invokeLater(
		new SetProgressSliderValueThread(bar, value));
    }


	/**	
	* If the progress slider is operational, the
	* controllerUpdate method starts and stops its
	* time based on Start- and StopEvents from the Controller.
	* If response to MediaTimeSetEvent, the value of the
	* progress slider is explicitly set.
	* <p>
	* The controllerUpdate method is also responsible for
	* setting the operational state of the Control based	
	* on duration value. This is done in response to first
	* PrefetchCompleteEvent.
	*/
    public void controllerUpdate(ControllerEvent event) {
  	if (isOperational()) {
      	    if (event instanceof StartEvent ||
                event instanceof RestartingEvent) {
		   controlTimer.start();
      	     } else if (event instanceof StopEvent ||
          	event instanceof ControllerErrorEvent) {
                   controlTimer.stop();
             } else if (event instanceof MediaTimeSetEvent) {
		// This catches any direct setting of media time	
		// by application. Additionally, it catches
		// setMediaTime(0) by StandardStopControl.
		   setValue(getTime());
	     }
  	} else {
	    if ( firstPrefetch && event instanceof PrefetchCompleteEvent) {
		firstPrefetch = false;
	        init();
		//SwingUtilities.invokeLater(new EnableComponentThread());
	    }
        }
    }
   
    class EnableComponentThread implements Runnable {
	public void run() {
	    getControlComponent().setEnabled(isOperational());
	}
    }
    
     	/**
  	* This method implements the SourcedTimerListener interface.
  	* Each timer tick causes slider thumbnail to move if a ProgressBar
  	* was built for this control panel.
  	*
  	* @see            net.sf.fmj.ejmf.toolkit.util.SourcedTimer
  	*/
    public void timerUpdate(SourcedTimerEvent e) {
     	// Since we are also the TimeSource, we can get
     	// directly from StandardControls instance.
     	// Normally, one would call e.getTime().

	setValue(getTime());
    }

    class SetProgressSliderValueThread implements Runnable {
        int 		value;
	ProgressBar	bar;

        public SetProgressSliderValueThread(ProgressBar bar, int value) {
	    this.value = value;
	    this.bar = bar;
        }

        public void run() {
	    bar.setValue(value);
	}
    }

  // For TimeSource interface
	/**
	* As part of TimeSource interface, getTime returns
	* the current media time in nanoseconds.	
	*/
    public long getTime() {
	return getController().getMediaNanoseconds();
    }

	/**
	* This method is used as a divisor to  convert
	* getTime to seconds.
	*/
    public long getConversionDivisor() {
         return TimeSource.NANOS_PER_SEC;
    }
}
