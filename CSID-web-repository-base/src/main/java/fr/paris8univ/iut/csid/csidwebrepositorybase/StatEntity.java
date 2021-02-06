package fr.paris8univ.iut.csid.csidwebrepositorybase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "statistique")
public class StatEntity {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "entry_type")
	private String entry_type;

	@Column(name = "creationdate")
	private long creationDate;

	@Column(name= "value")
	private int value;

	public StatEntity() {}

	public StatEntity(int id, String name, String entry_type, long creationDate, int value) {
		this.id = id;
		this.name = name;
		this.entry_type = entry_type;
		this.creationDate = creationDate;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEntry_type() {
		return entry_type;
	}

	public void setEntry_type(String entry_type) {
		this.entry_type = entry_type;
	}

	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
