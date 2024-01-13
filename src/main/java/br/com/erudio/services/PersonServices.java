package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exceptions.ResourceNotFoundExceptions;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository personRepository;

	@Autowired
	PersonMapper mapper;

	public List<PersonVO> findAll() {

		logger.info("Finding all people!");

		return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {
		logger.info("Finding on person!");

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptions("No records found this id"));
		return DozerMapper.parseObject(entity, PersonVO.class);
	}

	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");

		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}

	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating one person with V2!");

		var entity = mapper.convertVOToEntity(person);
		var vo = mapper.convertEntityToVO(personRepository.save(entity));
		return vo;
	}

	public PersonVO update(PersonVO person) {
		logger.info("Updating one person!");

		var entity = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundExceptions("No records found this id"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}

	public void delete(Long id) {
		logger.info("Deleting one person!");

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptions("No records found this id"));
		personRepository.delete(entity);
	}

}
