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

package net.sf.fmj.media.datasink.rtp;

/**
 * Parsed RTP URL.  Contains an array of ParsedRTPUrlElement, each of which corresponds to 
 * a subcomponent of the URL (audio or video).  
 * 
 *
 */
public class ParsedRTPUrl
{
	public final ParsedRTPUrlElement[] elements;
	
	public ParsedRTPUrl(final ParsedRTPUrlElement[] elements)
	{
		super();
		this.elements = elements;
	}
	
	public ParsedRTPUrl(ParsedRTPUrlElement e)
	{	this(new ParsedRTPUrlElement[] {e});
	}

	public ParsedRTPUrl(ParsedRTPUrlElement e, ParsedRTPUrlElement e2)
	{	this(new ParsedRTPUrlElement[] {e, e2});
	}
	
	public ParsedRTPUrlElement find(String type)
	{
		for (int i = 0; i < elements.length; ++i)
		{
			if (elements[i].type.equals(type))
				return elements[i];
		}
		return null;
	}
	
	public String toString()
	{
		if (elements == null)
			return "null";
		
		StringBuffer b = new StringBuffer();
		b.append("rtp://");
		
		for (int i = 0; i < elements.length; ++i)
		{
			if (i > 0)
				b.append("&");
			b.append(elements[i]);
		}
		return b.toString();
	}
}
