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
 * Complete.
n
 *
 */
public class StopEvent extends TransitionEvent
{

	private Time mediaTime;
	
	public StopEvent(Controller from,
            int previous,
            int current,
            int target,
            Time mediaTime)
	{
		super(from, previous, current, target);
		this.mediaTime = mediaTime;
	}
	
	public Time getMediaTime()
	{	return mediaTime;
	}
	
	public String toString()
	{
		return getClass().getName() + "[source=" + getSource() + ",previousState=" + getPreviousState() + 
			",currentState=" + getCurrentState() + ",targetState=" + getTargetState() + 
			",mediaTime=" + mediaTime + "]";
	}
}
