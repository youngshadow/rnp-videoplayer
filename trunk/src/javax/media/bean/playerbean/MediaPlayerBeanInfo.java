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

package javax.media.bean.playerbean;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

/**
 * Coding complete.
 * @author Ken Larson
 *
 */
public class MediaPlayerBeanInfo extends java.beans.SimpleBeanInfo
{

	private final PropertyDescriptor[] propertyDescriptors;
	
	public MediaPlayerBeanInfo()
	{	
		try
		{
			 propertyDescriptors = new PropertyDescriptor[] {
					 buildPropertyDescriptor(MediaPlayer.class, "mediaLocation", "media location", MediaPlayerMediaLocationEditor.class, true),
					 buildPropertyDescriptor(MediaPlayer.class, "controlPanelVisible", "show control panel", null, true),
					 buildPropertyDescriptor(MediaPlayer.class, "cachingControlVisible", "show caching control", null, true),
					 buildPropertyDescriptor(MediaPlayer.class, "fixedAspectRatio", "fixedAspectRatio", null, true),
					 buildPropertyDescriptor(MediaPlayer.class, "playbackLoop", "loop", null, true),
					 buildPropertyDescriptor(MediaPlayer.class, "volumeLevel", "volume", javax.media.bean.playerbean.MediaPlayerVolumePropertyEditor.class, true),
					 buildPropertyDescriptor(MediaPlayer.class, "background", "background", null, false),
					 buildPropertyDescriptor(MediaPlayer.class, "foreground", "foreground", null, false),
					 buildPropertyDescriptor(MediaPlayer.class, "font", "font", null, false),
				};
		}
		catch (IntrospectionException e)
		{	throw new RuntimeException(e);
		}
	}
	
	private static PropertyDescriptor buildPropertyDescriptor(Class clazz, String name, String displayName, Class propertyEditorClass, boolean bound) throws IntrospectionException
	{
		final PropertyDescriptor pd = new PropertyDescriptor(name, clazz);
		pd.setDisplayName(displayName);
		pd.setPropertyEditorClass(propertyEditorClass);
		pd.setBound(bound);	
		return pd;
	}
	
	public java.beans.PropertyDescriptor[] getPropertyDescriptors()
	{
		return propertyDescriptors;
	}
	
	public int getDefaultPropertyIndex()
	{
		return 1;
	}
	
	public java.beans.BeanDescriptor getBeanDescriptor()
	{
		final BeanDescriptor result = new BeanDescriptor(MediaPlayer.class);
		result.setName("MediaPlayer");
		result.setDisplayName("MediaPlayer Bean");
		result.setShortDescription("MediaPlayer Bean");
		return result;
	}
	
	public java.beans.EventSetDescriptor[] getEventSetDescriptors()
	{
		throw new java.lang.Error(new java.beans.IntrospectionException("Method \"controllerUpdate\" should have argument \"ControllerUpdateEvent\""));	// TODO - can't get the real one to work.
		// Get:
//		java.lang.Error: java.beans.IntrospectionException: Method "controllerUpdate" should have argument "ControllerUpdateEvent"
//		at javax.media.bean.playerbean.MediaPlayerBeanInfo.getEventSetDescriptors(MediaPlayerBeanInfo.java:150)
	
		// TODO: http://www.netbeans.org/servlets/ReadMsg?list=nbusers&msgNo=37579


	}
	
	public java.awt.Image getIcon(int ic)
	{
		return null;
	}

}
