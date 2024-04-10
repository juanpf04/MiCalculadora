package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import model.Observer;

public class DefaultCalculator extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField _text;
	private String _operation;
	private String _param1;
	private Controller _ctrl;

	public DefaultCalculator(Controller ctrl) {
		super("Mi Calculadora");
		this._ctrl = ctrl;
		this._operation = "";
		this._param1 = "";
		this.initGUI();
		this._ctrl.addObserver(this);
	}

	public JButton crearBotonNum(String text) {
		JButton b = new JButton(text);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_text.setText(_text.getText() + e.getActionCommand());
			}
		});
		return b;
	}

	public JButton crearBotonOp(String text) {
		JButton b = new JButton(text);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (_operation == "") {
					_param1 = _text.getText();
					_text.setText(_text.getText() + e.getActionCommand());
					_operation = e.getActionCommand();

				} else {
					String param2 = _text.getText().split(Pattern.quote(_operation))[1];
					_ctrl.executeOperacion(_param1, param2, _operation);
					_operation = "";
				}
			}
		});
		return b;
	}

	public void initGUI() {

		JPanel panelPrincipal = new JPanel(new BorderLayout());

		JPanel panelSuperior = new JPanel();
		this._text = new JTextField(30);
		this._text.setFont(new Font("SansSerif", Font.BOLD, 20));
		this._text.setEditable(false);

		JButton botonBorrar = new JButton("C");
		botonBorrar.addActionListener((ActionEvent e) -> {
			_text.setText("");
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

		panelSuperior.add(this._text);
		panelSuperior.add(botonBorrar);

		panelPrincipal.add(panelSuperior, BorderLayout.PAGE_START);
		panelPrincipal.add(panelInferior, BorderLayout.CENTER);
		this.setContentPane(panelPrincipal);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;

		setLocation(x, y);
		this.setVisible(true);

	}

	@Override
	public void onResult(double result) {
		this._param1 = "" + result;
		this._text.setText("" + result);
		this._operation = "";

	}

	@Override
	public void onError(String message) {
		JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}