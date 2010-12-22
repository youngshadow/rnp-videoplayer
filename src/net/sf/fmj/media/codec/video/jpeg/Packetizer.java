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

package net.sf.fmj.media.codec.video.jpeg;

import javax.media.Buffer;
import javax.media.Format;
import javax.media.format.VideoFormat;

import net.sf.fmj.media.AbstractPacketizer;

/**
 * 
 * Replacement for com.sun.media.codec.video.jpeg.Packetizer.
 * 
 *
 */
public class Packetizer extends AbstractPacketizer
{
	private final Format[] supportedInputFormats = new Format[] {
			new VideoFormat(VideoFormat.JPEG, null, -1, Format.byteArray, -1.0f),
		};
	private final Format[] supportedOutputFormats = new Format[] {
			new VideoFormat(VideoFormat.JPEG_RTP, null, -1, Format.byteArray, -1.0f),
		};

	private static final int PACKET_SIZE = 1000;	// TODO: what is the correct packet size?
	
	public String getName()
	{
		return "JPEG/RTP Packetizer";
	}
	
	public Packetizer()
	{
		super();
		this.inputFormats = supportedInputFormats;
		
	}

	public Format[] getSupportedOutputFormats(Format input)
	{
		if (input == null)
			return supportedOutputFormats;
		VideoFormat inputCast = (VideoFormat) input;
		final Format[] result = new Format[] {
				new VideoFormat(VideoFormat.JPEG_RTP, inputCast.getSize(), -1, Format.byteArray, -1.0f)};
	
		return result;
	}
	
	public void open()
	{
		setPacketSize(PACKET_SIZE);
		setDoNotSpanInputBuffers(true);
	}
	
	public void close()
	{
	}
	
	protected int doBuildPacketHeader(Buffer inputBuffer, byte[] packetBuffer)
	{	
		final VideoFormat format = (VideoFormat) inputFormat;
		int width = format.getSize().width;	
		int height = format.getSize().height;	
		
		byte typeSpecific = 0;	// TODO
		byte type = 0;	// TODO
		byte q = 0;	// TODO
		// TODO: where do we enforce that the width and height are multiples of 8?
		byte widthInBlocks = (byte) (width / 8);	
		byte heightInBlocks = (byte) (height / 8);
		
		final JpegRTPHeader jpegRTPHeader = new JpegRTPHeader(typeSpecific, inputBuffer.getOffset(), type, q, widthInBlocks, heightInBlocks);
		final byte[] bytes = jpegRTPHeader.toBytes();
		System.arraycopy(bytes, 0, packetBuffer, 0, bytes.length);
		return bytes.length;
	}
	
	public int process(Buffer input, Buffer output) 
	{
		// TODO: really, we should not be sending the JPEG header with each frame.  JMF has been observed doing this, and it
		// is much simpler, so for now we will do it.  The correct way is to encode the JPEG frame using parameters that are then
		// sent in an RTP header.  But that won't work with arbitrary JPEG images.  We should really change the input format of this
		// codec to be RGB, so that we can do the jpeg compression in an RTP/JPEG-compliant manner.
		
		final int result = super.process(input, output);
		//System.out.println("output ts input ts " + input.getTimeStamp() + " " + output.getTimeStamp());
		if (result == BUFFER_PROCESSED_OK)	// if input is consumed, then it must be the last part of the frame.
		{	output.setFlags(output.getFlags() | Buffer.FLAG_RTP_MARKER);
			//System.out.println("LAST PACKET IN FRAME, flags=" + Integer.toHexString(output.getFlags()) + " ts=" + output.getTimeStamp());
		}
		else
		{
			//System.out.println("     PACKET IN FRAME, flags=" + Integer.toHexString(output.getFlags()) + " ts=" + output.getTimeStamp());
		}

		
		return result;
	}
}
