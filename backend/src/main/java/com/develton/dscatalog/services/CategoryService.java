package com.develton.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.develton.dscatalog.dto.CategoryDTO;
import com.develton.dscatalog.entities.Category;
import com.develton.dscatalog.repositories.CategoryRepository;
import com.develton.dscatalog.services.exeptions.EntityNotFoundException;

@Service //definindo que essa classe é de serviço
		//gerencia as instancias de dependencias do spring

public class CategoryService {
	
	@Autowired //tag faz com que o spring gerencie uma dependencia valida do categoryrepository
	private CategoryRepository repository;
	
	@Transactional(readOnly = true) //define que esse metodo esteja envolvido numa transação com o banco, para garantirmos a integridade da transação
	//readOnly=true melhora a performace do BD , para que ele não fique travado só pra leitura
	public List<CategoryDTO> findAll(){
		List<Category> list =  repository.findAll();
		
		//pegando cada elemento do list e aplicando a função lambda contida na função map(map aplica uma função a cada elemento da lista original)
		//ou seja a função transforma o elemento x em categoryDTO
		List<CategoryDTO> listDTO = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList()); // collectors tranforma de string pra lista
		
		return listDTO;
	}
	
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		//o retorno do findById é um tipo optional
		Optional<Category> obj = repository.findById(id);
		//Category entity = obj.get(); // o get obtem aquilo que está dentro do optional , nesse caso a entidade
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		//orelseThrow nos permite definir uma chamada de exceção 
		//o metodo tenta acessar a category se a entidade não existir , retorna a exeção
		return new CategoryDTO(entity); //retornando o objeto mudando o tipo de category para dto
	}

	@Transactional(readOnly = true)
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);//save salva a nova entidade e retorno a referencia dela
		
		return new CategoryDTO(entity);
	}
}
