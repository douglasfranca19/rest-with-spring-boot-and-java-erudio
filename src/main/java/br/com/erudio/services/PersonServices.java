package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.ResourceNotFoundExceptions;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository personRepository;

	public List<Person> findAll() {

		logger.info("Finding all people!");

		return personRepository.findAll();
	}

	public Person findById(Long id) {
		logger.info("Finding on person!");

		return personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptions("No records found this id"));
	}

	public Person create(Person person) {
		logger.info("Creating one person!");

		return personRepository.save(person);
	}

	public Person update(Person person) {
		logger.info("Updating one person!");

		var entity = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundExceptions("No records found this id"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		return personRepository.save(person);
	}

	public void delete(Long id) {
		logger.info("Deleting one person!");

		Person entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptions("No records found this id"));
		personRepository.delete(entity);
	}

}
