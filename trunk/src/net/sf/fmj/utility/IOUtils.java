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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * 
 *
 */
public class IOUtils
{
	private IOUtils()
	{	super();
	}
	
	private static final int BUFFER_SIZE = 2048;

	public static void copyFile(String fileIn, String fileOut) throws IOException
	{	copyFile(new File(fileIn), new File(fileOut));
	}
	
	public static void copyFile(File fileIn, File fileOut) throws IOException
	{
		InputStream is = new FileInputStream(fileIn);
		OutputStream os = new FileOutputStream(fileOut);
		copyStream(is, os);
		is.close();
		os.close();
	}
	
	public static void copyStream(InputStream is, OutputStream os) throws IOException
	{
		byte[] buf = new byte[BUFFER_SIZE];
		while (true)
		{
			int len = is.read(buf);
			if (len == -1)
				return;
			os.write(buf, 0, len);
		}
	}
	/** Closes is when finished. */
	public static byte[] readAll(InputStream is) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		IOUtils.copyStream(is, baos);
		is.close();
		return baos.toByteArray();
	}
	/** TODO: only handles standard ASCII. */
	public static String readAll(java.io.Reader reader) throws IOException
	{
		final StringBuilder b = new StringBuilder();
		while (true)
		{
			int c = reader.read();
			if (c == -1)
				break;
			b.append((char) c);
		}
		return b.toString();
	}	

	/** Assumes default file encoding. */
	public static String readAllToString(java.io.InputStream is) throws IOException
	{
		byte[] b = readAll(is);
		return new String(b);
		
	}	
	public static String readAllToString(java.io.InputStream is, String encoding) throws IOException
	{
		byte[] b = readAll(is);
		return new String(b, encoding);
		
	}	
	
	/** Assumes default file encoding. */
	public static void readAllToStringBuffer(java.io.InputStream is, StringBuffer b) throws IOException
	{
		b.append(readAllToString(is));
	}	

	public static void readAllToStringBuffer(java.io.InputStream is, String encoding, StringBuffer b) throws IOException
	{
		b.append(readAllToString(is, encoding));
	}	
	
	/** Assumes default file encoding. */
	public static void readAllToStringBuilder(java.io.InputStream is, StringBuilder b) throws IOException
	{
		b.append(readAllToString(is));
	}	

	public static void readAllToStringBuilder(java.io.InputStream is, String encoding, StringBuilder b) throws IOException
	{
		b.append(readAllToString(is, encoding));
	}	
	
	public static void writeStringToFile(String value, String path) throws IOException
	{
		final FileOutputStream fos = new FileOutputStream(path);
		fos.write(value.getBytes());
		fos.close();
	}
}
