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

import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * 
 * @author Warren Bloomer
 *
 */
public class PackagesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private EntryPanel contentPrefixPanel = null;
	private EntryPanel protocolPrefixPanel = null;

	/**
	 * This is the default constructor
	 */
	public PackagesPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setSize(600, 400);
		this.setLayout(new GridLayout(2, 0));
		this.add(getContentPrefixPanel(), null);
		this.add(getProtocolPrefixPanel(), null);
	}

	/**
	 * This method initializes contentPrefixPanel	
	 * 	
	 * @return net.sf.fmj.ui.registry.EntryPanel	
	 */
	private EntryPanel getContentPrefixPanel() {
		if (contentPrefixPanel == null) {
			contentPrefixPanel = new EntryPanel(EntryPanel.TYPE_CONTENT);
		}
		return contentPrefixPanel;
	}

	/**
	 * This method initializes protocolPrefixPanel	
	 * 	
	 * @return net.sf.fmj.ui.registry.EntryPanel	
	 */
	private EntryPanel getProtocolPrefixPanel() {
		if (protocolPrefixPanel == null) {
			protocolPrefixPanel = new EntryPanel(EntryPanel.TYPE_PROTOCOL);
		}
		return protocolPrefixPanel;
	}

}
