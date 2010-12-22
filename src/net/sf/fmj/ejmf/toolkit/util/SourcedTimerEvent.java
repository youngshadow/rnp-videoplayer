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

package net.sf.fmj.ejmf.toolkit.util;

import java.util.EventObject;

/**
 * This event is sent by a SourcedTimer to its listeners in response
 * from a 'tick' by the SourcedTimer's base timer.
 *
 * From the book: Essential JMF, Gordon, Talley (ISBN 0130801046).  Used with permission.
 *
 * see            ejmf.toolkit.SourcedTimer
 * see		   ejmf.toolkit.TimeSource
 * @version        1.0
 * @author         Rob Gordon & Steve Talley
 */
public class SourcedTimerEvent extends EventObject {

    private long time;

    /**
     * Event constructor. 
     *
     * @param          src
     *                 Source of event.
     *
     * @param          t
     *                 Time in units of source.
     */
    public SourcedTimerEvent(Object src, long t) {
	super(src);
	time = t;
    }
    /**
     * Retrieve the time from the event Object.
     *
     * @return         Time in units of time source. 
     */
    public long getTime() {
	return time;
    }
    /**
     * Set the time of event object. This operation for a client
     * unless it happens to re-generated event for its own purposes.
     *
     * @param          t
     *                 Time in units detemined by caller.
     */
    public void setTime(long t) {
	time = t;
    }
}

