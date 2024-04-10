package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

public class DefaultCalculator extends JFrame {

	private JTextField texto;
	private String opAnt;
	private String num1;
	private Controller _ctrl;

	public DefaultCalculator(Controller ctrl) {
		super("Mi Calculadora");
		this._ctrl = ctrl;
		opAnt = "";
		num1 = "";
		initGUI();
	}

	public JButton crearBotonNum(String text) {
		JButton b = new JButton(text);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				texto.setText(texto.getText() + e.getActionCommand());
			}
		});
		return b;
	}

	public JButton crearBotonOp(String text) {
		JButton b = new JButton(text);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (opAnt == "") {
					num1 = texto.getText();
					texto.setText(texto.getText() + e.getActionCommand());
					opAnt = e.getActionCommand();

				} else {
					String num2 = texto.getText().split(Pattern.quote(opAnt))[1];
					double resultado = _ctrl.executeOperacion(num1, num2, opAnt);
					num1 = "" + resultado;
					if (e.getActionCommand() == "=") {
						texto.setText("" + resultado);
						opAnt = "";
					} else {
						texto.setText("" + resultado + e.getActionCommand());
						opAnt = e.getActionCommand();
					}

				}
			}
		});
		return b;
	}

	public void initGUI() {

		JPanel panelPrincipal = new JPanel(new BorderLayout());

		JPanel panelSuperior = new JPanel();
		texto = new JTextField(30);
		texto.setFont(new Font("SansSerif", Font.BOLD, 20));
		texto.setEditable(false);

		JButton botonBorrar = new JButton("C");
		botonBorrar.addActionListener((ActionEvent e) -> {
			texto.setText("");
		});
		botonBorrar.setToolTipText("Esto borra");

		/*
		 * texto.addKeyListener(new KeyListener() {
		 * 
		 * @Override public void keyTyped(KeyEvent e) { // TODO Auto-generated method
		 * stub e.getKeyChar() KeyEvent.
		 * botonBorrar.getActionListeners()[0].actionPerformed(new ActionEvent(e, 0,
		 * "C"));
		 * 
		 * }
		 * 
		 * @Override public void keyReleased(KeyEvent e) { // TODO Auto-generated method
		 * stub
		 * 
		 * }
		 * 
		 * @Override public void keyPressed(KeyEvent e) { // TODO Auto-generated method
		 * stub
		 * 
		 * } });
		 */

		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.LINE_AXIS));

		JPanel panelNumeros = new JPanel(new GridLayout(3, 3));

		for (int i = 3; i > 0; i--) {
			for (int j = 3; j > 0; j--) {
				panelNumeros.add(crearBotonNum("" + (3 * i - (j - 1))));
			}
		}
		JPanel panelOperaciones = new JPanel(new GridLayout(3, 2));

		panelOperaciones.add(crearBotonOp("*"));
		panelOperaciones.add(crearBotonOp("/"));
		panelOperaciones.add(crearBotonOp("+"));
		panelOperaciones.add(crearBotonOp("-"));
		panelOperaciones.add(crearBotonNum("0"));
		panelOperaciones.add(crearBotonOp("="));

		panelInferior.add(panelNumeros);
		panelInferior.add(panelOperaciones);

		panelSuperior.add(texto);
		panelSuperior.add(botonBorrar);

		panelPrincipal.add(panelSuperior, BorderLayout.PAGE_START);
		panelPrincipal.add(panelInferior, BorderLayout.CENTER);
		this.setContentPane(panelPrincipal);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocation(200, 200);
		this.setVisible(true);

	}
}