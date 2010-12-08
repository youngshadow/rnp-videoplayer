/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author alexandre
 */
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class TesteSwing extends JFrame {

    public TesteSwing() {
        Action okAction = new MessageAction("OK", "Ação OK");
        Action cancelAction = new MessageAction("Cancelar", "Ação Cancelar");
        Action helpAction = new MessageAction("Ajuda", "Ação Ajuda");
        Action teclap = new AbstractAction(null) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("p");
                
            }
        };

        JButton okButton = new JButton(okAction);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        c.add(new JTextField(40));
        c.add(okButton);
        c.add(new JButton(cancelAction));

        getRootPane().setDefaultButton(okButton);

        getRootPane().getActionMap().put("ok", okAction);
        getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0), "ok");

        getRootPane().getActionMap().put("cancel", cancelAction);
        getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancel");

        getRootPane().getActionMap().put("help", helpAction);
        getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "help");

        getRootPane().getActionMap().put("123",teclap);
        getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0),"1234");

        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    class MessageAction extends AbstractAction {

        String _msg;

        MessageAction(String name, String msg) {
            putValue(NAME, name);
            _msg = msg;
        }

        public void actionPerformed(ActionEvent evt) {
            JOptionPane.showMessageDialog(TesteSwing.this, _msg);
        }
    }

    public static void main(String[] args) {
        new TesteSwing().setVisible(true);
    }
}
