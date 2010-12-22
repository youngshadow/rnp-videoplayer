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

import java.net.FileNameMap;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Used to add mappings that are not in content-types.properties
 * 
 *
 */
public class CustomFileNameMap implements FileNameMap
{
	// The "real" one is accessed using URLConnection.getFileNameMap().
	// This class can be used together with the "real" to make one fill in the defaults for/override the other.
	// then use URLConnection.setFileNameMap().
	

	
	private final Map map = new HashMap();

	public void map(String[] exts, String type)
	{
		for (int i = 0; i < exts.length; ++i)
		{
			map(exts[i], type);
		}
	}
	public void map(String ext, String type)
	{
		if (ext.startsWith("."))
			throw new IllegalArgumentException("Extension should not include dot");
		
		map.put(ext.toLowerCase(), type);
	}
	
	public String getType(String ext)
	{
		if (ext == null)
			return null;
		return (String) map.get(ext.toLowerCase());
	}
	
	public String getContentTypeFor(String fileName)
	{
		return getType(PathUtils.extractExtension(fileName));
	}
}
