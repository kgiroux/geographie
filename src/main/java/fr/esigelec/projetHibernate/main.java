package fr.esigelec.projetHibernate;

import java.util.List;

import fr.esigelec.projetHibernate.dao.DAOFactory;
import fr.esigelec.projetHibernate.dao.HibernateDAOFactory;
import fr.esigelec.projetHibernate.dao.IPaysDAO;
import fr.esigelec.projetHibernate.dao.IVilleDAO;
import fr.esigelec.projetHibernate.dao.JDBCDAOFactory;
import fr.esigelec.projetHibernate.dto.Pays;

public class main {

	public static void main(String[] args) {
		Pays pays = new Pays(0,"France","645km2");
		Pays pays2 = new Pays(0,"USA","1500km2");
		
		HibernateDAOFactory hiber = (HibernateDAOFactory) DAOFactory.getDAOFactory(DAOFactory.HIBERNATE);
		JDBCDAOFactory jdbc = (JDBCDAOFactory) DAOFactory.getDAOFactory(DAOFactory.JDBC);
		
		hiber.getPaysDAO().ajouter(pays);
		jdbc.getPaysDAO().ajouter(pays2);
		
		System.out.println("\nHibernate :\n");
		System.out.println(hiber.getPaysDAO().getPays(pays.getId()).toString());
		System.out.println(hiber.getPaysDAO().getPays("USA").toString());
		
		List<Pays> paysList = hiber.getPaysDAO().getPays();
		for (int i=0; i<paysList.size(); i++){
			System.out.println(paysList.get(i).toString());
		}
		
		System.out.println("\nJDBC :\n");
		System.out.println(jdbc.getPaysDAO().getPays(pays.getId()).toString());
		System.out.println(jdbc.getPaysDAO().getPays("USA").toString());
		
		paysList = jdbc.getPaysDAO().getPays();
		for (int i=0; i<paysList.size(); i++){
			System.out.println(paysList.get(i).toString());
		}
		
		hiber.getPaysDAO().delete(pays);
		jdbc.getPaysDAO().delete(pays2);
	}

}
