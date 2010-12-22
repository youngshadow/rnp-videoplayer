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
import javax.media.control.H263Control;

/**
 * TODO: Stub
 
 *
 */
public class H263Adapter implements H263Control
{

	private boolean settable;
	private Codec owner;
	private boolean advancedPrediction;
	private boolean arithmeticCoding;
	private boolean errorCompensation;
	private boolean pbFrames;
	private boolean unrestrictedVector;
	private int hrd_B;
	private int bppMaxKb;
	
	public H263Adapter(Codec owner, boolean prediction, boolean coding, boolean compensation, boolean frames, boolean vector, int hrd_b, int kb, boolean settable)
	{
		super();
		this.owner = owner;
		advancedPrediction = prediction;
		arithmeticCoding = coding;
		errorCompensation = compensation;
		pbFrames = frames;
		unrestrictedVector = vector;
		hrd_B = hrd_b;
		bppMaxKb = kb;
		this.settable = settable;
	}

	public boolean getAdvancedPrediction()
	{
		return advancedPrediction;
	}

	public boolean getArithmeticCoding()
	{
		return arithmeticCoding;
	}

	public int getBppMaxKb()
	{
		return bppMaxKb;
	}

	public boolean getErrorCompensation()
	{
		return errorCompensation;
	}

	public int getHRD_B()
	{
		return hrd_B;
	}

	public boolean getPBFrames()
	{
		return pbFrames;
	}

	public boolean getUnrestrictedVector()
	{
		return unrestrictedVector;
	}

	public boolean isAdvancedPredictionSupported()
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public boolean isArithmeticCodingSupported()
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public boolean isErrorCompensationSupported()
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public boolean isPBFramesSupported()
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public boolean isUnrestrictedVectorSupported()
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public boolean setAdvancedPrediction(boolean newAdvancedPredictionMode)
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public boolean setArithmeticCoding(boolean newArithmeticCodingMode)
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public boolean setErrorCompensation(boolean newtErrorCompensationMode)
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public boolean setPBFrames(boolean newPBFramesMode)
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public boolean setUnrestrictedVector(boolean newUnrestrictedVectorMode)
	{
		throw new UnsupportedOperationException(); // TODO
	}

	public Component getControlComponent()
	{
		throw new UnsupportedOperationException(); // TODO
	}

}
