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

package net.sf.fmj.filtergraph;

/**
 * A link to a destination node, specifying a specific track at the
 * dest.  Track is only relevant for a mux.
 * 
 *
 */
public class FilterGraphLink
{
	private FilterGraphNode destNode;	// may not be null.
	private final int destTrack;
	
	public FilterGraphLink(FilterGraphNode destNode, final int destTrack)
	{
		super();
		if (destNode == null)
			throw new NullPointerException();
		if (destNode instanceof MuxNode && destTrack < 0)
			throw new IllegalArgumentException();
		this.destNode = destNode;
		this.destTrack = destTrack;
	}
	
	public FilterGraphLink(FilterGraphNode destNode)
	{
		super();
		if (destNode == null)
			throw new NullPointerException();
		if (destNode instanceof MuxNode)
			throw new IllegalArgumentException();
		this.destNode = destNode;
		this.destTrack = -1;
	}

	public FilterGraphNode getDestNode()
	{
		return destNode;
	}

	public int getDestTrack()
	{
		return destTrack;
	}
}
