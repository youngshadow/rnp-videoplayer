/*     */ package jtree.DnD.Daves.ufjf.tree;
/*     */ 
/*     */ import java.awt.dnd.DragGestureEvent;
/*     */ import java.awt.dnd.DragGestureListener;
/*     */ import java.awt.dnd.DragGestureRecognizer;
/*     */ import java.awt.dnd.DragSource;
/*     */ import java.awt.dnd.DragSourceDragEvent;
/*     */ import java.awt.dnd.DragSourceDropEvent;
/*     */ import java.awt.dnd.DragSourceEvent;
/*     */ import java.awt.dnd.DragSourceListener;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.tree.DefaultMutableTreeNode;
/*     */ import javax.swing.tree.DefaultTreeModel;
/*     */ import javax.swing.tree.TreePath;
/*     */ 
/*     */ class TreeDragSource
/*     */   implements DragSourceListener, DragGestureListener
/*     */ {
/*     */   DragSource source;
/*     */   DragGestureRecognizer recognizer;
/*     */   TransferableTreeNode transferable;
/*     */   DefaultMutableTreeNode oldNode;
/*     */   JTree sourceTree;
/*     */ 
/*     */   public TreeDragSource(JTree tree, int actions)
/*     */   {
/* 135 */     this.sourceTree = tree;
/* 136 */     this.source = new DragSource();
/* 137 */     this.recognizer = this.source.createDefaultDragGestureRecognizer(this.sourceTree, actions, this);
/*     */   }
/*     */ 
/*     */   public void dragGestureRecognized(DragGestureEvent dge)
/*     */   {
/* 145 */     TreePath path = this.sourceTree.getSelectionPath();
/* 146 */     if ((path == null) || (path.getPathCount() <= 1))
/*     */     {
/* 148 */       return;
/*     */     }
/* 150 */     this.oldNode = ((DefaultMutableTreeNode)path.getLastPathComponent());
/* 151 */     this.transferable = new TransferableTreeNode(path);
/* 152 */     this.source.startDrag(dge, DragSource.DefaultMoveNoDrop, this.transferable, this);
/*     */   }
/*     */ 
/*     */   public void dragEnter(DragSourceDragEvent dsde)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void dragExit(DragSourceEvent dse)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void dragOver(DragSourceDragEvent dsde)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void dropActionChanged(DragSourceDragEvent dsde)
/*     */   {
/* 173 */     System.out.println("Action: " + dsde.getDropAction());
/* 174 */     System.out.println("Target Action: " + dsde.getTargetActions());
/* 175 */     System.out.println("User Action: " + dsde.getUserAction());
/*     */   }
/*     */ 
/*     */   public void dragDropEnd(DragSourceDropEvent dsde)
/*     */   {
/* 182 */     System.out.println("Drop Action: " + dsde.getDropAction());
/* 183 */     if ((dsde.getDropSuccess()) && (dsde.getDropAction() == 2))
/*     */     {
/* 185 */       ((DefaultTreeModel)this.sourceTree.getModel()).removeNodeFromParent(this.oldNode);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Documents and Settings\alexandre\Meus documentos\Downloads\TreeTeste.jar
 * Qualified Name:     ufjf.tree.TreeDragSource
 * JD-Core Version:    0.6.0
 */