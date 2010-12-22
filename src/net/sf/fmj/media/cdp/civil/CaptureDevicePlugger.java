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

package net.sf.fmj.media.cdp.civil;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Format;
import javax.media.MediaLocator;
import javax.media.format.RGBFormat;

import net.sf.fmj.utility.LoggerSingleton;

import com.lti.civil.CaptureException;
import com.lti.civil.CaptureSystem;
import com.lti.civil.CaptureSystemFactory;
import com.lti.civil.DefaultCaptureSystemFactorySingleton;

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
		try
		{
			final CaptureSystemFactory factory = DefaultCaptureSystemFactorySingleton.instance();
			final CaptureSystem system = factory.createCaptureSystem();
			system.init();
			final List list = system.getCaptureDeviceInfoList();
			for (int i = 0; i < list.size(); ++i)
			{
				final com.lti.civil.CaptureDeviceInfo civilInfo = (com.lti.civil.CaptureDeviceInfo) list.get(i);
				
				{
					//String name, MediaLocator locator, Format[] formats
					// TODO: more accurate format
					// TODO: don't add if already there.
					
					final CaptureDeviceInfo jmfInfo = new CaptureDeviceInfo(civilInfo.getDescription(), new MediaLocator("civil:" + civilInfo.getDeviceID()), new Format[] {new RGBFormat()});
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
		}
		catch (CaptureException e)
		{
			logger.log(Level.WARNING, "" + e, e);
		}
	}
}
