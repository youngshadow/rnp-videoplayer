/*     */ package jtree.DnD.Daves.ufjf.tree;
/*     */ 
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import java.awt.datatransfer.Transferable;
/*     */ import java.awt.datatransfer.UnsupportedFlavorException;
/*     */ import java.io.IOException;
/*     */ import javax.swing.tree.TreePath;
/*     */ 
/*     */ class TransferableTreeNode
/*     */   implements Transferable
/*     */ {
/* 291 */   public static DataFlavor TREE_PATH_FLAVOR = new DataFlavor(TreePath.class, "Tree Path");
/*     */ 
/* 294 */   DataFlavor[] flavors = { TREE_PATH_FLAVOR };
/*     */   TreePath path;
/*     */ 
/*     */   public TransferableTreeNode(TreePath tp)
/*     */   {
/* 299 */     this.path = tp;
/*     */   }
/*     */ 
/*     */   public synchronized DataFlavor[] getTransferDataFlavors() {
/* 303 */     return this.flavors;
/*     */   }
/*     */ 
/*     */   public boolean isDataFlavorSupported(DataFlavor flavor) {
/* 307 */     return flavor.getRepresentationClass() == TreePath.class;
/*     */   }
/*     */ 
/*     */   public synchronized Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException
/*     */   {
/* 312 */     if (isDataFlavorSupported(flavor)) {
/* 313 */       return this.path;
/*     */     }
/* 315 */     throw new UnsupportedFlavorException(flavor);
/*     */   }
/*     */ }

/* Location:           C:\Documents and Settings\alexandre\Meus documentos\Downloads\TreeTeste.jar
 * Qualified Name:     ufjf.tree.TransferableTreeNode
 * JD-Core Version:    0.6.0
 */