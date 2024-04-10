package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		JPanel mainPanel = new JPanel(new FlowLayout());
		this.setContentPane(mainPanel);

		this._param1 = new JTextField();
		Dimension d = this._param1.getPreferredSize();
//		this._param1.setMinimumSize(new Dimension((d.width - 20) / 2, d.height * 2));
//		this._param1.setMaximumSize(new Dimension(d.width - 20, d.height * 2));
//		this._param1.setPreferredSize(new Dimension(d.width - 40, d.height * 2));
		this._param1.setToolTipText("First parameter");
		this.add(this._param1);

		this._param2 = new JTextField();
		d = this._param2.getPreferredSize();
//		this._param2.setMinimumSize(new Dimension((d.width - 20) / 2, d.height * 2));
//		this._param2.setMaximumSize(new Dimension(d.width - 20, d.height * 2));
//		this._param2.setPreferredSize(new Dimension(d.width - 40, d.height * 2));
		this._param2.setToolTipText("Second parameter");
		this.add(this._param2);

		JButton addButton = new JButton("+");
		addButton.setToolTipText("Addition");
		addButton.addActionListener(this);
		this.add(addButton);

		JButton subButton = new JButton("-");
		subButton.setToolTipText("Substraction");
		subButton.addActionListener(this);
		this.add(subButton);

		JButton mulButton = new JButton("*");
		mulButton.setToolTipText("Multiplication");
		mulButton.addActionListener(this);
		this.add(mulButton);

		JButton divButton = new JButton("/");
		divButton.setToolTipText("Division");
		divButton.addActionListener(this);
		this.add(divButton);

		this._result = new JTextField();
		d = this._result.getPreferredSize();
//		this._result.setMinimumSize(new Dimension((d.width - 20) / 2, d.height * 2));
//		this._result.setMaximumSize(new Dimension(d.width - 20, d.height * 2));
//		this._result.setPreferredSize(new Dimension(d.width - 40, d.height * 2));
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
		JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String param1 = this._param1.getText();
		String param2 = this._param2.getText();
		String operation = e.getActionCommand();
		this._ctrl.executeOperacion(param1, param2, operation);
	}

}
