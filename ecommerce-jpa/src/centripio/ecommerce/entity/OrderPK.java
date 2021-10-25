package centripio.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class OrderPK implements Serializable {
	
	private String serie;
	private Long folio;
		
	public OrderPK() {
		super();
	}
	public OrderPK(String serie, Long folio) {
		super();
		this.serie = serie;
		this.folio = folio;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public Long getFolio() {
		return folio;
	}
	public void setFolio(Long folio) {
		this.folio = folio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((folio == null) ? 0 : folio.hashCode());
		result = prime * result + ((serie == null) ? 0 : serie.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderPK other = (OrderPK) obj;
		if (folio == null) {
			if (other.folio != null)
				return false;
		} else if (!folio.equals(other.folio))
			return false;
		if (serie == null) {
			if (other.serie != null)
				return false;
		} else if (!serie.equals(other.serie))
			return false;
		return true;
	}
	
	
}
