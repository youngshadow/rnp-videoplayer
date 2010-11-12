package view;

import jtree.DnD.Daves.ufjf.tree.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.JTree.DropLocation;
import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

class TreeTransferHandler extends TransferHandler
{
  DataFlavor nodesFlavor;
  DataFlavor[] flavors = new DataFlavor[1];
  DefaultMutableTreeNode[] nodesToRemove;

  public TreeTransferHandler()
  {
    try
    {
      String mimeType = "application/x-java-jvm-local-objectref;class=\"" + javax.swing.tree.DefaultMutableTreeNode.class.getName() + "\"";

      this.nodesFlavor = new DataFlavor(mimeType);
      this.flavors[0] = this.nodesFlavor;
    } catch (ClassNotFoundException e) {
      System.out.println("ClassNotFound: " + e.getMessage());
    }
  }

  public boolean canImport(TransferHandler.TransferSupport support) {
    if (!support.isDrop()) {
      return false;
    }
    support.setShowDropLocation(true);
    if (!support.isDataFlavorSupported(this.nodesFlavor)) {
      return false;
    }

    JTree.DropLocation dl = (JTree.DropLocation)support.getDropLocation();

    JTree tree = (JTree)support.getComponent();
    int dropRow = tree.getRowForPath(dl.getPath());
    int[] selRows = tree.getSelectionRows();
    for (int i = 0; i < selRows.length; i++) {
      if (selRows[i] == dropRow) {
        return false;
      }

    }

    int action = support.getDropAction();
    if (action == 2) {
      return haveCompleteNode(tree);
    }

    TreePath dest = dl.getPath();
    DefaultMutableTreeNode target = (DefaultMutableTreeNode)dest.getLastPathComponent();

    TreePath path = tree.getPathForRow(selRows[0]);
    DefaultMutableTreeNode firstNode = (DefaultMutableTreeNode)path.getLastPathComponent();

    return (firstNode.getChildCount() <= 0) || (target.getLevel() >= firstNode.getLevel());
  }

  private boolean haveCompleteNode(JTree tree)
  {
    int[] selRows = tree.getSelectionRows();
    TreePath path = tree.getPathForRow(selRows[0]);
    DefaultMutableTreeNode first = (DefaultMutableTreeNode)path.getLastPathComponent();

    int childCount = first.getChildCount();

    if ((childCount > 0) && (selRows.length == 1)) {
      return false;
    }

    for (int i = 1; i < selRows.length; i++) {
      path = tree.getPathForRow(selRows[i]);
      DefaultMutableTreeNode next = (DefaultMutableTreeNode)path.getLastPathComponent();

      if (!first.isNodeChild(next))
        continue;
      if (childCount > selRows.length - 1)
      {
        return false;
      }
    }

    return true;
  }

  protected Transferable createTransferable(JComponent c) {
    JTree tree = (JTree)c;
    TreePath[] paths = tree.getSelectionPaths();
    if (paths != null)
    {
      List copies = new ArrayList();

      List toRemove = new ArrayList();

      DefaultMutableTreeNode node = (DefaultMutableTreeNode)paths[0].getLastPathComponent();

      DefaultMutableTreeNode copy = copy(node);
      copies.add(copy);
      toRemove.add(node);
      for (int i = 1; i < paths.length; i++) {
        DefaultMutableTreeNode next = (DefaultMutableTreeNode)paths[i].getLastPathComponent();

        if (next.getLevel() < node.getLevel())
          break;
        if (next.getLevel() > node.getLevel()) {
          copy.add(copy(next));
        }
        else {
          copies.add(copy(next));
          toRemove.add(next);
        }
      }
      DefaultMutableTreeNode[] nodes = (DefaultMutableTreeNode[])copies.toArray(new DefaultMutableTreeNode[copies.size()]);

      this.nodesToRemove = ((DefaultMutableTreeNode[])toRemove.toArray(new DefaultMutableTreeNode[toRemove.size()]));

      return new NodesTransferable(nodes);
    }
    return null;
  }

  private DefaultMutableTreeNode copy(TreeNode node)
  {
    return new DefaultMutableTreeNode(node);
  }

  protected void exportDone(JComponent source, Transferable data, int action) {
    if ((action & 0x2) == 2) {
      JTree tree = (JTree)source;
      DefaultTreeModel model = (DefaultTreeModel)tree.getModel();

      for (int i = 0; i < this.nodesToRemove.length; i++)
        model.removeNodeFromParent(this.nodesToRemove[i]);
    }
  }

  public int getSourceActions(JComponent c)
  {
    return 3;
  }

  public boolean importData(TransferHandler.TransferSupport support) {
    if (!canImport(support)) {
      return false;
    }

    DefaultMutableTreeNode[] nodes = null;
    try {
      Transferable t = support.getTransferable();
      nodes = (DefaultMutableTreeNode[])(DefaultMutableTreeNode[])t.getTransferData(this.nodesFlavor);
    } catch (UnsupportedFlavorException ufe) {
      System.out.println("UnsupportedFlavor: " + ufe.getMessage());
    } catch (IOException ioe) {
      System.out.println("I/O error: " + ioe.getMessage());
    }

    JTree.DropLocation dl = (JTree.DropLocation)support.getDropLocation();

    int childIndex = dl.getChildIndex();
    TreePath dest = dl.getPath();
    DefaultMutableTreeNode parent = (DefaultMutableTreeNode)dest.getLastPathComponent();

    JTree tree = (JTree)support.getComponent();
    DefaultTreeModel model = (DefaultTreeModel)tree.getModel();

    int index = childIndex;
    if (childIndex == -1) {
      index = parent.getChildCount();
    }

    for (int i = 0; i < nodes.length; i++) {
      model.insertNodeInto(nodes[i], parent, index++);
    }
    return true;
  }

  public String toString() {
    return getClass().getName();
  }

  public class NodesTransferable implements Transferable {
    DefaultMutableTreeNode[] nodes;

    public NodesTransferable(DefaultMutableTreeNode[] nodes) {
      this.nodes = nodes;
    }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
    {
      if (!isDataFlavorSupported(flavor)) {
        throw new UnsupportedFlavorException(flavor);
      }
      return this.nodes;
    }

    public DataFlavor[] getTransferDataFlavors() {
      return TreeTransferHandler.this.flavors;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
      return TreeTransferHandler.this.nodesFlavor.equals(flavor);
    }
  }
}