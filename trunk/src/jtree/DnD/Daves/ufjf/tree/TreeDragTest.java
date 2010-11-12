package jtree.DnD.Daves.ufjf.tree;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.dnd.Autoscroll;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;

public class TreeDragTest extends JFrame /*     */ {

    TreeDragSource ds;
    TreeDropTarget dt;
    JTree tree;

    public TreeDragTest() /*     */ {
        super("Rearrangeable Tree");
        setSize(300, 200);
        setDefaultCloseOperation(3);

        this.tree = new JTree();

        getContentPane().add(new JScrollPane(this.tree), "Center");
        this.ds = new TreeDragSource(this.tree, 0);
        this.dt = new TreeDropTarget(this.tree);
        setVisible(true);
    }

    public static void main(String[] args) /*     */ {
        new TreeDragTest();
    }

//    public class AutoScrollingJTree extends JTree  {
//
//        private int margin = 12;
//
//        public AutoScrollingJTree() {
//        }

//        public void autoscroll(Point p) {
//            int realrow = getRowForLocation(p.x, p.y);
//            Rectangle outer = getBounds();
//            realrow = realrow < getRowCount() - 1 ? realrow + 1 : p.y + outer.y <= this.margin ? realrow - 1 : realrow < 1 ? 0 : realrow;
//            scrollRowToVisible(realrow);
//        }
//
//        public Insets getAutoscrollInsets() {
//            Rectangle outer = getBounds();
//            Rectangle inner = getParent().getBounds();
//            return new Insets(inner.y - outer.y + this.margin, inner.x - outer.x + this.margin, outer.height - inner.height - inner.y + outer.y + this.margin, outer.width - inner.width - inner.x + outer.x + this.margin);
//        }

//        @Override
//        public void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            Rectangle outer = getBounds();
//            Rectangle inner = getParent().getBounds();
//            g.setColor(Color.red);
//            g.drawRect(-outer.x + 12, -outer.y + 12, inner.width - 24, inner.height - 24);
//        }
//    }
}
/* Location:           C:\Documents and Settings\alexandre\Meus documentos\Downloads\TreeTeste.jar
 * Qualified Name:     ufjf.tree.TreeDragTest
 * JD-Core Version:    0.6.0
 */
