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
 * Complete.

 *
 */
public class TransitionEvent extends ControllerEvent
{
	int previousState;
	int currentState;
	int targetState;
	
	public TransitionEvent(Controller from,
            int previousState,
            int currentState,
            int targetState)
	{	super(from);
		this.previousState = previousState;
		this.currentState = currentState;
		this.targetState = targetState;
	}
	
	public int getPreviousState()
	{	return previousState;
	}
	
	public int getCurrentState()
	{	return currentState;
	}
	
	public int getTargetState()
	{
		return targetState;
	}
	
	public String toString()
	{	return getClass().getName() + "[source=" + getSource() + ",previousState=" + previousState + 
			",currentState=" + currentState + ",targetState=" + targetState +"]";
	}
}
