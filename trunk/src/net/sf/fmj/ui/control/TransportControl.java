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

package net.sf.fmj.ui.control;

/**
 * Abstraction of a player/controller for use by the TransportControlPanel.
 * @author Warren Bloomer
 *
 */
public interface TransportControl {

	void start();
	
	void stop();
	
	void setPosition(double seconds);
        
	
	/**
	 * 1.0 is normal playback speed.
	 * 0 is stopped.
	 * @param rate
	 */
	void setRate(float rate);
	
	void setGain(float value);
	
	void setMute(boolean value);
	
	void setTransportControlListener(TransportControlListener listener);
}
