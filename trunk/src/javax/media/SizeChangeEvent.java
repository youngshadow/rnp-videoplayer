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

import javax.media.format.FormatChangeEvent;

/**
 * Complete.

 *
 */
public class SizeChangeEvent extends FormatChangeEvent
{
	protected  int 	height;
    
	protected  float 	scale;
	           
	protected  int 	width; 
	
	
	public SizeChangeEvent(Controller from,
            int width,
            int height,
            float scale)
	{
		super(from);
		this.width = width;
		this.height = height;
		this.scale = scale;
		
	}


	public int getHeight()
	{
		return height;
	}


	public float getScale()
	{
		return scale;
	}


	public int getWidth()
	{
		return width;
	}
}
