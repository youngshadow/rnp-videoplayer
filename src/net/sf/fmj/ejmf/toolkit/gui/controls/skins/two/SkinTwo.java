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

package net.sf.fmj.ejmf.toolkit.gui.controls.skins.two;
import java.awt.Component;

import javax.swing.AbstractButton;

import net.sf.fmj.ejmf.toolkit.gui.controls.Skin;
import net.sf.fmj.ejmf.toolkit.gui.controls.skins.ejmf.GainMeterButton;
import net.sf.fmj.ejmf.toolkit.gui.controls.skins.ejmf.ProgressSlider;
import net.sf.fmj.ejmf.toolkit.gui.controls.skins.ejmf.ReverseButton;
import net.sf.fmj.ejmf.toolkit.gui.controls.skins.ejmf.VolumeControlButton;

/**
 * 
 * 
 *
 */
public class SkinTwo implements Skin 
{
	public Component createStartButton() 
	{
		return new StartButton();
	}

	public Component createStopButton() 
	{
		return new StopButton();
	}
	public Component createFastForwardButton() 
	{
		return new FastForwardButton();
	}



	public Component createGainMeterButton() 
	{
		return new GainMeterButton();
	}

	public Component createPauseButton() 
	{
		return new PauseButton();
	}

	public Component createProgressSlider() 
	{
		return new ProgressSlider();
	}

	public Component createReverseButton() 
	{
		return new ReverseButton();
	}

	public AbstractButton createVolumeControlButton_Increase() 
	{
		return new VolumeControlButton(VolumeControlButton.INCREASE);
	}
	
	public AbstractButton createVolumeControlButton_Decrease() 
	{
		return new VolumeControlButton(VolumeControlButton.DECREASE);
	}
}
