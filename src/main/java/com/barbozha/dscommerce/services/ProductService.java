package com.barbozha.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Transactional(readOnly = true)
	// import org.springframework.data.domain.Pageable;
	// import org.springframework.data.domain.Page;
	// Será paginada os resultados
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> prod = productRepository.findAll(pageable);
		return prod.map(x -> new ProductDTO(x));
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = productRepository.save(entity);
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		Product entity = productRepository.getReferenceById(id);
		copyDtoToEntity(dto, entity);
		entity = productRepository.save(entity);
		return new ProductDTO(entity);
	}

	@Transactional
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
	}

}
