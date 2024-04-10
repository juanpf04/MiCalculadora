package controller;

import java.util.LinkedList;
import java.util.List;

import model.Calculator;
import model.Observable;
import model.Observer;

public class Controller implements Observable<Observer> {

	private Calculator _calculator;

	private List<Observer> _observers;

	public Controller(Calculator calculator) {
		this._calculator = calculator;
		this._observers = new LinkedList<Observer>();
	}

	public void executeOperacion(String param1, String param2, String op) {
		try {
			this._calculator.setParam1(Double.parseDouble(param1));
			this._calculator.setParam2(Double.parseDouble(param2));

			switch (op) {
			case "+":
				this._calculator.add();
				break;
			case "-":
				this._calculator.add();
				break;
			case "*":
				this._calculator.add();
				break;
			case "/":
				this._calculator.add();
				break;
			default:
				notify_on_error("Unkown operation");
				break;
			}
		} catch (NumberFormatException e) {
			notify_on_error("Parameters must be numbers");
		}
	}

	public void addObserver(Observer o) {
		this._observers.add(o);
		this._calculator.addObserver(o);
	}

	public void removeObserver(Observer o) {
		this._observers.remove(o);
		this._calculator.removeObserver(o);
	}

	private void notify_on_error(String error) {
		for (Observer o : this._observers)
			o.onError(error);
	}
}
