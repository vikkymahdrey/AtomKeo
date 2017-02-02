package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the mail_address database table.
 * 
 */
@Entity
@Table(name="mail_address")
@NamedQuery(name="MailAddress.findAll", query="SELECT m FROM MailAddress m")
public class MailAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String address;

	private BigInteger mail;

	private String type;

	public MailAddress() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigInteger getMail() {
		return this.mail;
	}

	public void setMail(BigInteger mail) {
		this.mail = mail;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}