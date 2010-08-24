/*    */ package jtree.DnD.Daves.ufjf.tree;
/*    */ 
/*    */ import java.awt.Container;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.tree.DefaultMutableTreeNode;
/*    */ 
/*    */ public class FSTest extends JFrame
/*    */ {
/*    */   public FSTest()
/*    */   {
/* 33 */     super("FSTree Component Test");
/* 34 */     setSize(300, 300);
/* 35 */     setDefaultCloseOperation(3);
/*    */ 
/* 37 */     FSTree fst = new FSTree(new DefaultMutableTreeNode("Starter"));
/* 38 */     getContentPane().add(new JScrollPane(fst));
/* 39 */     setVisible(true);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 43 */     new FSTest();
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\alexandre\Meus documentos\Downloads\TreeTeste.jar
 * Qualified Name:     ufjf.tree.FSTest
 * JD-Core Version:    0.6.0
 */