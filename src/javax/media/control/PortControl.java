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

package javax.media.control;

import javax.media.Control;

/**
 * Complete.
 * @author Ken Larson
 *
 */
public interface PortControl extends Control
{
	public static final int MICROPHONE = 1;
	public static final int LINE_IN = 2;
	public static final int SPEAKER = 4;
	public static final int HEADPHONE = 8;
	public static final int LINE_OUT = 16;
	public static final int COMPACT_DISC = 32;
	public static final int SVIDEO = 64;
	public static final int COMPOSITE_VIDEO = 128;
	public static final int TV_TUNER = 256;
	public static final int COMPOSITE_VIDEO_2 = 512;
	
	public int setPorts(int ports);
	public int getPorts();
	public int getSupportedPorts();
}
