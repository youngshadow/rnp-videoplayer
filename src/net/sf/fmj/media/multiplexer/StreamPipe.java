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

package net.sf.fmj.media.multiplexer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.media.Buffer;

import net.sf.fmj.media.BufferQueueInputStream;

/**
 * A way of converting an output stream to an input stream.
 * 
 *
 */
public class StreamPipe
{
	private final BufferQueueInputStream is = new BufferQueueInputStream();
	private final MyOutputStream os = new MyOutputStream();
	
	public InputStream getInputStream()
	{	return is;
	}
	
	public OutputStream getOutputStream()
	{	return os;
	}
	
	private Buffer createBuffer(byte[] data, int offset, int length)
	{
		Buffer b = new Buffer();
		// TODO: set format to something? - doesn't seem needed.
		b.setData(data);
		b.setOffset(offset);
		b.setLength(length);
		return b;
	}
	
	private Buffer createEOMBuffer()
	{
		Buffer b = new Buffer();
		// TODO: set format to something? - doesn't seem needed.
		b.setData(new byte[0]);
		b.setOffset(0);
		b.setLength(0);
		b.setEOM(true);
		return b;
	}
	
	private class MyOutputStream extends OutputStream
	{

		public void write(int b) throws IOException
		{
			write(new byte[] {(byte) b});
		}

		public void write(byte[] b, int off, int len) throws IOException
		{
			is.blockingPut(createBuffer(b, off, len)); 
		}

		public void write(byte[] b) throws IOException
		{
			write(b, 0, b.length);
		}

		public void close() throws IOException
		{
			System.out.println("MyOutputStream Closing");
			is.blockingPut(createEOMBuffer());

			super.close();
		}
		
		
	}
}
