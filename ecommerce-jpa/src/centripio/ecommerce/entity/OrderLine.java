package centripio.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_lines")
public class OrderLine {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "quantity",  nullable = false)
	private Double quantity = 0d;
	
	@Column(name = "unit_price", nullable = false)
	private Double unitPrice = 0d;
	
	@Column(name = "total", nullable = false)
	private Double total = 0d;
	
	@Column(name = "ctr")
	private Integer ctr;
	
	public Integer getCtr() {
		return ctr;
	}

	public void setCtr(Integer ctr) {
		this.ctr = ctr;
	}
	@ManyToOne
	@JoinColumn(name = "fk_order", nullable = false, updatable = false)
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "fk_product", nullable = false)
	private Product product;
	
	public void updateTotal() {
		this.total = unitPrice * quantity;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
		updateTotal();
	}	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
		updateTotal();
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
