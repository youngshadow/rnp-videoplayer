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

package net.sf.fmj.media;

import javax.media.Buffer;
import javax.media.Codec;
import javax.media.Format;

import net.sf.fmj.codegen.MediaCGUtils;

/**
 * 
 * 
 *
 */
public abstract class AbstractCodec extends AbstractPlugIn implements Codec
{

	protected Format inputFormat = null;
	protected Format outputFormat = null;
	protected boolean opened = false;
	protected Format[] inputFormats = new Format[0];

	public Format[] getSupportedInputFormats()
	{
		return inputFormats;
	}

	public abstract Format[] getSupportedOutputFormats(Format input);

	public abstract int process(Buffer input, Buffer output);
	
	public Format setInputFormat(Format format)
	{
		this.inputFormat = format;
		return inputFormat;
	}

	public Format setOutputFormat(Format format)
	{
		this.outputFormat = format;
		return outputFormat;
	}
	
	protected Format getInputFormat()
	{	return inputFormat;
	}
	protected Format getOutputFormat()
	{	return outputFormat;
	}	
	
	protected boolean checkInputBuffer(Buffer b)
	{
		return true;	// TODO
	}
	
	protected boolean isEOM(Buffer b)
	{	return b.isEOM();
	}
	
	protected void propagateEOM(Buffer b)
	{	b.setEOM(true);
	}
	
	protected final void dump(String label, Buffer buffer)
	{
		System.out.println(label + ": " + MediaCGUtils.bufferToStr(buffer));

	}

}
