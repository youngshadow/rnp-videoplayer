/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtree;

/**
 *
 * @author alexandre
 */
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.text.Position;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

public class TreeNodeRemove{
  public static void main(String[] argv) {
    Vector<String> v = new Vector<String>();
    v.add("a");
    v.add("b");
    v.add("b");
    v.add("b");
    v.add("c");
    v.add("c");
    v.add("c");
    v.add("c");
    v.add("c");
    JTree tree = new JTree(v);
    DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

    int startRow = 0;
    String prefix = "b";
    TreePath path = tree.getNextMatch(prefix, startRow, Position.Bias.Forward);
    MutableTreeNode node = (MutableTreeNode) path.getLastPathComponent();

    model.removeNodeFromParent(node);

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new JScrollPane(tree));
    frame.setSize(380, 320);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
