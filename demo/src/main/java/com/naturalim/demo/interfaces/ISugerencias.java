package com.naturalim.demo.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.naturalim.demo.models.Sugerencias;

@Repository
public interface ISugerencias extends CrudRepository<Sugerencias, Integer> {
	
}
