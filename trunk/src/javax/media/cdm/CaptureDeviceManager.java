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


package javax.media.cdm;

import java.util.Vector;

import javax.media.CaptureDeviceInfo;
import javax.media.Format;

import net.sf.fmj.utility.Registry;

/**
 * 
 * Coding complete.
 * TODO: commit.
 * @author Ken Larson
 *
 */
public class CaptureDeviceManager extends javax.media.CaptureDeviceManager
{
	public CaptureDeviceManager()
	{
		super();
	}

	public static synchronized CaptureDeviceInfo getDevice(String deviceName)
	{	
		final Vector v = getDeviceList();
		for (int i = 0; i < v.size(); ++i)
		{
			final CaptureDeviceInfo captureDeviceInfo = (CaptureDeviceInfo) v.get(i);
			if (captureDeviceInfo.getName().equals(deviceName))
				return captureDeviceInfo;
		}
		return null;
	}

	public static synchronized Vector getDeviceList()	// not in javax.media.CaptureDeviceManager
	{	return Registry.getInstance().getDeviceList();
	}
	
	public static synchronized Vector getDeviceList(Format format)
	{	
		final Vector v = getDeviceList();
		final Vector result = new Vector();
		for (int i = 0; i < v.size(); ++i)
		{
			final CaptureDeviceInfo captureDeviceInfo = (CaptureDeviceInfo) v.get(i);
			if (format == null)
			{	result.add(captureDeviceInfo);
			}
			else
			{
				final Format[] formats = captureDeviceInfo.getFormats();
				
				for (int j = 0; j < formats.length; ++j)
				{
					if (format.matches(formats[j]))
					{
						result.add(captureDeviceInfo);
						break;
					}
				}
			}
					
				
		}
		return result;
		
	}

	public static synchronized boolean addDevice(CaptureDeviceInfo newDevice)
	{	
		return Registry.getInstance().addDevice(newDevice);
	}

	public static synchronized boolean removeDevice(CaptureDeviceInfo device)
	{	return Registry.getInstance().removeDevice(device);
	}

	public static synchronized void commit() throws java.io.IOException
	{	
		Registry.getInstance().commit();
	}
}
