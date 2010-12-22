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

package net.sf.fmj.utility;

/**
* Because java does not support unsigned types, a collection of useful functions for 
* treating types as unsigned.
* 
* 
*/ 
public final class UnsignedUtils
{
	private UnsignedUtils()
	{	super();
	}
	
	public static final int MAX_UBYTE = ((int) Byte.MAX_VALUE) * 2 + 1;		// 255
	public static final int MAX_USHORT = ((int) Short.MAX_VALUE) * 2 + 1;	// 65535
	public static final long MAX_UINT = ((long) Integer.MAX_VALUE) * 2 + 1;	

	public static int uByteToInt(byte value)
	{	if (value >= 0)
			return value;
		else
			return MAX_UBYTE + 1 + value;
	}
	
	public static int uShortToInt(short value)
	{	if (value >= 0)
			return value;
		else
			return MAX_USHORT + 1 + value;
	}

	public static long uIntToLong(int value)
	{	if (value >= 0)
			return value;
		else
			return MAX_UINT + 1L + value;
	}	
}
