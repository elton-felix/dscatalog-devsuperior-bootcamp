package com.develton.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.develton.dscatalog.entities.Product;

//a interface criada  extendendo a JpaRepository ja nos da muitas funcionalidades de acesso a dados no BD
@Repository //registrando como um componente injetavel pelo mecanismo de injeção de dependencia e serão gerenciados pelos spring
public interface ProductRepository extends JpaRepository<Product, Long>{

}
