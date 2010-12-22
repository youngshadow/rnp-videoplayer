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

package com.sun.media.parser;

import java.io.IOException;

import javax.media.IncompatibleSourceException;
import javax.media.Track;
import javax.media.protocol.DataSource;
import javax.media.protocol.SourceStream;

/**
 * TODO: stub.

 *
 */
public class RawPullStreamParser extends RawParser
{
	protected SourceStream[] streams;
	protected Track[] tracks;
	static final String NAME = "Raw pull stream parser";
	public String getName()
	{	return NAME;
	}
	public RawPullStreamParser()
	{
		super();
	}
	
	public void setSource(DataSource source) throws IOException, IncompatibleSourceException
	{
		throw new IncompatibleSourceException("DataSource not supported: " + source);	// TODO: what types of DataSource are supported?
	}
	protected boolean supports(SourceStream[] streams)
	{
		return false;	// TODO
	}
	
	public Track[] getTracks()
	{
		return tracks;	
	}
	
	public void open()
	{
		// TODO
	}
	
	public void close()
	{
		// TODO
	}
	
	public void start() throws IOException
	{
		source.start();	// JMF does this
	}
	
	public void stop()
	{
		
	}
}
