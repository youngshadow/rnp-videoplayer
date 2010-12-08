/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

/**
 *
 * @author alexandre
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class CalculadoraFrame extends JFrame
{
	private JPanel pnlPrincipal;
	private JTextField txtVisor;
	private JPanel pnlBotoes;

	//Criação das ações dos botões.
	private BotaoNumericoAction acaoBotao1 = new BotaoNumericoAction(1);
	private BotaoNumericoAction acaoBotao2 = new BotaoNumericoAction(2);
	private BotaoNumericoAction acaoBotao3 = new BotaoNumericoAction(3);
	private BotaoNumericoAction acaoBotao4 = new BotaoNumericoAction(4);
	private BotaoNumericoAction acaoBotao5 = new BotaoNumericoAction(5);
	private BotaoNumericoAction acaoBotao6 = new BotaoNumericoAction(6);
	private BotaoNumericoAction acaoBotao7 = new BotaoNumericoAction(7);
	private BotaoNumericoAction acaoBotao8 = new BotaoNumericoAction(8);
	private BotaoNumericoAction acaoBotao9 = new BotaoNumericoAction(9);
	private BotaoNumericoAction acaoBotao0 = new BotaoNumericoAction(0);

	public CalculadoraFrame()
	{
		super("Calculadora");
		setContentPane(getPnlPrincipal());
		setSize(200,250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private JPanel getPnlPrincipal() {
		if (pnlPrincipal != null)
			return pnlPrincipal;

		pnlPrincipal = new JPanel(new BorderLayout());
		pnlPrincipal.add(getTxtVisor(), BorderLayout.NORTH);
		pnlPrincipal.add(getPnlBotoes(), BorderLayout.CENTER);
		registrarAcoesDoTeclado(pnlPrincipal);
		return pnlPrincipal;
	}

	private JTextField getTxtVisor() {
		if (txtVisor != null)
			return txtVisor;

		txtVisor = new JTextField();
		txtVisor.setEditable(false);
		txtVisor.setHorizontalAlignment(JTextField.RIGHT);
		return txtVisor;
	}

	private JPanel getPnlBotoes() {
		if (pnlBotoes != null)
			return pnlBotoes;
		pnlBotoes = new JPanel();
		pnlBotoes.setLayout(new GridLayout(4,4));

		//Associamos os botões as suas respectivas ações.
		//Isso só associará a ação ao clique do botão.
		pnlBotoes.add(new JButton(acaoBotao7));
		pnlBotoes.add(new JButton(acaoBotao8));
		pnlBotoes.add(new JButton(acaoBotao9));
		pnlBotoes.add(new JButton("/"));

		pnlBotoes.add(new JButton(acaoBotao4));
		pnlBotoes.add(new JButton(acaoBotao5));
		pnlBotoes.add(new JButton(acaoBotao6));
		pnlBotoes.add(new JButton("*"));

		pnlBotoes.add(new JButton(acaoBotao1));
		pnlBotoes.add(new JButton(acaoBotao2));
		pnlBotoes.add(new JButton(acaoBotao3));
		pnlBotoes.add(new JButton("-"));

		pnlBotoes.add(new JButton(acaoBotao0));
		pnlBotoes.add(new JButton("C"));
		pnlBotoes.add(new JButton("="));
		pnlBotoes.add(new JButton("+"));

		return pnlBotoes;
	}

	//Ações para o botão numérico. Ela simplesmente concatena o número ao final
	//do texto do visor.
	private class BotaoNumericoAction extends AbstractAction
	{
		private int numero;

		public BotaoNumericoAction(int numero)
		{
			super(Integer.toString(numero));
			this.numero = numero;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			getTxtVisor().setText(getTxtVisor().getText() + numero);
		}
	}

	private void registrarAcoesDoTeclado(JPanel painel) {
		//Damos um nome para cada ação. Esse nome é útil pois mais de
		//uma tecla pode ser associada a cada ação, como veremos abaixo
		ActionMap actionMap = painel.getActionMap();
		actionMap.put("botao1", acaoBotao1);
		actionMap.put("botao2", acaoBotao2);
		actionMap.put("botao3", acaoBotao3);
		actionMap.put("botao4", acaoBotao4);
		actionMap.put("botao5", acaoBotao5);
		actionMap.put("botao6", acaoBotao6);
		actionMap.put("botao7", acaoBotao7);
		actionMap.put("botao8", acaoBotao8);
		actionMap.put("botao9", acaoBotao9);
		actionMap.put("botao0", acaoBotao0);
		painel.setActionMap(actionMap);

		//Pegamos o input map que ocorre sempre que a janela atual está em foco
		InputMap imap = painel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);

		//Associamos o pressionar das teclas (keystroke) aos eventos.
		//O nome do KeyStroke pode ser obtido através da classe KeyEvent.
		//Lá está cheio de constantes como KeyEvent.VK_NUMPAD1.
		//Essa string é o nome sem o VK_

		//Teclas da parte de cima do teclado.
		imap.put(KeyStroke.getKeyStroke("1"), "botao1");
		imap.put(KeyStroke.getKeyStroke("2"), "botao2");
		imap.put(KeyStroke.getKeyStroke("3"), "botao3");
		imap.put(KeyStroke.getKeyStroke("4"), "botao4");
		imap.put(KeyStroke.getKeyStroke("5"), "botao5");
		imap.put(KeyStroke.getKeyStroke("6"), "botao6");
		imap.put(KeyStroke.getKeyStroke("7"), "botao7");
		imap.put(KeyStroke.getKeyStroke("8"), "botao8");
		imap.put(KeyStroke.getKeyStroke("9"), "botao9");
		imap.put(KeyStroke.getKeyStroke("0"), "botao0");

		//Botões do teclado numérico
		imap.put(KeyStroke.getKeyStroke("NUMPAD1"), "botao1");
		imap.put(KeyStroke.getKeyStroke("NUMPAD2"), "botao2");
		imap.put(KeyStroke.getKeyStroke("NUMPAD3"), "botao3");
		imap.put(KeyStroke.getKeyStroke("NUMPAD4"), "botao4");
		imap.put(KeyStroke.getKeyStroke("NUMPAD5"), "botao5");
		imap.put(KeyStroke.getKeyStroke("NUMPAD6"), "botao6");
		imap.put(KeyStroke.getKeyStroke("NUMPAD7"), "botao7");
		imap.put(KeyStroke.getKeyStroke("NUMPAD8"), "botao8");
		imap.put(KeyStroke.getKeyStroke("NUMPAD9"), "botao9");
		imap.put(KeyStroke.getKeyStroke("NUMPAD0"), "botao0");
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run() {
				new CalculadoraFrame().setVisible(true);
			}
		});
	}
}
