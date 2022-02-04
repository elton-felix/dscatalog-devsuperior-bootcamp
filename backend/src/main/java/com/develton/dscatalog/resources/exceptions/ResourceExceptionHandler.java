package com.develton.dscatalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.develton.dscatalog.services.exeptions.ResourceNotFoundException;

@ControllerAdvice //permite que essa classe intercepte alguma inserção na camada de resouce
//e faz o seu tratamento
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)//passamos o nome da excepção para q ele saiba o tipo de exceção que ira ser interceptada
	public ResponseEntity<StandardError> entityNotFounder(ResourceNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());//now do Instant pega o horario atual
		err.setStatus(HttpStatus.NOT_FOUND.value());//passando o erro 404 e pegando o numero inteiro dele com o value
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());//pegando o caminho da requisição que foi feita
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		//passando o status do erro e o body retorna o corpo da resposta
	}
}
//o servletrequest tem as informações da requisição
//sempre que a exeção for essa que criamos , ela será redirecionada para esse metodo
//o status permite que possa ser customizado oq será retornado