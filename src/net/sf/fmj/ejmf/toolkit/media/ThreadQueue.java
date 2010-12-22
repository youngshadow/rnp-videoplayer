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

package net.sf.fmj.ejmf.toolkit.media;

import java.util.Vector;

/**
 * The ThreadQueue class provides a mechanism to run threads
 * serially.  When a thread is added, it will be started as soon
 * as all threads added before it have completed.
 *
 * From the book: Essential JMF, Gordon, Talley (ISBN 0130801046).  Used with permission.
 * 
 * @author     Steve Talley & Rob Gordon
 */
public class ThreadQueue extends Thread {
    private Thread running;
    private Vector queue = new Vector();

    /**
     * Constructs a ThreadQueue.
     */
    public ThreadQueue(String threadName) {
        super();
        setName(threadName);
        setDaemon(true);
    }

    /**
     * Monitor the thread queue.  When a thread is added, start
     * it and block until it finishes.
     * <p>
     * This method is called when the thread is started.  It
     * should not be called directly.
     */
    public void run() {
        while(true) {
            synchronized(this) {
                while( queue.size() == 0 ) {
                    try {
                        wait();
                    } catch(InterruptedException e) {}
                }
                running = (Thread)queue.elementAt(0);
                queue.removeElementAt(0);
            }

            //  Start thread
            running.start();

            //  Block until it finishes
            while(true) {
                try {
                    running.join();
                    break;
                }
                catch(InterruptedException e) {}
            }
        }
    }
    
    /**
     * Add a thread to this ThreadQueue.
     *
     * @param      t
     *             The Thread to add.
     */
    public synchronized void addThread(Thread t) {
        queue.addElement(t);
        notify();
    }

    /**
     * Stop the currently running thread and any threads
     * queued to run.  Remove all threads from the queue.
     */
    public synchronized void stopThreads() {
        if( running != null ) {
            running.stop();
        }

        for(int i = 0, n = queue.size(); i < n; i++) {
            Thread t = (Thread)queue.elementAt(i);
            t.stop();
        }

        queue.removeAllElements();
    }
}
