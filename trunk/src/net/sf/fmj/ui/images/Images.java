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

package net.sf.fmj.ui.images;

import java.util.HashMap;

import javax.swing.ImageIcon;

/**
 * 
 * @author Warren Bloomer
 *
 */
public class Images {
	
	public static final String SLIDER_THUMB_HORIZ = "slider_thumb_horiz.png";
	public static final String SLIDER_THUMB_VERT = "slider_thumb_vert.png";

	public static final String MEDIA_PLAY = "Play24.gif";
	public static final String MEDIA_STOP = "Stop24.gif";
	public static final String MEDIA_PAUSE = "Pause24.gif";
	public static final String MEDIA_REWIND = "Rewind24.gif";
	public static final String MEDIA_FASTFORWARD = "FastForward24.gif";
	public static final String MEDIA_STEPBACK = "StepBack24.gif";
	public static final String MEDIA_STEPFORWARD = "StepForward24.gif";
	
	public static ImageIcon get(String name) {
		return singleton.doGet(name);
	}
	
	public static void flush() {
		singleton.doFlush();
	}
	
	private static final Images singleton = new Images();
	
	private static final String basePath = "/net/sf/fmj/ui/images/";
	
	private HashMap images = new HashMap();
	
	private void doFlush() {
		images.clear();
	}

	private ImageIcon doGet(String imageName) {
		ImageIcon icon = (ImageIcon) images.get(imageName);
		if (icon == null) {
			
			icon = new ImageIcon(getClass().getResource(basePath + imageName));
			if (icon != null) {
				images.put(imageName, icon);
			}
		}

		return icon;
	}

}
