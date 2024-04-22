package com.zkteco.jpapaging.data;

import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.zkteco.jpapaging.model.Address;
import com.zkteco.jpapaging.model.Person;
import com.zkteco.jpapaging.repository.PersonRepository;


@Component
public class SampleDataLoader implements CommandLineRunner{

	
	private Logger logger = LoggerFactory.getLogger(SampleDataLoader.class);
	private final PersonRepository repository;
	private final Faker faker;
	
	public SampleDataLoader(PersonRepository repository, Faker faker) {
		this.repository=repository;
		/* this.faker=new Faker(); */
		this.faker=faker;
	}
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("Loading Sample Data...");
		/*
		 * Person person = new Person("Kushal","Das","9876543210", "test@gmail.com",new
		 * Address("street","city","state","zip")); repository.save(person);
		 */
		
		//create 100 rows of people in the database
		List<Person> people = IntStream.rangeClosed(1, 100)
		.mapToObj(i -> new Person(
				faker.name().firstName(),
				faker.name().lastName(),
				faker.phoneNumber().cellPhone(),
				faker.internet().emailAddress(),
				new Address(
						faker.address().streetAddress(),
						faker.address().city(),
						faker.address().state(),
						faker.address().zipCode()
						)
				))
		.toList();
		
		repository.saveAll(people);
		
	}

}
