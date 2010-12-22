
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

import java.util.EventListener;

import javax.media.Controller;
import javax.swing.event.ChangeListener;

import net.sf.fmj.ejmf.toolkit.gui.controls.skins.ejmf.ProgressSlider;


/**
  * A Control that requires ChangeListener will extend from 
  * ChangeListenerControl.
  */

public abstract class ChangeListenerControl extends AbstractListenerControl {

	/** Create a ChangeListenerControl and associate it with
	* a Controller.
	* @param controller A Controller with which listener is		
	* associated.
	*/
    protected ChangeListenerControl(Skin skin, Controller controller)  {
	super(skin, controller);
    }

	/** Create a ChangeListenerControl
	*/
    protected ChangeListenerControl(Skin skin) {
 	super(skin);	
    }

	/**
	* Add control semantics to this Control. Remove the
	* listener named by the <code>listener</code> argument.
	* <p>	
	* @param listener java.util.EventListener representing 
	* 		control semantics to be removed.
	*/
    protected void addControlListener(EventListener  listener)  {
        ((ProgressSlider) getControlComponent()).addChangeListener(
		(ChangeListener) listener);
    }

	/**	
	* Remove control semantics from this Control.
	* <p>	
	* @param listener java.util.EventListener representing 
	* 		control semantics to be added.
	*/
    protected void removeControlListener(EventListener  listener)  {
	((ProgressSlider) getControlComponent()).removeChangeListener(
		(ChangeListener) listener);
    }

	/**
	* Type-safe way to set Control Component and control listener.
	* @param slider A ProgressSlider that serves as Control	
	* component.
	* @param listener A ChangeListener that implements
	* Control semantics.
	*/	
    public void setComponentAndListener(ProgressSlider slider, ChangeListener listener) {
	super.setComponentAndListener(slider, listener);
    }

	/**
	* Type-safe way to set Control listener.
	* @param listener A ChangeListener that implements
	* Control semantics.
	*/	
    public void setControlListener(ChangeListener listener) {
 	super.setControlListener(listener);
    }

	/**
	* Type-safe way to set Control Component.
	*/
    public void setComponent(ProgressSlider slider) {
	super.setComponent(slider);
    }
}
