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

package net.sf.fmj.ejmf.toolkit.gui.controls.skins.ejmf;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.UIManager;
/**
* Pause button for StandardControlPanel.
* <p>
* PauseButton over-rides BasicControlButton's paintIcon
* method to draw two vertical bars as button icon.
* <p>
* @see net.sf.fmj.ejmf.toolkit.gui.controls.skins.ejmf.BasicControlButton
*/ 

public class PauseButton extends BasicControlButton
{
	/** Create a pause button.
	*/
	public PauseButton() {
	   super();
	}
	
	/**
	* Draw two rectangles into button.
        *
	* @param g Graphics into which rectangles are drawn.
	* @param x not used.
	* @param y not used	.
	* @param size not used. Rather Component.getSize() is used to 
	* to determine size of button and size of bars is determined
	* relative to those dimensions.
	* @param isEnabled If true, bars are drawn enabled (i.e. black), 
	* otherwise, they are offset by (1,1) and drawn with UIManager's
	* controlShadow color.
	*/
 	protected void paintIcon(Graphics g, int x, int y, int size, boolean isEnabled) {
	    int w = getSize().width;
	    int h = getSize().height;
	    int x_inc = w / 8;
	    int y_inc = h / 4;
	    int bar_w = 2 * x_inc - x_inc/2;
	    int bar_h = 2 * y_inc;
	    g.translate(2*x_inc+1, y_inc);
	    if (isEnabled) {
	        g.fillRect(0, 0, bar_w, bar_h);
	        g.fillRect(3*x_inc, 0, bar_w, bar_h);
	    } else {
		g.translate(1, 1);
		Color oldColor = g.getColor();
		g.setColor(UIManager.getColor("controlShadow"));
	        g.fillRect(0, 0, bar_w, bar_h);
	        g.fillRect(3*x_inc, 0, bar_w, bar_h);

		// "Hightlight" edge of bars to make them look etched.
		g.setColor(UIManager.getColor("controlHighlight"));

		// Etch right-side of left bar
		g.drawLine(bar_w, 0, bar_w, bar_h-1);
		// Etch bottom of left bar
		g.drawLine(0, bar_h-1, bar_w, bar_h-1);
		// Etch right-side of right bar
		g.drawLine(3*x_inc+bar_w, 0, 3*x_inc+bar_w, bar_h-1);
		// Etch bottom of right bar
		g.drawLine(3*x_inc, bar_h-1, 3*x_inc+bar_w, bar_h-1);

		g.setColor(oldColor);
		g.translate(-1, -1);
	    }
	    g.translate(-x_inc, -y_inc);
        }
}
