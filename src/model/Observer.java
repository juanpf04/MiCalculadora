package model;

public interface Observer {

	public void onResult(double result);

	public void onError(String message);
}
