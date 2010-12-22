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

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

/**
 * 
 * @author Warren Bloomer
 *
 */
public class UserSettingsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel logLabel = null;
	private JLabel regFileLabel = null;
	private JTextField logFileTextField = null;
	private JTextField regFileTextField = null;

	/**
	 * This is the default constructor
	 */
	public UserSettingsPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints3.gridy = 1;
		gridBagConstraints3.weightx = 1.0;
		gridBagConstraints3.gridx = 2;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints2.gridy = 0;
		gridBagConstraints2.weightx = 1.0;
		gridBagConstraints2.gridwidth = 2;
		gridBagConstraints2.gridx = 1;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.gridy = 1;
		regFileLabel = new JLabel();
		regFileLabel.setText("Registry File Location");
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		logLabel = new JLabel();
		logLabel.setText("Log File");
		this.setSize(422, 284);
		this.setLayout(new GridBagLayout());
		this.add(logLabel, gridBagConstraints);
		this.add(regFileLabel, gridBagConstraints1);
		this.add(getLogFileTextField(), gridBagConstraints2);
		this.add(getRegFileTextField(), gridBagConstraints3);
	}

	/**
	 * This method initializes logFileTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getLogFileTextField() {
		if (logFileTextField == null) {
			logFileTextField = new JTextField();
			logFileTextField.setPreferredSize(new Dimension(220, 22));
		}
		return logFileTextField;
	}

	/**
	 * This method initializes regFileTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getRegFileTextField() {
		if (regFileTextField == null) {
			regFileTextField = new JTextField();
			regFileTextField.setPreferredSize(new Dimension(220, 22));
		}
		return regFileTextField;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
