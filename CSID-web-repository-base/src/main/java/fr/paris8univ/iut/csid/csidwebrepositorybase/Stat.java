package fr.paris8univ.iut.csid.csidwebrepositorybase;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Stat {

	private int id;
	private String name;
	private String entry_type;
	private LocalDateTime creationDate;
	private int value;
	
	public Stat(){}
	
	public Stat(int id, String name, String entry_type, long creationDate, int value) {
		this.id = id;
		this.name = name;
		this.entry_type = entry_type;
		this.creationDate = LocalDateTime.ofEpochSecond(creationDate, 0, ZoneOffset.UTC);
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

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	

}
