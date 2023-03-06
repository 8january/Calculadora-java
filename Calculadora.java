package zero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Calculadora extends JFrame {

	Font fontePadrao = new Font("Times", Font.BOLD, 23);
	Font fonteOperacoes = new Font("Times", Font.BOLD, 35);
	Color background = new Color(40, 40, 40);
	Color operacaoCor = new Color(252, 186, 3);

	private JPanel panelResultado, panelNumeros;

	private JTextField campoResultado;

	private JButton clear, porcentagem, parenteses, maisOuMenos;
	private JButton[] numeros = new JButton[9];
	private JButton nZero, resultado, virgula;
	private JButton soma, subtracao, divisao, multiplicacao;

	private Dimension opSize = new Dimension(120, 120);

	private Dimension dimensoes = Toolkit.getDefaultToolkit().getScreenSize();

	private String contaMatematica = "";
	private Boolean parenteseAtivado = false;

	private Resultado contaResultado = new Resultado();

	private int screenWidth = (int) dimensoes.getWidth();
	private int screenHeight = (int) dimensoes.getHeight();

	public Calculadora() {
		new JPanel();
		panelResultado = new JPanel();
		panelNumeros = new JPanel();

		setLayoutAndSizeAndLocation();
		setResultado();
		setNumeros();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void setLayoutAndSizeAndLocation() {
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(494, 737);
		this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
	}

	private void setResultado() {

		panelResultado.setBackground(background);
		panelResultado.setSize(500, 100);
		panelResultado.setLocation(0, 0);
		panelResultado.setLayout(null);

		campoResultado = new JTextField();
		campoResultado.setBounds(0, 0, 479, 100);
		campoResultado.setEditable(false);
		campoResultado.setFont(fonteOperacoes);
		campoResultado.setBackground(background);
		campoResultado.setForeground(Color.white);

		panelResultado.add(campoResultado);

		this.add(panelResultado);
	}

	private void setNumeros() {
		panelNumeros.setBackground(background);
		panelNumeros.setSize(500, 600);
		panelNumeros.setLocation(0, 100);

		panelNumeros.setLayout(null);

		setAllButtons();
		this.add(panelNumeros);
	}

	private void setAllButtons() {

		setAcoes();
		setNumerosNoPanel();
		setOperacoes();

		panelNumeros.add(clear);
		panelNumeros.add(parenteses);
		panelNumeros.add(porcentagem);
		panelNumeros.add(maisOuMenos);

		panelNumeros.add(nZero);
		panelNumeros.add(virgula);
		panelNumeros.add(resultado);

		panelNumeros.add(soma);
		panelNumeros.add(subtracao);
		panelNumeros.add(divisao);
		panelNumeros.add(multiplicacao);

	}

	void setAcoes() {

		NumberListener numberListener = new NumberListener();

		clear = new JButton("C");
		clear.setSize(opSize);
		clear.setFont(fontePadrao);
		clear.setBackground(background);
		clear.setForeground(Color.white);
		clear.setLocation(0, 0);
		clear.addActionListener(numberListener);

		porcentagem = new JButton("%");
		porcentagem.setSize(opSize);
		porcentagem.setFont(fontePadrao);
		porcentagem.setBackground(background);
		porcentagem.setForeground(Color.white);
		porcentagem.setLocation(120, 0);
		porcentagem.addActionListener(numberListener);

		parenteses = new JButton("(");
		parenteses.setSize(opSize);
		parenteses.setFont(fontePadrao);
		parenteses.setBackground(background);
		parenteses.setForeground(Color.white);
		parenteses.setLocation(240, 0);
		parenteses.addActionListener(numberListener);

		maisOuMenos = new JButton("+/-");
		maisOuMenos.setSize(opSize);
		maisOuMenos.setFont(fontePadrao);
		maisOuMenos.setBackground(background);
		maisOuMenos.setForeground(Color.white);
		maisOuMenos.setLocation(0, 480);
		maisOuMenos.addActionListener(numberListener);

		nZero = new JButton("0");
		nZero.setSize(opSize);
		nZero.setFont(fontePadrao);
		nZero.setBackground(background);
		nZero.setForeground(Color.white);
		nZero.setLocation(120, 480);
		nZero.addActionListener(numberListener);

		resultado = new JButton("=");
		resultado.setSize(opSize);
		resultado.setFont(fontePadrao);
		resultado.setBackground(background);
		resultado.setForeground(operacaoCor);
		resultado.setLocation(360, 480);
		resultado.addActionListener(numberListener);

		virgula = new JButton(",");
		virgula.setSize(opSize);
		virgula.setFont(fontePadrao);
		virgula.setBackground(background);
		virgula.setForeground(Color.white);
		virgula.setLocation(240, 480);
		virgula.addActionListener(numberListener);
	}

	void setOperacoes() {

		NumberListener numberListener = new NumberListener();

		soma = new JButton("+");
		soma.setSize(opSize);
		soma.setFont(fonteOperacoes);
		soma.setForeground(operacaoCor);
		soma.setBackground(background);
		soma.addActionListener(numberListener);
		soma.setLocation((int) (panelNumeros.getWidth() - opSize.getWidth()) - 20, 0);

		subtracao = new JButton("-");
		subtracao.setSize(opSize);
		subtracao.setFont(fonteOperacoes);
		subtracao.setBackground(background);
		subtracao.setForeground(operacaoCor);
		subtracao.addActionListener(numberListener);
		subtracao.setLocation((int) (panelNumeros.getWidth() - opSize.getWidth()) - 20, 120);

		divisao = new JButton("÷");
		divisao.setSize(opSize);
		divisao.setFont(fonteOperacoes);
		divisao.setBackground(background);
		divisao.setForeground(operacaoCor);
		divisao.addActionListener(numberListener);
		divisao.setLocation((int) (panelNumeros.getWidth() - opSize.getWidth()) - 20, 240);

		multiplicacao = new JButton("×");
		multiplicacao.setSize(opSize);
		multiplicacao.setFont(fonteOperacoes);
		multiplicacao.setBackground(background);
		multiplicacao.setForeground(operacaoCor);
		multiplicacao.addActionListener(numberListener);
		multiplicacao.setLocation((int) (panelNumeros.getWidth() - opSize.getWidth()) - 20, 360);
	}

	void setNumerosNoPanel() {

		NumberListener numberListener = new NumberListener();

		for (int i = 0; i < 9; i++) {
			numeros[i] = new JButton(Integer.toString(9 - i));
			numeros[i].addActionListener(numberListener);
			numeros[i].setFont(fontePadrao);
			numeros[i].setBackground(background);
			numeros[i].setForeground(Color.white);

			if (i < 3)
				numeros[i].setBounds(0 + i * 120, 120, 120, 120);
			else if (i >= 3 && i < 6)
				numeros[i].setBounds(0 + (i - 3) * 120, 240, 120, 120);
			else
				numeros[i].setBounds(0 + (i - 6) * 120, 360, 120, 120);

			panelNumeros.add(numeros[i]);
		}
	}

	private class NumberListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String valor = e.getActionCommand();

			if (ehUmaOperacao(valor) && terminaComOperacao(contaMatematica))
				valor = "";

			if (valor.equals(","))
				if (terminaComOperacao(contaMatematica))
					valor = "";

			switch (valor) {
			case "C":
				valor = clear();
				break;
			case "(":
				valor = parenteses();
				break;
			case "+/-":
				valor = "";
				break;
			default:
			}

			contaMatematica += valor;

			if (iniciaComOperacao(contaMatematica))
				contaMatematica = contaMatematica.substring(1);

			campoResultado.setText(" " + contaMatematica.replace("=", ""));

			if (valor.equals("=")) {
				calcular();
			}

		}

		private String clear() {
			contaMatematica = "";
			return "";
		}

		private String parenteses() {

			@SuppressWarnings("unused")
			String valor = null;

			if (parenteseAtivado) {
				valor = ")";
				parenteseAtivado = false;
			} else {
				valor = "(";
				parenteseAtivado = true;
			}
			return "";
		}

		private void calcular() {
			String resultadoCalculado = contaResultado.calcula(contaMatematica);
			campoResultado.setText(" " + resultadoCalculado);
			contaMatematica = resultadoCalculado;
		}
	}

	private boolean ehUmaOperacao(String valor) {

		if (valor.equals("+") || valor.equals("-") || valor.equals("÷") || valor.equals("×") || valor.equals("%"))
			return true;

		return false;
	}

	private boolean iniciaComOperacao(String conta) {

		if (conta.length() == 0)
			return false;

		conta = Character.toString(conta.charAt(0));

		if (conta.equals("+") || conta.equals("-") || conta.equals("÷") || conta.equals("×") || conta.equals("%"))
			return true;

		return false;
	}

	private boolean terminaComOperacao(String conta) {
		if (conta.length() == 0)
			return false;

		conta = Character.toString(conta.charAt(conta.length() - 1));

		if (conta.equals("+") || conta.equals("-") || conta.equals("÷") || conta.equals("×") || conta.equals("%"))
			return true;

		return false;
	}

}
