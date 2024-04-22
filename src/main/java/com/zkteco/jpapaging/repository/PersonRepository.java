package com.zkteco.jpapaging.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.zkteco.jpapaging.model.Person;


@Primary
public interface PersonRepository extends PagingAndSortingRepository<Person, Integer>{

	<S extends Person> List<S> saveAll(List<Person> people);

	//void save(Person person);

	//void saveAll(List<Person> people);
	
}
