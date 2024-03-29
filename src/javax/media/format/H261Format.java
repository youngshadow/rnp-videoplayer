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

package javax.media.format;

import javax.media.Format;

import net.sf.fmj.utility.FormatUtils;

/**
 * Coding complete.
 * @author Ken Larson
 *
 */
public class H261Format extends VideoFormat
{

	protected int stillImageTransmission = NOT_SPECIFIED;
	
	private static String ENCODING = H261;
	
	public H261Format()
	{	super(ENCODING);
		dataType = Format.byteArray;

	}
	
	public H261Format(java.awt.Dimension size,
            int maxDataLength,
            Class dataType,
            float frameRate,
            int stillImageTransmission)
	{
		super(ENCODING, size, maxDataLength, dataType, frameRate);
		this.stillImageTransmission = stillImageTransmission;
	}
	
	public int getStillImageTransmission()
	{	return stillImageTransmission;
	}
	
	public Object clone()
	{	return new H261Format(FormatUtils.clone(size), maxDataLength, dataType, frameRate, stillImageTransmission);
	}
	
	protected void copy(Format f)
	{
		super.copy(f);
		final H261Format oCast = (H261Format) f;	// it has to be a H261Format, or ClassCastException will be thrown.
		this.stillImageTransmission = oCast.stillImageTransmission;
	
	}
	
	public String toString()
	{	return "H.261 video format";
	}
	public boolean equals(Object format)
	{
		if (!super.equals(format))
			return false;
		
		if (!(format instanceof H261Format))	
		{
			return false;
		}
		
		final H261Format oCast = (H261Format) format;
		return this.stillImageTransmission == oCast.stillImageTransmission;
	}
	
	public boolean matches(Format format)
	{
		if (!super.matches(format))
		{	
			FormatUtils.traceMatches(this, format, false);
			return false;
		}
		
		if (!(format instanceof H261Format))
		{	
			final boolean result = true;
			FormatUtils.traceMatches(this, format, result);
			return result;
		}
		
		final H261Format oCast = (H261Format) format;

		final boolean result = 
			FormatUtils.matches(this.stillImageTransmission, oCast.stillImageTransmission);
		
		FormatUtils.traceMatches(this, format, result);
		
		return result;
		
	}
	
	public Format intersects(Format other)
	{
		final Format result = super.intersects(other);

		if (other instanceof H261Format)
		{
			final H261Format resultCast = (H261Format) result;
			
			final H261Format oCast = (H261Format) other;
			if (getClass().isAssignableFrom(other.getClass()))
			{	
				// "other" was cloned.
				
				if (FormatUtils.specified(this.stillImageTransmission))
					resultCast.stillImageTransmission = this.stillImageTransmission;
				
			}
			else if (other.getClass().isAssignableFrom(getClass()))
			{	// this was cloned
				
				if (!FormatUtils.specified(resultCast.stillImageTransmission))
					resultCast.stillImageTransmission = oCast.stillImageTransmission;
			
			}
		}
		
		FormatUtils.traceIntersects(this, other, result);
		
		return result;
	}
	
	static
	{	// for Serializable compatibility.
	}
}
