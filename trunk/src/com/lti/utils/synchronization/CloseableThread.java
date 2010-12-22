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
 * A base class for threads which need to be gracefully closed, since Thread.stop() is
 * deprecated.  Subclass should check isClosing() in their main loop in run(), and 
 * call setClosed() when run() completes.
 * 
 * 
 */
public abstract class CloseableThread extends Thread
{
	protected final SynchronizedBoolean closing = new SynchronizedBoolean(false); 
	private final SynchronizedBoolean closed = new SynchronizedBoolean(false); 
	
	/** @deprecated */
	public CloseableThread()
	{
		super();
	}
	/** @deprecated */
	public CloseableThread(String threadName)
	{
		super(threadName);
	}
	public CloseableThread(final ThreadGroup group, final String threadName)
	{
		super(group, threadName);
	}
	public void close()	
	{	closing.setValue(true);
		interrupt();
	}
	protected void setClosing()
	{
		closing.setValue(true);
	}

	public boolean isClosed()
	{	return closed.getValue();
	}
	public void waitUntilClosed() throws InterruptedException
	{	closed.waitUntil(true);
	}
	
	/**
	 * intended to be checked by thread in its main loop.  break out of the main loop
	 * if true.
	 */
	protected boolean isClosing()
	{	return closing.getValue();
	}
	
	/**
	 * to be called by the thread upon exit.
	 */
	protected void setClosed()
	{	closed.setValue(true);
	}
}
