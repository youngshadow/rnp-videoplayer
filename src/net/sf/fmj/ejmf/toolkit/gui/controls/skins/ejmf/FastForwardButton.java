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

import java.awt.Graphics;

import javax.swing.UIManager;

/**
* Draw a east-facing arrow into a BasicControlButton
* by over-riding paintIcon. An east-facing arrow points
* to the right.
*/

public class FastForwardButton extends BasicControlButton {

	/**
	*  Draw east-facing arrow.
	*/
    protected void paintIcon(Graphics g, int x, int y, int size, boolean isEnabled)
    {
	int i, j;
	int mid = size / 2;	

	g.translate(x+3, y);
        if (!isEnabled)  {
            g.translate(1, 1);
            g.setColor(UIManager.getColor("controlHighlight"));
	    j = 0;
            for (i = size-1; i >= 0; i--)   {
                g.drawLine(j, mid-i, j, mid+i);
                j++;
            }

	    // Draw bar
	    g.translate(-5, 0);
    	    g.drawLine(0, mid-size+1, 0, mid+size-1);
    	    g.drawLine(1, mid-size+1, 1, mid+size-1);
    	    g.drawLine(2, mid-size+1, 2, mid+size-1);
	    g.translate(5, 0);

	    g.translate(-1, -1);
	    g.setColor(UIManager.getColor("controlShadow"));
        }

	j = 0;
        for (i = size-1; i >= 0; i--)   {
            g.drawLine(j, mid-i, j, mid+i);
            j++;
        }


	// Draw Bar
	g.translate(-5, 0);
        g.drawLine(0, mid-size+1, 0, mid+size-1);
        g.drawLine(1, mid-size+1, 1, mid+size-1);
    	g.drawLine(2, mid-size+1, 2, mid+size-1);
	g.translate(5, 0);

	g.translate(-(x+3), -y);
    }
}
