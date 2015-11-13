package fr.esigelec.projetHibernate.dto;

public class Ville {
	int id;
	String nom;
	int nb_habitant;
	int id_pays;
	public Ville() {
		
	}
	public Ville(int id, String nom, int nb_habitant, int id_pays) {
		super();
		this.id = id;
		this.nom = nom;
		this.nb_habitant = nb_habitant;
		this.id_pays = id_pays;
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
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getNb_habitant() {
		return nb_habitant;
	}
	public void setNb_habitant(int nb_habitant) {
		this.nb_habitant = nb_habitant;
	}
	public int getId_pays() {
		return id_pays;
	}
	public void setId_pays(int id_pays) {
		this.id_pays = id_pays;
	}


	@Override
	public String toString() {
		return "Ville [id=" + id + ", nom=" + nom + ", nb_habitant=" + nb_habitant + ", id_pays=" + id_pays + "]";
	}
	
}
