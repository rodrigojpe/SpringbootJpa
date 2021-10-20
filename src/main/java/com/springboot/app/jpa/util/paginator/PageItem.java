package com.springboot.app.jpa.util.paginator;

public class PageItem {
	
	private int numero;
	
	private boolean actual;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isActual() {
		return actual;
	}

	public void setActual(boolean actual) {
		this.actual = actual;
	}

	public PageItem(int numero, boolean actual) {
	
		this.numero = numero;
		this.actual = actual;
	}

	public PageItem() {

	}
	
	
	
	
	
	
	

}
