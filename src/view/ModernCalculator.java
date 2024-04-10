package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import model.Observer;

public class ModernCalculator extends JFrame implements Observer, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Controller _ctrl;

	private JTextField _param1;
	private JTextField _param2;
	private JTextField _result;

	public ModernCalculator(Controller ctrl) {
		this._ctrl = ctrl;
		this.initGUI();
		this._ctrl.addObserver(this);
	}

	public void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		this.setContentPane(mainPanel);

		JPanel top = new JPanel();
		this._param1 = new JTextField();
		Dimension d = this._param1.getPreferredSize();
		this._param1.setPreferredSize(new Dimension(70, d.height));
		this._param1.setToolTipText("First parameter");
		top.add(this._param1);

		this._param2 = new JTextField();
		d = this._param2.getPreferredSize();
		this._param2.setPreferredSize(new Dimension(70, d.height));
		this._param2.setToolTipText("Second parameter");
		top.add(this._param2);

		this.add(top);

		JPanel buttons = new JPanel();

		JButton addButton = new JButton("+");
		addButton.setToolTipText("Addition");
		addButton.addActionListener(this);
		buttons.add(addButton);

		JButton subButton = new JButton("-");
		subButton.setToolTipText("Substraction");
		subButton.addActionListener(this);
		buttons.add(subButton);

		JButton mulButton = new JButton("*");
		mulButton.setToolTipText("Multiplication");
		mulButton.addActionListener(this);
		buttons.add(mulButton);

		JButton divButton = new JButton("/");
		divButton.setToolTipText("Division");
		divButton.addActionListener(this);
		buttons.add(divButton);

		this.add(buttons);

		this._result = new JTextField();
		d = this._result.getPreferredSize();
		this._result.setPreferredSize(new Dimension(120, d.height));
		this._result.setToolTipText("Operation result");

		this.add(this._result);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 3;

		setLocation(x, y);
		this.setVisible(true);
	}

	@Override
	public void onResult(double result) {
		this._result.setText("" + result);
	}

	@Override
	public void onError(String message) {
		//JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE); // si lo ponemos sale dos veces el mensaje de error
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String param1 = this._param1.getText();
		String param2 = this._param2.getText();
		String operation = e.getActionCommand();
		this._ctrl.executeOperacion(param1, param2, operation);
	}

}
