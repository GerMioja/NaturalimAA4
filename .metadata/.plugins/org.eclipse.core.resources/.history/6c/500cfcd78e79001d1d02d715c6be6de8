package com.naturalim.demo.repositorio;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.naturalim.demo.models.Productos;

@Repository
public interface ProductosDAO extends CrudRepository<Productos, Integer>{
	public List<Productos> findByNombre( String nombre);
}
