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

package net.sf.fmj.media.multiplexer.audio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.media.Format;
import javax.media.format.AudioFormat;
import javax.media.protocol.FileTypeDescriptor;
import javax.sound.sampled.AudioFileFormat;

import net.sf.fmj.media.codec.JavaSoundCodec;
import net.sf.fmj.utility.IOUtils;


/**
 * 
 * 
 *
 */
public class WAVMux extends JavaSoundMux
{

	public WAVMux()
	{
		super(new FileTypeDescriptor(FileTypeDescriptor.WAVE), AudioFileFormat.Type.WAVE);
	}
	
	public Format setInputFormat(Format format, int trackID)
	{
		AudioFormat af = (AudioFormat) format;
		if (af.getSampleSizeInBits() == 8 && af.getSigned() == AudioFormat.SIGNED)
			return null;	// 8-bit is always unsigned for Wav.
		
		if (af.getSampleSizeInBits() == 16 && af.getSigned() == AudioFormat.UNSIGNED)
			return null;	// 16-bit is always signed for Wav.

		return super.setInputFormat(format, trackID);
	}

	protected void write(InputStream in, OutputStream out, javax.sound.sampled.AudioFormat javaSoundFormat) throws IOException
	{
		if (true)
		{	super.write(in, out, javaSoundFormat);
			
		}
		else
		{	// alternative to JavaSound - not necessary.
			try
			{
				byte[] header = JavaSoundCodec.createWavHeader(javaSoundFormat);	// TODO: no length
				if (header == null)
					throw new IOException("Unable to create wav header");
				
				//System.out.println("WAVMux Header: " + header.length);
				out.write(header);
				
				IOUtils.copyStream(in, out);
				
				// TODO: go back and write header.
			}
			catch (IOException e)
			{
				e.printStackTrace();
				throw e;
			}
		}
	}
	

}
