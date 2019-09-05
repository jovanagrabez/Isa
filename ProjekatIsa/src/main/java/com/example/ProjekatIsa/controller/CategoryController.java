package com.example.ProjekatIsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.model.Category;
import com.example.ProjekatIsa.repository.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryRepository catRepository;
	
	@RequestMapping(value="getAll",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	
	public List<Category> getAll(){
		return catRepository.findAll();
	}

}
