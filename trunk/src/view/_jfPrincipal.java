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
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.Time;


import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import jtree.WindowUtilities;
import model.DAOIndex;
import model.DAOSync;
import model.DAOxml;
import videoplayer.ManipList;
import videoplayer.VideoController;
import net.sf.fmj.ui.application.PlayerPanel;

/**
 *
 * @author bisaggio
 */
public abstract class _jfPrincipal extends javax.swing.JFrame implements TreeSelectionListener {

    public void alerta(String titulo, String msg) {
        JOptionPane.showMessageDialog(this, msg, titulo, JOptionPane.ERROR_MESSAGE);
        return;
    }

    public abstract void abrirArquivo(String arquivo);
    Dimension dimension;
    //videoJMF video;
    boolean isPlay = false;
    public static URL url = null;
    ManipList slideList = new ManipList();
    DefaultListModel listModel;
    FileDialog file = null;
    private final DefaultTreeModel jtreeModel;
    VideoController video;
    String nomeFLV;
    String dirFinal;
    String sizeFlv;
    private PlayerPanel playerPanel;

    /** Creates new form _jfPrincipal */
    public _jfPrincipal() {
        initComponents();

        Container contentPane = getContentPane();
        playerPanel = new PlayerPanel();
        playerPanel.setSize(330, 360);
        playerPanel.setBounds(5, 120, 330, 360);
        contentPane.add(playerPanel);



        this.setTitle("Ferramenta de Sincronização  - Sistema RIO - V 0.28");
        //define o tamanho do video
        //dimension = new Dimension(jpContainerVideo.getWidth(), jpContainerVideo.getHeight());
        listModel = new DefaultListModel();
        jListSlides.setModel(listModel);
        jtreeModel = new DefaultTreeModel(null);
        jtTopicos.setEditable(true);
        jtTopicos.setSelectionRow(0);
        //WindowUtilities.setNativeLookAndFeel();
        gerarRoot();


        // jtTopicos.setModel(new javax.swing.tree.DefaultTreeModel(null));

        MouseListener ml = new MouseAdapter() {
     public void mousePressed(MouseEvent e) {
    //     System.out.println("Mouse Evento "+ jtTopicos.getInvokesStopCellEditing());
         if (jtTopicos.isEditing()){
             System.out.println("sair");
         }
         int selRow = jtTopicos.getRowForLocation(e.getX(), e.getY());
         TreePath selPath = jtTopicos.getPathForLocation(e.getX(), e.getY());
         if(selRow != -1) {
         }
     }
 };
 jtTopicos.addMouseListener(ml);

 KeyListener key = new KeyListener(){

            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped " + e);
                System.out.println("Mouse Evento "+ jtTopicos.getInvokesStopCellEditing());
            }

            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed "+ e);
                System.out.println("Mouse Evento "+ jtTopicos.getInvokesStopCellEditing());
            }

            public void keyReleased(KeyEvent e) {
                System.out.println("KeyEvent "+e);
                System.out.println("Mouse Evento "+ jtTopicos.getInvokesStopCellEditing());
            }

 };

 //jtTopicos.addKeyListener(key);

jtTopicos.addTreeSelectionListener(
     new  javax.swing.event.TreeSelectionListener() {
          public void valueChanged(TreeSelectionEvent e) {
               System.out.println("no selecyt "+e);
          }
     }
);






    }

    protected void gerarRoot() {
        DefaultMutableTreeNode rootTopic = new DefaultMutableTreeNode("Roteiro");
        jtTopicos.setModel(jtreeModel);
        jtreeModel.setRoot(rootTopic);
        TreeNode[] nodes = jtreeModel.getPathToRoot(rootTopic);
        TreePath treepath = new TreePath(nodes);
        jtTopicos.scrollPathToVisible(treepath);
        jtTopicos.setSelectionPath(treepath);
        jtTopicos.startEditingAtPath(treepath);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtfDisciplina = new javax.swing.JTextField();
        jtfAula = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jtfCurso = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtfProfessor = new javax.swing.JTextField();
        jtfInstituic = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtfCodCurso = new javax.swing.JFormattedTextField();
        jtfNumAula = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jlNomeFlv1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jlTamanhoFlv = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListSlides = new javax.swing.JList();
        btnCapturar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtTopicos = new javax.swing.JTree();
        btnNovo = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnCapturarTempos = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
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
        jTFIsntituição = new javax.swing.JTextField();
        jTFNumAula = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLNomeFlv = new javax.swing.JLabel();

        jDialog1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog1.setTitle("Propriedades");
        jDialog1.setLocationByPlatform(true);
        jDialog1.setMinimumSize(new java.awt.Dimension(550, 400));
        jDialog1.setResizable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Propriedades"));
        jPanel3.setMinimumSize(new java.awt.Dimension(350, 450));

        jLabel4.setText("Curso:");

        jLabel5.setText("Aula:");

        jLabel6.setText("Número da Aula");

        jtfDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDisciplinaActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Salvar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel7.setText("Disciplina:");

        jtfCurso.setText(" ");
        jtfCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfCursoActionPerformed(evt);
            }
        });

        jLabel8.setText("Código do Disciplina:");

        jLabel9.setText("Professor:");

        jtfProfessor.setText(" ");

        jtfInstituic.setText(" ");
        jtfInstituic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfInstituicActionPerformed(evt);
            }
        });

        jLabel10.setText("Instituição:");

        jtfCodCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfCodCursoActionPerformed(evt);
            }
        });

        jtfNumAula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtfNumAula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNumAulaActionPerformed(evt);
            }
        });

        jLabel13.setText("Nome:");

        jButton6.setText("Arquivo .flv");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jlNomeFlv1.setText(" ");

        jLabel17.setText("Tamanho:");

        jlTamanhoFlv.setText(" ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 308, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addComponent(jLabel13)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfNumAula, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jlNomeFlv1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlTamanhoFlv, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jtfInstituic, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtfProfessor, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtfAula, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtfDisciplina, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtfCodCurso, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtfCurso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCodCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfAula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfInstituic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfNumAula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jlNomeFlv1)
                    .addComponent(jlTamanhoFlv)
                    .addComponent(jLabel17))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2)))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jListSlides.setBackground(new java.awt.Color(240, 240, 240));
        jListSlides.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jListSlides.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jListSlides.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListSlides);

        btnCapturar.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        btnCapturar.setText("Nova transparência");
        btnCapturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapturarActionPerformed(evt);
            }
        });

        btnRemover.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        jtTopicos.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jtTopicos.setModel(null);
        jtTopicos.setAutoscrolls(true);
        jScrollPane2.setViewportView(jtTopicos);

        btnNovo.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        btnNovo.setText("Novo Tópico");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jButton4.setText("Remover");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/fmj/ui/images/Save24.gif"))); // NOI18N
        jButton5.setText("Salvar Aula");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Copiar Tempos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 11))); // NOI18N

        btnCapturarTempos.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        btnCapturarTempos.setText("Copiar ");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCapturarTempos, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnCapturarTempos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Propriedades"));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel1.setText("Curso:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel2.setText("Aula:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel3.setText("Número da Aula:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel11.setText("Código do Disciplina");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel12.setText("Disciplina:");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel14.setText("Professor:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel15.setText("Instituição");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel18.setText("Nome do Arquivo:");

        jTFDisciplina.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTFDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFDisciplinaActionPerformed(evt);
            }
        });

        jTFCodDisc.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTFCurso.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTFAula.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTFProfessor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTFProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFProfessorActionPerformed(evt);
            }
        });

        jTFIsntituição.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTFNumAula.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton7.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jButton7.setText("Arquivo .flv");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLNomeFlv.setText(" ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTFAula, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNumAula, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jTFProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(jLabel15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jTFDisciplina, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCodDisc, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLNomeFlv, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTFIsntituição, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTFCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel1)
                    .addComponent(jTFCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTFDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFCodDisc, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFIsntituição, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jTFProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFAula, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jTFNumAula, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLNomeFlv)))
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btnNovo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(7, 7, 7))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(325, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRemover, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCapturar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnCapturar, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 258, Short.MAX_VALUE)
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(365, 365, 365)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapturarActionPerformed
        if (playerPanel.getContainerPlayer().getControll() == null) {

            alerta("Erro!", "Nenhum vídeo em reprodução!");
            return;
        }

        //enquanto o tempo atual do getTimeVideo nao é atualizado
         playerPanel.getTransportControlPanel().stop();

        slideList.dialogo(listModel, "00:" + playerPanel.getTransportControlPanel().getPositionLabel().getText());
        if (listModel.getSize() > 0) {
            url = slideList.ultimaURL(listModel.getSize() - 1);
        }
    }//GEN-LAST:event_btnCapturarActionPerformed

    private void btnCapturarTemposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapturarTemposActionPerformed
        for (int i = 0; i <listModel.size(); i++){
            
             DefaultMutableTreeNode nodeSelect = (DefaultMutableTreeNode) jtTopicos.getModel().getRoot();
        System.out.println("nodeSelect: " + nodeSelect);
        if (nodeSelect != null) {
     
            DefaultMutableTreeNode newTopic = new DefaultMutableTreeNode(listModel.get(i).toString().substring(0, listModel.get(i).toString().lastIndexOf(".")));
            jtreeModel.insertNodeInto(newTopic, nodeSelect, nodeSelect.getChildCount());
            TreeNode[] nodes = jtreeModel.getPathToRoot(newTopic);
            TreePath treepath = new TreePath(nodes);
            jtTopicos.scrollPathToVisible(treepath);
            jtTopicos.setSelectionPath(treepath);
            jtTopicos.startEditingAtPath(treepath);
        } else {
            gerarRoot();
        }


        }
    }//GEN-LAST:event_btnCapturarTemposActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // TODO add your handling code here:
        if (jListSlides.getSelectedIndex() != -1) {
            slideList.removeVideo(jListSlides.getSelectedValue().toString());
            listModel.removeElementAt(jListSlides.getSelectedIndex());
            url = null;
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
        System.out.println("nodeSelect: " + nodeSelect);
        if (nodeSelect != null) {

            DefaultMutableTreeNode newTopic = new DefaultMutableTreeNode("00:" + playerPanel.getTransportControlPanel().getPositionLabel().getText() + " - " + "novo tópico");
            jtreeModel.insertNodeInto(newTopic, nodeSelect, nodeSelect.getChildCount());
            TreeNode[] nodes = jtreeModel.getPathToRoot(newTopic);
            TreePath treepath = new TreePath(nodes);
            jtTopicos.scrollPathToVisible(treepath);
            jtTopicos.setSelectionPath(treepath);
            jtTopicos.startEditingAtPath(treepath);
        } else {
            gerarRoot();
        }






    }//GEN-LAST:event_btnNovoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        DefaultMutableTreeNode nodeSelected = (DefaultMutableTreeNode) jtTopicos.getLastSelectedPathComponent();

        if (nodeSelected == null) {
            JOptionPane.showMessageDialog(this, "Nenhum Tópico Selecionado", "Erro!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MutableTreeNode nodeParent = (MutableTreeNode) nodeSelected.getParent();

        if (nodeParent == null) {
//            if (JOptionPane.showConfirmDialog(this, "Gostaria de excluir o tópico raiz?", "Atenção!", 0) == 0) {
//               jtreeModel.setRoot(null);
//                return;
//            }
            alerta("Erro!", "O tópico raiz não pode ser removido!");
            return;
        }else{

        MutableTreeNode toBeSelNode = (MutableTreeNode) nodeSelected.getNextSibling();

        if (toBeSelNode == null) {
            toBeSelNode = (MutableTreeNode) nodeSelected.getPreviousSibling();

        }
        if (toBeSelNode == null) {
            toBeSelNode = nodeSelected;
        }
        TreeNode[] nodes = jtreeModel.getPathToRoot(toBeSelNode);
        TreePath path = new TreePath(nodes);
        jtTopicos.scrollPathToVisible(path);
        jtTopicos.setSelectionPath(path);
        jtreeModel.removeNodeFromParent(nodeSelected);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        DAOIndex topicos = new DAOIndex();
        DAOSync slides = new DAOSync();
        DAOxml configuracoes = new DAOxml();
        boolean resp, resp1, resp2;
        if (playerPanel.getContainerPlayer().getControll() == null || jtreeModel.getRoot() == null) {
            alerta("Erro!", "não foi possível gravar a aula. \n Verifique as configurações.");
            return;
        }

     //   resp = topicos.gravarTopicos((TreeNode) jtTopicos.getModel().getRoot(), jlCurso.getText().trim(), jlAula.getText().trim(), playerPanel.getDir() + playerPanel.getFile().substring(0, playerPanel.getFile().indexOf(".")), jtreeModel.getRoot().toString(), Integer.parseInt(jlNumAula.getText().trim()));
        resp = topicos.gravarTopicos((TreeNode) jtTopicos.getModel().getRoot(), jTFDisciplina.getText().trim(), jTFAula.getText().trim(), dirFinal + nomeFLV.substring(0, nomeFLV.indexOf(".")), jtreeModel.getRoot().toString(), Integer.parseInt(jTFNumAula.getText().trim()));
        //resp1 = slides.gravarSlides(listModel, playerPanel.getDir() + playerPanel.getFile().substring(0, playerPanel.getFile().indexOf(".")));
        resp1 = slides.gravarSlides(listModel,  dirFinal + nomeFLV.substring(0, nomeFLV.indexOf(".")));


        configuracoes.getAulas().setBitrate("1100.0");
        configuracoes.getAulas().setCourse(jTFDisciplina.getText().trim());
        configuracoes.getAulas().setCoursecode(jTFCodDisc.getText().trim());
        configuracoes.getAulas().setDuration(playerPanel.getTransportControlPanel().getLengthLabel().getText().trim());
        configuracoes.getAulas().setGrad_program(jTFCurso.getText().trim());
        configuracoes.getAulas().setSource(jTFIsntituição.getText().trim());
        configuracoes.getAulas().setObj_filename(nomeFLV);
        configuracoes.getAulas().setObj_filesize(Long.parseLong(sizeFlv));//(playerPanel.getFileSize());
        configuracoes.getAulas().setObj_title(jTFAula.getText().trim());
        configuracoes.getAulas().setObj_type("h.264 FLV");
        configuracoes.getAulas().setProfessor(jTFProfessor.getText().trim());
        configuracoes.getAulas().setResolution(playerPanel.getContainerPlayer().getDimensao()[0], playerPanel.getContainerPlayer().getDimensao()[1]);
        configuracoes.setAulas(configuracoes.getAulas());

        configuracoes.setRm_item_index(playerPanel.getFile().substring(0, playerPanel.getFile().indexOf(".")) + ".index");
        configuracoes.setRm_item_video(playerPanel.getFile().substring(0, playerPanel.getFile().indexOf(".")) + ".flv");
        configuracoes.setRm_itemsync(playerPanel.getFile().substring(0, playerPanel.getFile().indexOf(".")) + ".sync");
        //resp2 = configuracoes.gravarXML(playerPanel.getDir() + playerPanel.getFile().substring(0, playerPanel.getFile().indexOf(".")));
        resp2 = configuracoes.gravarXML(dirFinal + nomeFLV.substring(0, nomeFLV.indexOf(".")));

        if (resp && resp1 && resp2) {
            JOptionPane.showMessageDialog(this, "Arquivos gravados em: \n" + playerPanel.getDir(), "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jtfDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDisciplinaActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jtfDisciplinaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jTFDisciplina.setText(jtfCurso.getText());
        jTFCodDisc.setText(jtfCodCurso.getText());
        jTFCurso.setText(jtfDisciplina.getText());
        jTFAula.setText(jtfAula.getText());
        jTFProfessor.setText(jtfProfessor.getText());
        jTFIsntituição.setText(jtfInstituic.getText());
        jTFNumAula.setText(jtfNumAula.getText());
        jLNomeFlv.setText(jlNomeFlv1.getText());
        


        jDialog1.setVisible(false);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jtfCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfCursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfCursoActionPerformed

    private void jtfCodCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfCodCursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfCodCursoActionPerformed

    private void jtfInstituicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfInstituicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfInstituicActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jDialog1.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jtfNumAulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNumAulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNumAulaActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      System.setProperty("apple.awt.fileDialogForDirectories", "true");
        file = new FileDialog(this, "Abrir Vídeo", FileDialog.LOAD);
        file.setVisible(true);

        if (file.getFile() != null) {
            nomeFLV = file.getFile();
            dirFinal = file.getDirectory();
            System.out.println("dirFinal: " + dirFinal);
            File flv = new File(file.getDirectory() + file.getFile());
            jlNomeFlv1.setText(file.getFile());
            jlTamanhoFlv.setText("" + flv.length());
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTFDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFDisciplinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFDisciplinaActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
//            System.setProperty("apple.awt.fileDialogForDirectories", "true");
//        file = new FileDialog(this, "Abrir Vídeo", FileDialog.LOAD);
//        file.setVisible(true);
//
//        if (file.getFile() != null) {
//            nomeFLV = file.getFile();
//            dirFinal = file.getDirectory();
//            System.out.println("dirFinal: " + dirFinal);
//            File flv = new File(file.getDirectory() + file.getFile());
//            jLNomeFlv.setText(file.getFile());
//            sizeFlv = ""+flv.length();
//        }


        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo flv","flv"));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            URI uri = fileChooser.getSelectedFile().toURI();
           
            try {
                URL url = uri.toURL();
                 nomeFLV = fileChooser.getSelectedFile().getName();
                 jLNomeFlv.setText(nomeFLV);
                
                  dirFinal = fileChooser.getCurrentDirectory().toString()+File.separator;
                 System.out.println("url: "+ fileChooser.getCurrentDirectory());
                 System.out.println("dir final: " +dirFinal);

                 File flv = fileChooser.getSelectedFile();
                 
                
                 sizeFlv = ""+flv.length();
                 System.out.println("nome flv: "+nomeFLV+" - "+sizeFlv);

            } catch (MalformedURLException ex) {
                System.out.println(ex);
            }
        }





    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTFProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFProfessorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFProfessorActionPerformed

    public static void main(String args[]) {


        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new _jfPrincipal() {

                    @Override
                    public void abrirArquivo(String arquivo) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLNomeFlv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jListSlides;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFAula;
    private javax.swing.JTextField jTFCodDisc;
    private javax.swing.JTextField jTFCurso;
    private javax.swing.JTextField jTFDisciplina;
    private javax.swing.JTextField jTFIsntituição;
    private javax.swing.JTextField jTFNumAula;
    private javax.swing.JTextField jTFProfessor;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel jlNomeFlv1;
    private javax.swing.JLabel jlTamanhoFlv;
    private javax.swing.JTree jtTopicos;
    private javax.swing.JTextField jtfAula;
    private javax.swing.JFormattedTextField jtfCodCurso;
    private javax.swing.JTextField jtfCurso;
    private javax.swing.JTextField jtfDisciplina;
    private javax.swing.JTextField jtfInstituic;
    private javax.swing.JFormattedTextField jtfNumAula;
    private javax.swing.JTextField jtfProfessor;
    // End of variables declaration//GEN-END:variables



}


