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

package net.sf.fmj.ui.objeditor;

import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * Generic OK cancel dialog with an ObjEditor inside.
 * 
 *
 */
public class ObjEditorOKCancelDialog 
{

	public static Object run(Frame parent, final ObjEditor objEditor, Object o, String title)
	{
		
		objEditor.setObjectAndUpdateControl(o);
		
		// TODO: center on parent
		final JDialog d = new JDialog(parent);
		d.setTitle(title);
		final Component p = objEditor.getComponent();
		
		d.getContentPane().add(p, java.awt.BorderLayout.CENTER);
		
        JPanel panelButtons = new javax.swing.JPanel();
        JButton buttonOK = new javax.swing.JButton();
        JButton buttonCancel = new javax.swing.JButton();

        panelButtons.setLayout(new java.awt.GridBagLayout());

        final Object[] result = new Object[1];	// primitive object holder
        
        buttonOK.setText("OK");
        buttonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //buttonOKActionPerformed(evt);
            	if (!objEditor.validateAndUpdateObj())
            		return;
            	result[0] = objEditor.getObject();
            	d.dispose();
            }
        });

        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        panelButtons.add(buttonOK, gridBagConstraints);

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //buttonCancelActionPerformed(evt);
            	d.dispose();
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        panelButtons.add(buttonCancel, gridBagConstraints);

        d.getContentPane().add(panelButtons, java.awt.BorderLayout.SOUTH);

        d.setModal(true);
        d.pack();
		d.setLocationRelativeTo(parent);

        d.setVisible(true);
        
        return result[0];
		
	}
}
