/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtree;

/**
 *
 * @author alexandre
 */
import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import javax.swing.event.*;

import javax.swing.tree.*;

public class TreeExample extends JFrame implements ActionListener {

private static final int WIDTH = 640;

private static final int HEIGHT = 480;

private static final String MOVE_UP_ACTION = "move_up_action";

private static final String MOVE_DOWN_ACTION = "move_down_action";

private JTree tree = null;

public TreeExample() {

super("Wizard test");

Container container = getContentPane();

container.setLayout(new BorderLayout());

int locX = (Toolkit.getDefaultToolkit().getScreenSize().width - WIDTH) / 2;

int locY = (Toolkit.getDefaultToolkit().getScreenSize().height - HEIGHT) / 2;

setBounds(locX, locY, WIDTH, HEIGHT);

addWindowListener(new WindowAdapter() {

public void windowClosing( WindowEvent e ) {

System.exit(0);

}

});

JPanel buttonsPanel = new JPanel();

buttonsPanel.setLayout(new GridLayout(2, 1, 0, 8));

final JButton moveUpButton = new JButton("Move Up");

moveUpButton.setFont(moveUpButton.getFont().deriveFont(Font.PLAIN));

moveUpButton.setMnemonic('U');

moveUpButton.setActionCommand(MOVE_UP_ACTION);

moveUpButton.setEnabled(false);

moveUpButton.addActionListener(this);

final JButton moveDownButton = new JButton("Move Down");

moveDownButton.setFont(moveUpButton.getFont().deriveFont(Font.PLAIN));

moveDownButton.setMnemonic('D');

moveDownButton.setActionCommand(MOVE_DOWN_ACTION);

moveDownButton.setEnabled(false);

moveDownButton.addActionListener(this);

buttonsPanel.add(moveUpButton);

buttonsPanel.add(moveDownButton);

/*************** Create accounts tree table and buttons panel on the right of it **************/

DefaultMutableTreeNode root = new DefaultMutableTreeNode("Sample");

DefaultMutableTreeNode colors = new DefaultMutableTreeNode("colors");

colors.add(new DefaultMutableTreeNode("blue"));

colors.add(new DefaultMutableTreeNode("violet"));

colors.add(new DefaultMutableTreeNode("red"));

colors.add(new DefaultMutableTreeNode("yellow"));

DefaultMutableTreeNode sports = new DefaultMutableTreeNode("sports");

sports.add(new DefaultMutableTreeNode("basketball"));

sports.add(new DefaultMutableTreeNode("soccer"));

sports.add(new DefaultMutableTreeNode("football"));

sports.add(new DefaultMutableTreeNode("hockey"));

DefaultMutableTreeNode programs = new DefaultMutableTreeNode("programs");

programs.add(new DefaultMutableTreeNode("Java"));

programs.add(new DefaultMutableTreeNode("ASP .Net"));

programs.add(new DefaultMutableTreeNode("C"));

programs.add(new DefaultMutableTreeNode("Perl"));

root.add(colors);

root.add(sports);

root.add(programs);

tree = new JTree(root);

tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

tree.addTreeSelectionListener(new TreeSelectionListener() {

public void valueChanged(TreeSelectionEvent e) {

Object nodeInfo = tree.getLastSelectedPathComponent();

if (nodeInfo == null) return;

DefaultMutableTreeNode treeSelectNode = (DefaultMutableTreeNode) nodeInfo;

DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) treeSelectNode.getParent();

if (parentNode != null) {

moveUpButton.setEnabled(parentNode.getChildCount() > 1 &&

!parentNode.getFirstChild().equals(treeSelectNode));

moveDownButton.setEnabled(parentNode.getChildCount() > 1 &&

!parentNode.getLastChild().equals(treeSelectNode));

}

else {

moveDownButton.setEnabled(false);

moveUpButton.setEnabled(false);

}

}

});

JScrollPane scrollPane = new JScrollPane(tree);

JPanel mainPanel = new JPanel();

mainPanel.setLayout(new GridBagLayout());

constrain( mainPanel, scrollPane,

GridBagConstraints.BOTH, GridBagConstraints.NORTHWEST,

0, 0, 1, 1, 1.0, 1.0, new Insets(10, 10, 10, 10));

constrain( mainPanel, buttonsPanel,

GridBagConstraints.NONE, GridBagConstraints.NORTHWEST,

1, 0, 1, 1, 0.0, 0.0, new Insets(10, 0, 10, 10));

container.add(mainPanel, BorderLayout.NORTH);

}

public void constrain( Container container, Component component,

int fill, int anchor,

int gx, int gy, int gw, int gh, double wx, double wy ) {

GridBagConstraints c = new GridBagConstraints();

c.fill = fill;

c.anchor = anchor;

c.gridx = gx;

c.gridy = gy;

c.gridwidth = gw;

c.gridheight = gh;

c.weightx = wx;

c.weighty = wy;

( (GridBagLayout) container.getLayout() ).setConstraints( component, c );

container.add( component );

}

public void constrain( Container container, Component component,

int fill, int anchor,

int gx, int gy, int gw, int gh,

double wx, double wy, Insets inset ) {

GridBagConstraints c = new GridBagConstraints();

c.fill = fill;

c.anchor = anchor;

c.gridx = gx;

c.gridy = gy;

c.gridwidth = gw;

c.gridheight = gh;

c.weightx = wx;

c.weighty = wy;

c.insets = inset;

( (GridBagLayout) container.getLayout() ).setConstraints( component, c );

container.add( component );

}

public void actionPerformed(ActionEvent e) {

String action = e.getActionCommand();

Object nodeInfo = tree.getLastSelectedPathComponent();

if (action != null && nodeInfo != null) {

DefaultMutableTreeNode treeSelectNode = (DefaultMutableTreeNode) nodeInfo;

DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) treeSelectNode.getParent();

if (parentNode != null) {

int treeSelectIndex = parentNode.getIndex(treeSelectNode);

if (action.equals(MOVE_UP_ACTION)) {

DefaultMutableTreeNode nextNodeUp = (DefaultMutableTreeNode) parentNode.getChildAt(treeSelectIndex - 1);

System.out.println("Want to swap " + treeSelectNode + " with " + nextNodeUp);

}

if (action.equals(MOVE_DOWN_ACTION)) {

DefaultMutableTreeNode nextNodeDown = (DefaultMutableTreeNode) parentNode.getChildAt(treeSelectIndex + 1);

System.out.println("Want to swap " + treeSelectNode + " with " + nextNodeDown);

}

}

}

}

public static void main(String args[]) {

new TreeExample().setVisible(true);

}

}
