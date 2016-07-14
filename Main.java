package org.larp.JavaConsoleLast;

public class Main {
	
	public static void main(String[] args) {
		try{
		Console console = new Console();
		console.start();
	} catch (Exception e){
		e.printStackTrace();
		}
	}
}
