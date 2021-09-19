package br.com.sistemaveiculos.services.exceptions;

public class VeiculoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public VeiculoException(String mensagem) { 
		super(mensagem);
	}
	
	public VeiculoException(String mensagem, Exception ex) { 
		super(mensagem, ex);
	} 

}
