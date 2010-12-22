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


package javax.media;

import javax.media.datasink.DataSinkListener;

/**
 * Complete.
 * 
 * 
 */
public interface DataSink extends MediaHandler, Controls
{
	public void setOutputLocator(MediaLocator output);

	public MediaLocator getOutputLocator();

	public void start() throws java.io.IOException;

	public void stop() throws java.io.IOException;

	public void open() throws java.io.IOException, SecurityException;

	public void close();

	public String getContentType();

	public void addDataSinkListener(DataSinkListener listener);

	public void removeDataSinkListener(DataSinkListener listener);
}
