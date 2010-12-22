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

package net.sf.fmj.ui.wizards;

import javax.media.format.AudioFormat;


/**
 * 
 * 
 *
 */
public class Main {
    
    public static void main(String[] args) {
        
    	RTPTransmitWizardConfig config = new RTPTransmitWizardConfig();
		// set defaults:
		config.url = "file://samplemedia/gulp2.wav";
		config.trackConfigs = new TrackConfig[] {new TrackConfig(true, new AudioFormat(AudioFormat.ULAW_RTP, 8000.0, 8, 1, AudioFormat.LITTLE_ENDIAN, AudioFormat.SIGNED))};
//		try
//		{
			config.destUrl = "rtp://192.168.1.4:8000/audio/16";//RTPUrlParser.parse("rtp://192.168.1.4:8000/audio/16");
//		} catch (RTPUrlParserException e)
//		{
//			throw new RuntimeException(e);
//		}
		
        new RTPTransmitWizard(null, config).run();
        
    }
    
}
