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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.media.Controller;
import javax.media.TimeBase;

import net.sf.fmj.ejmf.toolkit.util.StateWaiter;

/*
* Start Control for StandardControlPanel.
*/

public class StandardStartControl extends ActionListenerControl {

	public StandardStartControl(Skin skin, Controller controller) {
	super(skin, controller);
	getControlComponent().setEnabled(true);
    }
 
	 
    public StandardStartControl(Skin skin) {
	super(skin);
	getControlComponent().setEnabled(true);
    }

	/**	
	* Create StartButton.	
	* @see net.sf.fmj.ejmf.toolkit.gui.controls.skins.ejmf.StartButton
	*/

    @Override
    protected Component createControlComponent(Skin skin) {
    	return skin.createStartButton();
    }

	/**
	* Creates an ActionListener for start button
	* that starts Controller when clicked.	
	* <p>	
	* Since syncStart is used to start Controller is not in at 
	* least Prefetched state, it is move there.
	*/
    protected EventListener createControlListener() {
	return new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
		    Controller controller = getController();
		    int state = controller.getState();

		    if (state == Controller.Started) 
			return;

		    if (state < Controller.Prefetched) {
			StateWaiter w = new StateWaiter(controller);
			w.blockingPrefetch();
		    }

		    TimeBase tb = controller.getTimeBase();
            	    controller.syncStart(tb.getTime());
        	}
	    };
    }
}
