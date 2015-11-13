package fr.esigelec.projetHibernate.dto;

import java.util.ArrayList;

public class Pays {
	int id;
	String name;
	int superficie;
	ArrayList<Ville> listVille;
	
	public Pays() {
		
	}
	public Pays(int id, String name, int superficie) {
		super();
		this.id = id;
		this.name = name;
		this.superficie = superficie;
	}
	public ArrayList<Ville> getListVille() {
		return listVille;
	}
	public void setListVille(ArrayList<Ville> listVille) {
		this.listVille = listVille;
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
	public int getSuperficie() {
		return superficie;
	}
	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}
	@Override
	public String toString() {
		return "Pays [id=" + id + ", name=" + name + ", superficie=" + superficie + ", listVille=" + listVille + "]";
	}
	
	
}
