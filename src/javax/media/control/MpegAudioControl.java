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
public interface MpegAudioControl extends Control
{

	public static final int LAYER_1 = 1;

	public static final int LAYER_2 = 2;

	public static final int LAYER_3 = 4;

	public static final int SAMPLING_RATE_16 = 1;

	public static final int SAMPLING_RATE_22_05 = 2;

	public static final int SAMPLING_RATE_24 = 4;

	public static final int SAMPLING_RATE_32 = 8;

	public static final int SAMPLING_RATE_44_1 = 16;

	public static final int SAMPLING_RATE_48 = 32;

	public static final int SINGLE_CHANNEL = 1;

	public static final int TWO_CHANNELS_STEREO = 2;

	public static final int TWO_CHANNELS_DUAL = 4;

	public static final int THREE_CHANNELS_2_1 = 4;

	public static final int THREE_CHANNELS_3_0 = 8;

	public static final int FOUR_CHANNELS_2_0_2_0 = 16;

	public static final int FOUR_CHANNELS_2_2 = 32;

	public static final int FOUR_CHANNELS_3_1 = 64;

	public static final int FIVE_CHANNELS_3_0_2_0 = 128;

	public static final int FIVE_CHANNELS_3_2 = 256;

	public int getSupportedAudioLayers();

	public int getSupportedSamplingRates();

	public int getSupportedChannelLayouts();

	public boolean isLowFrequencyChannelSupported();

	public boolean isMultilingualModeSupported();

	public int setAudioLayer(int audioLayer);

	public int getAudioLayer();

	public int setChannelLayout(int channelLayout);

	public int getChannelLayout();

	public boolean setLowFrequencyChannel(boolean on);

	public boolean getLowFrequencyChannel();

	public boolean setMultilingualMode(boolean on);

	public boolean getMultilingualMode();
}
