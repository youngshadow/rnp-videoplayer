/*
 * @(#)RTPHeader.java
 * Created: 2005-04-21
 * Version: 2-0-alpha
 * Copyright (c) 2005-2006, University of Manchester All rights reserved. 
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials 
 * provided with the distribution. Neither the name of the University of 
 * Manchester nor the names of its contributors may be used to endorse or 
 * promote products derived from this software without specific prior written
 * permission. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

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

package net.sf.fmj.media.rtp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;

/**
 * Represents the header of an RTP packet
 * 
 * @author Andrew G D Rowley
 * @version 1-1-alpha
 */
public class RTPHeader {

    /**
     * The current RTP Header Version
     */
    public static final int VERSION = 2;

    /**
     * The maximum payload type
     */
    public static final int MAX_PAYLOAD = 127;

    /**
     * The size of the RTP Header
     */
    public static final int SIZE = 12;

    /**
     * The maximum RTP sequence
     */
    public static final int MAX_SEQUENCE = 65535;

    /**
     * Unsigned int to long conversion mask
     */
    public static final long UINT_TO_LONG_CONVERT = 0x00000000ffffffffL;

    // Header bit masks and shifts
    private static final int VERSION_MASK = 0xc000;

    private static final int VERSION_SHIFT = 14;

    private static final int PADDING_MASK = 0x2000;

    private static final int PADDING_SHIFT = 13;

    private static final int EXTENSION_MASK = 0x1000;

    private static final int EXTENSION_SHIFT = 12;

    private static final int CSRC_MASK = 0x0f00;

    private static final int CSRC_SHIFT = 8;

    private static final int MARKER_MASK = 0x0080;

    private static final int MARKER_SHIFT = 7;

    private static final int TYPE_MASK = 0x007f;

    private static final int TYPE_SHIFT = 0;

    // The first 16 bits of the header
    private int flags;

    // The second 16 bits of the header
    private int sequence;

    // The third and fourth 16 bits of the header
    private long timestamp;

    // The fifth and sixth 16 bits of the header
    private long ssrc;

    /**
     * Creates a new RTPHeader
     * 
     * @param packet
     *            The packet to parse the header from
     * @throws IOException
     */
    public RTPHeader(DatagramPacket packet) throws IOException {
        this(packet.getData(), packet.getOffset(), packet.getLength());
    }

    /**
     * Creates a new RTPHeader
     * 
     * @param data
     *            The packet to parse the header from
     * @throws IOException
     */
    public RTPHeader(byte[] data, int offset, int length) throws IOException {
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(
                data, offset, length));

        // Read the header values
        this.flags = stream.readUnsignedShort();
        this.sequence = stream.readUnsignedShort();
        this.timestamp = stream.readInt() & UINT_TO_LONG_CONVERT;
        this.ssrc = stream.readInt() & UINT_TO_LONG_CONVERT;
        
        if (getVersion() != VERSION) {
            throw new IOException("Invalid Version");
        }
    }

    /**
     * @return The flags of the header
     */
    public int getFlags() {
        return flags;
    }

    /**
     * @return The RTP version implemented
     */
    short getVersion() {
        return (short) ((getFlags() & VERSION_MASK) >> VERSION_SHIFT);
    }

    /**
     * @return The padding in the data of the packet
     */
    short getPadding() {
        return (short) ((getFlags() & PADDING_MASK) >> PADDING_SHIFT);
    }

    /**
     * @return Any extension to the header
     */
    short getExtension() {
        return (short) ((getFlags() & EXTENSION_MASK) >> EXTENSION_SHIFT);
    }

    /**
     * @return A count of Csrcs in the packet
     */
    short getCsrcCount() {
        return (short) ((getFlags() & CSRC_MASK) >> CSRC_SHIFT);
    }

    /**
     * @return The marker of the packet
     */
    short getMarker() {
        return (short) ((getFlags() & MARKER_MASK) >> MARKER_SHIFT);
    }

    /**
     * @return The type of the data in the packet
     */
    short getPacketType() {
        return (short) ((getFlags() & TYPE_MASK) >> TYPE_SHIFT);
    }

    /**
     * @return The sequence number of the packet
     */
    int getSequence() {
        return sequence;
    }

    /**
     * @return The timestamp of the packet
     */
    long getTimestamp() {
        return timestamp;
    }

    /**
     * @return The ssrc of the data source
     */
    long getSsrc() {
        return ssrc;
    }
    
    int getSize() {
        return SIZE + (getCsrcCount() * 4); 
    }
    
    /**
     * Prints the header
     *
     */
    public void print() {
        System.err.println(getVersion() + "|" + getPadding() + "|" +
                getExtension() + "|" + getCsrcCount() + "|" + getMarker() + 
                "|" + getPacketType() + "|" + getSequence() + "|" + 
                getTimestamp() + "|" + getSsrc());
    }
}