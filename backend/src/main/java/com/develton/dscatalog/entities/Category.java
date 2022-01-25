package com.develton.dscatalog.entities;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable{
	
	/**
	 * Serializable é um padrão java dizendo que 
	 * o objeto possa ser convertido em bytes.
	 * serve para ser salvo em arquivos , passar em redes etc...
	 * colocamos por boa medida
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	
	public Category() {
		
	}

	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	//hash code é o um metodo padrão do eclipse para comparar objetos
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	
	//metodo padrão para comparar objetos de uma forma mais lenta , porem mais certa
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	
	
}
