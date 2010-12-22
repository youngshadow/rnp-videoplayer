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

import javax.media.Buffer;
import javax.media.Duration;
import javax.media.Format;
import javax.media.Time;
import javax.media.Track;
import javax.media.TrackListener;

/**
 * 
 * 
 *
 */
public abstract class AbstractTrack implements Track
{

	private boolean enabled = true;	// default to enabled.  JMF won't play the track if it is not enabled.  TODO: FMJ should do the same.
	private TrackListener trackListener;
	
	public abstract Format getFormat();

	public Time getStartTime()
	{
		return TIME_UNKNOWN;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public Time mapFrameToTime(int frameNumber)
	{
		return TIME_UNKNOWN;
	}

	public int mapTimeToFrame(Time t)
	{
		return FRAME_UNKNOWN;
	}

	public abstract void readFrame(Buffer buffer);

	public void setEnabled(boolean t)
	{
		this.enabled = t;
	}

	public void setTrackListener(TrackListener listener)
	{
		this.trackListener = listener;
	}

	public Time getDuration()
	{
		return Duration.DURATION_UNKNOWN;
	}

}
