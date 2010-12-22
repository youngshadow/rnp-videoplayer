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

/**
 * 
 * Complete.
 * Note: JMF will call close if open() fails.  Observed on a Demultiplexer.n
 *
 */
public interface PlugIn extends Controls
{
	public static int BUFFER_PROCESSED_OK = 0;
	public static int BUFFER_PROCESSED_FAILED = 1;
	public static int INPUT_BUFFER_NOT_CONSUMED = 2;
	public static int OUTPUT_BUFFER_NOT_FILLED = 4;
	public static int PLUGIN_TERMINATED = 8;

	public void open() throws ResourceUnavailableException;
	public void close();
	public String getName();
	public void reset();

}
