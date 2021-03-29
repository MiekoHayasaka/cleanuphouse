package model;

import java.io.Serializable;

public class Room implements Serializable{
	private int id;
	private String name; // 掃除場所（部屋名、ベランダ、庭など）

	public Room() {}
	public Room(String name) {
		this.name=name;
	}
	public Room(int id,String name) {
		this.id=id;
		this.name=name;
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

}
