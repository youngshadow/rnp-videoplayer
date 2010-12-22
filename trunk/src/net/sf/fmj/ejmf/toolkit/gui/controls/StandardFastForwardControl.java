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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventListener;

import javax.media.Controller;
import javax.media.Time;


/*
* Fast forward Control for Standard EJMF Control Panel.
*/


public class StandardFastForwardControl extends MouseListenerControl {

    private static final float DEFAULT_FF_RATE	= 2.0f;

    private float	fastForwardRate;

	/**
	* Create StandardFastForwardControl.
	* @param controller Associates Controller with Control.
	* @param rate fast forward rate.
 	*/	
    public StandardFastForwardControl(Skin skin, Controller controller, float rate) {
	super(skin, controller);
	fastForwardRate = (rate < 1.0f) ? 
			DEFAULT_FF_RATE : rate;
	getControlComponent().setEnabled(true);
    }

	/**
	* Create StandardFastForwardControl.
	* @param controller Associates Controller with Control.
	*/
    public StandardFastForwardControl(Skin skin, Controller controller) {
	this(skin, controller, DEFAULT_FF_RATE);
    }

	/**
	* Create StandardFastForwardControl.
	*/
    public StandardFastForwardControl(Skin skin) {
	super(skin);
	fastForwardRate = DEFAULT_FF_RATE;
	getControlComponent().setEnabled(true);
    }

	/**
	* Create FastForwardButton
	* @return a component for display by control.
	* @see net.sf.fmj.ejmf.toolkit.gui.controls.skins.ejmf.FastForwardButton
	*/
    @Override
    protected Component createControlComponent(Skin skin) {
	return skin.createFastForwardButton();
    }

	/**
	* Create MouseListener that implements
	* Control semantics.	
	* @return listener that listens on control's component
	* and implements fast forward semantics.
	*/
    protected EventListener createControlListener() {
	return new MouseAdapter() {
            private float   saveRate;
            private int     priorState;

		/* Stop the controller, set the new rate
		* and restart controller with "fast" rate.
		* Remember previous rate so it can be reset.
		*/
            public void mousePressed(MouseEvent mouseEvent) {
		Controller controller = getController();
                saveRate = controller.getRate();
                priorState = controller.getState();

		if (priorState == Controller.Started) {
		    controller.stop();	
	        }

                controller.setRate(fastForwardRate);

		// Always must start, since if controller was
                // started, it needed to be stopped to setRate.
	 	Time now = controller.getTimeBase().getTime();
                controller.syncStart(now);
            }

		/* Reset previous rate and restart controller.
		*/
            public void mouseReleased(MouseEvent mouseEvent) {
		Controller controller = getController();
                controller.setRate(saveRate);
                if (priorState != Controller.Started) {
                    controller.stop();
                }
            }
        };
    }
}
	
