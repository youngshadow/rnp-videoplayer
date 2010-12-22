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

package javax.media.rtp;

import java.io.IOException;

import javax.media.protocol.PushSourceStream;

/**
 * Complete.
 * @author Ken Larson
 *
 */
public interface RTPConnector
{
	public PushSourceStream getDataInputStream() throws IOException;

	public OutputDataStream getDataOutputStream() throws IOException;

	public PushSourceStream getControlInputStream() throws IOException;

	public OutputDataStream getControlOutputStream() throws IOException;

	public void close();

	public void setReceiveBufferSize(int size) throws IOException;

	public int getReceiveBufferSize();

	public void setSendBufferSize(int size) throws IOException;

	public int getSendBufferSize();

	public double getRTCPBandwidthFraction();

	public double getRTCPSenderBandwidthFraction();

}
