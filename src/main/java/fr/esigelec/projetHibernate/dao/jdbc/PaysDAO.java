package fr.esigelec.projetHibernate.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.esigelec.projetHibernate.dao.IPaysDAO;
import fr.esigelec.projetHibernate.dto.Pays;
import fr.esigelec.projetHibernate.dto.Ville;

public class PaysDAO implements IPaysDAO {
	final static String URL = "jdbc:mysql://127.0.0.1/bdd_geographie";
	final static String LOGIN="root";
	final static String PASS="";
	
	public PaysDAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}
	}
	public void ajouter(Pays p) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO pays (nom, superficie) VALUES ( ? , ? )", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,p.getNom()); 
			ps.setString(2,p.getSuperficie());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			p.setId(rs.getInt(1));
			
			VilleDAO villeDAO = new VilleDAO();
			
			for(Ville v : p.getVilles()){
				villeDAO.ajouter(v);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
	}
	public Pays getPays(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Pays retour=null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM pays WHERE id= ?");
			ps.setInt(1,id);
			rs=ps.executeQuery();
			if(rs.next()){
				VilleDAO villeDAO = new VilleDAO();
				retour =new Pays(rs.getInt("id"),rs.getString("nom"),rs.getString("superficie"));
				retour.setVilles(villeDAO.getVillesByPays(retour));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			try {if (rs != null)rs.close();} catch (Exception t) {}
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		return retour;
	}
	public List<Pays> getPays() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<Pays> retour=new ArrayList<Pays>();
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM pays");
			rs=ps.executeQuery();

			Pays pays = new Pays();
			VilleDAO villeDAO = new VilleDAO();
			
			while(rs.next()){
				pays = new Pays(rs.getInt("id"),rs.getString("nom"),rs.getString("superficie"));
				pays.setVilles(villeDAO.getVillesByPays(pays));
				retour.add(pays);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			try {if (rs != null)rs.close();} catch (Exception t) {}
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		return retour;
	}
	public Pays getPays(String nomPays) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Pays retour=null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM pays WHERE nom= ?");
			ps.setString(1,nomPays);
			rs=ps.executeQuery();
			if(rs.next()){
				VilleDAO villeDAO = new VilleDAO();
				retour =new Pays(rs.getInt("id"),rs.getString("nom"),rs.getString("superficie"));
				retour.setVilles(villeDAO.getVillesByPays(retour));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			try {if (rs != null)rs.close();} catch (Exception t) {}
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		return retour;
	}
	public void update(Pays p) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE pays SET nom = ?, superficie = ? WHERE id = ?");
			ps.setString(1,p.getNom()); 
			ps.setString(2,p.getSuperficie());
			ps.setInt(3, p.getId());
			ps.executeUpdate();
			
			VilleDAO villeDAO = new VilleDAO();
			
			for(Ville v : p.getVilles()){
				villeDAO.update(v);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
	}
	public void delete(Pays p) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("DELETE FROM pays WHERE id = ?");
			ps.setInt(1,p.getId());
			ps.executeUpdate();
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
	}
	public void refresh(Pays p) {
		Pays pays = getPays(p.getId());
		VilleDAO villeDAO = new VilleDAO();
		p.setNom(pays.getNom());
		p.setSuperficie(pays.getSuperficie());
		p.setVilles(villeDAO.getVillesByPays(p));
	}
}