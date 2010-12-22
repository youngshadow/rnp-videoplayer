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

package net.sf.jdshow;

/**
 * 
 * @author Ken Larson
 *
 */
public class IVideoWindow extends IUnknown
{
	
	public IVideoWindow(long ptr)
	{
		super(ptr);
		
	}
	
	public native int put_Owner(long hwnd);
	public native int put_WindowStyle(int value);
	public native int put_MessageDrain(long hwnd);
	public native int put_Top(int value);
	public native int put_Left(int value);
	
	public native int get_Width(long[] pWidth);
	public native int get_Height(long[] pHeight);
	
	
}
