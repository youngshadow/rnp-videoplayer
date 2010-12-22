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

package net.sf.fmj.media;

import javax.media.Controls;

import net.sf.fmj.utility.ObjectCollection;

/**
 * 
 * Abstract implementation of Controls, useful for subclassing.
 * 
 * @author Warren Bloomer
 *
 */
public abstract class AbstractControls implements Controls
{
	/** A collection of Objects that allows retrieval of objects based on classname */
	private final ObjectCollection controls = new ObjectCollection();
	
	/**
	 * Retrieve an array of objects that control the object. If no controls are supported, 
	 * a zero length array is returned.
	 *
	 * @return  the array of object controls
	 */
	public Object[] getControls() {
		return controls.getControls();
	}

	/**
	 * Retrieve the first object that implements the given Class or Interface. The
	 * full class name must be used. If the control is not supported then null is returned.
	 *
	 * @return  the object that implements the control, or null.
	 */
	public Object getControl(String controlType) {
		return controls.getControl(controlType);
	}
	
	/**
	 * Called by subclasses of this Abstract class to add a control.
	 * 
	 * @param control The control object to add to the controls list.
	 */
	protected void addControl(Object control) {
		controls.addControl(control);
	}
	
	/**
	 * Remove a control object from the list of controls for this object.  Will be
	 * used by subclasses of this Abstract class.
	 * 
	 * @param control the control object to remove from the list.
	 */
	protected void removeControl(Object control) {
		controls.removeControl(control);
	}
}
