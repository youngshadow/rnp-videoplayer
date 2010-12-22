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

package net.sf.fmj.media.codec.audio.alaw;

import java.util.logging.Logger;

import javax.media.Format;
import javax.media.format.AudioFormat;

import net.sf.fmj.media.AbstractPacketizer;
import net.sf.fmj.media.BonusAudioFormatEncodings;
import net.sf.fmj.utility.LoggerSingleton;

/**
 * 
 * 
 *
 */
public class Packetizer extends AbstractPacketizer
{
	private static final Logger logger = LoggerSingleton.logger;
	
	private static final int PACKET_SIZE = 480;
	
	public String getName()
	{
		return "ALAW Packetizer";
	}
	
	public Packetizer()
	{
		super();
		this.inputFormats = new Format[] {new AudioFormat(AudioFormat.ALAW, -1.0, 8, 1, -1, -1, 8, -1.0, Format.byteArray)};
		
	}
	
	// TODO: move to base class?
	protected Format[] outputFormats = new Format[] {new AudioFormat(BonusAudioFormatEncodings.ALAW_RTP, -1.0, 8, 1, -1, -1, 8, -1.0, Format.byteArray)};
	
	public Format[] getSupportedOutputFormats(Format input)
	{
		if (input == null)
			return outputFormats;
		else
		{	
			if (!(input instanceof AudioFormat))
			{	
				logger.warning(this.getClass().getSimpleName() + ".getSupportedOutputFormats: input format does not match, returning format array of {null} for " + input); // this can cause an NPE in JMF if it ever happens.
				return new Format[] {null};
			}
			final AudioFormat inputCast = (AudioFormat) input;
			if (!inputCast.getEncoding().equals(AudioFormat.ALAW) ||
				(inputCast.getSampleSizeInBits() != 8 && inputCast.getSampleSizeInBits() != Format.NOT_SPECIFIED) ||
				(inputCast.getChannels() != 1 && inputCast.getChannels() != Format.NOT_SPECIFIED) ||
				(inputCast.getFrameSizeInBits() != 8 && inputCast.getFrameSizeInBits() != Format.NOT_SPECIFIED)
				)
			{
				logger.warning(this.getClass().getSimpleName() + ".getSupportedOutputFormats: input format does not match, returning format array of {null} for " + input); // this can cause an NPE in JMF if it ever happens.
				return new Format[] {null};
			}
			final AudioFormat result = new AudioFormat(BonusAudioFormatEncodings.ALAW_RTP, inputCast.getSampleRate(), 8,
					1, inputCast.getEndian(), inputCast.getSigned(), 8, 
					inputCast.getFrameRate(), inputCast.getDataType());

			return new Format[] {result};
		}
	}

	public void open()
	{
		setPacketSize(PACKET_SIZE);
	}
	
	public void close()
	{
	}
	
	public Format setInputFormat(Format arg0)
	{
		return super.setInputFormat(arg0);
	}

	public Format setOutputFormat(Format arg0)
	{
		return super.setOutputFormat(arg0);
	}

	
}
