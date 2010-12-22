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

import javax.media.control.QualityControl;

/**
 * TODO: Stub
 *
 */
public class QualityAdapter implements QualityControl
{

	protected float preferredValue;
	protected float minValue;
	protected float maxValue;
	protected float value;
	protected boolean settable;
	protected boolean isTSsupported;
	
	
	public QualityAdapter(float value, float value2, float value3, boolean ssupported, boolean settable)
	{
		super();
		preferredValue = value;
		minValue = value2;
		maxValue = value3;
		isTSsupported = ssupported;
		this.settable = settable;
	}

	public QualityAdapter(float value, float value2, float value3, boolean settable)
	{
		super();
		preferredValue = value;
		minValue = value2;
		maxValue = value3;
		this.settable = settable;
	}

	
	public float getPreferredQuality()
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public float getQuality()
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public boolean isTemporalSpatialTradeoffSupported()
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public float setQuality(float newQuality)
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public Component getControlComponent()
	{
		throw new UnsupportedOperationException(); // TODO
	}

}
