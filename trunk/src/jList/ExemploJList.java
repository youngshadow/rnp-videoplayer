package jList;


import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;


public class ExemploJList extends JFrame {
        protected JList lista;
        public static void main(String[] args) {
                new ExemploJList();
        }
        public ExemploJList() {
                super("Exemplo JList");
                Container contentPane = this.getContentPane();
       
         DefaultListModel data = new DefaultListModel();
                data.addElement("um");
                data.addElement("dois");
                data.addElement("tr�s");
                data.addElement("quatro");
                data.addElement("cinco");
                lista = new JList(data);

                       JScrollPane scroller = new JScrollPane(lista);
                scroller.setPreferredSize(new Dimension(250, 80));
                
                lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

               lista.addListSelectionListener(
new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting())
                //ainda selecionando
                return;
                JList list = (JList)e.getSource();
                if (list.isSelectionEmpty()) {
                        Toolkit.getDefaultToolkit().beep();
                        System.out.println("nenhuma sele��o");
                } else {
                        System.out.println("Selecionou " + 
                        list.getSelectedValue() +
                        " indice " + list.getSelectedIndex());
                }
        }
});
               
       MouseListener mouseListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
                JList list = (JList)e.getSource();
                //Verifica se pressionou dois cliques do mouse
                if (e.getClickCount() == 2) {
                        int index = list.locationToIndex(e.getPoint());
                        System.out.println("duplo clique no item " + index);
                }
        }
};
lista.addMouseListener(mouseListener);

                contentPane.add(scroller);
                this.pack();
                this.setVisible(true);
        }
}

