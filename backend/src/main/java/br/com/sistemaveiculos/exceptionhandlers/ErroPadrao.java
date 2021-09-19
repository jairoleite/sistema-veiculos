package br.com.sistemaveiculos.exceptionhandlers;

import java.time.Instant;

public class ErroPadrao {

	private Instant timestamp;
	private Integer status;
	private String erro;
	private Exception erroDesenvolvedor;
	private String mensagem;
	private String caminho;
	
	
	public Instant getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getErro() {
		return erro;
	}
	public void setErro(String erro) {
		this.erro = erro;
	}
	
	public Exception getErroDesenvolvedor() {
		return erroDesenvolvedor;
	}
	public void setErroDesenvolvedor(Exception erroDesenvolvedor) {
		this.erroDesenvolvedor = erroDesenvolvedor;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
	
}
