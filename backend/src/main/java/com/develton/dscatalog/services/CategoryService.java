package com.develton.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.develton.dscatalog.entities.Category;
import com.develton.dscatalog.repositories.CategoryRepository;

@Service //definindo que essa classe é de serviço
		//gerencia as instancias de dependencias do spring

public class CategoryService {
	
	@Autowired //tag faz com que o spring gerencie uma dependencia valida do categoryrepository
	private CategoryRepository repository;
	
	@Transactional(readOnly = true) //define que esse metodo esteja envolvido numa transação com o banco, para garantirmos a integridade da transação
	//readOnly=true melhora a performace do BD , para que ele não fique travado só pra leitura
	public List<Category> findAll(){
		return repository.findAll();
	}
}
