package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the billing_type database table.
 * 
 */
@Entity
@Table(name="billing_type")
@NamedQuery(name="BillingType.findAll", query="SELECT b FROM BillingType b")
public class BillingType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String description;

	private String key;

	private String name;

	private int parent;

	//bi-directional many-to-one association to BillingTypeMapping
	@OneToMany(mappedBy="billingTypeBean")
	private List<BillingTypeMapping> billingTypeMappings;

	public BillingType() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParent() {
		return this.parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public List<BillingTypeMapping> getBillingTypeMappings() {
		return this.billingTypeMappings;
	}

	public void setBillingTypeMappings(List<BillingTypeMapping> billingTypeMappings) {
		this.billingTypeMappings = billingTypeMappings;
	}

	public BillingTypeMapping addBillingTypeMapping(BillingTypeMapping billingTypeMapping) {
		getBillingTypeMappings().add(billingTypeMapping);
		billingTypeMapping.setBillingTypeBean(this);

		return billingTypeMapping;
	}

	public BillingTypeMapping removeBillingTypeMapping(BillingTypeMapping billingTypeMapping) {
		getBillingTypeMappings().remove(billingTypeMapping);
		billingTypeMapping.setBillingTypeBean(null);

		return billingTypeMapping;
	}

}