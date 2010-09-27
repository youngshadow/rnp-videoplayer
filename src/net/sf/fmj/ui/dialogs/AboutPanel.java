/*
 * AboutPanel.java
 *
 * Created on July 4, 2007, 4:36 AM
 */

package net.sf.fmj.ui.dialogs;

import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author  ken
 */
public class AboutPanel extends javax.swing.JPanel {
    
    /** Creates new form AboutPanel */
    public AboutPanel() {
        initComponents();
    }
    
	public static void run(Frame parent)
	{
		
		final JDialog d = new JDialog(parent);
		final Component p = new AboutPanel();
		
		d.getContentPane().add(p, java.awt.BorderLayout.CENTER);
		
		
        JPanel panelButtons = new javax.swing.JPanel();
        JButton buttonOK = new javax.swing.JButton();
        
        panelButtons.setLayout(new java.awt.GridBagLayout());

        buttonOK.setText("OK");
        buttonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	
            	d.dispose();
            }
        });

        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        panelButtons.add(buttonOK, gridBagConstraints);

        
        d.getContentPane().add(panelButtons, java.awt.BorderLayout.SOUTH);

        d.setTitle("About FMJ");
        d.setModal(true);
        d.pack();
		d.setLocationRelativeTo(parent);

        d.setVisible(true);
      
	}
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        labelImage = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        labelImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/fmj/ui/images/about_image.png")));
        add(labelImage, new java.awt.GridBagConstraints());

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelImage;
    // End of variables declaration//GEN-END:variables
    
}
