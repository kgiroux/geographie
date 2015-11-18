package fr.esigelec.projetHibernate.dto;

public class Ville {
	private int id;
	private String nom;
	private int nb_habitant;
	private Pays pays;
	public Ville() {
		
	}
	public Ville(int id, String nom, int nb_habitant, Pays pays) {
		super();
		this.id = id;
		this.nom = nom;
		this.nb_habitant = nb_habitant;
		this.pays = pays;
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
	public Pays getPays() {
		return pays;
	}
	public void setPays(Pays pays) {
		this.pays = pays;
	}


	@Override
	public String toString() {
		return "Ville [id=" + id + ", nom=" + nom + ", nb_habitant=" + nb_habitant + ", id_pays=" + pays.toString() + "]";
	}
	
}
