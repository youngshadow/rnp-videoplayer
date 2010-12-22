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

package net.sf.fmj.media.cdp.javasound;

import java.util.logging.Logger;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Format;
import javax.media.MediaLocator;

import net.sf.fmj.media.protocol.javasound.DataSource;
import net.sf.fmj.utility.LoggerSingleton;

/**
 * Dynamically adds CaptureDeviceInfo to the CaptureDeviceManager.  Does not commit.
 * 
 *
 */
public class CaptureDevicePlugger
{
	private static final Logger logger = LoggerSingleton.logger;

	public void addCaptureDevices()
	{
		// TODO: detect number of lines.

		final Format[] formats = DataSource.querySupportedFormats();
		
		final CaptureDeviceInfo jmfInfo = new CaptureDeviceInfo("JavaSound", new MediaLocator("javasound:" + "//"), formats);
		if (CaptureDeviceManager.getDevice(jmfInfo.getName()) == null)
		{
			CaptureDeviceManager.addDevice(jmfInfo);
			logger.fine("CaptureDevicePlugger: Added " + jmfInfo.getLocator());
		}
		else
		{
			logger.fine("CaptureDevicePlugger: Already present, skipping " + jmfInfo.getLocator());
		}

	}
}
