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

import javax.media.Controller;
import javax.media.GainControl;
import javax.media.Player;

/**
* Abstract class from which AbstractListenerControls that depend
* on a GainControl should extend. Upon construction it properly
* set the operational state of the Control.
* 
*/

public abstract class AbstractGainControl extends ActionListenerControl  {
    private GainControl	gc;

	/**
	* Create an AbstractGainControl and associate with 
	* controller.	
	* @param controller Controller with which this control is associated.
	*/
    protected AbstractGainControl(Skin skin, Controller controller) {
	super(skin, controller);
    }
 
	/**
	* Create an AbstractGainControl. Controller will be assigned		
	* later.
	*/
    protected AbstractGainControl(Skin skin) {	
	super(skin);
    }

	/**	
	* Invoked when Controller is associated with Control.
	* Properly sets operational state and initializes 
	* initializes private reference to GainControl if
	* Controller is a Player and it has a one.
	* @param controller Controller with which this control is associated.
	*/
    protected void setControllerHook(Controller controller) {
	if (controller instanceof Player) {
	    gc = ((Player)controller).getGainControl();
            setOperational(gc != null);
	} else {
	    setOperational(false);
        }
    }

	/**
	* @return GainControl associated with this AbstractGainControl.	
	*/
    protected GainControl getGainControl() {
	return gc;
    }
}
