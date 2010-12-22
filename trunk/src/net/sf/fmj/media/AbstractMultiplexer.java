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

import java.util.logging.Logger;

import javax.media.Format;
import javax.media.Multiplexer;
import javax.media.protocol.ContentDescriptor;

import net.sf.fmj.utility.LoggerSingleton;

/**
 * 
 * 
 *
 */
public abstract class AbstractMultiplexer extends AbstractPlugIn implements Multiplexer
{
	private static final Logger logger = LoggerSingleton.logger;
	
	protected ContentDescriptor outputContentDescriptor;
	public ContentDescriptor setContentDescriptor(ContentDescriptor outputContentDescriptor)
	{
		this.outputContentDescriptor = outputContentDescriptor;
		return outputContentDescriptor;
	}
	
	protected Format[] inputFormats;
	
	public Format setInputFormat(Format format, int trackID)
	{
		logger.finer("setInputFormat " + format + " " + trackID);
		if (inputFormats != null)	// TODO: should we save this somewhere and apply once inputFormats is not null?
			inputFormats[trackID] = format;
		
		return format;
	}

	protected int numTracks;
	public int setNumTracks(int numTracks)
	{
		logger.finer("setNumTracks " + numTracks);
		inputFormats = new Format[numTracks];

		this.numTracks = numTracks;
		return numTracks;
	}
}
