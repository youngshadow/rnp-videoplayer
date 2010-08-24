/*     */ package jtree.DnD.Daves.ufjf.tree;
/*     */ 
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import java.awt.datatransfer.Transferable;
/*     */ import java.awt.datatransfer.UnsupportedFlavorException;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.TransferHandler;
/*     */ import javax.swing.tree.DefaultMutableTreeNode;
/*     */ import javax.swing.tree.DefaultTreeModel;
/*     */ import javax.swing.tree.TreeModel;
/*     */ import javax.swing.tree.TreeNode;
/*     */ 
/*     */ class FSTree extends JTree
/*     */ {
/*     */   public FSTree()
/*     */   {
/*  55 */     init();
/*     */   }
/*     */ 
/*     */   public FSTree(TreeModel newModel) {
/*  59 */     super(newModel);
/*  60 */     init();
/*     */   }
/*     */ 
/*     */   public FSTree(TreeNode root) {
/*  64 */     super(root);
/*  65 */     init();
/*     */   }
/*     */ 
/*     */   public FSTree(TreeNode root, boolean asks) {
/*  69 */     super(root, asks);
/*  70 */     init();
/*     */   }
/*     */ 
/*     */   private void init()
/*     */   {
/*  75 */     setDragEnabled(false);
/*  76 */     setTransferHandler(new FSTransfer());
/*     */   }
/*     */   public class FSTransfer extends TransferHandler {
/*     */     public FSTransfer() {
/*     */     }
/*     */     public boolean importData(JComponent comp, Transferable t) {
/*  82 */       if (!(comp instanceof FSTree)) {
/*  83 */         return false;
/*     */       }
/*  85 */       if (!t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
/*  86 */         return false;
/*     */       }
/*     */ 
/*  90 */       FSTree tree = (FSTree)comp;
/*  91 */       DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
/*  92 */       DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
/*     */       try
/*     */       {
/*  95 */         List data = (List)t.getTransferData(DataFlavor.javaFileListFlavor);
/*     */ 
/*  97 */         Iterator i = data.iterator();
/*  98 */         while (i.hasNext()) {
/*  99 */           File f = (File)i.next();
/* 100 */           root.add(new DefaultMutableTreeNode(f.getName()));
/*     */         }
/* 102 */         model.reload();
/* 103 */         return true;
/*     */       } catch (UnsupportedFlavorException ufe) {
/* 105 */         System.err.println("Ack! we should not be here.\nBad Flavor.");
/*     */       } catch (IOException ioe) {
/* 107 */         System.out.println("Something failed during import:\n" + ioe);
/*     */       }
/* 109 */       return false;
/*     */     }
/*     */ 
/*     */     public boolean canImport(JComponent comp, DataFlavor[] transferFlavors)
/*     */     {
/* 114 */       if ((comp instanceof FSTree)) {
/* 115 */         for (int i = 0; i < transferFlavors.length; i++) {
/* 116 */           if (!transferFlavors[i].equals(DataFlavor.javaFileListFlavor))
/*     */           {
/* 118 */             return false;
/*     */           }
/*     */         }
/* 121 */         return true;
/*     */       }
/* 123 */       return false;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Documents and Settings\alexandre\Meus documentos\Downloads\TreeTeste.jar
 * Qualified Name:     ufjf.tree.FSTree
 * JD-Core Version:    0.6.0
 */