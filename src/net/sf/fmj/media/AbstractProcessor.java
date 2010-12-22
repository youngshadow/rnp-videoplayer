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

import javax.media.ConfigureCompleteEvent;
import javax.media.NotConfiguredError;
import javax.media.Processor;
import javax.media.control.TrackControl;
import javax.media.protocol.ContentDescriptor;

import net.sf.fmj.ejmf.toolkit.media.AbstractPlayer;

/**
 * 
 * 
 *
 */
public abstract class AbstractProcessor extends AbstractPlayer implements Processor
{

	// configure, synchronousConfigure, doConfigure modeled after realize, etc, in AbstractController.
	
	public void configure()
	{
        //  Has this state already been reached?
        if( getState() >= Configured ) {
            postConfigureCompleteEvent();
            return;
        }

        //  Set the target state
        if( getTargetState() < Configured ) {
            setTargetState(Configured);
        }

        //  Realize on a separate thread
        Thread thread = new Thread() {
            public void run() {
                if( AbstractProcessor.this.getState() < Configured ) {
                    synchronousConfigure();
                }
            }
        };

        getThreadQueue().addThread(thread);
	}
	
    protected void synchronousConfigure() {
        //  Set the current state and post event
        setState( Configuring );
        postTransitionEvent();

        //  Do the actual realizing
        if( doConfigure() ) {

            //  The realize was successful

            //  Set the current state and post event
            setState( Configured );
            postConfigureCompleteEvent();


        } else {

            //  The Configure was unsuccessful
            //  Rely on the Controller to post the
            //  ControllerErrorEvent

            //  Reset the current and target states
            setState( Unrealized );
            setTargetState( Unrealized );
        }
    }
    
    public abstract boolean doConfigure();

    protected void postConfigureCompleteEvent() {
        postEvent( new ConfigureCompleteEvent(
            this, getPreviousState(), getState(), getTargetState()) );
    }
    
	public ContentDescriptor getContentDescriptor() throws NotConfiguredError
	{
		if (getState() < Configured)
			throw new NotConfiguredError("Cannot call getContentDescriptor on an unconfigured Processor.");
		
        return outputContentDescriptor;
	}

//	public DataSource getDataOutput() throws NotRealizedError
//	{
//		if (getState() < Realized)
//			throw new NotRealizedError("Cannot call getDataOutput on an unrealized Processor.");
//
//		return null;
//	}

	public ContentDescriptor[] getSupportedContentDescriptors() throws NotConfiguredError
	{
		if (getState() < Configured)
			throw new NotConfiguredError("Cannot call getSupportedContentDescriptors on an unconfigured Processor.");

		return null;
	}

	public TrackControl[] getTrackControls() throws NotConfiguredError
	{
		if (getState() < Configured)
			throw new NotConfiguredError("Cannot call getTrackControls on an unconfigured Processor.");

		return null;
	}

	protected ContentDescriptor outputContentDescriptor;
	public ContentDescriptor setContentDescriptor(ContentDescriptor outputContentDescriptor) throws NotConfiguredError
	{
		// TODO: check that it matches a supported content descriptor
		this.outputContentDescriptor = outputContentDescriptor;
		return outputContentDescriptor;
	}

}
