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

import javax.media.BadHeaderException;
import javax.media.Demultiplexer;
import javax.media.IncompatibleSourceException;
import javax.media.Time;
import javax.media.Track;
import javax.media.protocol.ContentDescriptor;
import javax.media.protocol.DataSource;

/**
 * 
 * 
 *
 */
public abstract class AbstractDemultiplexer extends AbstractPlugIn implements Demultiplexer
{

	
	public Time getDuration()
	{
		return DURATION_UNKNOWN;
	}

	public Time getMediaTime()
	{
		return Time.TIME_UNKNOWN;
	}

	public abstract ContentDescriptor[] getSupportedInputContentDescriptors();

	public abstract Track[] getTracks() throws IOException, BadHeaderException;

	public boolean isPositionable()
	{
		return false;
	}

	public boolean isRandomAccess()
	{
		return false;
	}

//	 subclasses must override if they override isPositionable.
	public Time setPosition(Time where, int rounding)
	{
		return Time.TIME_UNKNOWN; 
		// TODO returning null will cause this:
//		Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
//		at com.sun.media.BasicSourceModule.setPosition(BasicSourceModule.java:474)
		// interestingly, BasicSourceModule will call setPosition even if isPositionable
		// returns false.
	}

	public void start() throws IOException
	{
	}

	public void stop()
	{
	}

	public abstract void setSource(DataSource source) throws IOException, IncompatibleSourceException;


}
