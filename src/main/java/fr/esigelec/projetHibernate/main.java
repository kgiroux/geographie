package fr.esigelec.projetHibernate;

import java.util.List;

import fr.esigelec.projetHibernate.dao.DAOFactory;
import fr.esigelec.projetHibernate.dao.HibernateDAOFactory;
import fr.esigelec.projetHibernate.dao.JDBCDAOFactory;
import fr.esigelec.projetHibernate.dto.Pays;
import fr.esigelec.projetHibernate.dto.Ville;

public class main {
	public static void main(String[] args) {
		Pays pays = new Pays(0,"France","645km2");
		Pays pays2 = new Pays(0,"USA","1500km2");
		Ville ville = new Ville(0,"Paris",2000000,pays);
		Ville ville2 = new Ville(0,"Washington",7000000,pays2);
		
		HibernateDAOFactory hiber = (HibernateDAOFactory) DAOFactory.getDAOFactory(DAOFactory.HIBERNATE);
		JDBCDAOFactory jdbc = (JDBCDAOFactory) DAOFactory.getDAOFactory(DAOFactory.JDBC);
		
		
		System.out.println("\n			Add :\n");
		
		System.out.println("Hibernate");
		
		pays.setVille(ville);
		hiber.getPaysDAO().ajouter(pays);
		
		
		hiber.getVilleDAO().ajouter(ville);
		
		System.out.println("JDBC");
		jdbc.getPaysDAO().ajouter(pays2);
		jdbc.getVilleDAO().ajouter(ville2);
		
		
		System.out.println("\n			Get :\n");
		
		System.out.println("Hibernate :\n");
		System.out.println("Id :");
		System.out.println("PAYS hibernate");
		Pays paysTest= hiber.getPaysDAO().getPays(pays.getId());
		//System.out.println(paysTest.toString());
		for(Ville v : paysTest.getVilles()){
			System.out.println(v.toString());
		}
		
		System.out.println("Ville hibernate");
		System.out.println(hiber.getVilleDAO().getVille(ville.getId()).toString());
		System.out.println("Name :");
		System.out.println(hiber.getPaysDAO().getPays("USA").toString());
		System.out.println("All :");
		List<Pays> paysList = hiber.getPaysDAO().getPays();
		for (int i=0; i<paysList.size(); i++)
			System.out.println(paysList.get(i).toString());
		List<Ville> villeList = hiber.getVilleDAO().getVilles();
		for (int i=0; i<villeList.size(); i++)
			System.out.println(villeList.get(i).toString());
		
		System.out.println("JDBC :\n");
		System.out.println("Id :");
		System.out.println(jdbc.getPaysDAO().getPays(pays2.getId()).toString());
		System.out.println(jdbc.getVilleDAO().getVille(ville2.getId()).toString());
		System.out.println("Name :");
		System.out.println(jdbc.getPaysDAO().getPays("France").toString());
		System.out.println("All :");
		paysList = jdbc.getPaysDAO().getPays();
		for (int i=0; i<paysList.size(); i++)
			System.out.println(paysList.get(i).toString());
		villeList = jdbc.getVilleDAO().getVilles();
		for (int i=0; i<villeList.size(); i++)
			System.out.println(villeList.get(i).toString());
		
		
		System.out.println("\n			Update :\n");
		
		System.out.println("Hibernate :\n");
		pays.setNom("Irlande");
		hiber.getPaysDAO().update(pays);
		System.out.println(hiber.getPaysDAO().getPays(pays.getId()).toString());
		ville.setNom("Dublin");
		hiber.getVilleDAO().update(ville);
		System.out.println(hiber.getVilleDAO().getVille(ville.getId()).toString());
		
		System.out.println("JDBC :\n");
		pays2.setNom("Russie");
		jdbc.getPaysDAO().update(pays2);
		System.out.println(jdbc.getPaysDAO().getPays(pays2.getId()).toString());
		ville2.setNom("Moscou");
		jdbc.getVilleDAO().update(ville2);
		System.out.println(jdbc.getVilleDAO().getVille(ville2.getId()).toString());
		
		
		System.out.println("\n			Refresh :\n");
		
		System.out.println("Hibernate :\n");
		pays.setNom("France");
		hiber.getPaysDAO().refresh(pays);
		System.out.println(hiber.getPaysDAO().getPays(pays.getId()).toString());
		ville.setNom("Paris");
		hiber.getVilleDAO().refresh(ville);
		System.out.println(hiber.getVilleDAO().getVille(ville.getId()).toString());
		
		System.out.println("JDBC :\n");
		pays2.setNom("USA");
		jdbc.getPaysDAO().refresh(pays2);
		System.out.println(jdbc.getPaysDAO().getPays(pays2.getId()).toString());
		ville2.setNom("Washington");
		jdbc.getVilleDAO().refresh(ville2);
		System.out.println(jdbc.getVilleDAO().getVille(ville2.getId()).toString());

		
		System.out.println("\n			Delete :\n");
		
		System.out.println("Hibernate");
		hiber.getPaysDAO().delete(pays2);
		
		System.out.println("JDBC");
		jdbc.getPaysDAO().delete(pays);
		
		
		System.out.println("\n			Bidirectionnal :\n");
		
		System.out.println("\nHibernate :\n");
		Pays paysBid = new Pays(0,"Allemagne","645km2");
		hiber.getPaysDAO().ajouter(paysBid);
		Ville berlin = new Ville(0, "Berlin", 2240000, paysBid);
		hiber.getVilleDAO().ajouter(berlin);
		
		Pays paysBidTest = hiber.getPaysDAO().getPays(paysBid.getId());
		System.out.println(paysBidTest.toString());
		Ville hamburg = new Ville (0, "Hamburg", 250000, paysBid);
		hiber.getVilleDAO().ajouter(hamburg);
		paysBidTest.setVille(hamburg);
		System.out.println(paysBidTest.toString());
		paysBidTest = hiber.getPaysDAO().getPays(paysBid.getId());
		System.out.println(paysBidTest.toString());
		
		hiber.getPaysDAO().delete(paysBidTest);
		
		System.out.println("\nJDBC :\n");
		paysBid = new Pays(0,"Allemagne","645km2");
		jdbc.getPaysDAO().ajouter(paysBid);
		berlin = new Ville(0, "Berlin", 2240000, paysBid);
		jdbc.getVilleDAO().ajouter(berlin);
		
		paysBidTest = jdbc.getPaysDAO().getPays(paysBid.getId());
		System.out.println(paysBidTest.toString());
		hamburg = new Ville (0, "Hamburg", 250000, paysBid);
		jdbc.getVilleDAO().ajouter(hamburg);
		paysBidTest.setVille(hamburg);
		System.out.println(paysBidTest.toString());
		paysBidTest = jdbc.getPaysDAO().getPays(paysBid.getId());
		System.out.println(paysBidTest.toString());
		
		jdbc.getPaysDAO().delete(paysBidTest);
	}
}