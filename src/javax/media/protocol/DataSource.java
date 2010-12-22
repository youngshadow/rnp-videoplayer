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

package javax.media.protocol;

import javax.media.Duration;
import javax.media.MediaLocator;
import javax.media.Time;

/**
 * Coding Complete.
 * @author Ken Larson
 *
 */
public abstract class DataSource implements Controls, Duration
{
	private MediaLocator locator;
	
	public DataSource()
	{
		
	}

	public DataSource(MediaLocator source)
	{
		this.locator = source;
	}

	public void setLocator(MediaLocator source)
	{
		this.locator = source;
	}

	public MediaLocator getLocator()
	{
		return locator;
	}

	protected void initCheck()
	{
		if (locator == null)
			throw new Error("Uninitialized DataSource error.");	// JavaDoc claims this should be UninitializedError(), but this is not the case.;
		
	} 

	public abstract String getContentType();

	public abstract void connect() throws java.io.IOException;

	public abstract void disconnect();

	public abstract void start() throws java.io.IOException;

	public abstract void stop() throws java.io.IOException;

	public abstract Object getControl(String controlType);

	public abstract Object[] getControls();

	public abstract Time getDuration();

}
