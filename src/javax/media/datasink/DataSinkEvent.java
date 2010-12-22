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

package javax.media.datasink;

import javax.media.DataSink;
import javax.media.MediaEvent;

/**
 * Complete.
 * @author Ken Larson
 *
 */
public class DataSinkEvent extends MediaEvent
{
	private String message;
	
	public DataSinkEvent(DataSink from)
	{	super(from);
		this.message = "";
	}
	
	public DataSinkEvent(DataSink from, String reason)
	{	super(from);
		this.message = reason;
	}
	
	public DataSink getSourceDataSink()
	{	return (DataSink) getSource();
	}
	
	public String toString()
	{	return DataSinkEvent.class.getName() + "[source=" + getSource() + "] message: " + message;
	}
}
