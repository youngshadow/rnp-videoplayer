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
 * Utility for measuring FPS, used for benchmarking and optimization. 
 * 
 *
 */
public class FPSCounter 
{
	private int frames;
	private long start;

	public void reset()
	{
		start = 0;
		frames = 0;
	}
	
	public void nextFrame()
	{	
		if (start == 0)
			start = System.currentTimeMillis();
		
		++frames;
	}
	
	public int getNumFrames()
	{	return frames;
	}
	
	public double getFPS()
	{
		long now = System.currentTimeMillis();
		return 1000.0 * frames / (now - start);
	}
	
	public String toString()
	{
		return "FPS: " + getFPS();
	}
}
