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

package net.sf.fmj.media.datasink.rtp;

import javax.media.format.AudioFormat;
import javax.media.rtp.RTPManager;

import net.sf.fmj.media.BonusAudioFormatEncodings;

/**
 * 
 * No way to add extra formats globally and for all sessions, or to commit, with JMF.
 * So we will just call this any time we initialize a new RTPManager instance.
 * 
 * See http://archives.java.sun.com/cgi-bin/wa?A2=ind0107&L=jmf-interest&P=33617
 * 
 * 
 *
 */
public class RTPBonusFormatsMgr
{
	public static final int FIRST_BONUS_FORMAT = 100;
	public static final int ALAW_RTP_INDEX = 100;
	public static final int SPEEX_RTP_INDEX = 101;
	public static final int ILBC_RTP_INDEX = 102;
	
	
	public static void addBonusFormats(RTPManager mgr)
	{
		mgr.addFormat(new AudioFormat(
				BonusAudioFormatEncodings.ALAW_RTP,
                8000,
                8,
                1,
                -1,
                AudioFormat.SIGNED
            ), ALAW_RTP_INDEX);
		
		// see net.java.sip.communicator.impl.media.codec.audio.speex.JavaDecoder.
		// only relevant if this encoder/decoder pair is available.
		mgr.addFormat(new AudioFormat(
				BonusAudioFormatEncodings.SPEEX_RTP,
                8000,
                8,
                1,
                -1,
                AudioFormat.SIGNED //isSigned());
            ), SPEEX_RTP_INDEX);
		
		// ditto, for net.java.sip.communicator.impl.media.codec.audio.ilbc.JavaDecoder:
		mgr.addFormat(new AudioFormat(
				BonusAudioFormatEncodings.ILBC_RTP,
                8000.0,
                16,
                1,
                AudioFormat.LITTLE_ENDIAN,
                AudioFormat.SIGNED
		), ILBC_RTP_INDEX);
		
		
	}
}
