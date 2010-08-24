/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtree;

/**
 *
 * @author alexandre
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class DeletingNodes extends JFrame {
  DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("A");

  private DefaultTreeModel m_model = new DefaultTreeModel(rootNode);

  private JTree m_tree = new JTree(m_model);

  private JButton m_addButton;

  private JButton m_delButton;

  public DeletingNodes() {
    DefaultMutableTreeNode forums = new DefaultMutableTreeNode("B");
    forums.add(new DefaultMutableTreeNode("T"));
    DefaultMutableTreeNode articles = new DefaultMutableTreeNode("A");
    articles.add(new DefaultMutableTreeNode("A"));
    DefaultMutableTreeNode examples = new DefaultMutableTreeNode("E");
    examples.add(new DefaultMutableTreeNode("E"));

    rootNode.add(forums);
    rootNode.add(articles);
    rootNode.add(examples);

    m_tree.setEditable(true);
    m_tree.setSelectionRow(0);

    JScrollPane scrollPane = new JScrollPane(m_tree);
    getContentPane().add(scrollPane, BorderLayout.CENTER);

    JPanel panel = new JPanel();
    m_addButton = new JButton("Add Node");
    m_addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) m_tree
            .getLastSelectedPathComponent();

        if (selNode == null) {
          return;
        }

        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("New");
        m_model.insertNodeInto(newNode, selNode, selNode.getChildCount());

        TreeNode[] nodes = m_model.getPathToRoot(newNode);
        TreePath path = new TreePath(nodes);
        m_tree.scrollPathToVisible(path);

        m_tree.setSelectionPath(path);

        m_tree.startEditingAtPath(path);
      }

    });
    panel.add(m_addButton);
    getContentPane().add(panel, BorderLayout.SOUTH);
    m_delButton = new JButton("Delete Node");
    m_delButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) m_tree
            .getLastSelectedPathComponent();
        if (selNode == null) {
          return;
        }
        MutableTreeNode parent = (MutableTreeNode) (selNode.getParent());
        if (parent == null) {
          return;
        }
        MutableTreeNode toBeSelNode = (MutableTreeNode) selNode.getPreviousSibling();
        if (toBeSelNode == null) {
          toBeSelNode = (MutableTreeNode) selNode.getNextSibling();
        }
        if (toBeSelNode == null) {
          toBeSelNode = parent;
        }
        TreeNode[] nodes = m_model.getPathToRoot(toBeSelNode);
        TreePath path = new TreePath(nodes);
        m_tree.scrollPathToVisible(path);
        m_tree.setSelectionPath(path);
        m_model.removeNodeFromParent(selNode);
      }

    });
    panel.add(m_delButton);
    getContentPane().add(panel, BorderLayout.SOUTH);

    setSize(300, 400);
    setVisible(true);
  }

  public static void main(String[] arg) {
    new DeletingNodes();
  }
}