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

import javax.media.control.KeyFrameControl;

import com.sun.media.ui.TextComp;

/**
 * TODO: Stub
 *
 */
public class KeyFrameAdapter implements KeyFrameControl
{

	private int preferred;
	private int value;
	private boolean settable;
	private final TextComp textComp = new TextComp();
	
	public int getKeyFrameInterval()
	{
		return value;
	}

	public KeyFrameAdapter(int preferred, boolean settable)
	{
		super();
		this.preferred = preferred;
		this.settable = settable;
	}

	public int getPreferredKeyFrameInterval()
	{
		return preferred;
	}

	public int setKeyFrameInterval(int frames)
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public Component getControlComponent()
	{
		throw new UnsupportedOperationException(); // TODO
	}

}
