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

package net.sf.fmj.ejmf.toolkit.media.event;

import javax.media.ControllerErrorEvent;
import javax.media.Player;

/**
 * A <code>ManagedControllerErrorEvent</code> indicates that a
 * <code>Controller</code> managed by a Player posted a
 * ControllerErrorEvent and subsequently caused the managing
 * Player to close.
 * 
 * From the book: Essential JMF, Gordon, Talley (ISBN 0130801046).  Used with permission.
 * @author     Steve Talley & Rob Gordon
 */
public class ManagedControllerErrorEvent extends ControllerErrorEvent {
    private ControllerErrorEvent event;

    /**
     * Create a ManagedControllerErrorEvent for the given managing
     * Player and ControllerErrorEvent.
     *
     * @param      manager
     *             The managing Player.
     *
     * @param      event
     *             The original ControllerErrorEvent posted by a
     *             managed Controller.
     */
    public ManagedControllerErrorEvent(
        Player manager,
        ControllerErrorEvent event)
    {
        super(manager);
        this.event = event;
    }

    /**
     * Create a ManagedControllerErrorEvent for the given managing
     * Player, ControllerErrorEvent, and description.
     *
     * @param      manager
     *             The managing Player.
     *
     * @param      event
     *             The original ControllerErrorEvent posted by a
     *             managed Controller.
     *
     * @param      message
     *             A message describing the error.
     */
    public ManagedControllerErrorEvent(
        Player manager,
        ControllerErrorEvent event,
        String message)
    {
        super(manager, message);
        this.event = event;
    }

    /**
     * Get the original ControllerErrorEvent posted by the managed
     * Controller.
     */
    public ControllerErrorEvent getControllerErrorEvent() {
        return event;
    }
}
