package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Demo;
import com.example.demo.repositories.DemoRepository;

@Service
public class DemoService {

	@Autowired
	private DemoRepository demoRepository;
	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Demo> searchByContent(String content) {
		return demoRepository.searchByContent(content);
	}

	public Demo get(int id) {
		return demoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public List<Demo> getAll() {
		return demoRepository.findAll();
	}

	public void add(Demo demo) {
		demoRepository.save(demo);
	}

	public void edit(int id, Demo demo) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id).and("version").is(demo.getVersion()));
		Update update = new Update();
		update.set("content", demo.getContent());
		if (mongoTemplate.findAndModify(query, update, Demo.class) == null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
	}

	public void delete(int id) {
		demoRepository.deleteById(id);
	}

	public void addAll(List<Demo> demos) {
		demoRepository.saveAll(demos);
	}

	public void deleteAll() {
		demoRepository.deleteAll();
	}
}