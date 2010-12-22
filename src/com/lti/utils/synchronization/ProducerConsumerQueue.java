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

import com.lti.utils.collections.Queue;

/**
 * A synchronized queue where get/put waits if needed.
 
 */
public class ProducerConsumerQueue/*<T>*/
{
	private final Queue/*<T>*/ q = new Queue/*<T>*/();
	private final int sizeLimit;

	public ProducerConsumerQueue()
	{	super();
		sizeLimit = -1;
	}	

	public ProducerConsumerQueue(int sizeLimit)
	{	this.sizeLimit = sizeLimit;
	}

	public synchronized int size()
	{	return q.size();
	}
	public int sizeLimit()
	{	return sizeLimit;
	}
	public synchronized boolean isFull()
	{	return sizeLimit > 0 && q.size() >= sizeLimit;
	}
	public synchronized boolean isEmpty()
	{	return q.size() == 0;
	}
	
	public synchronized void waitUntilNotEmpty() throws InterruptedException
	{
		while (q.isEmpty()) 
		{
			wait();	// java programming note: this causes the lock to be released until we are notified
		}
	}
	public synchronized void waitUntilEmpty() throws InterruptedException
	{
		while (!q.isEmpty()) 
		{
			wait();	// java programming note: this causes the lock to be released until we are notified
		}
	}
	/**
	 * @return true if success, false if timeout.
	 * 
	 */
	public synchronized boolean waitUntilNotEmpty(long timeout) throws InterruptedException
	{
		final long start = System.currentTimeMillis();
		while (q.isEmpty()) 
		{
			final long now = System.currentTimeMillis();
			final long alreadyWaited = now - start;
			final long waitRemaining = timeout - alreadyWaited;
			if (waitRemaining < 1)
				return false;
			wait(waitRemaining);	// java programming note: this causes the lock to be released until we are notified
		}
		return !q.isEmpty();
	}

	
	public synchronized Object /*T*/ get() throws InterruptedException	// java programming note: making a method synchronized is the same as putting the contents of the method insided synchronized (this){}
	{
		while (q.isEmpty()) 
		{
//			try 
//			{
				wait();	// java programming note: this causes the lock to be released until we are notified
//			} 
//			catch (InterruptedException e) { }
		}
		final Object /*T*/ o = q.dequeue();
//		if (sizeLimit > 0 && q.size() == sizeLimit - 1)
		notifyAll();
		return o;
	}
	
	/** @return returnOnTimeout if timeout. */	
	public synchronized Object /*T*/ get(long timeout, Object /*T*/ returnOnTimeout) throws InterruptedException	
	{
		// TODO: what if null were queued?
		while (q.isEmpty()) 
		{
			final long t1 = System.currentTimeMillis();
			wait(timeout);
			if ((System.currentTimeMillis() - t1) > timeout)
			{	return returnOnTimeout;	// timeout
			}
		}
		final Object /*T*/ o = q.dequeue();
//		if (sizeLimit > 0 && q.size() == sizeLimit - 1)
		notifyAll();
		return o;
	}
	

	public synchronized void put(Object /*T*/ value) throws InterruptedException
	{
		while (sizeLimit > 0 && q.size() >= sizeLimit) 
		{
//			try 
//			{
				wait();
//			} 
//			catch (InterruptedException e) { }
		}

		q.enqueue(value);
		notifyAll();
	}

	/**
	 * @return false if timeout, true otherwise
	 */
	public synchronized boolean put(Object /*T*/ value, long timeout)  throws InterruptedException
	{
		while (sizeLimit > 0 && q.size() >= sizeLimit) 
		{
//			try 
//			{
				long t1 = System.currentTimeMillis();
				wait(timeout);
				if ((System.currentTimeMillis() - t1) > timeout)
				{	return false;	// timeout
				}
//			} 
//			catch (InterruptedException e) 
//			{ 
//			}
		}

		q.enqueue(value);
		notifyAll();
		return true;
	}
}
