package fr.esigelec.projetHibernate.dao.hibernate;

import java.security.InvalidParameterException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.esigelec.projetHibernate.dao.IVilleDAO;
import fr.esigelec.projetHibernate.dto.Ville;

public class VilleDAOImpl implements IVilleDAO {
	public void ajouter(Ville v) throws IllegalArgumentException{
		if(v != null){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(v);
			session.getTransaction().commit();
			session.close();
		}else{
			throw new IllegalArgumentException("Null Parameter");
		}
	}
	public Ville getVille(int id) throws InvalidParameterException {
		if(id > 0){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Ville city=(Ville)session.get(Ville.class,id);
			session.getTransaction().commit();
			session.close();
			return city;
		}else{
			System.out.println("\n ID cannot be inferior to 1\n");
			throw new InvalidParameterException();
		}
	}
	public List<Ville> getVilles() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Ville";
		Query query = session.createQuery(hql);
		List<Ville> cities=query.list();
		session.getTransaction().commit();
		session.close();
		return cities;
	}
	public void update(Ville v) throws InvalidParameterException {
		if(v != null){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			try{
				session.saveOrUpdate(v);
				session.getTransaction().commit();
			}catch (Exception e){
				session.getTransaction().rollback();
			}finally{
				session.close();
			}
		}else{
			throw new InvalidParameterException("Null Parameter");
		}
	}
	public void delete(Ville v) throws IllegalArgumentException{
		if(v != null){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			try{
				session.delete(v);
				session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally{
				session.close();
			}
		}else{
			throw new InvalidParameterException("Null Parameter");
		}
	}
	public void refresh(Ville v) throws InvalidParameterException{
		if(v != null){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			try{
				session.refresh(v);	
				session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally{
				session.close();
			}
		}else{
			throw new InvalidParameterException("Null parameter");
		}
	}
}