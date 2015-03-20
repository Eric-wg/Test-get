package com.ebay.eric.xstream_test;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "EntityFields")
public class EntityFields {
	@XmlElement(name="EntityFields")
	private List<EntityField> entityFields = new ArrayList<EntityField>();
	
	@XmlElement(name = "OrderId", required = true)
	private Long orderId;
	
	@XmlElement(name = "BuyerFirstName", required = true)
	private String buyerFirstName;
	
	public String getBuyerFirstName() {
		return buyerFirstName;
	}

	public void setBuyerFirstName(String buyerFirstName) {
		this.buyerFirstName = buyerFirstName;
	}

	public List<EntityField> getEntityFields() {
		return entityFields;
	}

	public void setEntityFields(List<EntityField> entityFields) {
		this.entityFields = entityFields;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
}
