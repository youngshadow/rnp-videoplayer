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
 * Helper to set up windowed rendering.
 * @author Ken Larson
 *
 */
public class WindowedRendering
{
	/**
	 * Pure native.
	 */
	public static native int InitWindowedRendering( 
		    long hwndApp,                  // Window to hold the video. 
		    long /*IGraphBuilder**/ pGraph         // Pointer to the Filter Graph Manager. 
		    );   

	public static int InitWindowedRendering2( 
		    long hwndApp,                  // Window to hold the video. 
		    IGraphBuilder /*IGraphBuilder**/ pGraph         // Pointer to the Filter Graph Manager. 
		)
	{
		int hr;
		final long[] p = new long[1];
		
		hr = pGraph.QueryInterface(Com.IID_IVideoWindow, p);
		if (Com.FAILED(hr)) return hr;
		
		final IVideoWindow videoWindow = new IVideoWindow(p[0]);

		hr = videoWindow.put_Owner(hwndApp);
		if (Com.FAILED(hr)) return hr;
		 
		hr = videoWindow.put_WindowStyle(Com.WS_CHILD | Com.WS_CLIPSIBLINGS);
		if (Com.FAILED(hr)) return hr;
		 
		hr = videoWindow.put_MessageDrain(hwndApp);
		if (Com.FAILED(hr)) return hr;
		 
		hr = videoWindow.put_Top(0);
		if (Com.FAILED(hr)) return hr;

		hr = videoWindow.put_Left(0);
		if (Com.FAILED(hr)) return hr;


		videoWindow.Release(); 
		
		return Com.S_OK;
	}
}
