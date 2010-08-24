/*     */ package jtree.DnD.Daves.ufjf.tree;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import java.awt.datatransfer.Transferable;
/*     */ import java.awt.dnd.DropTarget;
/*     */ import java.awt.dnd.DropTargetContext;
/*     */ import java.awt.dnd.DropTargetDragEvent;
/*     */ import java.awt.dnd.DropTargetDropEvent;
/*     */ import java.awt.dnd.DropTargetEvent;
/*     */ import java.awt.dnd.DropTargetListener;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.tree.DefaultMutableTreeNode;
/*     */ import javax.swing.tree.DefaultTreeModel;
/*     */ import javax.swing.tree.TreeNode;
/*     */ import javax.swing.tree.TreePath;
/*     */ 
/*     */ class TreeDropTarget
/*     */   implements DropTargetListener
/*     */ {
/*     */   DropTarget target;
/*     */   JTree targetTree;
/*     */ 
/*     */   public TreeDropTarget(JTree tree)
/*     */   {
/* 207 */     this.targetTree = tree;
/* 208 */     this.target = new DropTarget(this.targetTree, this);
/*     */   }
/*     */ 
/*     */   private TreeNode getNodeForEvent(DropTargetDragEvent dtde)
/*     */   {
/* 215 */     Point p = dtde.getLocation();
/* 216 */     DropTargetContext dtc = dtde.getDropTargetContext();
/* 217 */     JTree tree = (JTree)dtc.getComponent();
/* 218 */     TreePath path = tree.getClosestPathForLocation(p.x, p.y);
/* 219 */     return (TreeNode)path.getLastPathComponent();
/*     */   }
/*     */ 
/*     */   public void dragEnter(DropTargetDragEvent dtde) {
/* 223 */     TreeNode node = getNodeForEvent(dtde);
/* 224 */     if (node.isLeaf()) {
/* 225 */       dtde.rejectDrag();
/*     */     }
/*     */     else
/*     */     {
/* 229 */       dtde.acceptDrag(dtde.getDropAction());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void dragOver(DropTargetDragEvent dtde) {
/* 234 */     TreeNode node = getNodeForEvent(dtde);
/* 235 */     if (node.isLeaf()) {
/* 236 */       dtde.rejectDrag();
/*     */     }
/*     */     else
/*     */     {
/* 240 */       dtde.acceptDrag(dtde.getDropAction());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void dragExit(DropTargetEvent dte) {
/*     */   }
/*     */ 
/*     */   public void dropActionChanged(DropTargetDragEvent dtde) {
/*     */   }
/*     */ 
/*     */   public void drop(DropTargetDropEvent dtde) {
/* 251 */     Point pt = dtde.getLocation();
/* 252 */     DropTargetContext dtc = dtde.getDropTargetContext();
/* 253 */     JTree tree = (JTree)dtc.getComponent();
/* 254 */     TreePath parentpath = tree.getClosestPathForLocation(pt.x, pt.y);
/* 255 */     DefaultMutableTreeNode parent = (DefaultMutableTreeNode)parentpath.getLastPathComponent();
/*     */ 
/* 257 */     if (parent.isLeaf()) {
/* 258 */       dtde.rejectDrop();
/* 259 */       return;
/*     */     }
/*     */     try
/*     */     {
/* 263 */       Transferable tr = dtde.getTransferable();
/* 264 */       DataFlavor[] flavors = tr.getTransferDataFlavors();
/* 265 */       for (int i = 0; i < flavors.length; i++) {
/* 266 */         if (tr.isDataFlavorSupported(flavors[i])) {
/* 267 */           dtde.acceptDrop(dtde.getDropAction());
/* 268 */           TreePath p = (TreePath)tr.getTransferData(flavors[i]);
/* 269 */           DefaultMutableTreeNode node = (DefaultMutableTreeNode)p.getLastPathComponent();
/*     */ 
/* 271 */           DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
/* 272 */           model.insertNodeInto(node, parent, 0);
/* 273 */           dtde.dropComplete(true);
/* 274 */           return;
/*     */         }
/*     */       }
/* 277 */       dtde.rejectDrop();
/*     */     } catch (Exception e) {
/* 279 */       e.printStackTrace();
/* 280 */       dtde.rejectDrop();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Documents and Settings\alexandre\Meus documentos\Downloads\TreeTeste.jar
 * Qualified Name:     ufjf.tree.TreeDropTarget
 * JD-Core Version:    0.6.0
 */