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

import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
* A panel containing two buttons for manipulating
* gain.
* <p>
* Subclasses provide buttons but supplying definitions of
* the following two methods:
* <p>
*    AbstractButton createGainIncreaseButton();
*    AbstractButton createGainDecreaseButton();
*/

public abstract class AbstractGainButtonPanel extends JPanel {
    protected AbstractButton	gainIncreaseButton;
    protected AbstractButton	gainDecreaseButton;

    public AbstractGainButtonPanel(Skin skin) {
	GridLayout grid;
	setLayout(grid = new GridLayout(2, 1));
        grid.setVgap(0);
        grid.setHgap(0);
	setBorder(new EmptyBorder(0, 0, 0, 0));
	add(gainIncreaseButton = createGainIncreaseButton(skin));
	add(gainDecreaseButton = createGainDecreaseButton(skin));
    }

	/** Get button repsonsible for increasing gain
	* @return An AbstractButton
	*/
    public AbstractButton getGainIncreaseButton() {
   	return gainIncreaseButton;
    }

	/** Get button repsonsible for decreasing gain
	* @return An AbstractButton
	*/
    public AbstractButton getGainDecreaseButton() {
   	return gainDecreaseButton;
    }

	/**	
	* Create a button for increasing gain.
	* @return An AbstractButton
	*/
    protected abstract AbstractButton createGainIncreaseButton(Skin skin);
	/**	
	* Create a button for decreasing gain.
	*/
    protected abstract AbstractButton createGainDecreaseButton(Skin skin);
}
