package com.zkteco.jpapaging.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zkteco.jpapaging.model.Person;
import com.zkteco.jpapaging.repository.PersonRepository;

@RestController
@RequestMapping("/api/people")
public class PersonController {

	private final PersonRepository repository;
	
	public PersonController(@Qualifier("personRepository") PersonRepository repository) {
		this.repository=repository;
	}
	
	@GetMapping
	public Page<Person> findAll(@RequestParam int page, @RequestParam int size){
		PageRequest pr = PageRequest.of(page, size);
		return repository.findAll(pr);
	}
	
}
