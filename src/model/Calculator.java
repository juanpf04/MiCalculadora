package model;

import java.util.LinkedList;
import java.util.List;

public class Calculator implements Observable<Observer> {

	private double _param1;
	private double _param2;
	private double _result;

	private List<Observer> _observers;

	public Calculator() {
		this._observers = new LinkedList<Observer>();
	}

	public double getParam1() {
		return _param1;
	}

	public void setParam1(double param1) {
		this._param1 = param1;
	}

	public double getParam2() {
		return _param2;
	}

	public void setParam2(double param2) {
		this._param2 = param2;
	}

	public double getResult() {
		return _result;
	}

	public void setResult(double result) {
		this._result = result;
	}

	public void add() {
		this.setResult(this.getParam1() + this.getParam2());
		notify_on_result();
	}

	public void sub() {
		this.setResult(this.getParam1() - this.getParam2());
		notify_on_result();
	}

	public void mul() {
		this.setResult(this.getParam1() * this.getParam2());
		notify_on_result();
	}

	public void div() {
		if (this.getParam2() == 0)
			notify_on_error("You cannot divided by zero >:(");
		else {
			this.setResult(this.getParam1() / this.getParam2());
			notify_on_result();
		}
	}

	@Override
	public void addObserver(Observer o) {
		this._observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		this._observers.remove(o);
	}

	private void notify_on_result() {
		for (Observer o : this._observers)
			o.onResult(this.getResult());
	}

	private void notify_on_error(String error) {
		for (Observer o : this._observers)
			o.onError(error);
	}
}
