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

package javax.media.rtp.event;

import javax.media.rtp.Participant;
import javax.media.rtp.ReceiveStream;
import javax.media.rtp.SessionManager;

/**
 * Complete.
 * 
 *
 */
public class ApplicationEvent extends ReceiveStreamEvent
{
	private int appSubtype;

	private String appString;

	private byte[] appData;

	public ApplicationEvent(SessionManager from, Participant participant,
			ReceiveStream recvStream, int appSubtype,
			String appString, byte[] appData)
	{
		super(from, recvStream, participant);
		this.appSubtype = appSubtype;
		this.appString = appString;
		this.appData = appData;
	}

	public int getAppSubType()
	{
		return appSubtype;
	}

	public String getAppString()
	{
		return appString;
	}

	public byte[] getAppData()
	{
		return appData;
	}

}
