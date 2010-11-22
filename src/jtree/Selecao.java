/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtree;

/**
 *
 * @author alexandre
 */
import java.awt.Container;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class Selecao {

    public static void main(String[] argv) {
        JTree tree = new JTree();
        JButton btn = new JButton();
        btn.setText("count");

        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(tree));
        frame.add(btn, 1);
        frame.setSize(380, 320);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        System.out.println("" + tree.getSelectionCount());

    }
}
