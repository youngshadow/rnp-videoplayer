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

package net.java.sip.communicator.impl.media.codec.audio;

/**
 * @author Damian Minkov
 */
public class Utils
{
    /**
     * Converts a byte array into a short array. Since a byte is 8-bits,
     * and a short is 16-bits, the returned short array will be half in
     * length than the byte array. If the length of the byte array is odd,
     * the length of the short array will be
     * <code>(byteArray.length - 1)/2</code>, i.e., the last byte is
     * discarded.
     *
     * @param byteArray a byte array
     * @param offset which byte to start from
     * @param length how many bytes to convert
     *
     * @return a short array, or <code>null</code> if byteArray is of zero
     *    length
     *
     * @throws java.lang.ArrayIndexOutOfBoundsException
     */
    public static short[] byteToShortArray
        (byte[] byteArray, int offset, int length, boolean little)
        throws ArrayIndexOutOfBoundsException
    {

        if (0 < length && (offset + length) <= byteArray.length)
        {
            int shortLength = length / 2;
            short[] shortArray = new short[shortLength];
            int temp;
            for (int i = offset, j = 0; j < shortLength;
                 j++, temp = 0x00000000)
            {
                if(little)
                {
                    temp = byteArray[i++] & 0x000000FF;
                    temp |= 0x0000FF00 & (byteArray[i++] << 8);
                }
                else
                {
                    temp = byteArray[i++] << 8;
                    temp |= 0x000000FF & byteArray[i++];
                }

                shortArray[j] = (short) temp;
            }
            return shortArray;
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException
                ("offset: " + offset + ", length: " + length
                 + ", array length: " + byteArray.length);
        }
    }

    /**
     * The result array must be twice as the input one. Since a byte is 8-bits,
     * and a short is 16-bits.
     * @param in short[]
     * @param res byte[]
     * @param little boolean
     * @return byte[]
     */
    public static void shortArrToByteArr(short[] in, byte[] res, boolean little)
    {
        int resIx = 0;

        byte[] tmp = null;
        for (int i = 0; i < in.length; i++)
        {
            tmp = shortToBytes(in[i], little);
            res[resIx++] = tmp[0];
            res[resIx++] = tmp[1];
        }
    }

    /**
     * Get a pair of bytes representing a short value.
     * @param v short
     * @param little boolean
     * @return byte[]
     */
    public static byte[] shortToBytes(short v, boolean little)
    {
        byte[] rtn = new byte[2];
        if (little)
        {
            rtn[0] = (byte) (v & 0xff);
            rtn[1] = (byte) ( (v >>> 8) & 0xff);
        }
        else
        {
            rtn[0] = (byte) ( (v >>> 8) & 0xff);
            rtn[1] = (byte) (v & 0xff);
        }
        return rtn;
    }
}
