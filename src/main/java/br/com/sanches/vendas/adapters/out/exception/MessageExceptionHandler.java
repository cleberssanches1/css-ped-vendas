package br.com.sanches.vendas.adapters.out.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Data;

@Data
public class MessageExceptionHandler {

    private Integer status;	
	private String mensagem;
	private List<HashMap<String, String>> erros = new ArrayList<HashMap<String, String>>();
	
	public MessageExceptionHandler(LocalDateTime dataHora, Integer status, String mensagem) {
		super();
		this.status = status;
		this.mensagem = mensagem;
	}

	public MessageExceptionHandler(LocalDateTime dataHora, String mensagem) {
		super();
		this.mensagem = mensagem;
	}
	
	public MessageExceptionHandler() {
		super();
	}
	 
}
	 
 
