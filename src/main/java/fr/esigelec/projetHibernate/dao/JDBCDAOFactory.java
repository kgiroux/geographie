package fr.esigelec.projetHibernate.dao;

import fr.esigelec.projetHibernate.dao.jdbc.PaysDAO;
import fr.esigelec.projetHibernate.dao.jdbc.VilleDAO;

public class JDBCDAOFactory extends DAOFactory{

	@Override
	public IPaysDAO getPaysDAO() {
		return new PaysDAO();
	}

	@Override
	public IVilleDAO getVilleDAO() {
		return new VilleDAO();
	}

}
