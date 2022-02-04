package com.develton.dscatalog.services.exeptions;

//definindo uma exeção herdando o runtimeexeption ou exeption
//se definirmos exeption a exeção precisa obrigatoriamente ser tratada
public class DatabaseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String msg) {
		super(msg);//repassando a mensagem pelo construtor da super classe
	}

}
