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

import java.io.File;

/**
 * 
 * 
 *
 */
public final class URLUtils 
{
	private URLUtils()
	{	super();
	}
	
	/** returns null if file does not exist. */
	public static String createAbsoluteFileUrl(String urlStr)
	{
		final String path = URLUtils.extractValidPathFromFileUrl(urlStr);
		if (path == null)
			return null;
		return URLUtils.createUrlStr(new File(path));

	}
	
	public static String createUrlStr(File file)
	{
		String path = file.getAbsolutePath();
		final String prefix;
		if (path.startsWith("/"))
			prefix = "file://";
		else
			prefix = "file:///";	// handles windows, where the path might might be C:\myfile.txt, and what we need is file:///C:\myfile.txt

		if (OSUtils.isWindows())
		{	path = path.replaceAll("\\\\", "/");
		}
		
		return prefix + StringUtils.replaceSpecialUrlChars(path, true);
	}
	
	/**
	 * Handles the various violations of the file URL format that are commonplace.
	 * For example, file://home/ken/foo.txt really refers to the relative path 
	 * home/ken/foo.txt, not /home/ken/foo.txt.  But we need to be able to handle it.
	 * Assumes the local file system, checks for existance for help.
	 * @param url
	 * @return the file path, null if no valid path found.
	 */
	public static String extractValidPathFromFileUrl(String url)
	{
		return extractValidPathFromFileUrl(url, false);
	}
	
	/**
	 * same as extractValidPathFromFileUrl, but checks
	 * if parent dir is valid, not file itself.  Intended for new files about to be created.
	 */
	public static String extractValidNewFilePathFromFileUrl(String url)
	{
		return extractValidPathFromFileUrl(url, true);

	}
	
	private static boolean exists(String path, boolean checkParentDirOnly)
	{
		if (checkParentDirOnly)
			return new File(path).getParentFile().exists();
		else
			return new File(path).exists();
		
	}
	
	// TODO: Java thinks that /C:\foo.txt is a valid path on windows
	private static String extractValidPathFromFileUrl(String url, boolean checkParentDirOnly)
	{
		if (!url.startsWith("file:"))
			return null;
		String remainder = url.substring("file:".length());
		
		remainder = StringUtils.restoreSpecialURLChars(remainder);
		
		if (!remainder.startsWith("/"))
			return remainder;
		
		// first, try the exact value, as it should be
		if (remainder.startsWith("//"))
		{
			String result = remainder.substring(2);
			if (exists(result, checkParentDirOnly))
				return result;
		}
		
		// just start pulling / of the front until we find something
		String result = remainder;

		// no need for two slashes on a real path:
		
		while (result.startsWith("//"))
			result = result.substring(1);
		
		if (exists(result, checkParentDirOnly))
			return result;
		
		while (result.startsWith("/"))
		{
			result = result.substring(1);
			if (exists(result, checkParentDirOnly))
				return result;
		}
		
		return null;
		
	}
	

}
