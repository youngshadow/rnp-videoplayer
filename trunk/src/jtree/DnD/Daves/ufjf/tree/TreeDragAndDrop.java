/*    */ package jtree.DnD.Daves.ufjf.tree;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import javax.swing.DropMode;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JTree;
/*    */ import javax.swing.tree.DefaultMutableTreeNode;
/*    */ import javax.swing.tree.TreeModel;
/*    */ import javax.swing.tree.TreePath;
/*    */ import javax.swing.tree.TreeSelectionModel;
/*    */ 
/*    */ public class TreeDragAndDrop
/*    */ {
/*    */   private JScrollPane getContent()
/*    */   {
/* 20 */     JTree tree = new JTree();
/* 21 */     tree.setDragEnabled(true);
/* 22 */     tree.setDropMode(DropMode.ON_OR_INSERT);
/* 23 */     tree.setTransferHandler(new TreeTransferHandler());
/* 24 */     tree.getSelectionModel().setSelectionMode(2);
/*    */ 
/* 26 */     expandTree(tree);
/* 27 */     return new JScrollPane(tree);
/*    */   }
/*    */ 
/*    */   private void expandTree(JTree tree) {
/* 31 */     DefaultMutableTreeNode root = (DefaultMutableTreeNode)tree.getModel().getRoot();
/*    */ 
/* 33 */     Enumeration e = root.breadthFirstEnumeration();
/* 34 */     while (e.hasMoreElements()) {
/* 35 */       DefaultMutableTreeNode node = (DefaultMutableTreeNode)e.nextElement();
/*    */ 
/* 37 */       if (node.isLeaf()) {
/*    */         continue;
/*    */       }
/* 40 */       int row = tree.getRowForPath(new TreePath(node.getPath()));
/* 41 */       tree.expandRow(row);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 46 */     JFrame f = new JFrame();
/* 47 */     f.setDefaultCloseOperation(3);
/* 48 */     f.add(new TreeDragAndDrop().getContent());
/* 49 */     f.setSize(400, 400);
/* 50 */     f.setLocation(200, 200);
/* 51 */     f.setVisible(true);
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\alexandre\Meus documentos\Downloads\TreeTeste.jar
 * Qualified Name:     ufjf.tree.TreeDragAndDrop
 * JD-Core Version:    0.6.0
 */