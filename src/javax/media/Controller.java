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
 *
 */
public interface Controller extends Clock, Duration
{
	public static final Time LATENCY_UNKNOWN = new Time(Long.MAX_VALUE);

	public static final int Prefetched = 500;

	public static final int Prefetching = 400;

	public static final int Realized = 300;

	public static final int Realizing = 200;

	public static final int Started = 600;

	public static final int Unrealized = 100;

	public int getState();
	public int getTargetState();
	public void realize();
	public void prefetch();
	public void deallocate();
	public void close();
	public Time getStartLatency();
	public Control[] getControls();
	public Control getControl(String forName);
	public void addControllerListener(ControllerListener listener);
	public void removeControllerListener(ControllerListener listener);
}
