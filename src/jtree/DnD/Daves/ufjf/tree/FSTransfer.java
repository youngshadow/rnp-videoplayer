/*    */ package jtree.DnD.Daves.ufjf.tree;
/*    */ 
/*    */ import java.awt.datatransfer.DataFlavor;
/*    */ import java.awt.datatransfer.Transferable;
/*    */ import java.io.File;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.TransferHandler;
/*    */ import javax.swing.tree.DefaultMutableTreeNode;
/*    */ import javax.swing.tree.DefaultTreeModel;
/*    */ 
/*    */ class FSTransfer extends TransferHandler
/*    */ {
/*    */   public boolean importData(JComponent comp, Transferable t)
/*    */   {
/* 36 */     if (!(comp instanceof MyTree)) {
/* 37 */       return false;
/*    */     }
/* 39 */     if (!t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
/* 40 */       return false;
/*    */     }
/* 42 */     MyTree tree = (MyTree)comp;
/* 43 */     DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
/* 44 */     DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
/*    */     try {
/* 46 */       List data = (List)t.getTransferData(DataFlavor.javaFileListFlavor);
/* 47 */       Iterator i = data.iterator();
/* 48 */       while (i.hasNext()) {
/* 49 */         File f = (File)i.next();
/* 50 */         root.add(new DefaultMutableTreeNode(f.getName()));
/*    */       }
/* 52 */       model.reload();
/* 53 */       return true;
/*    */     } catch (Exception ioe) {
/* 55 */       System.out.println(ioe);
/*    */     }
/* 57 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
/* 61 */     if ((comp instanceof MyTree)) {
/* 62 */       for (int i = 0; i < transferFlavors.length; i++) {
/* 63 */         if (!transferFlavors[i].equals(DataFlavor.javaFileListFlavor)) {
/* 64 */           return false;
/*    */         }
/*    */       }
/* 67 */       return true;
/*    */     }
/* 69 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\alexandre\Meus documentos\Downloads\TreeTeste.jar
 * Qualified Name:     ufjf.tree.FSTransfer
 * JD-Core Version:    0.6.0
 */