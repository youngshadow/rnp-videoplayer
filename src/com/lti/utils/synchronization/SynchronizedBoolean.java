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

package com.lti.utils.synchronization;

/**
 * Useful class for setting a flag between threads.  For simple uses, this class is 
 * not that useful, since boolean access is guaranteed to be atomic.
 * 

 */ 
public class SynchronizedBoolean
{
	private boolean b = false;
	
	public SynchronizedBoolean()
	{	super();
	}
	public SynchronizedBoolean(boolean initValue)
	{
		this.b = initValue;
	}
	
	public synchronized boolean getValue()
	{
		return b;
	}
	public synchronized void setValue(boolean newValue)
	{
		if (b != newValue)
		{	b = newValue;
			notifyAll();
		}
		
	}
	public synchronized void waitUntil(boolean value) throws InterruptedException
	{	while (b != value)
		{
			wait();
		}
			
	}
	/**
	 * @return true if value matches value, false if timeout occurred
	 */
	public synchronized boolean waitUntil(boolean value, int timeout) throws InterruptedException
	{	long start = System.currentTimeMillis();
		while (b != value)
		{	long now = System.currentTimeMillis();
			long diff = now - start;
			long wait = timeout - diff;
			if (wait <= 0)
				return false;
			wait(wait);
		}
		return true;
	
	}	
	
	/**
	 * 
	 * If the value is oldValue, set to newValue and return true, otherwise return false.
	 */
	public synchronized boolean getAndSet(boolean oldValue, boolean newValue)
	{
		if (b != oldValue)
			return false;
		setValue(newValue);
		return true;
	}
	
}
