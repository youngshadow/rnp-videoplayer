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
 * A useful base class for a thread that wishes to simply respond to messages.
 * 
 * 
 */
public class MessageDrivenThread extends CloseableThread
{
	
	private MessageDrivenThreadListener listener;
	
	public MessageDrivenThread(final ThreadGroup group, final String threadName, MessageDrivenThreadListener listener)
	{	super(group, threadName);
		this.listener = listener;
	}
	public MessageDrivenThread(final ThreadGroup group, final String threadName)
	{	super(group, threadName);
	}
	public void setListener(MessageDrivenThreadListener listener)
	{	this.listener = listener;
	}
	
	private ProducerConsumerQueue q = new ProducerConsumerQueue(); 
	public void post(Object msg) throws InterruptedException
	{
		q.put(msg);
	}
	public void run()
	{
		try
		{
			while (!isClosing())
			{
				Object o = q.get();
				
				doMessageReceived(o);
			}
		}
		catch (InterruptedException e)
		{
		}
		finally
		{
			setClosed();
		}
	}
	
	/**
	 * subclass should override to do message processing.
	 */
	protected void doMessageReceived(Object o)
	{
		if (listener != null)
			listener.onMessage(this, o);
	}
}
