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

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.media.PlugInManager;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * @author Warren Bloomer
 *
 */
public class PluginsPanel extends JPanel implements DetailsListener {

	private static final long serialVersionUID = 1L;
	
	private int pluginType = PlugInManager.CODEC;
	
	private JPanel detailsPanel = null;
	private JTextArea detailsValue = null;
	private JLabel detailLabel = null;

	private JScrollPane detailsScrollPane = null;
	private EntryPanel entryPanel = null;
	/**
	 * This is the default constructor
	 */
	public PluginsPanel(int pluginType) {
		super();
		this.pluginType = pluginType;
		initialize();
	}

	/**
	 * This method initializes this
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		this.setSize(389, 274);
		this.setLayout(new GridLayout(1, 2));
		this.add(getEntryPanel(), null);
		this.add(getDetailsPanel());
	}

	/**
	 * This method initializes detailsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDetailsPanel() {
		if (detailsPanel == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.weightx = 1.0;
			detailLabel = new JLabel();
			detailLabel.setText("Details");
			detailsPanel = new JPanel();
			detailsPanel.setLayout(new BorderLayout());
			detailsPanel.add(detailLabel, BorderLayout.NORTH);
			detailsPanel.add(getDetailsScrollPane(), BorderLayout.CENTER);
		}
		return detailsPanel;
	}

	/**
	 * This method initializes detailsScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getDetailsScrollPane() {
		if (detailsScrollPane == null) {
			
			detailsValue = new JTextArea();
			
			
			detailsScrollPane = new JScrollPane(detailsValue);
			
		}
		return detailsScrollPane;
	}

	/**
	 * This method initializes entryPanel	
	 * 	
	 * @return net.sf.fmj.ui.registry.EntryPanel	
	 */
	EntryPanel getEntryPanel() {
		if (entryPanel == null) {
			entryPanel = new EntryPanel(pluginType);
			entryPanel.setDetailsListener(this);
		}
		return entryPanel;
	}

	public void onDetails(String text)
	{
		detailsValue.setText(text);
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
