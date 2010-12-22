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

/**
 * Complete.
 * @author Ken Larson
 *
 */
public class EncryptionInfo implements java.io.Serializable
{

	private int type;

	private byte[] key;

	public static final int NO_ENCRYPTION = 0;

	public static final int XOR = 1;

	public static final int MD5 = 2;

	public static final int DES = 3;

	public static final int TRIPLE_DES = 4;

	public EncryptionInfo(int type, byte[] key)
	{
		this.type = type;
		this.key = key;
	}

	public int getType()
	{
		return type;
	}

	public byte[] getKey()
	{
		return key;
	}
}
