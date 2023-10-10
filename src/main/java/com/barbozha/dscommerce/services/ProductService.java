package com.barbozha.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.barbozha.dscommerce.dto.ProductDTO;
import com.barbozha.dscommerce.entities.Product;
import com.barbozha.dscommerce.repositories.ProductRepository;
import com.barbozha.dscommerce.services.exceptions.DatabaseException;
import com.barbozha.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service

public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true) // para não dar lock no banco de dados
	public ProductDTO findById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso Não Encontrado"));
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
		try {
			Product entity = productRepository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = productRepository.save(entity);
			return new ProductDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso Não Encontrado");
		}
		
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso Não ENcontrado");
		}
		try {
			productRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de Integridade Referencial");
		}
	
	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
	}

}
