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

 *
 */
public final class SystemTimeBase implements TimeBase
{

	private static long start = -1L; 
	public long getNanoseconds()
	{
		// This version only has millisecond accuracy.
		
		if (start < 0)
		{	start = System.currentTimeMillis();
			return 0;
		}
		return (System.currentTimeMillis() - start) * 1000000L;
		//return System.nanoTime();	// TODO: does this need to be relative to a specific point in time?
	}

	public Time getTime()
	{
		return new Time(getNanoseconds());
	}

}
