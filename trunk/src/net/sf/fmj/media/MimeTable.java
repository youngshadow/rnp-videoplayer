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

package net.sf.fmj.media;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 
 * 
 *
 */
public class MimeTable
{

	private final Hashtable<String, String> hashTable = new Hashtable<String, String>();
	private static final Hashtable<String, String> reverseHashTable = new Hashtable<String, String>();

	
	public boolean addMimeType(String fileExtension, String mimeType)
	{	
		hashTable.put(fileExtension, mimeType);
		reverseHashTable.put(mimeType, fileExtension);
		return true;
	}
	public boolean removeMimeType(String fileExtension)
	{	
		if (hashTable.get(fileExtension) == null)
			return false;
		reverseHashTable.remove(hashTable.get(fileExtension));
		hashTable.remove(fileExtension);
		return true;

	}
	public String getMimeType(String fileExtension)
	{	
		final String result = (String) hashTable.get(fileExtension);
		return result;
	}
	public Hashtable<String, String> getMimeTable()
	{	final Hashtable<String, String> result = new Hashtable<String, String>();
		result.putAll(hashTable);
		return result;
	}

	public String getDefaultExtension(String mimeType)
	{	
		return (String) reverseHashTable.get(mimeType);
		
		
	}
	
	public List<String> getExtensions(String mimeType)
	{
		final List<String> result = new ArrayList<String>();
		final Iterator<String> i = hashTable.keySet().iterator();
		while (i.hasNext())
		{
			String k = i.next();
			if (hashTable.get(k).equals(mimeType))
				result.add(k);
		}
		return result;
		
	}
	
	public Set<String> getMimeTypes()
	{
		final Set<String> result = new HashSet<String>();
		final Iterator<String> i = hashTable.values().iterator();
		while (i.hasNext())
		{
			result.add(i.next());
		}
		return result;
	}

	public void clear()
	{
		hashTable.clear();
		reverseHashTable.clear();
	}
	
}
