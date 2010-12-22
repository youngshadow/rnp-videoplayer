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

/**
* Pause Control for StandardControlPanel.
*/

public class StandardPauseControl extends ActionListenerControl {

	/** Create a StandardPauseControl and associate it
	* with a Controller.
	* @param controller A Controller with which control is associated.
	*/
    public StandardPauseControl(Skin skin, Controller controller) {
	super(skin);
	getControlComponent().setEnabled(false);
    }

	/** Create a StandardPauseControl */
    public StandardPauseControl(Skin skin) {
    super(skin);
	getControlComponent().setEnabled(false);
    }

	/**
	* Create PauseButton.
	* @return The component that acts as pause button.
	* @see net.sf.fmj.ejmf.toolkit.gui.controls.skins.ejmf.PauseButton
	*/
    @Override
    protected Component createControlComponent(Skin skin) {
	return skin.createPauseButton();
    }

	/** Create and return an ActionListener that
	* implements pause semantics.
	* @return An ActionListener for pausing controller.
	*/
    protected EventListener createControlListener() {
	return new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
	    	    getController().stop();
		}
            };
    }
}
	
