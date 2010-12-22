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

package javax.media.protocol;

/**
 * Complete.
 * @author Ken Larson
 *
 */
public class RateRange implements java.io.Serializable
{

	private float value;
	private float min;
	private float max;
	private boolean exact;
	public RateRange(RateRange r)
	{	this(r.value, r.min, r.max, r.exact);
	}
	
	public RateRange(float init,
            float min,
            float max,
            boolean isExact)
	{	this.value = init;
		this.min = min;
		this.max = max;
		this.exact = isExact;
	}
	
	public float setCurrentRate(float rate)
	{	
		// do not enforce min/max
		this.value = rate;
		
		return this.value;
	}
	
	public float getCurrentRate()
	{	return value;
	}
	
	public float getMinimumRate()
	{	return min;
	}
	
	public float getMaximumRate()
	{	return max;
	}
	
	public boolean inRange(float rate)
	{
		if (true)
			throw new UnsupportedOperationException();	// TODO
		return rate >= min && rate <= max; // TODO: boundaries?
	}
	
	public boolean isExact()
	{	return exact;
	}
}
