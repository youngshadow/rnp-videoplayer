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

package net.sf.fmj.filtergraph;

import javax.media.Buffer;
import javax.media.Format;
import javax.media.Multiplexer;
import javax.media.ResourceUnavailableException;

/**
 * 
 * 
 *
 */
public class MuxNode extends FilterGraphNode
{
	private final Format inputFormat;
	private final Multiplexer mux;

	public MuxNode(Multiplexer mux, Format inputFormat)
	{
		super(mux);
		this.inputFormat = inputFormat;
		this.mux = mux;
	}
	
	public FilterGraphNode duplicate()
	{
		return propagateDuplicate(new MuxNode(getMultiplexer(), getInputFormat()));
		
	}

	public Format getInputFormat()
	{
		return inputFormat;
	}

	public Multiplexer getMultiplexer()
	{
		return mux;
	}
	
	public void open() throws ResourceUnavailableException
	{
		getMultiplexer().open();
	}
	
	public int process(final Buffer input, final int sourceTrackNumber, final int destTrackNumber, final int flags)
	{
		if (input.getFormat() == null)
			input.setFormat(getInputFormat());	// TODO: is this right?  JMF appears to set the format in between demux track read adnd mux process.
		
		final int processResult = getMultiplexer().process(input, destTrackNumber);	// TODO: check return code
		// TODO: set buffer1 format if it is not set?
		if (processResult != Multiplexer.BUFFER_PROCESSED_OK)
		{
			if (processResult == Multiplexer.OUTPUT_BUFFER_NOT_FILLED)
				logger.finer("Multiplexer process result: " + processResult);	// this is common, so don't pollute the log.
			else
				logger.warning("Multiplexer process result: " + processResult);
				
			// TODO: set discard in buffer or any other flags?
			// TODO: check any other buffer flags?
		}
		
		return processResult;

	}
	
	

	public void addDestLink(FilterGraphLink n)
	{
		throw new UnsupportedOperationException();
	}
}