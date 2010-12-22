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

package net.sf.fmj.capture.test;

import java.util.List;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.format.RGBFormat;

import net.sf.fmj.ejmf.toolkit.install.PackageUtility;
import net.sf.fmj.utility.PlugInUtility;

import com.lti.civil.CaptureException;
import com.lti.civil.CaptureSystem;
import com.lti.civil.CaptureSystemFactory;
import com.lti.civil.DefaultCaptureSystemFactorySingleton;


/**
 * 
 * 
 *
 */
public class FMJCaptureTest
{

	public static void main(String[] args) throws Exception
	{
		
		
//		for (Format f : new SimpleAWTRenderer().getSupportedInputFormats())
//		{
//			final Format civilOutputFormat = new RGBFormat(null, -1, byte[].class, -1, 24, 1, 2, 3);
//			System.out.println(civilOutputFormat.matches(f) + " " + f);
//			
//		}
		
		
		PackageUtility.addContentPrefix("net.sf.fmj", false);
		PackageUtility.addProtocolPrefix("net.sf.fmj", false);
		
    	PlugInUtility.registerPlugIn("net.sf.fmj.media.renderer.video.SimpleAWTRenderer");
    	  
		
		
		CaptureSystemFactory factory = DefaultCaptureSystemFactorySingleton.instance();
		CaptureSystem system = factory.createCaptureSystem();
		system.init();
		List list = system.getCaptureDeviceInfoList();
		for (int i = 0; i < list.size(); ++i)
		{
			com.lti.civil.CaptureDeviceInfo civilInfo = (com.lti.civil.CaptureDeviceInfo) list.get(i);
			
			{
				
				//String name, MediaLocator locator, Format[] formats
				CaptureDeviceInfo jmfInfo = new CaptureDeviceInfo(civilInfo.getDescription(), new MediaLocator("civil:" + civilInfo.getDeviceID()), new Format[] {new RGBFormat()});
				CaptureDeviceManager.addDevice(jmfInfo);
			}
			
		}


		final java.util.Vector vectorDevices = CaptureDeviceManager.getDeviceList(null);
		if (vectorDevices == null)
		{
			System.out.println("CaptureDeviceManager.getDeviceList returned null");
			return;
		}
		if (vectorDevices.size() == 0)
		{
			System.out.println("CaptureDeviceManager.getDeviceList returned empty list");
			return;
		}

		
		for ( int i = 0;  i < vectorDevices.size();  i++ ) 
		{
			CaptureDeviceInfo infoCaptureDevice = (CaptureDeviceInfo) vectorDevices.elementAt ( i );
			System.out.println("CaptureDeviceInfo: ");
			System.out.println(infoCaptureDevice.getName());
			System.out.println(infoCaptureDevice.getLocator());
			System.out.println(infoCaptureDevice.getFormats()[0]);
			
			Player player;
			try
			{
				
				player = Manager.createRealizedPlayer(infoCaptureDevice.getLocator());
			} catch (Exception e)
			{	throw new CaptureException(e);
			}
	        player.start();
			
		}
			
		system.dispose();
	}

}
