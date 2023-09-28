package com.barbozha.dscommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbozha.dscommerce.dto.ProductDTO;
import com.barbozha.dscommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping(value = "/{id}")
	public ProductDTO findById(@PathVariable Long id) {
		return service.findById(id);
		//ProductDTO dto = service.findById(id);
		//return dto;
	}
	
	@GetMapping
	//import org.springframework.data.domain.Page;
	//import o org.springframework.data.domain.Pageable;
	public Page<ProductDTO> findAll(Pageable pageable){
		return service.findAll(pageable);
	}
	
	//Inserindo dados no banco de dados
	@PostMapping
	public ProductDTO insert(@RequestBody ProductDTO dto) {
		dto = service.insert(dto);
		return dto;
	}
}