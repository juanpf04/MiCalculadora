package launcher;

import javax.swing.SwingUtilities;

import controller.Controller;
import model.Calculator;
import view.ConsoleCalculator;
import view.DefaultCalculator;
import view.ModernCalculator;

public class Main {

	public static void main(String[] args) {
		Calculator calculator = new Calculator();

		Controller controller = new Controller(calculator);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new DefaultCalculator(controller);
				new ModernCalculator(controller);
			}
		});

		new ConsoleCalculator(controller);
	}

}
