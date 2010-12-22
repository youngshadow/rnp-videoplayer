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

package javax.media.protocol;

import java.io.IOException;

/**
 * Coding complete.
 * @author Ken Larson
 *
 */
public class InputSourceStream implements PullSourceStream
{

	protected java.io.InputStream stream;

	protected boolean eosReached;
	private ContentDescriptor contentDescriptor;

	public InputSourceStream(java.io.InputStream s, ContentDescriptor type)
	{
		stream = s;
		contentDescriptor = type;
	}

	public ContentDescriptor getContentDescriptor()
	{
		return contentDescriptor;

	}

	public long getContentLength()
	{
		return LENGTH_UNKNOWN;	// TODO
		

	}

	public boolean willReadBlock()
	{
		try
		{
			return stream.available() <= 0;
		} catch (IOException e)
		{
			return true;
		}

	}

	public int read(byte[] buffer, int offset, int length)
			throws java.io.IOException
	{
		int result = stream.read(buffer, offset, length);
		if (result == -1)
			eosReached = true;
		return result;

	}

	public void close() throws java.io.IOException
	{
		stream.close();

	}

	public boolean endOfStream()
	{
		return eosReached;

	}

	public Object[] getControls()
	{
		return new Object[0];
	}

	public Object getControl(String controlName)
	{
		return null;
	}
}
