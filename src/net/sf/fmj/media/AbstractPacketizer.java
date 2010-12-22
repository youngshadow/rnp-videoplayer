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
 * 
 *
 */
public abstract class AbstractPacketizer extends AbstractCodec
{
	private static final boolean TRACE = false;
	

	private int packetSize;	// includes packet header size, if any
	private byte[] packetBuffer;
	private int bytesInPacketBuffer = 0;
	private boolean doNotSpanInputBuffers = false;	
		// do not span input buffers.  That is, if we have to send out a shorter than packetSize packet because an inputBuffer does not divide evenly by packetSize, do so.  Otherwise, the data will carry over to the next packet.
		// it makes sense for doNotSpanInputBuffers to be true for audio, and false for video.
	
	protected void setPacketSize(int packetSize)
	{	this.packetSize = packetSize;
		packetBuffer = new byte[packetSize];
		// TODO: copy old data if there was any.
	}
	
	
	protected void setDoNotSpanInputBuffers(boolean doNotSpanInputBuffers)
	{
		this.doNotSpanInputBuffers = doNotSpanInputBuffers;
	}


	/**
	 * @return the number of bytes added to the packetBuffer.
	 */
	protected int doBuildPacketHeader(Buffer inputBuffer, byte[] packetBuffer)
	{	return 0;
	}
	
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

        if (bytesInPacketBuffer == 0)
        {
        	//System.out.println("input buffer size: " + inputBuffer.getLength());
        	final int packetHeaderSize = doBuildPacketHeader(inputBuffer, packetBuffer);
        	bytesInPacketBuffer += packetHeaderSize;
        }
        
        final int bytesNeededToCompletePacket = packetSize - bytesInPacketBuffer;
        final int bytesAvailable = inputBuffer.getLength();
        final int bytesToCopy = bytesNeededToCompletePacket < bytesAvailable ? bytesNeededToCompletePacket : bytesAvailable;

        System.arraycopy((byte []) inputBuffer.getData(), inputBuffer.getOffset(), packetBuffer, bytesInPacketBuffer, bytesToCopy);
        bytesInPacketBuffer += bytesToCopy;
        
        inputBuffer.setOffset(inputBuffer.getOffset() + bytesToCopy);
        inputBuffer.setLength(inputBuffer.getLength() - bytesToCopy);
       
        final boolean packetComplete = (doNotSpanInputBuffers && inputBuffer.getLength() == 0) || bytesInPacketBuffer == packetSize;
        
        final int result;
        
        if (packetComplete)
        {
        	 outputBuffer.setData(packetBuffer);
        	 outputBuffer.setOffset(0);
        	 outputBuffer.setLength(bytesInPacketBuffer);
        	 bytesInPacketBuffer = 0;
        	 
        	 if (inputBuffer.getLength() == 0)
        		 result = BUFFER_PROCESSED_OK;
        	 else
        		 result = INPUT_BUFFER_NOT_CONSUMED;
        }
        else
        {
        	result = OUTPUT_BUFFER_NOT_FILLED;
        }

        
		if (TRACE)
		{	dump("input ", inputBuffer);
			dump("output", outputBuffer);
		
			System.out.println("Result=" + MediaCGUtils.plugInResultToStr(result));
		}
		return result;
	}
}
