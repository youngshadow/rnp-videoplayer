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

package javax.media;

/**
 * 
 * Complete.
n
 *
 */
public interface Track extends Duration
{
	public static int FRAME_UNKNOWN = Integer.MAX_VALUE;

	public static Time TIME_UNKNOWN = Time.TIME_UNKNOWN;

	public Format getFormat();

	public Time getStartTime();

	public boolean isEnabled();

	public Time mapFrameToTime(int frameNumber);

	public int mapTimeToFrame(Time t);

	/** TODO: the API is not clear as to what readFrame should do in the case of an error, like an IOException. */
	public void readFrame(Buffer buffer);

	public void setEnabled(boolean t);

	public void setTrackListener(TrackListener listener);
}
