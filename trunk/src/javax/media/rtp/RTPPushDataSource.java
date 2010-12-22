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

import javax.media.Time;
import javax.media.protocol.DataSource;
import javax.media.protocol.PushDataSource;
import javax.media.protocol.PushSourceStream;

/**
 * 
 * @deprecated
 * 
 */
public class RTPPushDataSource extends PushDataSource
{	

	public RTPPushDataSource()
	{	throw new UnsupportedOperationException();	// TODO
	}

	public void setChild(DataSource source)
	{	throw new UnsupportedOperationException();	// TODO
	}

	public PushSourceStream getOutputStream()
	{	throw new UnsupportedOperationException();	// TODO
	}

	public OutputDataStream getInputStream()
	{	throw new UnsupportedOperationException();	// TODO
	}

	public void setOutputStream(PushSourceStream outputstream)
	{	throw new UnsupportedOperationException();	// TODO
	}

	public void setInputStream(OutputDataStream inputstream)
	{	throw new UnsupportedOperationException();	// TODO
	}

	public String getContentType()
	{	throw new UnsupportedOperationException();	// TODO
	}

	public void setContentType(String contentType)
	{	throw new UnsupportedOperationException();	// TODO
	}

	public void connect() throws java.io.IOException
	{	throw new UnsupportedOperationException();	// TODO
	}

	public void disconnect()
	{	throw new UnsupportedOperationException();	// TODO
	}

	protected void initCheck()
	{	throw new UnsupportedOperationException();	// TODO
	}

	public void start() throws java.io.IOException
	{	throw new UnsupportedOperationException();	// TODO
	}

	public void stop() throws java.io.IOException
	{	throw new UnsupportedOperationException();	// TODO
	}

	public boolean isStarted()
	{	throw new UnsupportedOperationException();	// TODO
	}

	public Object[] getControls()
	{	throw new UnsupportedOperationException();	// TODO
	}

	public Object getControl(String controlName)
	{	throw new UnsupportedOperationException();	// TODO
	}

	public Time getDuration()
	{	throw new UnsupportedOperationException();	// TODO
	}

	public PushSourceStream[] getStreams()
	{	throw new UnsupportedOperationException();	// TODO
	}
}
