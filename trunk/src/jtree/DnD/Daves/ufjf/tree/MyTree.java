/*    */ package jtree.DnD.Daves.ufjf.tree;
/*    */ 
/*    */ import javax.swing.JTree;
/*    */ 
/*    */ public class MyTree extends JTree
/*    */ {
/*    */   public MyTree()
/*    */   {
/* 28 */     setDragEnabled(false);
/* 29 */     setTransferHandler(new FSTransfer());
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\alexandre\Meus documentos\Downloads\TreeTeste.jar
 * Qualified Name:     ufjf.tree.MyTree
 * JD-Core Version:    0.6.0
 */