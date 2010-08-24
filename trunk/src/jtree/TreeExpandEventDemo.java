/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtree;

/**
 *
 * @author alexandre
 */
/*
 * Copyright (c) 1995 - 2008 Sun Microsystems, Inc.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * TreeExpandEventDemo.java requires no other files.
 */

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class TreeExpandEventDemo extends JPanel {
  DemoArea demoArea;
  JTextArea textArea;
  final static String newline = "\n";

  public TreeExpandEventDemo() {
    super(new GridBagLayout());
    GridBagLayout gridbag = (GridBagLayout) getLayout();
    GridBagConstraints c = new GridBagConstraints();

    c.fill = GridBagConstraints.BOTH;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.weightx = 1.0;
    c.weighty = 1.0;

    c.insets = new Insets(1, 1, 1, 1);
    demoArea = new DemoArea();
    gridbag.setConstraints(demoArea, c);
    add(demoArea);

    c.insets = new Insets(0, 0, 0, 0);
    textArea = new JTextArea();
    textArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane
        .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(200, 75));
    gridbag.setConstraints(scrollPane, c);
    add(scrollPane);

    setPreferredSize(new Dimension(450, 450));
    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  }

  void saySomething(String eventDescription, TreeExpansionEvent e) {
    textArea
        .append(eventDescription + "; " + "path = " + e.getPath() + newline);
  }

  class DemoArea extends JScrollPane implements TreeExpansionListener {
    Dimension minSize = new Dimension(100, 100);
    JTree tree;

    public DemoArea() {
      TreeNode rootNode = createNodes();
      tree = new JTree(rootNode);
      tree.addTreeExpansionListener(this);

      setViewportView(tree);
    }

    private TreeNode createNodes() {
      DefaultMutableTreeNode root;
      DefaultMutableTreeNode grandparent;
      DefaultMutableTreeNode parent;
      DefaultMutableTreeNode child;

      root = new DefaultMutableTreeNode("San Francisco");

      grandparent = new DefaultMutableTreeNode("Potrero Hill");
      root.add(grandparent);
      //
      parent = new DefaultMutableTreeNode("Restaurants");
      grandparent.add(parent);
      child = new DefaultMutableTreeNode("Thai Barbeque");
      parent.add(child);
      child = new DefaultMutableTreeNode("Goat Hill Pizza");
      parent.add(child);
      //
      parent = new DefaultMutableTreeNode("Grocery Stores");
      grandparent.add(parent);
      child = new DefaultMutableTreeNode("Good Life Grocery");
      parent.add(child);
      child = new DefaultMutableTreeNode("Safeway");
      parent.add(child);

      grandparent = new DefaultMutableTreeNode("Noe Valley");
      root.add(grandparent);
      //
      parent = new DefaultMutableTreeNode("Restaurants");
      grandparent.add(parent);
      child = new DefaultMutableTreeNode("Hamano Sushi");
      parent.add(child);
      child = new DefaultMutableTreeNode("Hahn's Hibachi");
      parent.add(child);
      //
      parent = new DefaultMutableTreeNode("Grocery Stores");
      grandparent.add(parent);
      child = new DefaultMutableTreeNode("Real Foods");
      parent.add(child);
      child = new DefaultMutableTreeNode("Bell Market");
      parent.add(child);

      return root;
    }

    public Dimension getMinimumSize() {
      return minSize;
    }

    public Dimension getPreferredSize() {
      return minSize;
    }

    // Required by TreeExpansionListener interface.
    public void treeExpanded(TreeExpansionEvent e) {
      saySomething("Tree-expanded event detected", e);
    }

    // Required by TreeExpansionListener interface.
    public void treeCollapsed(TreeExpansionEvent e) {
      saySomething("Tree-collapsed event detected", e);
    }
  }

  /**
   * Create the GUI and show it. For thread safety, this method should be
   * invoked from the event-dispatching thread.
   */
  private static void createAndShowGUI() {
    // Create and set up the window.
    JFrame frame = new JFrame("TreeExpandEventDemo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create and set up the content pane.
    JComponent newContentPane = new TreeExpandEventDemo();
    newContentPane.setOpaque(true); // content panes must be opaque
    frame.setContentPane(newContentPane);

    // Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    // Schedule a job for the event-dispatching thread:
    // creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
}
