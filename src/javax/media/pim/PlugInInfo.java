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

package javax.media.pim;

import javax.media.Format;

/**
 * 
 * @author Ken Larson
 *
 */
class PlugInInfo
{
	public String className;
	public Format[] inputFormats;
	public Format[] outputFormats;
	public PlugInInfo(String name, Format[] formats, Format[] formats2)
	{
		super();
		className = name;
		inputFormats = formats;
		outputFormats = formats2;
	}
	
	public int hashCode() {
		return className.hashCode();
	}
	
	public boolean equals(Object other) {
		return (
			other instanceof PlugInInfo 
			&&
			(	className == ((PlugInInfo)other).className ||
				className != null && className.equals( ((PlugInInfo)other).className ) 
			)
		);
	}
}
