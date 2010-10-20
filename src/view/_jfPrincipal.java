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
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;
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
import model.Slide;
import model.Slides2Obj;
import model.Xml2Obj;
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



        this.setTitle("Ferramenta de Sincronização  - Sistema RIO - V 0.62");
        //define o tamanho do video
        //dimension = new Dimension(jpContainerVideo.getWidth(), jpContainerVideo.getHeight());
        listModel = new DefaultListModel();
        jListSlides.setModel(listModel);
        jtreeModel = new DefaultTreeModel(null);
        jtTopicos.setEditable(true);
        jtTopicos.setSelectionRow(0);
        WindowUtilities.setNativeLookAndFeel();
        gerarRoot();


        // jtTopicos.setModel(new javax.swing.tree.DefaultTreeModel(null));

        MouseListener ml = new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                //     System.out.println("Mouse Evento "+ jtTopicos.getInvokesStopCellEditing());
                if (jtTopicos.isEditing()) {
                    System.out.println("sair");
                }
                int selRow = jtTopicos.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = jtTopicos.getPathForLocation(e.getX(), e.getY());
                if (selRow != -1) {
                }
            }
        };
        jtTopicos.addMouseListener(ml);

        KeyListener key = new KeyListener() {

            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped " + e);
                System.out.println("Mouse Evento " + jtTopicos.getInvokesStopCellEditing());
            }

            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed " + e);
                System.out.println("Mouse Evento " + jtTopicos.getInvokesStopCellEditing());
            }

            public void keyReleased(KeyEvent e) {
                System.out.println("KeyEvent " + e);
                System.out.println("Mouse Evento " + jtTopicos.getInvokesStopCellEditing());
            }
        };

        //jtTopicos.addKeyListener(key);

        jtTopicos.addTreeSelectionListener(
                new javax.swing.event.TreeSelectionListener() {

                    public void valueChanged(TreeSelectionEvent e) {
                        System.out.println("no selecyt " + e);
                    }
                });






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
        jTFIsntituicao = new javax.swing.JTextField();
        jTFNumAula = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLNomeFlv = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jListSlides.setBackground(new java.awt.Color(240, 240, 240));
        jListSlides.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jListSlides.setFont(new java.awt.Font("Times New Roman", 0, 11));
        jListSlides.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListSlides);

        btnCapturar.setFont(new java.awt.Font("Times New Roman", 0, 11));
        btnCapturar.setText("Nova transparência");
        btnCapturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapturarActionPerformed(evt);
            }
        });

        btnRemover.setFont(new java.awt.Font("Times New Roman", 0, 11));
        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        jtTopicos.setFont(new java.awt.Font("Times New Roman", 0, 11));
        jtTopicos.setModel(null);
        jtTopicos.setAutoscrolls(true);
        jScrollPane2.setViewportView(jtTopicos);

        btnNovo.setFont(new java.awt.Font("Times New Roman", 0, 11));
        btnNovo.setText("Novo Tópico");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 11));
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

        btnCapturarTempos.setFont(new java.awt.Font("Times New Roman", 0, 11));
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

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 11));
        jLabel1.setText("Curso:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 11));
        jLabel2.setText("Aula:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 11));
        jLabel3.setText("Número da Aula:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel11.setText("Código da Disciplina");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 11));
        jLabel12.setText("Disciplina:");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 11));
        jLabel14.setText("Professor:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 11));
        jLabel15.setText("Instituição");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 11));
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

        jTFIsntituicao.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTFNumAula.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton7.setFont(new java.awt.Font("Times New Roman", 0, 11));
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
                        .addComponent(jTFIsntituicao, javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addComponent(jTFIsntituicao, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jButton1.setText("Abrir Aula");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(7, 7, 7))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(325, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addGap(121, 121, 121)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
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
        for (int i = 0; i < listModel.size(); i++) {

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
        } else {

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
        resp1 = slides.gravarSlides(listModel, dirFinal + nomeFLV.substring(0, nomeFLV.indexOf(".")));


        configuracoes.getAulas().setBitrate("1100.0");
        configuracoes.getAulas().setCourse(jTFDisciplina.getText().trim());
        configuracoes.getAulas().setCoursecode(jTFCodDisc.getText().trim());
        configuracoes.getAulas().setDuration(playerPanel.getTransportControlPanel().getLengthLabel().getText().trim());
        configuracoes.getAulas().setGrad_program(jTFCurso.getText().trim());
        configuracoes.getAulas().setSource(jTFIsntituicao.getText().trim());
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
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo flv", "flv"));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            URI uri = fileChooser.getSelectedFile().toURI();

            try {
                URL url = uri.toURL();
                nomeFLV = fileChooser.getSelectedFile().getName();
                jLNomeFlv.setText(nomeFLV);

                dirFinal = fileChooser.getCurrentDirectory().toString() + File.separator;
                System.out.println("url: " + fileChooser.getCurrentDirectory());
                System.out.println("dir final: " + dirFinal);

                File flv = fileChooser.getSelectedFile();


                sizeFlv = "" + flv.length();
                System.out.println("nome flv: " + nomeFLV + " - " + sizeFlv);

            } catch (MalformedURLException ex) {
                System.out.println(ex);
            }
        }





    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTFProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFProfessorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFProfessorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo xml", "xml"));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();

            // Abindo a nota fiscal
           // StringBuffer xml = new StringBuffer();
            try {
                FileInputStream in = new FileInputStream(file);
                Xml2Obj xmlObj = new Xml2Obj(in);

                jTFDisciplina.setText(xmlObj.getCourse());
                jTFCodDisc.setText(xmlObj.getCoursecode());
                jTFCurso.setText(xmlObj.getCourse());
                jTFProfessor.setText(xmlObj.getProfessor());
                jTFIsntituicao.setText(xmlObj.getSource());
                jTFAula.setText(xmlObj.getObj_title());
                //jTFNumAula.setText(xmlObj.get); é no index

                 xmlObj.getRm_item().get(2);

                Slides2Obj slides = new Slides2Obj("G:\\UFJF\\AULAS_PRONTAS\\dcc119_aula3\\dcc119_aula3.sync");
                
                for (int i = 0; i< slides.getSlide().size(); i++){
                    Slide slide = slides.getSlide().get(i);
                    Double tempo = Double.parseDouble(slide.getTime());
                    listModel.addElement(""+tempo.intValue() / 60+":"+tempo.intValue() % 60+" - "+slide.getRelative_path());
                   // listModel.addElement(slide.getTime()+" - "+slide.getRelative_path());
                }







                

                


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }





        }

    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLNomeFlv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jListSlides;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFAula;
    private javax.swing.JTextField jTFCodDisc;
    private javax.swing.JTextField jTFCurso;
    private javax.swing.JTextField jTFDisciplina;
    private javax.swing.JTextField jTFIsntituicao;
    private javax.swing.JTextField jTFNumAula;
    private javax.swing.JTextField jTFProfessor;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTree jtTopicos;
    // End of variables declaration//GEN-END:variables
}


