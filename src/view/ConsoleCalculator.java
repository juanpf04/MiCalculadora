package view;

import java.util.Scanner;

import controller.Controller;
import model.Observer;

public class ConsoleCalculator implements Observer {

	private Controller _ctrl;
	private Scanner _scanner;

	public ConsoleCalculator(Controller ctrl) {
		this._ctrl = ctrl;
		this._ctrl.addObserver(this);
		this._scanner = new Scanner(System.in);
		this.update();
		this._scanner.close();
	}

	private void update() {
		while (true) {
			System.out.print("First parameter : ");
			String param1 = this._scanner.nextLine();
			System.out.print("Second parameter: ");
			String param2 = this._scanner.nextLine();
			System.out.print("Operator: ");
			String operation = this._scanner.nextLine();
			this._ctrl.executeOperacion(param1, param2, operation);
		}
	}

	@Override
	public void onResult(double result) {
		System.out.println("Result: " + result + System.lineSeparator());
//		this.update(); // FIXME si ponemos esto, las vistas no avanzan nunca mas ya que solo ejecuta esto, hilos?
	}

	@Override
	public void onError(String message) {
		System.out.println("[ERROR] " + message + System.lineSeparator());
//		this.update();
	}
}
