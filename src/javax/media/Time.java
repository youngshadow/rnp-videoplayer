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
public class Time implements java.io.Serializable
{
	public static final long ONE_SECOND = 1000000000;

	public static final Time TIME_UNKNOWN = new Time(Long.MAX_VALUE - 1);

	private static final double NANO_TO_SEC = 1E-9;

	protected long nanoseconds;

	
	public Time(long nanoseconds) 
	{	this.nanoseconds = nanoseconds;
	}
	
	public Time(double seconds)
	{
		this.nanoseconds = secondsToNanoseconds(seconds);
	}

	protected long secondsToNanoseconds(double seconds)
	{
		return (long) (seconds * ONE_SECOND);
	}
	
	public long getNanoseconds()
	{
		return nanoseconds;
	}

	public double getSeconds()
	{
		return nanoseconds * NANO_TO_SEC;
	}


	
	static
	{	// for Serializable compatibility.
	}
}
