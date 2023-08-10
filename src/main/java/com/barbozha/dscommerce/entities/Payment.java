package com.barbozha.dscommerce.entities;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") // Horário UTC.
	private Instant moment;

	@OneToOne
	@MapsId 
	/* A anotação para este tipo de relacionamento é MapsId
	Para o JPA o id desta entidade SERÁ A CHAVE PRIMÁRIA LÁ NA TABELA (tb_payment)
	Isto porque foi mapeado uma REFERÊNCIA para ORDER(Pedido)do tipo (@OneToOne)
	e também para MAPEAR OS Ids(@MapsId)
	Na tabela (tb_payment) terá o campo ORDER_ID como chave primária por CONVENSÃO DA JPA.
	O pedido(Order) de número 5 terá ORDER_ID da tabela tb_payment de numero 5 também. */
	private Order order;

	public Payment() {

	}

	public Payment(Long id, Instant moment, Order order) {
		this.id = id;
		this.moment = moment;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
