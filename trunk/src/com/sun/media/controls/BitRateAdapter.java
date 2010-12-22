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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.control.BitRateControl;

import com.sun.media.ui.TextComp;

/**
 * TODO: Stub
 * n
 *
 */
public class BitRateAdapter implements BitRateControl, ActionListener
{
	protected int min;
	protected int max;
	protected int value;
	protected boolean settable;
	protected final TextComp textComp;
	public BitRateAdapter(int value, int min, int max, boolean settable)
	{
		super();
		this.value = value;
		this.min = min;
		this.max = max;
		this.settable = settable;
		this.textComp = new TextComp();		// TODO - implement this class
	}
	public Component getControlComponent()
	{
		throw new UnsupportedOperationException(); // TODO
	}
	public void actionPerformed(ActionEvent e)
	{
		throw new UnsupportedOperationException(); // TODO
	}
	public int getBitRate()
	{
		return value;
	}
	public int getMaxSupportedBitRate()
	{
		return max;
	}
	public int getMinSupportedBitRate()
	{
		return min;
	}
	public int setBitRate(int bitrate)
	{
		throw new UnsupportedOperationException(); // TODO
	}
	
}
