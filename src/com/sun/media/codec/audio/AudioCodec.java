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

package com.sun.media.codec.audio;

import javax.media.Format;
import javax.media.format.AudioFormat;

import com.sun.media.BasicCodec;

/**
 * TODO: Still partially a stub - enough so that  so that FOBS will work.  Also
 * com.sun.media.codec.audio.rc.RateCvrt will work.
 * 
 *
 */
public abstract class AudioCodec extends BasicCodec
{
	protected AudioFormat inputFormat;
	protected AudioFormat outputFormat;
	
	public AudioCodec()
	{	super();
	
	}
	public Format setInputFormat(Format format)
	{	
		super.setInputFormat(format);
		this.inputFormat = (AudioFormat) format;
		return format; 
	}
	public Format setOutputFormat(Format format)
	{	super.setOutputFormat(format);
		this.outputFormat = (AudioFormat) format;
		return format; 
	}
	public boolean checkFormat(Format format)
	{	return true; // TODO: FOBS extends this class so we have to do something here.  if we return false, FOBS audio won't work.  TODO: what needs to be checked here?
	}
}
