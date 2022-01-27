package com.develton.dscatalog.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develton.dscatalog.entities.Category;
import com.develton.dscatalog.services.CategoryService;

//o resource ele implementa o controlador rest

@RestController //definindo que essa classe é um controlador rest, que irá responder as requisições
@RequestMapping(value = "/categories")//definindo a rota rest desse recurso
public class CategoryResource {
	
	@Autowired //deixando que o spring regerencie e instancie as dependencias necessarias
	private CategoryService service;
	
	//objeto do spring que irá encapsular uma resposta http
	//colocamos o tipo dos dados do corpo do metodo entre <>
	@GetMapping //configurando que esse metodo é um webservice / endpoint
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = service.findAll();
		
		return ResponseEntity.ok().body(list);//o ok fala que a resposta é 200 que significa que a requisição foi realizada com sucesso
	}
}