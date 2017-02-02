package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cometsettings database table.
 * 
 */
@Entity
@Table(name="cometsettings")
@NamedQuery(name="Cometsetting.findAll", query="SELECT c FROM Cometsetting c")
public class Cometsetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String cometAuthType;

	private String cometVersionNo;

	public Cometsetting() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCometAuthType() {
		return this.cometAuthType;
	}

	public void setCometAuthType(String cometAuthType) {
		this.cometAuthType = cometAuthType;
	}

	public String getCometVersionNo() {
		return this.cometVersionNo;
	}

	public void setCometVersionNo(String cometVersionNo) {
		this.cometVersionNo = cometVersionNo;
	}

}