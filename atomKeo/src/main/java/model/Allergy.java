package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the allergies database table.
 * 
 */
@Entity
@Table(name="allergies")
@NamedQuery(name="Allergy.findAll", query="SELECT a FROM Allergy a")
public class Allergy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String allergiesName;

	private String allergiesType;

	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	public Allergy() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAllergiesName() {
		return this.allergiesName;
	}

	public void setAllergiesName(String allergiesName) {
		this.allergiesName = allergiesName;
	}

	public String getAllergiesType() {
		return this.allergiesType;
	}

	public void setAllergiesType(String allergiesType) {
		this.allergiesType = allergiesType;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

}