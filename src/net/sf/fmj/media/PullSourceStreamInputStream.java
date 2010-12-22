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

import java.io.IOException;
import java.io.InputStream;

import javax.media.protocol.PullSourceStream;
import javax.media.protocol.Seekable;

/**
 * Implements an InputStream by wrapping a PullSourceStream.
 * 
 *
 */
public class PullSourceStreamInputStream extends InputStream
{

	private final PullSourceStream pss;
	private final Seekable seekable;	// == pss if pss instanceof Seekable.  Used for mark/reset

	public PullSourceStreamInputStream(PullSourceStream pss)
	{
		super();
		this.pss = pss;
		if (pss instanceof Seekable)
			seekable = (Seekable) pss;
		else
			seekable = null;
	}

	//@Override
	public int read() throws IOException
	{
		final byte[] buffer = new byte[1];
		final int nRead = pss.read(buffer, 0, 1);
		if (nRead <= 0)
			return -1;
		return buffer[0] & 0xff;
	}

	//@Override
	public int read(byte[] b, int off, int len) throws IOException
	{
		final int result = pss.read(b, off, len);
		return result;
	}

	private long markPosition = -1L;
	//@Override
	public synchronized void mark(int readlimit)
	{
		if (!markSupported())
			super.mark(readlimit);
		
		markPosition = seekable.tell();
	}

	//@Override
	public boolean markSupported()
	{
		return seekable != null;
	}

	//@Override
	public synchronized void reset() throws IOException
	{
		if (!markSupported())
			super.reset();
		
		if (markPosition < 0)
			throw new IOException("mark must be called before reset");
		
		seekable.seek(markPosition);
		
	}

	//@Override
	public long skip(long n) throws IOException
	{
		if (seekable == null)
		{
			return super.skip(n);
		}
		else
		{
			if (n <= 0)
				return 0;
			
			final long beforeSeek = seekable.tell();
			final long afterSeek = seekable.seek(beforeSeek + n);
			return afterSeek - beforeSeek;
		}
	}
}
