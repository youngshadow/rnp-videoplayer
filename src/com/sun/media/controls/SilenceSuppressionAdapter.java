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

import javax.media.Codec;
import javax.media.control.SilenceSuppressionControl;

/**
 * TODO: stub.
 *
 */
public class SilenceSuppressionAdapter implements SilenceSuppressionControl
{

	protected Codec owner;
	protected boolean silenceSuppression;
	protected boolean isSetable;
	Component component;
	String CONTROL_STRING;
	
	public SilenceSuppressionAdapter(Codec owner, boolean silenceSuppression, boolean isSetable)
	{	
		this.owner = owner;
		this.silenceSuppression = silenceSuppression;
		this.isSetable = isSetable;
	}

	public boolean getSilenceSuppression()
	{
		return silenceSuppression;
	}

	public boolean setSilenceSuppression(boolean silenceSuppression)
	{
		if (isSetable)
			this.silenceSuppression = silenceSuppression;
		return this.silenceSuppression;
	}

	public boolean isSilenceSuppressionSupported()
	{
		return isSetable;
	}
	
	public Component getControlComponent()
	{
		return component;	// TODO: is null
	}
}
