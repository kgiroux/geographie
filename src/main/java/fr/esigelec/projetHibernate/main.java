package fr.esigelec.projetHibernate;

import fr.esigelec.projetHibernate.dao.DAOFactory;
import fr.esigelec.projetHibernate.dao.HibernateDAOFactory;
import fr.esigelec.projetHibernate.dao.IPaysDAO;
import fr.esigelec.projetHibernate.dao.IVilleDAO;
import fr.esigelec.projetHibernate.dto.Pays;

public class main {

	public static void main(String[] args) {
		Pays pays = new Pays(0,"France","645km2");
		Pays pays2 = new Pays(0,"USA","1500km2");
		
		HibernateDAOFactory hiber = (HibernateDAOFactory) DAOFactory.getDAOFactory(DAOFactory.HIBERNATE);
		hiber.getPaysDAO().ajouter(pays);
	}

}
