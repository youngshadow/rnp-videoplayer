/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * _jfPrincipal.java
 *
 * Created on 03/08/2010, 10:25:14
 */
package view;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DropMode;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import model.DAOIndex;
import model.DAOSync;
import model.DAOxml;
import model.Index2Obj;
import model.Rm_item;
import model.Slides2Obj;
import model.Xml2Obj;
import net.sf.fmj.ui.application.PlayerPanel;
import net.sf.fmj.utility.URLUtils;
import util.CloneObj;
import util.GravarArquivo;
import util.VerificaCaractere;

/**
 *
 * @author bisaggio
 */
public abstract class _jfPrincipal extends javax.swing.JFrame implements TreeSelectionListener {

    public boolean ExcluirJtree(JTree jtree, DefaultTreeModel treeModel) {
        TreePath path[] = jtree.getSelectionPaths();
        DefaultTreeModel model = (DefaultTreeModel) jtree.getModel();

        if (jtree.getSelectionCount() < 1) {
            JOptionPane.showMessageDialog(this, "Nenhum ítem Selecionado", "Erro!", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            for (int i = 0; i < path.length; i++) {
                MutableTreeNode noSelect = (MutableTreeNode) path[i].getLastPathComponent();

                if (noSelect.getParent() == null) {
                    alerta("Erro!", "O ítem raiz não pode ser removido!");
                    return false;
                }
                model.removeNodeFromParent(noSelect);
            }
        }
        return true;
    }

    public void alerta(String titulo, String msg) {
        JOptionPane.showMessageDialog(this, msg, titulo, JOptionPane.ERROR_MESSAGE);
        return;
    }
    //public abstract void abrirArquivo(String arquivo);
    //Dimension dimension;
    //videoJMF video;
    // boolean isPlay = false;
    public static URL url = null;
    // FileDialog file = null;
    private DefaultTreeModel jtreeModelTopicos;
    private DefaultTreeModel jtreeModelSlides;
    String dirFinal;
    String sizeFlv;
    File ultimaURL = null;
    private PlayerPanel playerPanel;
    private boolean aulaModificada = false;

    /** Creates new form _jfPrincipal */
    public _jfPrincipal() {
        initComponents();
        Container contentPane = getContentPane();
        playerPanel = new PlayerPanel();
        playerPanel.setSize(330, 360);
        playerPanel.setBounds(35, 101, 330, 320);
        contentPane.add(playerPanel);



        this.setTitle(" RIOComposer - V 0.752");
        //define o tamanho do video
        //dimension = new Dimension(jpContainerVideo.getWidth(), jpContainerVideo.getHeight());

        // jListSlides.setModel(listModel);

        jtreeModelTopicos = new DefaultTreeModel(null);
        jtreeModelSlides = new DefaultTreeModel(null);
        jtTopicos.setEditable(true);
        jtTopicos.setSelectionRow(0);
        jTSlides.setEditable(true);
        jTSlides.setSelectionRow(0);

        // configurando drag and drop
        jtTopicos.setDropMode(DropMode.ON_OR_INSERT);
        jtTopicos.setTransferHandler(new TreeTransferHandler());
        jtTopicos.getSelectionModel().setSelectionMode(2);

        jTSlides.setDropMode(DropMode.INSERT);
        jTSlides.setTransferHandler(new TreeTransferHandler());
        jTSlides.getSelectionModel().setSelectionMode(2);

//        WindowUtilities.setMotifLookAndFeel();
        gerarRoot();
        gerarRoot1();



        KeyListener key = new KeyListener() {

            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped " + e);
            }

            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed " + e);
            }

            public void keyReleased(KeyEvent e) {
                if (!aulaModificada) {
                    aulaModificada = true;
                }
            }
        };
        jtTopicos.addKeyListener(key);
        jTSlides.addKeyListener(key);


    }

    protected void gerarRoot() {
        DefaultMutableTreeNode rootTopic = new DefaultMutableTreeNode("Roteiro");
        jtTopicos.setModel(jtreeModelTopicos);
        jtreeModelTopicos.setRoot(rootTopic);
        TreeNode[] nodes = jtreeModelTopicos.getPathToRoot(rootTopic);
        TreePath treepath = new TreePath(nodes);
        jtTopicos.scrollPathToVisible(treepath);
        jtTopicos.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        jtTopicos.setSelectionPath(treepath);
//        jtTopicos.startEditingAtPath(treepath);
    }

    protected void gerarRoot1() {
        DefaultMutableTreeNode rootTopic = new DefaultMutableTreeNode("Roteiro");
        jTSlides.setModel(jtreeModelSlides);
        jtreeModelSlides.setRoot(rootTopic);
        TreeNode[] nodes = jtreeModelSlides.getPathToRoot(rootTopic);
        TreePath treepath = new TreePath(nodes);
        jTSlides.scrollPathToVisible(treepath);
        //configurando multiselect
//        jTSlides.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
//        jTSlides.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
        jTSlides.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);

//        jTSlides.setSelectionPath(treepath);
//        jTSlides.startEditingAtPath(treepath);

    }

    private boolean VerificaAulaModificada() throws HeadlessException {
        // TODO add your handling code here:
        if (aulaModificada) {
            Object[] options = {"Sim", "Não", "cancelar"};
            int i = JOptionPane.showOptionDialog(null, "Deseja salvar as alterações na aula? ", "Saída", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (i == JOptionPane.NO_OPTION) {
                return true;
            }
            if (i == JOptionPane.YES_OPTION) {
                if (salvarAula()) {
                    return true;
                }
            }
        } else {
            return true;
        }
        return false;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        btnCapturar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtTopicos = new javax.swing.JTree();
        btnNovo = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTFDisciplina = new javax.swing.JTextField();
        jTFCodDisc = new javax.swing.JTextField();
        jTFCurso = new javax.swing.JTextField();
        jTFAula = new javax.swing.JTextField();
        jTFProfessor = new javax.swing.JTextField();
        jTFIsntituicao = new javax.swing.JTextField();
        jLNomeFlv = new javax.swing.JLabel();
        btnCapturarTempos = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTSlides = new javax.swing.JTree();
        jTSlides.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jTSlides.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
        jTSlides.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnCapturar.setFont(new java.awt.Font("SansSerif", 0, 11));
        btnCapturar.setMnemonic('n');
        btnCapturar.setText("Nova transparência");
        btnCapturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapturarActionPerformed(evt);
            }
        });

        btnRemover.setFont(new java.awt.Font("SansSerif", 0, 11));
        btnRemover.setMnemonic('e');
        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        jtTopicos.setFont(new java.awt.Font("SansSerif", 0, 11));
        jtTopicos.setAutoscrolls(true);
        jtTopicos.setDragEnabled(true);
        jtTopicos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFDisciplinaKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jtTopicos);

        btnNovo.setFont(new java.awt.Font("SansSerif", 0, 11));
        btnNovo.setMnemonic('t');
        btnNovo.setText("Novo Tópico");
        btnNovo.setToolTipText("Alt t");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("SansSerif", 0, 11));
        jButton4.setMnemonic('r');
        jButton4.setText("Remover");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("SansSerif", 0, 11));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/fmj/ui/images/Save24.gif"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações gerais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 11))); // NOI18N

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 11));
        jLabel1.setText("Curso");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 11));
        jLabel2.setText("Aula");

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 11));
        jLabel11.setText("Código da Disciplina");

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 11));
        jLabel12.setText("Disciplina");

        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 11));
        jLabel14.setText("Professor");

        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 11));
        jLabel15.setText("Instituição");

        jLabel18.setFont(new java.awt.Font("SansSerif", 0, 11));
        jLabel18.setText("Arquivo");

        jTFDisciplina.setFont(new java.awt.Font("SansSerif", 0, 11));
        jTFDisciplina.setAutoscrolls(false);
        jTFDisciplina.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTFDisciplina.setHighlighter(null);
        jTFDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFDisciplinaActionPerformed(evt);
            }
        });
        jTFDisciplina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFDisciplinaKeyTyped(evt);
            }
        });

        jTFCodDisc.setFont(new java.awt.Font("SansSerif", 0, 11));
        jTFCodDisc.setAutoscrolls(false);
        jTFCodDisc.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTFCodDisc.setHighlighter(null);
        jTFCodDisc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFDisciplinaKeyTyped(evt);
            }
        });

        jTFCurso.setFont(new java.awt.Font("SansSerif", 0, 11));
        jTFCurso.setAutoscrolls(false);
        jTFCurso.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTFCurso.setHighlighter(null);
        jTFCurso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFDisciplinaKeyTyped(evt);
            }
        });

        jTFAula.setFont(new java.awt.Font("SansSerif", 0, 11));
        jTFAula.setAutoscrolls(false);
        jTFAula.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTFAula.setHighlighter(null);
        jTFAula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFDisciplinaKeyTyped(evt);
            }
        });

        jTFProfessor.setFont(new java.awt.Font("SansSerif", 0, 11));
        jTFProfessor.setAutoscrolls(false);
        jTFProfessor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTFProfessor.setHighlighter(null);
        jTFProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFProfessorActionPerformed(evt);
            }
        });
        jTFProfessor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFDisciplinaKeyTyped(evt);
            }
        });

        jTFIsntituicao.setFont(new java.awt.Font("SansSerif", 0, 11));
        jTFIsntituicao.setAutoscrolls(false);
        jTFIsntituicao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTFIsntituicao.setHighlighter(null);
        jTFIsntituicao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFDisciplinaKeyTyped(evt);
            }
        });

        jLNomeFlv.setText(" ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTFDisciplina)
                    .addComponent(jTFProfessor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTFAula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLNomeFlv, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCodDisc, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCurso, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFIsntituicao, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTFCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTFDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFCodDisc, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTFProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jTFIsntituicao, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTFAula, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jLNomeFlv)))
                .addContainerGap())
        );

        btnCapturarTempos.setFont(new java.awt.Font("SansSerif", 0, 11));
        btnCapturarTempos.setMnemonic('c');
        btnCapturarTempos.setText("<<Copiar tempos");
        btnCapturarTempos.setActionCommand("Copiar");
        btnCapturarTempos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCapturarTempos.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnCapturarTempos.setMaximumSize(new java.awt.Dimension(53, 19));
        btnCapturarTempos.setMinimumSize(new java.awt.Dimension(53, 19));
        btnCapturarTempos.setPreferredSize(new java.awt.Dimension(53, 19));
        btnCapturarTempos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapturarTemposActionPerformed(evt);
            }
        });

        jTSlides.setAutoscrolls(true);
        jTSlides.setDragEnabled(true);
        jTSlides.setMinimumSize(new java.awt.Dimension(53, 48));
        jTSlides.setRootVisible(false);
        jTSlides.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFDisciplinaKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(jTSlides);

        jMenu1.setMnemonic('r');
        jMenu1.setText("Arquivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Abrir Videoaula");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Abrir Vídeo");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Salvar Aula");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem4.setText("Fechar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setMnemonic('j');
        jMenu2.setText("Ajuda");

        jMenuItem6.setText("Manual ");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem5.setText("Sobre ");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addComponent(btnCapturarTempos, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(398, 398, 398)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCapturar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(231, 231, 231)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapturar, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btnCapturarTempos, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapturarActionPerformed

        if (playerPanel.getContainerPlayer().getControll() == null) {

            alerta("Erro!", "Nenhum vídeo em reprodução!");
            return;
        }

        String nomeFile = null;
        //enquanto o tempo atual do getTimeVideo nao é atualizado
        playerPanel.getTransportControlPanel().stop();

        //  slideList.dialogo(listModel, "00:" + playerPanel.getTransportControlPanel().getPositionLabel().getText());

        //passando para jtree


        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo swf", "swf"));
        fileChooser.setAcceptAllFileFilterUsed(false);

        fileChooser.setCurrentDirectory(ultimaURL);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            URI uri = fileChooser.getSelectedFile().toURI();
            ultimaURL = fileChooser.getCurrentDirectory();


            if (VerificaCaractere.Verifica(fileChooser.getSelectedFile().getName().substring(0, fileChooser.getSelectedFile().getName().lastIndexOf(".")))) {
                JOptionPane.showMessageDialog(this, "Caracteres inválidos no nome do arquivo \n" + fileChooser.getSelectedFile().getName(), "Erro!", JOptionPane.ERROR_MESSAGE);
                return;
            }




            try {
                URL url = uri.toURL();
                nomeFile = fileChooser.getSelectedFile().getName();


            } catch (MalformedURLException ex) {
            }




            DefaultMutableTreeNode nodeSelect = (DefaultMutableTreeNode) jTSlides.getModel().getRoot();
            if (nodeSelect != null) {

                DefaultMutableTreeNode newTopic = new DefaultMutableTreeNode("00:" + playerPanel.getTransportControlPanel().getPositionLabel().getText() + " - " + nomeFile);
                jtreeModelSlides.insertNodeInto(newTopic, nodeSelect, nodeSelect.getChildCount());
                TreeNode[] nodes = jtreeModelSlides.getPathToRoot(newTopic);
                TreePath treepath = new TreePath(nodes);
                jTSlides.scrollPathToVisible(treepath);
                jTSlides.setSelectionPath(treepath);
                jTSlides.startEditingAtPath(treepath);
            } else {
                gerarRoot1();
            }
        }
    }//GEN-LAST:event_btnCapturarActionPerformed

    private void btnCapturarTemposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapturarTemposActionPerformed
        TreeNode treeNode = (TreeNode) jtTopicos.getModel().getRoot();

        if (!jtreeModelTopicos.asksAllowsChildren()) {
            if (treeNode.getChildCount() > 0) {
                Object[] options = {"Não", "Sim"};
                if (JOptionPane.showOptionDialog(null, "Esta ação irá apagar todos os tópicos do roteiro. \n Deseja Continuar?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == 0) {
                    return;
                }
            }
        }
        try {
            jtreeModelTopicos = (DefaultTreeModel) CloneObj.getClone((Serializable) jTSlides.getModel());
        } catch (IOException ex) {
            Logger.getLogger(_jfPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(_jfPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        jtTopicos.setModel(jtreeModelTopicos);

//        for (int i = 0; i < listModel.size(); i++) {
//            DefaultMutableTreeNode nodeSelect = (DefaultMutableTreeNode) jtTopicos.getModel().getRoot();
//            System.out.println("nodeSelect: " + nodeSelect);
////            if (nodeSelect != null) {
////
////                DefaultMutableTreeNode newTopic = new DefaultMutableTreeNode(listModel.get(i).toString().substring(0, listModel.get(i).toString().lastIndexOf(".")));
////
//////                jtreeModelTopicos.insertNodeInto(newTopic, nodeSelect, nodeSelect.getChildCount());
////                TreeNode[] nodes = jtreeModelTopicos.getPathToRoot(newTopic);
////                TreePath treepath = new TreePath(nodes);
////                jtTopicos.scrollPathToVisible(treepath);
////                jtTopicos.setSelectionPath(treepath);
////                jtTopicos.startEditingAtPath(treepath);
////            } else {
////                gerarRoot();
////            }
//
//
//        }
    }//GEN-LAST:event_btnCapturarTemposActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed

        if (ExcluirJtree(jTSlides, jtreeModelSlides)) {
            return;
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        if (playerPanel.getContainerPlayer().getControll() == null) {

            alerta("Erro!", "Nenhum vídeo em reprodução!");
            return;
        }

        //enquanto o tempo atual do getTimeVideo nao é atualizado
        playerPanel.getTransportControlPanel().stop();
//        try {
//            Thread.sleep(300);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(_jfPrincipal.class.getName()).log(Level.SEVERE, null, ex);
//        }



        DefaultMutableTreeNode nodeSelect = (DefaultMutableTreeNode) jtTopicos.getLastSelectedPathComponent();

        if (nodeSelect != null) {

            DefaultMutableTreeNode newTopic = new DefaultMutableTreeNode("00:" + playerPanel.getTransportControlPanel().getPositionLabel().getText() + " - " + "novo tópico");
            jtreeModelTopicos.insertNodeInto(newTopic, nodeSelect, nodeSelect.getChildCount());
            TreeNode[] nodes = jtreeModelTopicos.getPathToRoot(newTopic);
            TreePath treepath = new TreePath(nodes);
            jtTopicos.scrollPathToVisible(treepath);
            jtTopicos.setSelectionPath(treepath);
            jtTopicos.startEditingAtPath(treepath);
        } else {
            gerarRoot();
        }






    }//GEN-LAST:event_btnNovoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (ExcluirJtree(jtTopicos, jtreeModelTopicos)) {
            return;
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        DAOIndex topicos = new DAOIndex();
        DAOSync slides = new DAOSync();
        DAOxml configuracoes = new DAOxml();
        boolean resp, resp1, resp2;
        if (playerPanel.getContainerPlayer().getControll() == null || jtreeModelTopicos.getRoot() == null) {
            alerta("Erro!", "não foi possível gravar a aula. \n Verifique as configurações.");
            return;
        }

        resp = topicos.gravarTopicos((TreeNode) jtTopicos.getModel().getRoot(), jTFDisciplina.getText().trim(), jTFAula.getText().trim(), playerPanel.getDir() + playerPanel.getFile().substring(0, playerPanel.getFile().indexOf(".")), jtreeModelTopicos.getRoot().toString(), 1);
        // resp = topicos.gravarTopicos((TreeNode) jtTopicos.getModel().getRoot(), jTFDisciplina.getText().trim(), jTFAula.getText().trim(), dirFinal + nomeFLV.substring(0, nomeFLV.lastIndexOf(".")), jtreeModel.getRoot().toString(), Integer.parseInt(jTFNumAula.getText().trim()));
        resp1 = true;//slides.gravarSlides(listModel, playerPanel.getDir() + playerPanel.getFile().substring(0, playerPanel.getFile().indexOf(".")));
        // resp1 = slides.gravarSlides(listModel, dirFinal + nomeFLV.substring(0, nomeFLV.lastIndexOf(".")));


        configuracoes.getAulas().setBitrate("1100.0");
        configuracoes.getAulas().setCourse(jTFDisciplina.getText().trim());
        configuracoes.getAulas().setCoursecode(jTFCodDisc.getText().trim());
        configuracoes.getAulas().setDuration(playerPanel.getTransportControlPanel().getLengthLabel().getText().trim());
        configuracoes.getAulas().setGrad_program(jTFCurso.getText().trim());
        configuracoes.getAulas().setSource(jTFIsntituicao.getText().trim());
        configuracoes.getAulas().setObj_filename(playerPanel.getFile());
        configuracoes.getAulas().setObj_filesize(playerPanel.getFileSize());//  (Long.parseLong(sizeFlv));;
        configuracoes.getAulas().setObj_title(jTFAula.getText().trim());
        configuracoes.getAulas().setObj_type("h.264 FLV");
        configuracoes.getAulas().setProfessor(jTFProfessor.getText().trim());
        configuracoes.getAulas().setResolution(playerPanel.getContainerPlayer().getDimensao()[0], playerPanel.getContainerPlayer().getDimensao()[1]);
        configuracoes.setAulas(configuracoes.getAulas());

        configuracoes.setRm_item_index(playerPanel.getFile().substring(0, playerPanel.getFile().lastIndexOf(".")) + ".index");
        configuracoes.setRm_item_video(playerPanel.getFile().substring(0, playerPanel.getFile().lastIndexOf(".")) + ".flv");
        configuracoes.setRm_itemsync(playerPanel.getFile().substring(0, playerPanel.getFile().lastIndexOf(".")) + ".sync");
        resp2 = configuracoes.gravarXML(playerPanel.getDir() + playerPanel.getFile().substring(0, playerPanel.getFile().indexOf(".")));
        //resp2 = configuracoes.gravarXML(dirFinal + nomeFLV.substring(0, nomeFLV.lastIndexOf(".")));

        if (resp && resp1 && resp2) {
            //  JOptionPane.showMessageDialog(this, "Arquivos gravados em: \n" + playerPanel.getDir(), "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(this, "Arquivos gravados em: \n" + dirFinal, "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTFDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFDisciplinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFDisciplinaActionPerformed

    private void jTFProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFProfessorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFProfessorActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        if (VerificaAulaModificada()) {


//        VerificaAulaModificada();
            String rm_index = null;
            String rm_video = null;
            String rm_synk = null;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo xml", "xml"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
//            playerPanel.getTransportControlPanel().pause();
//            playerPanel.getTransportControlPanel().onDurationChange(0);
//            playerPanel.getTransportControlPanel().onProgressChange(0);

                File file = fileChooser.getSelectedFile();

                try {

                    FileInputStream in = new FileInputStream(file);
                    Xml2Obj xmlObj = new Xml2Obj(in);

                    jTFDisciplina.setText(xmlObj.getCourse());
                    jTFCodDisc.setText(xmlObj.getCoursecode());
                    jTFCurso.setText(xmlObj.getGrad_program());
                    jTFProfessor.setText(xmlObj.getProfessor());
                    jTFIsntituicao.setText(xmlObj.getSource());
                    jTFAula.setText(xmlObj.getObj_title());


                    for (Rm_item rm_item : xmlObj.getRm_item()) {
                        if (rm_item.getRm_type().equals("index")) {
                            rm_index = rm_item.getRm_filename();
                        }
                        if (rm_item.getRm_type().equals("video")) {
                            rm_video = rm_item.getRm_filename();
                        }
                        if (rm_item.getRm_type().equals("sync")) {
                            rm_synk = rm_item.getRm_filename();
                        }
                    }

                    //abrindo vídeo
//               setando dir e file
                    playerPanel.setDir(fileChooser.getCurrentDirectory().toString() + File.separator);
                    playerPanel.setFile(rm_video);
                    jLNomeFlv.setText(rm_video);
                    // playerPanel.addMediaLocatorAndLoad(fileChooser.getCurrentDirectory().toString() + File.separator +xmlObj.getObj_filename());


                    new Index2Obj(fileChooser.getCurrentDirectory().toString() + File.separator + rm_index, jtTopicos, jtreeModelTopicos);

                    Slides2Obj slides = new Slides2Obj(fileChooser.getCurrentDirectory().toString() + File.separator + rm_synk, jTSlides, jtreeModelSlides);
                    if (slides.getFilesNotFound().size() > 0) {
                        String msg = "";
                        String msgfinal = "";
                        int j = 0;
//                        msg.replaceAll("(\n|\r)+", " ")
                        for (int i = 0; i < slides.getFilesNotFound().size(); i++) {
                            msg += slides.getFilesNotFound().get(i) + ",  ";
                            if (j >= 4) {
                                msgfinal += msg.replaceAll("(\n|\r)+", " ") + "\n";
                                msg = "";
                                j = -1;
                            }
                            j++;
                        }

                        JOptionPane.showMessageDialog(this, "O(s) Arquivo(s) \n " + msgfinal + msg.replaceAll("(\n|\r)+", " ") + "\n" + "\n não foi (ram) encontrado(s)", "Alerta", JOptionPane.ERROR_MESSAGE);
                    }
                    //                Index2Obj index = new Index2Obj(fileChooser.getCurrentDirectory().toString() + File.separator + rmItemIndex.getRm_filename());
//                System.out.println("--->"+index.getMain_title());




                    File video = new File(fileChooser.getCurrentDirectory().toString() + File.separator + rm_video);
                    if (video.exists()) {
//                    playerPanel.getTransportControlPanel().pause();

                        playerPanel.addMediaLocatorAndLoad(URLUtils.createUrlStr(video));
                        playerPanel.getTransportControlPanel().start();

                    } else {
                        // playerPanel.getComponent(0).remove(playerPanel.getTransportControlPanel().getComponent(0));


                        // getContentPane().remove(playerPanel.getTransportControlPanel());
//                    Container contentPane = getContentPane();
//                    playerPanel.setSize(330, 360);
//                    playerPanel.setBounds(35, 101, 330, 320);
//                    contentPane.add(playerPanel);
                        alerta("erro", "Vídeo não encontrado");
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (salvarAula()) {
        }


    }

    private boolean salvarAula() throws HeadlessException {
        DAOIndex topicos = new DAOIndex();
        DAOSync slides = new DAOSync();
        DAOxml configuracoes = new DAOxml();
        boolean respTopicos;
        boolean respSlides;
        boolean resp2 = false;
        if (playerPanel.getContainerPlayer().getControll() == null || jtreeModelTopicos.getRoot() == null) {
            alerta("Erro!", "não foi possível gravar a aula. \n Verifique as configurações.");
            return false;
        }
        playerPanel.getTransportControlPanel().stop();
        DAOIndex.setMsgn("");
        respTopicos = topicos.gravarTopicos((TreeNode) jtTopicos.getModel().getRoot(), jTFDisciplina.getText().trim(), jTFAula.getText().trim(), playerPanel.getDir() + playerPanel.getFile().substring(0, playerPanel.getFile().indexOf(".")), jtreeModelTopicos.getRoot().toString(), 1);
        //        if (respTopicos) {
        //            GravarArquivo.salvarArquivo(topicos.getXml(), playerPanel.getDir() + playerPanel.getFile().substring(0, playerPanel.getFile().indexOf(".")) + ".index");
        //        }
        // resp = topicos.gravarTopicos((TreeNode) jtTopicos.getModel().getRoot(), jTFDisciplina.getText().trim(), jTFAula.getText().trim(), dirFinal + nomeFLV.substring(0, nomeFLV.lastIndexOf(".")), jtreeModel.getRoot().toString(), Integer.parseInt(jTFNumAula.getText().trim()));
        //  respSlides = slides.gravarSlides(listModel, playerPanel.getDir() + playerPanel.getFile().substring(0, playerPanel.getFile().indexOf(".")));
        DAOSync.setMsgn("");
        respSlides = slides.gravarSlides((TreeNode) jTSlides.getModel().getRoot());
        //        if (respSlides) {
        //            GravarArquivo.salvarArquivo(slides.getXml(), playerPanel.getDir() + playerPanel.getFile().substring(0, playerPanel.getFile().indexOf(".")) + ".sync");
        //        }
        // resp1 = slides.gravarSlides(listModel, dirFinal + nomeFLV.substring(0, nomeFLV.lastIndexOf(".")));
        configuracoes.getAulas().setBitrate("1100.0");
        configuracoes.getAulas().setCourse(jTFDisciplina.getText().trim());
        configuracoes.getAulas().setCoursecode(jTFCodDisc.getText().trim());
        configuracoes.getAulas().setDuration(playerPanel.getTransportControlPanel().getLengthLabel().getText().trim());
        configuracoes.getAulas().setGrad_program(jTFCurso.getText().trim());
        configuracoes.getAulas().setSource(jTFIsntituicao.getText().trim());
        configuracoes.getAulas().setObj_filename(playerPanel.getFile());
        configuracoes.getAulas().setObj_filesize(playerPanel.getFileSize()); //  (Long.parseLong(sizeFlv));;
        configuracoes.getAulas().setObj_title(jTFAula.getText().trim());
        configuracoes.getAulas().setObj_type("h.264 FLV");
        configuracoes.getAulas().setProfessor(jTFProfessor.getText().trim());
        configuracoes.getAulas().setResolution(playerPanel.getContainerPlayer().getDimensao()[0], playerPanel.getContainerPlayer().getDimensao()[1]);
        configuracoes.setAulas(configuracoes.getAulas());
        configuracoes.setRm_item_index(playerPanel.getFile().substring(0, playerPanel.getFile().lastIndexOf(".")) + ".index");
        configuracoes.setRm_item_video(playerPanel.getFile().substring(0, playerPanel.getFile().lastIndexOf(".")) + ".flv");
        configuracoes.setRm_itemsync(playerPanel.getFile().substring(0, playerPanel.getFile().lastIndexOf(".")) + ".sync");
        if (respTopicos && respSlides) {
            GravarArquivo.salvarArquivo(topicos.getXml(), playerPanel.getDir() + playerPanel.getFile().substring(0, playerPanel.getFile().indexOf(".")) + ".index");
            GravarArquivo.salvarArquivo(slides.getXml(), playerPanel.getDir() + playerPanel.getFile().substring(0, playerPanel.getFile().indexOf(".")) + ".sync");
            resp2 = configuracoes.gravarXML(playerPanel.getDir() + playerPanel.getFile().substring(0, playerPanel.getFile().indexOf(".")));
            //resp2 = configuracoes.gravarXML(dirFinal + nomeFLV.substring(0, nomeFLV.lastIndexOf(".")));
        }
        if (respTopicos && respSlides && resp2) {
            //  JOptionPane.showMessageDialog(this, "Arquivos gravados em: \n" + playerPanel.getDir(), "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(this, "Arquivos gravados em: \n" + playerPanel.getDir(), "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            aulaModificada = false;
            return true;
        }
        if (!respTopicos) {
            alerta("Erro!", "não foi possível gravar a aula. \n Tópico com tempo fora do intervalo, verifique.\n" + DAOIndex.getMsgn());
            return false;
        }
        if (!respSlides) {
            alerta("Erro!", "não foi possível gravar a aula. \n Slide com tempo fora do intervalo, verifique.\n" + DAOSync.getMsgn());
            return false;
        }
        return false;


    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
//        playerPanel.getTransportControlPanel().pause();
//        playerPanel.getTransportControlPanel().onDurationChange(0);
//        playerPanel.getTransportControlPanel().onProgressChange(0);
//        playerPanel.getContainerPlayer().close();
//        playerPanel.getContainerPlayer().deallocate();
//        playerPanel.getContainerPlayer().setMediaTime(new Time(0));
//        playerPanel.getContainerPlayer().setPosition(0);
//        playerPanel.getContainerPlayer().getVisualComponent().setBackground(Color.red);

        playerPanel.onOpenFile(jLNomeFlv);
        playerPanel.getTransportControlPanel().start();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        if (VerificaAulaModificada()) {
            this.setVisible(false);
            Runtime.getRuntime().exit(0);
        }

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        _jfSobre ajuda = new _jfSobre();
        ajuda.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        Desktop desktop = null;
        //Primeiro verificamos se é possível a integração com o desktop
        if (!Desktop.isDesktopSupported()) {
            throw new IllegalStateException("Acesso ao browser negado!");
        }

        desktop = Desktop.getDesktop();
        //Agora vemos se é possível disparar o browser default.
        if (!desktop.isSupported(Desktop.Action.BROWSE)) {
            throw new IllegalStateException("Browser padrão não encontrado!");
        }

        //Pega a URI de um componente de texto.
        URI uri = null;
        try {
            uri = new URI("http://sites.google.com/a/ice.ufjf.br/se-edad/download-1/Manual_FS_SE-EDAD.pdf");
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

        //Dispara o browser default, que pode ser o Explorer, Firefox ou outro.
        try {
            desktop.browse(uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jTFDisciplinaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFDisciplinaKeyTyped
        if (!aulaModificada) {
            aulaModificada = true;
        }
    }//GEN-LAST:event_jTFDisciplinaKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (VerificaAulaModificada()) {
            this.setVisible(false);
            Runtime.getRuntime().exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    public static void main(String args[]) {


        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new _jfPrincipal() {

//                    @Override
//                    public void abrirArquivo(String arquivo) {
//                        throw new UnsupportedOperationException("Not supported yet.");
//                    }
                    public void valueChanged(TreeSelectionEvent e) {
                        if (e != null) {
                            System.out.println("Listener");
                        }
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                }.setVisible(true);

            }
        });


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapturar;
    private javax.swing.JButton btnCapturarTempos;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLNomeFlv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTFAula;
    private javax.swing.JTextField jTFCodDisc;
    private javax.swing.JTextField jTFCurso;
    private javax.swing.JTextField jTFDisciplina;
    private javax.swing.JTextField jTFIsntituicao;
    private javax.swing.JTextField jTFProfessor;
    private javax.swing.JTree jTSlides;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTree jtTopicos;
    // End of variables declaration//GEN-END:variables
}


