package com.develton.dscatalog.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.develton.dscatalog.dto.CategoryDTO;
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
	public ResponseEntity<List<CategoryDTO>> findAll(){
		List<CategoryDTO> list = service.findAll();
		
		return ResponseEntity.ok().body(list);//o ok fala que a resposta é 200 que significa que a requisição foi realizada com sucesso
	}
	
	@GetMapping(value = "/{id}") //configurando a URL que este edpoint erá responder
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){//anotation casa a variavel passada a URL com a que está no argumento
		CategoryDTO dto = service.findById(id);
		
		return ResponseEntity.ok().body(dto);//o ok fala que a resposta é 200 que significa que a requisição foi realizada com sucesso
	}
	
	@PostMapping
	public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto){//anotação reconhece o obj enviado na requisição e casa com o obj que está em argumento
		dto = service.insert(dto);
		
		//dando o status 201(e tendo sido inserido) e retornando no cabeçalho da resposta o reader location contendo o endereço da requisição
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")//path dizendo qual a estrutura do endereço criado
				.buildAndExpand(dto.getId()).toUri();

		return ResponseEntity.created(uri).body(dto);
		//ultilizamos o created passando a uri que foi instanciada para dizer que é uma criação e retornar o status correto
	}
}
