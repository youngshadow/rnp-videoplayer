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

/** 
  * ProgressBar provides a generalized interface for a component
  * used to display the progression of time. 
  * <p>
  * NOTE: This interface will make it easy to slide JSlider into 
  * StandardControlPanel if performance ever improves to a 
  * point where it could run with a controller without causing
  * stutter.
  */

import javax.swing.event.ChangeListener;

public interface ProgressBar  {
	/**
	* Get current value of ProgressBar.
	* @return value of Slider
	*/
    public int		getValue();
	/**
	* Set current value of ProgressBar.
	* @param value new value of Slider
	*/
    public void		setValue(int value);

	/**
	* Get minimum legal value of ProgressBar.
	* @return minimum legal Slider value
	*/
    public int		getMinimum();
	/**
	* Set legal minimum value of ProgressBar.
	* @param value legal minimum value of Slider
	*/
    public void		setMinimum(int value);
   
	/**
	* Get maximum legal value of ProgressBar.
	* @return maximum legal Slider value
	*/
    public int		getMaximum();
	/**
	* Set maximum legal value of ProgressBar.
	* @param value maximum legal value of Slider
	*/
    public void 	setMaximum(int value);

	/**
	* Register ChangeListener with ProgressBar
	*/
    public void		addChangeListener(ChangeListener l);
	/**
	* Remove object as ProgressBar ChangeListener 
	*/
    public void		removeChangeListener(ChangeListener l);
}
