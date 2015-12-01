/**
 * @author GIROUX Kévin & LEFEBVRE Cyril
 */
package fr.esigelec.projetHibernate.dto;

import java.util.HashSet;
import java.util.Set;

public class Pays {
	int id;
	String nom;
	String superficie;
	Set<Ville> villes;
	public Pays() {
		
	}
	public Pays(int id, String name, String superficie) {
		this.id = id;
		this.nom = name;
		this.superficie = superficie;
		villes = new HashSet<Ville>();
	}
	public Set<Ville> getVilles() {
		return villes;
	}
	public void setVilles(Set<Ville> villes) {
		this.villes = villes;
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
	public String getVillesString(){
		String returnString ="";
		if(villes != null){
			/*for(Ville v : villes){
				returnString = v.toString() + "\n";
			}*/
		}
		
		return returnString;
	}
	
	public void setVille(Ville v){
		villes.add(v);
	}
	@Override
	public String toString() {
		String returnString = getVillesString();
		return "Pays [id=" + id + ", nom=" + nom + ", superficie=" + superficie + ", villes=" + returnString + "]";
	}	
}
