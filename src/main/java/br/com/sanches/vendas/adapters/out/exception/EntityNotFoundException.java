package br.com.sanches.vendas.adapters.out.exception;

public class EntityNotFoundException  extends RuntimeException {
 
	private static final long serialVersionUID = -5438404186550088697L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}