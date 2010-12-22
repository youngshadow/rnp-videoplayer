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

import java.io.Serializable;

/**
 * Coding complete.

 *
 */
public class MediaLocator implements Serializable
{

	private static final long serialVersionUID = -6747425113475481405L;
	
	private String locatorString;
	
	public MediaLocator(java.net.URL url)
	{	this.locatorString = url.toExternalForm();	// TODO: is this correct?
	}

	public MediaLocator(String locatorString)
	{	
		if (locatorString == null)
			throw new NullPointerException();
		this.locatorString = locatorString;
	}

	public java.net.URL getURL() throws java.net.MalformedURLException
	{	
		return new java.net.URL(locatorString);
	}

	public String getProtocol()
	{	
		int colonIndex = locatorString.indexOf(':');
		if (colonIndex < 0)
			return "";
		return locatorString.substring(0, colonIndex);
	}

	public String getRemainder()
	{	
		int colonIndex = locatorString.indexOf(':');
		if (colonIndex < 0)
			return "";
		return locatorString.substring(colonIndex + 1, locatorString.length());
	}

	public String toString()
	{	return locatorString;
	}

	public String toExternalForm()
	{	return locatorString;
	}
}
