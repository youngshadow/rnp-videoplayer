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

import net.sf.fmj.codegen.MediaCGUtils;

/**
 * 
 * Generic DePacketizer.  Doesn't have to do much, just copies input to output.  Uses buffer-swapping observed in debugging
 * and seen in other open-source DePacketizer implementations.
 * 
 *
 */
public abstract class AbstractDePacketizer extends AbstractCodec
{
	private static final boolean TRACE = false;
	
	public int process(Buffer inputBuffer, Buffer outputBuffer)
	{
		if (TRACE) dump("input ", inputBuffer);
		
        if (!checkInputBuffer(inputBuffer))
        {
            return BUFFER_PROCESSED_FAILED;
        }

        if (isEOM(inputBuffer))
        {
            propagateEOM(outputBuffer);	// TODO: what about data? can there be any?
            return BUFFER_PROCESSED_OK;
        }

        final Object temp = outputBuffer.getData();
        outputBuffer.setData(inputBuffer.getData());
        inputBuffer.setData(temp);
        outputBuffer.setLength(inputBuffer.getLength());
        outputBuffer.setFormat(outputFormat);
        outputBuffer.setOffset(inputBuffer.getOffset());
        int result = BUFFER_PROCESSED_OK;
        
		if (TRACE)
		{	dump("input ", inputBuffer);
			dump("output", outputBuffer);
		
			System.out.println("Result=" + MediaCGUtils.plugInResultToStr(result));
		}
		return result;
	}
}
