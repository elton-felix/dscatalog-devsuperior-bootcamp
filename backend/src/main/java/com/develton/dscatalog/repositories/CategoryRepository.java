package com.develton.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.develton.dscatalog.entities.Category;

//a interface criada  extendendo a JpaRepository ja nos da muitas funcionalidades de acesso a dados no BD
@Repository //registrando como um componente injetavel pelo mecanismo de injeção de dependencia e serão gerenciados pelos spring
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
