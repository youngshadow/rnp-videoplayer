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

package net.sf.fmj.media;

import java.util.ArrayList;
import java.util.List;

import javax.media.DataSink;
import javax.media.MediaLocator;
import javax.media.datasink.DataSinkEvent;
import javax.media.datasink.DataSinkListener;

/**
 * 
 * 
 *
 */
public abstract class AbstractDataSink implements DataSink
{

	private final List listeners = new ArrayList();	// of DataSinkListener
	
	public void addDataSinkListener(DataSinkListener listener)
	{
		synchronized (listeners)
		{
			listeners.add(listener);
		}
	}

	public void removeDataSinkListener(DataSinkListener listener)
	{
		synchronized (listeners)
		{
			listeners.remove(listener);
		}
	}
	
	protected void notifyDataSinkListeners(DataSinkEvent event)
	{
		final List listenersCopy = new ArrayList();
		
		synchronized (listeners)
		{
			listenersCopy.addAll(listeners);
		}
		
		for (int i = 0; i < listenersCopy.size(); ++i)
		{
			DataSinkListener listener = (DataSinkListener) listenersCopy.get(i);
			listener.dataSinkUpdate(event);
		}
	}
	
	protected MediaLocator outputLocator;
	
	public void setOutputLocator(MediaLocator output)
	{
		this.outputLocator = output;
	}
	
	public MediaLocator getOutputLocator()
	{
		return outputLocator;
	}

}
