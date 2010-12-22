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

package net.sf.fmj.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.sf.fmj.codegen.CGUtils;

/**
 * Utilities for serializing/de-serializing objects using standard Java serialization.
 * 
 *
 */
public class SerializationUtils
{
	public static String serialize(javax.media.Format f) throws IOException
	{
		final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		final ObjectOutputStream output = new ObjectOutputStream( buffer );
	    output.writeObject(f);
	    output.close();
	    buffer.close();
	    return CGUtils.byteArrayToHexString(buffer.toByteArray());
	
	}
	
	public static javax.media.Format deserialize(String s) throws IOException, ClassNotFoundException
	{
	    final byte[] ba = CGUtils.hexStringToByteArray(s);
	    	final ByteArrayInputStream inbuf = new ByteArrayInputStream(ba);
	    	final ObjectInputStream input = new ObjectInputStream(inbuf);
	    	final Object oRead = input.readObject();
	    	input.close();
	    	inbuf.close();
	    	return (javax.media.Format) oRead;
	    	    
	}
}
