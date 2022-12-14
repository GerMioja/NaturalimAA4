package com.naturalim.demo.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.naturalim.demo.models.Productos;

@Repository
public interface IProductos extends CrudRepository<Productos, Integer> {
	
}
