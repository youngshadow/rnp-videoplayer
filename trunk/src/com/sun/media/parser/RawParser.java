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

import javax.media.Demultiplexer;
import javax.media.Duration;
import javax.media.IncompatibleSourceException;
import javax.media.Time;
import javax.media.Track;
import javax.media.protocol.ContentDescriptor;
import javax.media.protocol.DataSource;

import com.sun.media.BasicPlugIn;

/**
 * TODO: stub.

 *
 */
public abstract class RawParser extends BasicPlugIn implements Demultiplexer
{
	static final String NAME = "Raw parser";
	
	protected DataSource source;
	
	ContentDescriptor[] supported = new ContentDescriptor[]{
			new ContentDescriptor(ContentDescriptor.RAW)
	};
	
	public String getName()
	{	return NAME;
	}
	
	public RawParser()
	{
	}
	
	public ContentDescriptor[] getSupportedInputContentDescriptors()
	{	return supported;
	}
	
	public boolean isPositionable()
	{
		return false;
	}
	
	public boolean isRandomAccess()
	{
		return false;
	}
	
	public Track[] getTracks()
	{
		return null;
	}
	
	public Time getMediaTime()
	{
		
		return Time.TIME_UNKNOWN;		
	}
	
	public Time getDuration()
	{
		if (source != null)
			return source.getDuration();
		return Duration.DURATION_UNKNOWN;	
	}
	
	public void reset()
	{
	}
	
	public Time setPosition(Time when, int round)
	{
		return null;	// TODO
	}
	
	public Object[] getControls()
	{
		return null;
	}
	
	public abstract void stop();
	
	public abstract void start() throws IOException;
	
	public abstract void setSource(DataSource arg) throws IOException, IncompatibleSourceException;
	
}
