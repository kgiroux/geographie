package fr.esigelec.projetHibernate.dao;

import java.util.List;

import fr.esigelec.projetHibernate.dto.Ville;

public interface IVilleDAO {
	public void ajouter(Ville v);
	public Ville getVille(int id);
	public List<Ville> getVilles();
	public void update(Ville v);
	public void delete(Ville v);
	public void refresh(Ville v);
}