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

public class StopButton extends BasicControlButton 	
{
	/** Create a StopButton
	*/
    public StopButton() {
	super();
    }
	/** 
	* Paint a small square into BasicControlButton
	* @param g Graphics into which rectangles are drawn.
	* @param x Original translation (x) to point in button where	
	* where square is drawn.
	* @param y Original translation (y) to point in button where	
	* where square is drawn.
	* @param size Size of square.
	* @param isEnabled If true, square is drawn enabled (i.e. black), 
	* otherwise, they are offset by (1,1) and drawn with UIManager's
	* controlShadow color.
	*/
    protected void paintIcon(Graphics g, int x, int y, int size, boolean isEnabled)
    {
  	g.translate(x, y);
	if (isEnabled)
	    g.fillRect(0, 0, size, size);
	else {
  	    g.translate(1, 1);
	    Color oldColor = g.getColor();
	    g.setColor(UIManager.getColor("controlShadow"));
	    g.fill3DRect(0, 0, size, size, false);
	    g.setColor(oldColor);
  	    g.translate(-1, -1);
	}
  	g.translate(-x, -y);
    }
}
