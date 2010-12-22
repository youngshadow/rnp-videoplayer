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

import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.media.Controller;
import javax.swing.AbstractButton;

/**
  * All button controls extends from ActionListenerControl.
  *
  * ActionListenerControl supplies public methods for manipulating
  * a control's EventListener as an ActionListener thereby providing
  * type-safety.
  */

public abstract class ActionListenerControl extends AbstractListenerControl {

	/** Create an ActionListenerControl and associate it
	* with a Controller.	
	* @param controller A Controller with which listener is
	* associated.
	*/
    protected ActionListenerControl(Skin skin, Controller controller)  
			throws ClassCastException {
	super(skin, controller);
	if (!(getControlComponent() instanceof AbstractButton)) {
	    throw new ClassCastException("AbstractButton required");
	}
    }

	/** Create an ActionListenerControl
	*/
    protected ActionListenerControl(Skin skin) {
	super(skin);
    }

    private AbstractButton getButton() {
	return (AbstractButton) getControlComponent();
    }

	/**
	* Add control semantics to this Control. Remove the
	* listener named by the <code>listener</code> argument.
	* <p>	
	* @param listener java.util.EventListener representing 
	* 		control semantics to be removed.
	*/
    protected void removeControlListener(EventListener  listener) {
	getButton().removeActionListener((ActionListener) listener);
    }

	/**	
	* Add control semantics to this Control.
	* <p>	
	* @param listener java.util.EventListener representing 
	* 		control semantics to be added.
	*/
    protected void addControlListener(EventListener  listener) {
        getButton().addActionListener((ActionListener) listener);
    }

	/**
	* Type-way to set Control Component and control listener.
	* @param button An AbstractButton that serves as Control	
	* component.
	* @param listener An ActionListener that implements
	* Control semantics.
	*/	
    public void setComponentAndListener(AbstractButton button, 
				ActionListener listener) {
	super.setComponentAndListener(button, listener);
    }

	/**
	* Type-way to set Control listener.
	* @param listener An ActionListener that implements
	* Control semantics.
	*/	
    public void setControlListener(ActionListener listener) {
 	super.setControlListener(listener);
    }

	/**
	* Type-way to set Control Component.
	*/
    public void setComponent(AbstractButton button) {
	super.setComponent(button);
    }
}
