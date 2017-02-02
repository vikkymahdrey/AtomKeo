package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the slab_based_billing_slabs database table.
 * 
 */
@Entity
@Table(name="slab_based_billing_slabs")
@NamedQuery(name="SlabBasedBillingSlab.findAll", query="SELECT s FROM SlabBasedBillingSlab s")
public class SlabBasedBillingSlab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private float rate;

	@Column(name="slab_billing_id")
	private BigInteger slabBillingId;

	private BigInteger slabId;

	private String status;

	public SlabBasedBillingSlab() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getRate() {
		return this.rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public BigInteger getSlabBillingId() {
		return this.slabBillingId;
	}

	public void setSlabBillingId(BigInteger slabBillingId) {
		this.slabBillingId = slabBillingId;
	}

	public BigInteger getSlabId() {
		return this.slabId;
	}

	public void setSlabId(BigInteger slabId) {
		this.slabId = slabId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}