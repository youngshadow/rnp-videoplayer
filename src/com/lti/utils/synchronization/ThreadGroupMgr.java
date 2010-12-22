
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

import com.lti.utils.ObjUtils;

/**
 * Manages a singleton thread group.  This is useful because we often want to assign
 * all threads to a single thread group to allow centralized uncaught exception handling.

 */
public final class ThreadGroupMgr
{
	private static ThreadGroup mainThreadGroup;
	private static ThreadGroup defaultThreadGroup;
	
	private ThreadGroupMgr()
	{	super();
	}
	
	/** Returns the current thread group if it differs from the main thread group, otherwise it returns the default thread group. */
	public static synchronized ThreadGroup getThreadGroup()
	{
		ThreadGroup currentThreadGroup = Thread.currentThread().getThreadGroup();
		if (mainThreadGroup != null && defaultThreadGroup != null && ObjUtils.equal(currentThreadGroup, mainThreadGroup))
			return getDefaultThreadGroup();
		else
			return currentThreadGroup;
	}
	
	public static synchronized ThreadGroup getMainThreadGroup()
	{
		return mainThreadGroup;
	}
	public static synchronized void setMainThreadGroup(ThreadGroup mainThreadGroup)
	{
		ThreadGroupMgr.mainThreadGroup = mainThreadGroup;
	}
	public static synchronized ThreadGroup getDefaultThreadGroup()
	{
		return defaultThreadGroup;
	}
	public static synchronized void setDefaultThreadGroup(ThreadGroup defaultThreadGroup)
	{
		ThreadGroupMgr.defaultThreadGroup = defaultThreadGroup;
	}
}
