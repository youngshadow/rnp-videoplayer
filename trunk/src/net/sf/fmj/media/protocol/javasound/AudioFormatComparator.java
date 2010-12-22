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

package net.sf.fmj.media.protocol.javasound;

import java.util.Comparator;

import javax.media.format.AudioFormat;

/**
 * Used to sort audio formats by quality
 * 
 *
 */
public class AudioFormatComparator implements Comparator
{

	public int compare(Object a, Object b)
	{
		// null-safety: not strictly necessary, but defensive:
		if (a == null && b == null)
			return 0;
		if (a == null)	// then a < b, return -1
			return -1;
		if (b == null)
			return 1;	// a > b
		
		final AudioFormat aCast = (AudioFormat) a;
		final AudioFormat bCast = (AudioFormat) b;
		
		if (aCast.getSampleRate() > bCast.getSampleRate())
			return 1;
		else if (aCast.getSampleRate() < bCast.getSampleRate())
			return -1;
		
		if (aCast.getChannels() > bCast.getChannels())
			return 1;
		else if (aCast.getChannels() < bCast.getChannels())
			return -1;
			
		if (aCast.getSampleSizeInBits() > bCast.getSampleSizeInBits())
			return 1;
		else if (aCast.getSampleSizeInBits() < bCast.getSampleSizeInBits())
			return -1;
		
		// endian and signed do not affect quality, don't bother to compare.
		
		return 0;
				
				
		
		
	}

}
