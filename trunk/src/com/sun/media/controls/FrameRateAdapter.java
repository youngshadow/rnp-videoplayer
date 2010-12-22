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

package com.sun.media.controls;

import java.awt.Component;

import javax.media.control.FrameRateControl;

import com.sun.media.ui.TextComp;

/**
 * TODO: Stub

 *
 */
public class FrameRateAdapter implements FrameRateControl
{

	protected float value;
	protected float min;
	protected float max;
	
	protected final TextComp textComp = new TextComp();
	protected boolean settable;
	protected Object owner;
	
	public Component getControlComponent()
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public FrameRateAdapter(float initialFrameRate, float minFrameRate, float maxFrameRate, boolean settable)
	{
		value = initialFrameRate;
		min = minFrameRate;
		max = maxFrameRate;
		this.settable = settable;
	}

	public FrameRateAdapter(Object owner, float initialFrameRate, float minFrameRate, float maxFrameRate, boolean settable)
	{
		this.owner = owner;
		value = initialFrameRate;
		min = minFrameRate;
		max = maxFrameRate;
		this.settable = settable;
	}
	public float getFrameRate()
	{
		return value;
	}

	public float getMaxSupportedFrameRate()
	{
		return max;
	}

	public float getPreferredFrameRate()
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public float setFrameRate(float newFrameRate)
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public Object getOwner()
	{
		return owner;
	}

	public void setOwner(Object owner)
	{
		this.owner = owner;
	}

}
