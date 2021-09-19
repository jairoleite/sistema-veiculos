package br.com.sistemaveiculos.exceptionhandlers;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.sistemaveiculos.services.exceptions.VeiculoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(VeiculoException.class)
	public ResponseEntity<ErroPadrao> usuarioException(VeiculoException e, HttpServletRequest request) {
		ErroPadrao err = new ErroPadrao();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setErro("Erro ao manipular veículo");
		err.setErroDesenvolvedor(e);
		err.setMensagem(e.getMessage());
		err.setCaminho(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(err);
	}
}
