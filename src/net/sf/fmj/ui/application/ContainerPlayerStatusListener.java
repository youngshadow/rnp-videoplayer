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

package net.sf.fmj.ui.application;

/**
 * Used to allow PlayerPanel to update its status bar in response to state changes of ContainerPlayer.
 * 
 *
 */
public interface ContainerPlayerStatusListener
{
	public static final String LOADING = "Loading...";

	public static final String CREATE_PLAYER_FAILED = "Error loading media.";
	public static final String REALIZE_COMPLETE = "Ready.";
	public static final String STOPPED = "Stopped.";
	public static final String STARTED = "Playing...";
	public static final String RESOURCE_UNAVAILABLE = "Error loading to media.";
	public static final String ERROR_SHOWING_PLAYER = "Error displaying player.";
	public static final String ERROR_PREFIX = "Error: ";
	public static final String END_OF_MEDIA = "End of media.";
	public static final String PROCESSING = "Processing...";
	
	
	public void onStatusChange(String newStatus);
}
