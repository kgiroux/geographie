package fr.esigelec.projetHibernate.dao;

import fr.esigelec.projetHibernate.dao.hibernate.PaysDAOImpl;
import fr.esigelec.projetHibernate.dao.hibernate.VilleDAOImpl;

public class HibernateDAOFactory extends DAOFactory{
	@Override
	public IPaysDAO getPaysDAO() {
		return new PaysDAOImpl();
	}
	@Override
	public IVilleDAO getVilleDAO() {
		return new VilleDAOImpl();
	}
}
