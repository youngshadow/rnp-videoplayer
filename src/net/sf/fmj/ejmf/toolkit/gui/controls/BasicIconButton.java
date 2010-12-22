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

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * 
 * 
 *
 */
public class BasicIconButton extends JButton implements SwingConstants
{

	private final ImageIcon icon;
	private final ImageIcon disabledIcon;
	private final Dimension size;
	
    public BasicIconButton(
    		ImageIcon icon, 
    		ImageIcon disabledIcon) 
    {
    	//setBackground(UIManager.getColor("control"));
    	this.icon = icon;
    	this.disabledIcon = disabledIcon;
    	this.size = new Dimension(icon.getIconWidth(), icon.getIconHeight());
    }
    
	
    public void paint(Graphics g)
    {
    	final boolean isPressed = getModel().isPressed();
    	final boolean isEnabled = isEnabled();
    	
    	g.setColor(UIManager.getColor("control"));
    	g.fillRect(0, 0, size.width, size.height);
    	if (isEnabled)
    		g.drawImage(icon.getImage(), 0, 0, size.width, size.height, null);
    	else
    		g.drawImage(disabledIcon.getImage(), 0, 0, size.width, size.height, null);
    }


    public Dimension getPreferredSize() 
    {	
    	return size;
    }
        

	/**
	* Don't let button get so small that icon	
	* is unrecognizable.	
	*/
	public Dimension getMinimumSize() {
	      return size;
	}
	
	
	/** 	
	* Always return false.	
	*/
	public boolean isFocusTraversable() {
	    return false;
	}
}
