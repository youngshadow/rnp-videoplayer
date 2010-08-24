/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

    package jtree;
 //   import jtree.dnd.DNDTree;
    import java.awt.Color;
    import java.awt.event.MouseAdapter;
    import java.awt.event.MouseEvent;
    import java.io.File;
   import java.io.FileInputStream;
    import java.io.FileOutputStream;
    import java.io.ObjectInputStream;
  import java.io.ObjectOutputStream;
   import java.util.ArrayList;
   import java.util.Enumeration;
   import java.util.List;
   import javax.swing.JOptionPane;
   import javax.swing.event.TreeSelectionEvent;
   import javax.swing.event.TreeSelectionListener;
   import javax.swing.tree.DefaultMutableTreeNode;
   import javax.swing.tree.DefaultTreeCellRenderer;
   import javax.swing.tree.DefaultTreeModel;
   import javax.swing.tree.MutableTreeNode;
   import javax.swing.tree.TreeNode;
   import javax.swing.tree.TreePath;


   public class jTreeTestCard extends javax.swing.JFrame implements TreeSelectionListener {


       private javax.swing.tree.DefaultMutableTreeNode dn = new javax.swing.tree.DefaultMutableTreeNode("root");  //  @jve:decl-index=0:
       private javax.swing.tree.DefaultTreeModel treeModel = new javax.swing.tree.DefaultTreeModel(dn);
       private java.util.ArrayList list;

       /** Creates new form jTreeTestCard */
       public jTreeTestCard() {
           setVisible(true);
           initComponents();
       }
   // METODOS ADICIONAR ITEM NA JTREE

       /** Add child to the currently selected node. */
       public DefaultMutableTreeNode addObject(Object child) {
           DefaultMutableTreeNode parentNode = null;
           TreePath parentPath = jTree1.getSelectionPath();
           System.out.println(parentPath);

           if (parentPath == null) {
               parentNode = dn;
           } else {
               parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
           }

           return addObject(parentNode, child, true);
       }

       public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
               Object child) {
           return addObject(parent, child, false);
       }

       public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
               Object child,
               boolean shouldBeVisible) {
           DefaultMutableTreeNode childNode =
                   new DefaultMutableTreeNode(child);

           if (parent == null) {
               parent = dn;
           }

           //It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
           treeModel.insertNodeInto(childNode, parent,
                   parent.getChildCount());

           //Make sure the user can see the lovely new node.
           if (shouldBeVisible) {

               jTree1.scrollPathToVisible(new TreePath(childNode.getPath()));
               System.out.println("childNode "+new TreePath(childNode.getPath()));
           }
           return childNode;
       }

   // METODOS REQUERIDOS PARA REMOVER ITEM DA JTREE
       /**
        * Records the list of currently expanded paths in the specified tree.
        * This method is meant to be called before calling the
        * <code>reload()</code> methods to allow the tree to store the paths.
        *
        * @param  tree      the tree
        * @param  pathlist  the list of expanded paths
        */
       public ArrayList getExpandedPaths() {
           ArrayList expandedPaths = new ArrayList();
           addExpandedPaths(jTree1.getPathForRow(0), expandedPaths);
           return expandedPaths;
       }



      /**
       * Adds the expanded descendants of the specifed path in the specified
       * tree to the internal expanded list.
       *
 106.      * @param  tree      the tree
 107.      * @param  path      the path
 108.      * @param  pathlist  the list of expanded paths
 109.      */
      /**/ private void addExpandedPaths(TreePath path, ArrayList pathlist) {
          Enumeration enumTree = jTree1.getExpandedDescendants(path);

          try {
             while (enumTree.hasMoreElements()) {
                  TreePath tp = (TreePath) enumTree.nextElement();
                  pathlist.add(tp);
                  addExpandedPaths(tp, pathlist);
             }
          } catch (Exception e) {
              // nao faz nada
              //System.out.println(e);
          }
      }

      /**
       * Re-expands the expanded paths in the specified tree.  This method is
 127.      * meant to be called before calling the <code>reload()</code> methods
 128.      * to allow the tree to store the paths.
 129.      *
 130.      * @param  tree      the tree
 131.      * @param  pathlist  the list of expanded paths
 132.      */
      public void expandPaths(ArrayList pathlist) {
          for (int i = 0; i < pathlist.size(); i++) {
              jTree1.expandPath((TreePath) pathlist.get(i));
          }
      }

      // path para todos no
      // If expanded, return only paths of nodes that are expanded.
      public TreePath[] getPaths(javax.swing.JTree tree, boolean expanded) {
          TreeNode root = (TreeNode) tree.getModel().getRoot();

          // Create array to hold the treepaths
          list = new ArrayList();

          // Traverse tree from root adding treepaths for all nodes to list
         getPaths(tree, new TreePath(root), expanded, list);

          // Convert list to array
          return (TreePath[]) list.toArray(new TreePath[list.size()]);
      }

      public void getPaths(javax.swing.JTree tree, TreePath parent, boolean expanded, List list) {
          // Return if node is not expanded
          if (expanded && !tree.isVisible(parent)) {
              return;
          }

          // Add node to list
          list.add(parent);
 
          // Create paths for all children
          TreeNode node = (TreeNode) parent.getLastPathComponent();
          if (node.getChildCount() >= 0) {
              for (Enumeration e = node.children(); e.hasMoreElements();) {
                  TreeNode n = (TreeNode) e.nextElement();
                  TreePath path = parent.pathByAddingChild(n);
                  getPaths(tree, path, expanded, list);
              }
          }
      }
 
      // This method is called from within the constructor to
       // initialize the form.
       // WARNING: Do NOT modify this code. The content of this method is
       // always regenerated by the Form Editor.
 
      @SuppressWarnings("unchecked")
      // <editor-fold defaultstate="collapsed" desc="Generated Code">
     private void initComponents() {
        panelPrincipal = new javax.swing.JPanel();
        panelJTree = new javax.swing.JPanel();
          jScrollPane1 = new javax.swing.JScrollPane();
         jTree1 = new javax.swing.JTree();
          panelPadrao = new javax.swing.JPanel();
          jTextField1 = new javax.swing.JTextField();
          botaoAdd = new javax.swing.JButton();
          botaoRem = new javax.swing.JButton();
          rootVisibleCheck = new javax.swing.JCheckBox();
         jButton1 = new javax.swing.JButton();
          salvaJtree = new javax.swing.JButton();
          carregaJtree = new javax.swing.JButton();
          limparJtree = new javax.swing.JButton();
         panelInterno = new javax.swing.JPanel();
          panelGeral = new javax.swing.JPanel();
          jLabel1 = new javax.swing.JLabel();
          jScrollPane2 = new javax.swing.JScrollPane();
          jTextArea1 = new javax.swing.JTextArea();
          panelFolha = new javax.swing.JPanel();
          jLabel2 = new javax.swing.JLabel();
          panelPai = new javax.swing.JPanel();
          jLabel3 = new javax.swing.JLabel();
           setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
           DefaultMutableTreeNode root1 = new DefaultMutableTreeNode("root");
         // jTree1 = new DNDTree(root1);
          jTree1.setBackground(new java.awt.Color(255, 255, 102));
          jTree1.setBorder(new javax.swing.border.MatteBorder(null));
          jTree1.setForeground(new java.awt.Color(255, 204, 204));
         jTree1.setModel(treeModel);
          jTree1.setEditable(true);
         jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mousePressed(java.awt.event.MouseEvent evt) {
                  jTree1MousePressed(evt);
              }
         });
          jScrollPane1.setViewportView(jTree1);
         // personaliza cores da celula da jtree
          DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
          // cor da celula nao selecionada
         renderer.setBackgroundNonSelectionColor(new Color(255, 255, 102));
          // cor da celula selecionada
          renderer.setBackgroundSelectionColor(new Color(255, 231, 102));
 
         jTree1.setCellRenderer(renderer);
          jTree1.setModel(treeModel );
          jTree1.addMouseListener(new MouseAdapter() {
              public void mouseClicked(MouseEvent me){
               if(me.getClickCount() % 2 == 0)
                  {           jTree1.setEditable(true);
                      // identificar se nó é folha
                      DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                      jTree1.getLastSelectedPathComponent();
                      if (node != null)
                      {
                          System.out.println();
                          if (node.isRoot()) System.out.println(" duplo click no root");
 
                         if (node.isLeaf()) {
                              System.out.println("no folha 'duplo click'");
                         }
                        else
                         {System.out.println("no pai 'duplo click'");}
 
                          int selRow = jTree1.getRowForLocation(me.getX(), me.getY());
                          TreePath selPath = jTree1.getPathForLocation(me.getX(), me.getY());
                         if(selRow != -1) {
 
                             if(selPath.getLastPathComponent() != null) {
                                  System.out.println("Nodo selecionado "+selPath.getLastPathComponent());
                                 System.out.println("parent "+selPath.getParentPath());
                              }
 
                          }
                     }
               }
             }//mouseClicked
 
         });
         jScrollPane1.setViewportView(jTree1);
          jTree1.addTreeSelectionListener(this);
 
          javax.swing.GroupLayout panelJTreeLayout = new javax.swing.GroupLayout(panelJTree);
          panelJTree.setLayout(panelJTreeLayout);
         panelJTreeLayout.setHorizontalGroup(
            panelJTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(panelJTreeLayout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addContainerGap(19, Short.MAX_VALUE))
          );
          panelJTreeLayout.setVerticalGroup(
              panelJTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(panelJTreeLayout.createSequentialGroup()
                 .addContainerGap()
                  .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
         );
 
          botaoAdd.setText("Adicionar");
          botaoAdd.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  botaoAddActionPerformed(evt);
              }
          });
          botaoRem.setText("Remover");
         botaoRem.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  botaoRemActionPerformed(evt);
             }
          });
 
          rootVisibleCheck.setSelected(true);
         rootVisibleCheck.setText("Root visible");
          rootVisibleCheck.addChangeListener(new javax.swing.event.ChangeListener() {
              public void stateChanged(javax.swing.event.ChangeEvent evt) {
                 rootVisibleCheckStateChanged(evt);
              }
          });
          rootVisibleCheck.addItemListener(new java.awt.event.ItemListener() {
              public void itemStateChanged(java.awt.event.ItemEvent evt) {
                  rootVisibleCheckItemStateChanged(evt);
              }
          });
 
          jButton1.setText("Renomear Root");
          jButton1.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  jButton1ActionPerformed(evt);
              }
          });
 
          salvaJtree.setText("Salvar jtree");
          salvaJtree.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  salvaJtreeActionPerformed(evt);
              }
          });
 
          carregaJtree.setText("Carregar jTree");
          carregaJtree.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  carregaJtreeActionPerformed(evt);
              }
          });
 
          limparJtree.setText("Limpar jTree");
          limparJtree.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  limparJtreeActionPerformed(evt);
              }
          });
 
          javax.swing.GroupLayout panelPadraoLayout = new javax.swing.GroupLayout(panelPadrao);
          panelPadrao.setLayout(panelPadraoLayout);
          panelPadraoLayout.setHorizontalGroup(
              panelPadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(panelPadraoLayout.createSequentialGroup()
                  .addGroup(panelPadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(panelPadraoLayout.createSequentialGroup()
                          .addContainerGap()
                          .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                          .addGroup(panelPadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(panelPadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                  .addComponent(botaoRem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                  .addComponent(botaoAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                         .addGroup(panelPadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                              .addComponent(carregaJtree, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                             .addComponent(salvaJtree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(limparJtree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                      .addGroup(panelPadraoLayout.createSequentialGroup()
                          .addGap(167, 167, 167)
                          .addComponent(rootVisibleCheck)))
                  .addContainerGap())
          );
          panelPadraoLayout.setVerticalGroup(
              panelPadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(panelPadraoLayout.createSequentialGroup()
                  .addGap(19, 19, 19)
                  .addGroup(panelPadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                      .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addComponent(botaoAdd)
                      .addComponent(salvaJtree))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup(panelPadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                      .addComponent(botaoRem)
                      .addComponent(carregaJtree))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup(panelPadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                      .addComponent(jButton1)
                      .addComponent(limparJtree))
                  .addGap(18, 18, 18)
                  .addComponent(rootVisibleCheck)
                  .addContainerGap(12, Short.MAX_VALUE))
          );
          panelInterno.setLayout(new java.awt.CardLayout());
          panelGeral.setBackground(new java.awt.Color(244, 244, 209));
 
          jLabel1.setText("Panel Geral"); // NOI18N
          jTextArea1.setColumns(20);
          jTextArea1.setRows(5);
          jTextArea1.setText("Para adicionar um item na jtree:\n\n1) seleciona o item destino \n2) digita na caixa de texto o nome do novo item\n3) clica no botao Adicionar");
          jScrollPane2.setViewportView(jTextArea1);
 
          javax.swing.GroupLayout panelGeralLayout = new javax.swing.GroupLayout(panelGeral);
         panelGeral.setLayout(panelGeralLayout);
          panelGeralLayout.setHorizontalGroup(
              panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(panelGeralLayout.createSequentialGroup()
                  .addGroup(panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(panelGeralLayout.createSequentialGroup()
                          .addGap(47, 47, 47)
                          .addComponent(jLabel1))
                      .addGroup(panelGeralLayout.createSequentialGroup()
                          .addContainerGap()
                          .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)))
                  .addContainerGap(24, Short.MAX_VALUE))
          );
          panelGeralLayout.setVerticalGroup(
              panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(panelGeralLayout.createSequentialGroup()
                 .addGap(21, 21, 21)
                 .addComponent(jLabel1)
                  .addGap(18, 18, 18)
                  .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addContainerGap(197, Short.MAX_VALUE))
          );
 
          panelInterno.add(panelGeral, "panelGeral");
 
          panelFolha.setBackground(new java.awt.Color(224, 240, 224));
 
          jLabel2.setText("panel Folha"); // NOI18N
 
          javax.swing.GroupLayout panelFolhaLayout = new javax.swing.GroupLayout(panelFolha);
        panelFolha.setLayout(panelFolhaLayout);
          panelFolhaLayout.setHorizontalGroup(
              panelFolhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(panelFolhaLayout.createSequentialGroup()
                  .addGap(110, 110, 110)
                  .addComponent(jLabel2)
                  .addContainerGap(274, Short.MAX_VALUE))
          );
          panelFolhaLayout.setVerticalGroup(
             panelFolhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(panelFolhaLayout.createSequentialGroup()
                  .addGap(135, 135, 135)
                  .addComponent(jLabel2)
                  .addContainerGap(223, Short.MAX_VALUE))
          );
           panelInterno.add(panelFolha, "panelFolha");
 
          panelPai.setBackground(new java.awt.Color(226, 225, 225));
 
          jLabel3.setText("Panel Pai"); // NOI18N
 
          javax.swing.GroupLayout panelPaiLayout = new javax.swing.GroupLayout(panelPai);
          panelPai.setLayout(panelPaiLayout);
          panelPaiLayout.setHorizontalGroup(
              panelPaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(panelPaiLayout.createSequentialGroup()
                 .addGap(177, 177, 177)
                  .addComponent(jLabel3)
                  .addContainerGap(219, Short.MAX_VALUE))
          );
          panelPaiLayout.setVerticalGroup(
              panelPaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(panelPaiLayout.createSequentialGroup()
                  .addGap(139, 139, 139)
                  .addComponent(jLabel3)
                  .addContainerGap(219, Short.MAX_VALUE))
          );
 
          panelInterno.add(panelPai, "panelPai");
           javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
          panelPrincipal.setLayout(panelPrincipalLayout);
          panelPrincipalLayout.setHorizontalGroup(
             panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(panelPrincipalLayout.createSequentialGroup()
                  .addComponent(panelJTree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(panelInterno, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                     .addComponent(panelPadrao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
         );
          panelPrincipalLayout.setVerticalGroup(
              panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addComponent(panelJTree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addGroup(panelPrincipalLayout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(panelPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(panelInterno, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
          );
 
          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
         layout.setHorizontalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
        layout.setVerticalGroup(
              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
 
          pack();
      }// </editor-fold>
 
      private void botaoAddActionPerformed(java.awt.event.ActionEvent evt) {
 
            if (jTextField1.getText().length()==0)
               {  JOptionPane.showMessageDialog(null,"Digite um nome para inserir na jtree");
                 jTextField1.grabFocus();
                 return;
               }
 
            if (jTree1.getLastSelectedPathComponent()==null)  {JOptionPane.showMessageDialog(null,"Selecione um item na jtree"); return;}
              addObject(jTextField1.getText());
               jTextField1.setText("");
               jTextField1.grabFocus();
 
      }
 
      private void botaoRemActionPerformed(java.awt.event.ActionEvent evt) {
          TreePath currentSelection = jTree1.getSelectionPath();
 
          if (currentSelection==null) {
          JOptionPane.showMessageDialog(null,"Selecione um item que nao seja o root para excluir");
          return;
          }
 
 
          if (currentSelection != null) {
              //System.out.println(currentSelection);
              //System.out.println(currentSelection.getLastPathComponent());
             DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection.getLastPathComponent());
              MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
              //System.out.println("anterior..." + currentNode);
 
              if (currentNode.isRoot()) {
                JOptionPane.showMessageDialog(null,"Root nao pode ser apagado!");
                return;
              }
              if (!currentNode.isLeaf()) {
                int  excluirItem = JOptionPane.showConfirmDialog(null,  "Item selecionado nao esta vazio! Excluir mesmo assim!!!", "Excluir", JOptionPane.YES_NO_OPTION);
 
                  if (excluirItem == 1) return; // quer dizer 'nao', encerra sem apagar
                // System.out.println("possui filhos, nao deveria apagar...");
              }
              if (parent != null) {
                  System.out.println("apagando..." + currentSelection);
                  treeModel.removeNodeFromParent(currentNode);
 
 
 
                  ArrayList lista = getExpandedPaths(); // salva tree
                  treeModel.reload(dn);
                  expandPaths(lista); // restaura tree
 
                  getPaths(jTree1, false);
                  return;
              } else {
                  System.out.println("Erro ao apagar...");
              }
          }
 
 
 
 
 
    }
 
      private void rootVisibleCheckItemStateChanged(java.awt.event.ItemEvent evt) {
          if (rootVisibleCheck.isSelected()) {
             System.out.println("mostra root");
              jTree1.setRootVisible(true);
         } else {
             System.out.println("oculta root");
             jTree1.setRootVisible(false);
         }
      }
 
      private void rootVisibleCheckStateChanged(javax.swing.event.ChangeEvent evt) {
      }
 
      private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
 
          if (jTextField1.getText().length()==0)
               {  JOptionPane.showMessageDialog(null,"Digite um nome para o root");
                  jTextField1.grabFocus();
                return;
               }
            dn = (DefaultMutableTreeNode)treeModel.getRoot();
         dn.setUserObject(jTextField1.getText());
            treeModel.reload();
 
        }
 
      private void salvaJtreeActionPerformed(java.awt.event.ActionEvent evt) {
          File fFile = new File("salvaTree.jtr");
         saveModel(treeModel, fFile);
  }
 
      private void carregaJtreeActionPerformed(java.awt.event.ActionEvent evt) {
          File fFile = new File("salvaTree.jtr");
          jTree1.setModel(loadModel(fFile));
         treeModel = ((DefaultTreeModel) jTree1.getModel());
         dn.setUserObject(treeModel.getRoot());
  }
 
      private void limparJtreeActionPerformed(java.awt.event.ActionEvent evt) {
           dn = new javax.swing.tree.DefaultMutableTreeNode("root");  //  @jve:decl-index=0:
           treeModel = new javax.swing.tree.DefaultTreeModel(dn);
           treeModel.reload(dn);
           jTree1.setModel(treeModel);
 
   }
      private void jTree1MousePressed(java.awt.event.MouseEvent evt) {
    }
 
      /**
       * @param args the command line arguments
       */
      public static void main(String args[]) {
          java.awt.EventQueue.invokeLater(new Runnable() {
 
              public void run() {
                new jTreeTestCard().setLocationRelativeTo(null);
              }
          });
      }
 
      // Variables declaration - do not modify
     private javax.swing.JButton botaoAdd;
     private javax.swing.JButton botaoRem;
     private javax.swing.JButton carregaJtree;
      private javax.swing.JButton jButton1;
     private javax.swing.JLabel jLabel1;
      private javax.swing.JLabel jLabel2;
     private javax.swing.JLabel jLabel3;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
     private javax.swing.JTextField jTextField1;
     private javax.swing.JTree jTree1;
     private javax.swing.JButton limparJtree;
     private javax.swing.JPanel panelFolha;
      private javax.swing.JPanel panelGeral;
      private javax.swing.JPanel panelInterno;
     private javax.swing.JPanel panelJTree;
      private javax.swing.JPanel panelPadrao;
      private javax.swing.JPanel panelPai;
      private javax.swing.JPanel panelPrincipal;
      private javax.swing.JCheckBox rootVisibleCheck;
     private javax.swing.JButton salvaJtree;
     // End of variables declaration
      // Required by TreeSelectionListener interface.
      //identifica se é folha ou pai, clicando uma vez no icone
 
       public void valueChanged(TreeSelectionEvent e) {
         DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
          System.out.println();
         if (node == null) {
             // significa q nada foi clicado
             System.out.println("NULL");
            return;
         }
 
 
          if (node.isLeaf() && node.isRoot()) {
              System.out.println("é nodo root e folha");
             mostraCard("panelGeral");
             return;
         }
    if (node.isRoot()) {
          System.out.println("nodo root 'um click'  OU 'teclas'");
             mostraCard("panelGeral");
             return;
        }
 
 
          if (node.isLeaf()) {
              System.out.println("nodo folha 'um click' OU 'teclas'");
              mostraCard("panelFolha");
             return;
 
          } else {
              System.out.println("nodo pai 'um click'  OU 'teclas'");
              mostraCard("panelPai");
             return;
 
         }
 
 
      }
 
     private void mostraCard(String nome) {
         java.awt.CardLayout card = (java.awt.CardLayout) panelInterno.getLayout();
          card.show(panelInterno, nome);
     }
 
 
   // salva jtree em um arquivo
      public static void saveModel(DefaultTreeModel model, File name) {
          try {
              ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(name));
              out.writeObject(model);
              out.close();
          } catch (Exception e) {
              e.printStackTrace();
          }
 
      }
  // recupera jtree de um arquivo
 
      public static DefaultTreeModel loadModel(File name) {
          DefaultTreeModel model = null;
          try {
              ObjectInputStream in = new ObjectInputStream(new FileInputStream(name));
              model = (DefaultTreeModel) in.readObject();
              in.close();
          } catch (Exception e) {
              e.printStackTrace();
          }
          return model;
      }
 
 
 
 
 
 
  }