/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
