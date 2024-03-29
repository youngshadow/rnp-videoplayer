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

package net.sf.fmj.ui.registry;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.sf.fmj.media.cdp.GlobalCaptureDevicePlugger;

/**
 * Settings for capture devices.
 * TODO provide option to automatically detect capture devices - provide itime interval for polling.
 * 
 * @author Warren Bloomer
 * 
 *
 */
public class CaptureDevicePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * This is the default constructor
	 */
	public CaptureDevicePanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
		
		final PluginsPanel p = new PluginsPanel(EntryPanel.TYPE_CAPTURE_DEVICE);
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.BOTH;
		c1.weightx = 1.0;
		c1.weighty = 1.0;
		c1.gridwidth = GridBagConstraints.REMAINDER;
		//c1.insets = new Insets(0, 2, 0, 2);
		this.add(p, c1);
		JButton buttonDetect = new JButton("Detect Capture Devices");
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.anchor = GridBagConstraints.WEST;
		
		this.add(buttonDetect, c2);
		buttonDetect.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO: this updates the CaptureDeviceManager, when really, it
				// should just add stuff to the GUI's list.
				GlobalCaptureDevicePlugger.addCaptureDevices();	

				p.getEntryPanel().load();
				
			}
			
		});
	}

}
