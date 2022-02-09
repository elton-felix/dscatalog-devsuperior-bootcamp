package com.develton.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.develton.dscatalog.dto.ProductDTO;
import com.develton.dscatalog.entities.Product;
import com.develton.dscatalog.repositories.ProductRepository;
import com.develton.dscatalog.services.exeptions.DatabaseException;
import com.develton.dscatalog.services.exeptions.ResourceNotFoundException;

@Service //definindo que essa classe é de serviço
		//gerencia as instancias de dependencias do spring

public class ProductService {
	
	@Autowired //tag faz com que o spring gerencie uma dependencia valida do categoryrepository
	private ProductRepository repository;
	
	@Transactional(readOnly = true) //define que esse metodo esteja envolvido numa transação com o banco, para garantirmos a integridade da transação
	//readOnly=true melhora a performace do BD , para que ele não fique travado só pra leitura
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
		Page<Product> list =  repository.findAll(pageRequest);
		
		//pegando cada elemento do list e aplicando a função lambda contida na função map(map aplica uma função a cada elemento da lista original)
		//ou seja a função transforma o elemento x em categoryDTO
		return list.map(x -> new ProductDTO(x));
		
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		//o retorno do findById é um tipo optional
		Optional<Product> obj = repository.findById(id);
		//Product entity = obj.get(); // o get obtem aquilo que está dentro do optional , nesse caso a entidade
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		//orelseThrow nos permite definir uma chamada de exceção 
		//o metodo tenta acessar a category se a entidade não existir , retorna a exeção
		return new ProductDTO(entity, entity.getCategories()); //retornando o objeto mudando o tipo de category para dto
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		//entity.setName(dto.getName());
		entity = repository.save(entity);//save salva a nova entidade e retorno a referencia dela
		
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
		Product entity = repository.getOne(id);
		//entity.setName(dto.getName());
		entity = repository.save(entity);
		return new ProductDTO(entity);
		
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found "+ id);
		}
	}

	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e){
			throw new ResourceNotFoundException("Id not found "+ id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}

//o One se diferencia do findById
//o byId ele vai até o banco e tras o objeto que procuramos
//o One não toca no banco , ele instancia um obj provisorio com os dados e o id do obj
//quando mandarmos salvar é que ele vai no banco de dados
