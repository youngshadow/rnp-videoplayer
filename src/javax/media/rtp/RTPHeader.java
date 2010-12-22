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

package javax.media.rtp;

/**
 * Coding complete.
 * @author Ken Larson
 *
 */
public class RTPHeader implements java.io.Serializable
{	
	public static final int VALUE_NOT_SET = -1;
	
	private boolean extensionPresent;
	private int extensionType = VALUE_NOT_SET;
	private byte[] extension;

	public RTPHeader()
	{	super();
	}

	public RTPHeader(int marker)
	{	// TODO: none of the properties seem to be affected by this.
	}

	public RTPHeader(boolean extensionPresent, int extensionType,
			byte[] extension)
	{	this.extensionPresent = extensionPresent;
		this.extensionType = extensionType;
		this.extension = extension;
	}

	public boolean isExtensionPresent()
	{	return extensionPresent;
	}

	public int getExtensionType()
	{	return extensionType;
	}

	public byte[] getExtension()
	{	return extension;
	}

	public void setExtensionPresent(boolean p)
	{	this.extensionPresent = p;
	}

	public void setExtensionType(int t)
	{	this.extensionType = t;
	}

	public void setExtension(byte[] e)
	{	this.extension = e;
	}
}
