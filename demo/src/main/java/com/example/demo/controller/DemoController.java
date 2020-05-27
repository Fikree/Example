package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Demo;
import com.example.demo.service.DemoService;

@RestController
public class DemoController {

	@Autowired
	private DemoService demoService;

	@GetMapping(value = "/search")
	public List<Demo> searchByContent(String content) {
		return demoService.searchByContent(content);
	}

	@GetMapping(value = "/get/{id}")
	public Demo get(@PathVariable("id") int id) {
		return demoService.get(id);
	}

	@PostMapping(value = "/add")
	public void add(@RequestBody Demo demo) {
		demoService.add(demo);
	}

	@PutMapping(value = "/edit/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void edit(@PathVariable("id") int id, @RequestBody Demo demo) {
		demoService.edit(id, demo);
	}

	@DeleteMapping(value = "/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") int id) {
		demoService.delete(id);
	}

	@GetMapping(value = "/getAll")
	public List<Demo> getAll() {
		return demoService.getAll();
	}

	@PostMapping(value = "/addAll")
	@ResponseStatus(HttpStatus.OK)
	public void addAll(@RequestBody List<Demo> demos) {
		demoService.addAll(demos);
	}

	@DeleteMapping("/deleteAll")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAll() {
		demoService.deleteAll();
	}

}