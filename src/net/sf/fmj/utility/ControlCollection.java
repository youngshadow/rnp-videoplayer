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

package net.sf.fmj.utility;

import java.util.Vector;

import javax.media.Control;

/**
 * Maintains a list of Control objects.  The Controls may be retrieved by classname or interface name.
 * 
 * @author Warren Bloomer
 *
 */
public class ControlCollection {

	private static final Control[] CONTROL_SPEC = new Control[]{};
	
	private Vector controls = new Vector();
	
	/**
	 * Add a Control to the list.
	 * 
	 * @param control
	 */
	public void addControl(Control control) {
		synchronized (controls) {
			controls.add(control);
		}
	}

	/**
	 * Remove a Control from the list.
	 * 
	 * @param control
	 */
	public void removeControl(Control control) {
		synchronized (controls) {
			controls.remove(control);
		}
	}

	/**
	 * Retrieve an array of Controls that control the object. If no controls are supported, 
	 * a zero length array is returned.
	 *
	 * @return  the array of object controls
	 */
	public Control[] getControls() {
		synchronized (controls) {
			return (Control[]) controls.toArray(CONTROL_SPEC);
		}
	}

	/**
	 * Retrieve the first Control that implements the given Class or Interface. The
	 * full class name must be used. If the control is not supported then null is returned.
	 *
	 * @return  the object that implements the control, or null.
	 */
	public Control getControl(String controlType) {
		try {
			Class cls = Class.forName(controlType);
			synchronized (controls) {
				Control cs[] = getControls();
				for (int i = 0; i < cs.length; i++) {
					if (cls.isInstance(cs[i])) {
						return cs[i];
					}
				}
			}
			return null;

		}
		catch (Exception e) { 
			// no such controlType or such control
			return null;
		}
	}
}
