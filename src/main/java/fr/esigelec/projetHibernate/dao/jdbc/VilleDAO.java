package fr.esigelec.projetHibernate.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.esigelec.projetHibernate.dao.IVilleDAO;
import fr.esigelec.projetHibernate.dto.Pays;
import fr.esigelec.projetHibernate.dto.Ville;

public class VilleDAO implements IVilleDAO{
	final static String URL = "jdbc:mysql://127.0.0.1/bdd_geographie";
	final static String LOGIN="root";
	final static String PASS="";
	
	public VilleDAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}
	}
	public void ajouter(Ville v) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO ville (nom, nb_habitants, id_pays) VALUES ( ? , ?, ? )",PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,v.getNom()); 
			ps.setInt(2,v.getNb_habitants());
			ps.setInt(3,v.getPays().getId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			v.setId(rs.getInt(1));
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}		
	}
	public Ville getVille(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Ville retour=null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM ville WHERE id= ?");
			ps.setInt(1,id);
			rs=ps.executeQuery();
			if(rs.next()){
				PaysDAO paysDAO = new PaysDAO();
				retour =new Ville(rs.getInt("id"),rs.getString("nom"),rs.getInt("nb_habitants"),paysDAO.getPays(rs.getInt("id_pays")));
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
	public List<Ville> getVilles() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<Ville> retour = new ArrayList<Ville>();
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM ville");
			rs=ps.executeQuery();
			while(rs.next()){
				PaysDAO paysDAO = new PaysDAO();
				retour.add(new Ville(rs.getInt("id"),rs.getString("nom"),rs.getInt("nb_habitants"),paysDAO.getPays(rs.getInt("id_pays"))));
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
	public Set<Ville> getVillesByPays(Pays pays) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Set<Ville> retour = new HashSet<Ville>();;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM ville WHERE id_pays = ?");
			ps.setInt(1,pays.getId());
			rs=ps.executeQuery();
			while(rs.next()){
				PaysDAO paysDAO = new PaysDAO();
				retour.add(new Ville(rs.getInt("id"),rs.getString("nom"),rs.getInt("nb_habitants"),pays));
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
	public void update(Ville v) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE ville SET nom = ?, nb_habitants = ?, id_pays = ? WHERE id = ?");
			ps.setString(1,v.getNom()); 
			ps.setInt(2,v.getNb_habitants());
			ps.setInt(3,v.getPays().getId());
			ps.setInt(4, v.getId());
			ps.executeUpdate();
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
	}
	public void delete(Ville v) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("DELETE FROM ville WHERE id = ?");
			ps.setInt(1,v.getId());
			ps.executeUpdate();
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
	}
	public void refresh(Ville v) {
		Ville ville = getVille(v.getId());
		v.setNom(ville.getNom());
		v.setNb_habitants(ville.getNb_habitants());
		v.setPays(ville.getPays());
	}
}