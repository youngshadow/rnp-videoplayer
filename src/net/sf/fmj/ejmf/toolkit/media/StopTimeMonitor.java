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

package net.sf.fmj.ejmf.toolkit.media;

import javax.media.Clock;
import javax.media.ClockStoppedException;
import javax.media.Controller;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.DeallocateEvent;
import javax.media.MediaTimeSetEvent;
import javax.media.RateChangeEvent;
import javax.media.StartEvent;
import javax.media.StopEvent;
import javax.media.StopTimeChangeEvent;
import javax.media.Time;

/**
 * This class provides a thread to stop an AbstractController when
 * its stop time is reached.
 *
 * From the book: Essential JMF, Gordon, Talley (ISBN 0130801046).  Used with permission.
 * 
 * @see        AbstractController
 *
 * @author     Steve Talley & Rob Gordon
 */
public class StopTimeMonitor extends Thread implements ControllerListener {
    private boolean wokenUp;
    private AbstractController controller;

    /**
     * Constructs a StopTimeMonitor for the given
     * AbstractController.
     *
     * @param      controller
     *             The AbstractController to whose stop time to
     *             monitor.
     */
    public StopTimeMonitor(AbstractController controller, String threadName) {
        super();
        setName(threadName);
        this.controller = controller;
        controller.addControllerListener(this);
        setDaemon(true);
    }
    
    /**
     * Listen for RateChangeEvents or MediaTimeSetEvents and
     * notify the StopTimeMonitor thread to recalculate its wait
     * time.  Also listen for StartEvents and StopEvents so that
     * the monitor will know whether the controller is playing.
     *
     * @param      e
     *             The ControllerEvent
     */
    public synchronized void controllerUpdate(ControllerEvent e) {
        if( e instanceof StopTimeChangeEvent ||
            e instanceof RateChangeEvent || 
            e instanceof MediaTimeSetEvent ||
            e instanceof StartEvent ||
           (e instanceof StopEvent && ! (e instanceof DeallocateEvent) ) )
        {
            wokenUp = true;
            notifyAll();
        }
    }
    
    /**
     * Continuously monitor the controller, it's state, and it's
     * stop time.  Wait until a. the controller is started, and
     * b. a stop time has been set on the AbstractController.
     * Then calculate the how long to wait before stopping the
     * controller, based on the current media time, rate, and
     * stop time.  If we are woken up by an event, then
     * recalculate and begin again.  Otherwise, stop the
     * controller when we wake up.
     */
    private synchronized void monitorStopTime() {
        Time stopTime;
        long waittime;

        while(true) {
            //  Wait until the controller is started and the
            //  stop time has been set
            while( controller.getState() != Controller.Started ||
                  (stopTime = controller.getStopTime()) == Clock.RESET )
            {
                try {
                    wait();
                } catch(InterruptedException e) {}
            }

            wokenUp = false;

            //  Get the time until the AbstractController should
            //  be stopped.  If the Clock has stopped since we
            //  last checked, go back to sleep

            try {
                waittime = getWaitTime(stopTime);
            } catch(ClockStoppedException e) {
                continue;
            }

            //  Now wait until it's time to stop the controller
            if( waittime > 0 ) {
                try {
                    wait( waittime );
                } catch(InterruptedException e) {}
            }

            // Did we wake up naturally?
            if(! wokenUp) {
                //  Reset the stop time
                controller.stopAtTime();
                controller.setStopTime(Clock.RESET);
            }
        }
    }

    /**
     * Calculates the time that the monitor should sleep (in
     * milliseconds) before stopping the controller.
     */
    private long getWaitTime(Time stopTime)
        throws ClockStoppedException
    {
        long stop =
            controller.mapToTimeBase(stopTime).getNanoseconds();

        long now =
            controller.getTimeBase().getNanoseconds();

        return (stop - now) / 1000000;
    }

    /**
     * Continuously monitor the controller, it's state, and it's stop
     * time.
     */
    public void run() {
        monitorStopTime();
    }
}
