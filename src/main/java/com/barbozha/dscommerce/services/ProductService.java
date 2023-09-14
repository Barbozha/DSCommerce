package com.barbozha.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barbozha.dscommerce.dto.ProductDTO;
import com.barbozha.dscommerce.entities.Product;
import com.barbozha.dscommerce.repositories.ProductRepository;

@Service

public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true) // para não dar lock no banco de dados
	public ProductDTO findById(Long id) {
		Product product = productRepository.findById(id).get();
		return new ProductDTO(product);
	}
}
