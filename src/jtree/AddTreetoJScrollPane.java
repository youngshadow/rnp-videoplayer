/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtree;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
/**
 *
 * @author alexandre
 */
public class AddTreetoJScrollPane {
    public static void main(String args[]) {
    JFrame f = new JFrame("JTree Sample");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container content = f.getContentPane();
    JTree tree = new JTree();
    JScrollPane scrollPane = new JScrollPane(tree);
    content.add(scrollPane, BorderLayout.CENTER);
    f.setSize(300, 200);
    f.setVisible(true);
  }

}
