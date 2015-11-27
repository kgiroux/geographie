/**
 * @author GIROUX Kévin & LEFEBVRE Cyril
 */
package fr.esigelec.projetHibernate.dto;

import java.util.ArrayList;

public class Pays {
	int id;
	String nom;
	String superficie;
	ArrayList<Ville> villes;
	public Pays() {}
	public Pays(int id, String name, String superficie) {
		this.id = id;
		this.nom = name;
		this.superficie = superficie;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String name) {
		this.nom = name;
	}
	public String getSuperficie() {
		return superficie;
	}
	public void setSuperficie(String superficie) {
		this.superficie = superficie;
	}
	@Override
	public String toString() {
		return "Pays [id=" + id + ", name=" + nom + ", superficie=" + superficie + "]";
	}	
}
