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


package javax.media;

/**
 * Coding complete.
 * @author Ken Larson
 *
 */
public class CaptureDeviceInfo implements java.io.Serializable
{
	protected Format[] formats;
	protected MediaLocator locator;
	protected String name;
	
	public CaptureDeviceInfo()
	{	super();
	}
   
	public CaptureDeviceInfo(String name, MediaLocator locator, Format[] formats) 
	{
		this.name = name;
		this.locator = locator;
		this.formats = formats;
	}

	public Format[] getFormats()
	{	return formats;
	}
	
	public MediaLocator getLocator()
	{	return locator;
	}
	
	public String getName()
	{	return name;
	}
	
	public boolean equals(Object obj)
	{	
		if (name == null)
			return false;
		if (formats == null)
			return false;
		if (locator == null)
			return false;
		
		if (!(obj instanceof CaptureDeviceInfo))
			return false;
		CaptureDeviceInfo oCast = (CaptureDeviceInfo) obj;
		return oCast.name.equals(this.name) &&
			oCast.formats.equals(this.formats) &&	// same as ==, since it is an array.
			oCast.locator.equals(this.locator);	// a bit strange, since MediaLocator does not override equals: equivalent to ==
		
	}
	
	public String toString()
	{	final StringBuffer b = new StringBuffer();
		b.append(name);
		b.append(" : ");
		b.append(locator);
		b.append("\n");
		if (formats != null)
		{
			for (int i = 0; i < formats.length; ++i)
			{	
				b.append(formats[i]);
				b.append("\n");
			}
		}
		return b.toString();
	}
}
