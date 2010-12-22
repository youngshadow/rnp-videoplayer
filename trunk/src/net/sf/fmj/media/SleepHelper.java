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

package net.sf.fmj.media;

import java.util.logging.Logger;

import net.sf.fmj.utility.LoggerSingleton;

/**
 * Helps calculate and sleep based on buffer timestamps; useful for rendering
 * media at the correct rate.
 * TODO: it is strange that we use this in the RendererNode (which calls the Renderer), 
 * and in the RTPSyncBufferMux (but not in the MuxNode).
 * This implies that Multiplexers are responsible for timing but not Renderers.
 * This is perhaps a reasonable assumption, but needs to be checked against JMF.
 * 
 *
 */
public class SleepHelper 
{
	private static final Logger logger = LoggerSingleton.logger;

	private long mStart;
	private long tbStart;
	private boolean firstNonDiscard = true;
	
    /**
     * The number of nanoseconds in a millisecond.
     */
    public static long MILLI_TO_NANO = 1000000L;

	public void reset()
	{
		mStart = 0;
		tbStart = 0;
		firstNonDiscard = true;
	}
	
	public void sleep(long currentTimestamp) throws InterruptedException
	{
		final long sleep = calculateSleep(currentTimestamp);
        if (sleep > 0)
        {
        	logger.fine("Sleeping " + sleep);
            //  Sleep until the next frame
        	// TODO: the way sleeping should really work is that we start processing the next frame while sleeping on the current one (double buffer the filter graph)
            // TODO: where does JMF sleep?  It does not appear that the renderer does any sleeping.
        	Thread.sleep(sleep);
//				 TODO: I think the correct way to do this is to check the time when we wake up and re-sleep if awoken prematurely.
			         
        }
        
	}
	
	public long calculateSleep(long currentTimestamp)
	{
		final long tbNow = System.currentTimeMillis();

		// we don't want the first timestamp to be from a discarded frame.
		if (firstNonDiscard)
		{	mStart = currentTimestamp;
			tbStart = tbNow;
			firstNonDiscard = false;
			return 0;
		}
		final long bufferTimeStamp = currentTimestamp;
		final float rate = 1;	// TODO: support rates (controller/player rate)
		
		final long mTarget = bufferTimeStamp;	// TODO: negative rates?
		//logger.fine("bufferTimeStamp=" + bufferTimeStamp + " tbNow=" + tbNow);
		final long tbTarget = (long)((double) (mTarget - mStart)/((double) rate * (double) MILLI_TO_NANO)) + tbStart;
		final long sleep = tbTarget - tbNow;
		return sleep;	
				
	}
}
