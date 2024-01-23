package br.com.sanches.vendas.adapters.out.exception;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandlingController {

	@ResponseBody
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<MessageExceptionHandler> companyNotFound(EntityNotFoundException exception) {

		StringBuilder sb = new StringBuilder("Retorno da consulta");

		MessageExceptionHandler error = new MessageExceptionHandler(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				sb.toString());

		HashMap<String, String> json = new HashMap<String, String>();

		json.put("Registro", exception.getMessage());
		error.getErros().add(json);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MessageExceptionHandler> standardError(Exception e) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new MessageExceptionHandler(LocalDateTime.now(), "Erro inesperado" + " - " + e.getMessage()));
	}
}
