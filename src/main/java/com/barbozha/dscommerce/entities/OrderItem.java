package com.barbozha.dscommerce.entities;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();

	private Integer quantity;

	private Double price;

	public OrderItem() {

	}

	// Estou incluindo Order order, Product product para que as outras classes aou
	// instanciar um cara desse, a outra classe não deve conhecer o artifício
	// do orderItempk(id) da JPA.
	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		id.setOrder(order); // o construtor recebe o order e passa para OrderItemPK order
		id.setProduct(product); // o construtor recebe o product e passa para OrderItemPK product
		this.quantity = quantity;
		this.price = price;
	}

	// Incluirei os gets e sets do order e product na mão.
	public Order getOrder() {
		return id.getOrder();
	}

	public void setOrder(Order order) {
		id.setOrder(order);
	}

	public Product getProduct() {
		return id.getProduct();
	}

	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
