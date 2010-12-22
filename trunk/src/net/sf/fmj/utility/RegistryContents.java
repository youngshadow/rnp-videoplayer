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

import java.util.Vector;

import javax.media.CaptureDeviceInfo;

import net.sf.fmj.media.MimeTable;

/**
 * 
 * @author Warren Bloomer
 * 
 *
 */
public class RegistryContents
{
	/** Lists of Plugin for each category */
	Vector<String>[] plugins = new Vector[] {
				new Vector<String>(),
				new Vector<String>(),
				new Vector<String>(),
				new Vector<String>(),
				new Vector<String>(),
		};

	/** a List of protocol prefixes */
	Vector<String> protocolPrefixList = new Vector<String>();	// Vector of String

	/** a list of content prefixes */
	Vector<String> contentPrefixList = new Vector<String>();	// Vector of String
	
	/** the MIME-type map.  It maps file extensions to mime-types. */
	//Hashtable mimetypeMap = new Hashtable();

	/** MIME type map */
	final MimeTable mimeTable = new MimeTable();

	/** a List of protocol prefixes */
	Vector<CaptureDeviceInfo> captureDeviceInfoList = new Vector<CaptureDeviceInfo>();	// Vector of CaptureDeviceInfo
	
}
