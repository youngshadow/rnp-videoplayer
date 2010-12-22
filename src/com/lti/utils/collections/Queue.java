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

package com.lti.utils.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a FIFO.
 * 
 * @author Ken Larson
 */
public class Queue/*<T>*/
{
	private List/*<T>*/ v = new ArrayList/*<T>*/();

	public int size()
	{	return v.size();
	}

	public Object /*T*/ dequeue()
	{
//		if (v.size() == 0)
//			throw new ArrayIndexOutOfBoundsException("Queue empty");
		final Object /*T*/ o = v.get(0);
		v.remove(0);
		return o;
	}
	public Object /*T*/ peek()
	{
//		if (v.size() == 0)
//			throw new ArrayIndexOutOfBoundsException("Queue empty");
		return v.get(0);
	}
	public void enqueue(Object /*T*/ o)
	{	v.add(o);
	}
	public void removeAllElements()
	{	v.clear();
	}
	public boolean isEmpty()
	{	return v.size() == 0;
	}
}
