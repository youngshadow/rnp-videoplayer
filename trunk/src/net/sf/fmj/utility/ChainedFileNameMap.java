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
import java.util.ArrayList;
import java.util.List;

/**
 * Allows the creation of a FileNameMap using a list of FileNameMap.
 * This allows us to extend the default FileNameMap used by URLDataSource, for example, 
 * to add missing mappings, without editing content-types.properties.
 * 
 *
 */
public class ChainedFileNameMap implements FileNameMap
{

	private final List maps = new ArrayList();	// List of FileNameMap

	public ChainedFileNameMap(FileNameMap m1, FileNameMap m2)
	{
		maps.add(m1);
		maps.add(m2);
	}
	
	public String getContentTypeFor(String fileName)
	{
		for (int i = 0; i < maps.size(); ++i)
		{
			final FileNameMap m = (FileNameMap) maps.get(i);
			final String result = m.getContentTypeFor(fileName);
			if (result != null)
				return result;
		}
		return null;
	}
	
	
}
