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

package net.sf.fmj.media.cdp;

/**
 * Global singleton CaptureDevicePlugger.  Calls all specific CaptureDevicePluggers.
 * Dynamically adds CaptureDeviceInfo to the CaptureDeviceManager.  Does not commit.
 * 
 *
 */
public final class GlobalCaptureDevicePlugger
{
	private GlobalCaptureDevicePlugger()
	{	// Do nothing.
	}
	public static void addCaptureDevices()
	{
		// This is hard-coded to call all known capture device pluggers.  Would be nice to
		// make this more dynamic.
		new net.sf.fmj.media.cdp.civil.CaptureDevicePlugger().addCaptureDevices();
		new net.sf.fmj.media.cdp.javasound.CaptureDevicePlugger().addCaptureDevices();

	}

}
