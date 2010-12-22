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

package net.sf.fmj.ui.control;

/**
 * 
 * 
 *
 */
public class TransportControlState
{
	private boolean allowStop;
	private boolean allowPlay;
	private boolean allowVolume;
	
	public boolean isAllowPlay()
	{
		return allowPlay;
	}
	public void setAllowPlay(boolean allowPlay)
	{
		this.allowPlay = allowPlay;
	}
	public boolean isAllowStop()
	{
		return allowStop;
	}
	public void setAllowStop(boolean allowStop)
	{
		this.allowStop = allowStop;
	}
	public boolean isAllowVolume()
	{
		return allowVolume;
	}
	public void setAllowVolume(boolean allowVolume)
	{
		this.allowVolume = allowVolume;
	}
	
}
