/*     */ package jtree.DnD.Daves.ufjf.tree;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.dnd.Autoscroll;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTree;
/*     */ 
/*     */ public class TreeDragTest extends JFrame
/*     */ {
/*     */   TreeDragSource ds;
/*     */   TreeDropTarget dt;
/*     */   JTree tree;
/*     */ 
/*     */   public TreeDragTest()
/*     */   {
/*  57 */     super("Rearrangeable Tree");
/*  58 */     setSize(300, 200);
/*  59 */     setDefaultCloseOperation(3);
/*     */ 
/*  62 */     this.tree = new AutoScrollingJTree();
/*     */ 
/*  65 */     getContentPane().add(new JScrollPane(this.tree), "Center");
/*     */ 
/*  69 */     this.ds = new TreeDragSource(this.tree, 3);
/*  70 */     this.dt = new TreeDropTarget(this.tree);
/*  71 */     setVisible(true);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 113 */     new TreeDragTest();
/*     */   }
/*     */ 
/*     */   public class AutoScrollingJTree extends JTree
/*     */     implements Autoscroll
/*     */   {
/*  75 */     private int margin = 12;
/*     */ 
/*     */     public AutoScrollingJTree()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void autoscroll(Point p) {
/*  82 */       int realrow = getRowForLocation(p.x, p.y);
/*  83 */       Rectangle outer = getBounds();
/*  84 */       realrow = realrow < getRowCount() - 1 ? realrow + 1 : p.y + outer.y <= this.margin ? realrow - 1 : realrow < 1 ? 0 : realrow;
/*     */ 
/*  86 */       scrollRowToVisible(realrow);
/*     */     }
/*     */ 
/*     */     public Insets getAutoscrollInsets() {
/*  90 */       Rectangle outer = getBounds();
/*  91 */       Rectangle inner = getParent().getBounds();
/*  92 */       return new Insets(inner.y - outer.y + this.margin, inner.x - outer.x + this.margin, outer.height - inner.height - inner.y + outer.y + this.margin, outer.width - inner.width - inner.x + outer.x + this.margin);
/*     */     }
/*     */ 
/*     */     public void paintComponent(Graphics g)
/*     */     {
/* 102 */       super.paintComponent(g);
/* 103 */       Rectangle outer = getBounds();
/* 104 */       Rectangle inner = getParent().getBounds();
/* 105 */       g.setColor(Color.red);
/* 106 */       g.drawRect(-outer.x + 12, -outer.y + 12, inner.width - 24, inner.height - 24);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Documents and Settings\alexandre\Meus documentos\Downloads\TreeTeste.jar
 * Qualified Name:     ufjf.tree.TreeDragTest
 * JD-Core Version:    0.6.0
 */