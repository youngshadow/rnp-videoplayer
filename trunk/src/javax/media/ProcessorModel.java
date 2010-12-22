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

import javax.media.protocol.ContentDescriptor;
import javax.media.protocol.DataSource;

/**
 * Coding complete.
 *
 */
public class ProcessorModel
{	
	private MediaLocator inputLocator;
	/**
	 * Output formats.
	 */
	private Format[] formats;
	private ContentDescriptor outputContentDescriptor;
	private DataSource inputDataSource;
	
	public ProcessorModel()
	{	
	}

	public ProcessorModel(Format[] formats,
			ContentDescriptor outputContentDescriptor)
	{	this.formats = formats;
		this.outputContentDescriptor = outputContentDescriptor;
	}

	public ProcessorModel(DataSource inputDataSource, Format[] formats,
			ContentDescriptor outputContentDescriptor)
	{	
		this.inputDataSource = inputDataSource;
		
		this.formats = formats;
		this.outputContentDescriptor = outputContentDescriptor;
	}

	public ProcessorModel(MediaLocator inputLocator, Format[] formats,
			ContentDescriptor outputContentDescriptor)
	{	
		this.inputLocator = inputLocator;
		this.formats = formats;
		this.outputContentDescriptor = outputContentDescriptor;

	}

	public int getTrackCount(int availableTrackCount)
	{	
		if (formats == null)
			return -1;
		return formats.length;	// TODO: what is input param for?
	}

	public Format getOutputTrackFormat(int tIndex)
	{	
		if (formats == null)
			return null;
		if (tIndex < 0 || tIndex >= formats.length)
			return null;

		return formats[tIndex];
	}

	public boolean isFormatAcceptable(int tIndex, Format tFormat)
	{	
		if (formats == null)
			return true;
		if (tIndex < 0 || tIndex >= formats.length)
			return true;
		
		return tFormat.matches(formats[tIndex]);	

	}

	public ContentDescriptor getContentDescriptor()
	{	return outputContentDescriptor;
	}

	public DataSource getInputDataSource()
	{	return inputDataSource;
	}

	public MediaLocator getInputLocator()
	{	
//		if (inputDataSource != null)
//			return inputDataSource.getLocator();	// TODO: it appears we return null if a data source was specified.
		return inputLocator;
	}

}
