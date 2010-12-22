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

package net.sf.fmj.media.protocol.javasound;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.media.format.AudioFormat;

/**
 * 
 * 
 *
 */
public class JavaSoundUrlParser
{

	// for javasound url syntax, see http://archives.java.sun.com/cgi-bin/wa?A2=ind9906&L=jmf-interest&P=4678
	// javasound://<rate>/<sizeInBits>/<channels>/[big|little]/[signed|unsig
	
	private static final Pattern pattern = Pattern.compile(
		"javasound://" +
		
		"(([0-9]+)" + // rate - optional
		"(/([0-9]+)" + // size in bits - optional
		"(/([0-9]+)" + // channels - optional
		"(/(big|little)" + // endian  - optional
		"(/(signed|unsigned)" + // signed/unsigned  - optional
		")?)?)?)?)?" 

		);
	
	public static AudioFormat parse(String url) throws JavaSoundUrlParserException
	{
		if (url == null)
			throw new JavaSoundUrlParserException(new NullPointerException());
		
		if (!url.startsWith("javasound://"))
			throw new JavaSoundUrlParserException("Expected URL to start with: " + "javasound://");
		Matcher m = pattern.matcher(url);
		if (!m.matches())
			throw new JavaSoundUrlParserException("URL does not match regular expression for javasound URLs");
		
		int groupCount = m.groupCount();
		
		double rate = AudioFormat.NOT_SPECIFIED;
		int bits = AudioFormat.NOT_SPECIFIED;
		int channels = AudioFormat.NOT_SPECIFIED;
		int endian = AudioFormat.NOT_SPECIFIED; 
		int signed = AudioFormat.NOT_SPECIFIED; 
		
//		System.out.println("url: " + url);
//		for (int i = 0; i < 10; ++i)
//			System.out.println(" " + i + " " + m.group(i));
		
		
		try
		{
			if (m.group(2) != null && !m.group(2).equals(""))
				rate = Double.parseDouble(m.group(2));
			if (m.group(4) != null && !m.group(4).equals(""))
				bits = Integer.parseInt(m.group(4));
			if (m.group(6) != null && !m.group(6).equals(""))
				channels = Integer.parseInt(m.group(6));
			if (m.group(8) != null && !m.group(8).equals(""))
				endian = m.group(8).equals("big") ? AudioFormat.BIG_ENDIAN : AudioFormat.LITTLE_ENDIAN;
			if (m.group(10) != null && !m.group(10).equals(""))
				signed = m.group(10).equals("signed") ? AudioFormat.SIGNED : AudioFormat.UNSIGNED;
		
		}
		catch (NumberFormatException e)
		{	throw new JavaSoundUrlParserException("Invalid number", e);
		}
		return new AudioFormat(AudioFormat.LINEAR, rate, bits, channels, endian, signed);
		
	}
}
