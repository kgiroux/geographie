package fr.esigelec.projetHibernate.dao.hibernate;

import java.security.InvalidParameterException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.esigelec.projetHibernate.dao.IPaysDAO;
import fr.esigelec.projetHibernate.dto.Pays;

public class PaysDAOImpl implements IPaysDAO {

	public void ajouter(Pays p) throws IllegalArgumentException {
		if(p != null){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			// Seul ligne qui peut changer
			session.save(p);
			session.getTransaction().commit();
			session.close();
		}else{
			throw new IllegalArgumentException("Null Parameter");
		}
	}
	public Pays getPays(int id) throws InvalidParameterException {
		if(id > 0){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Pays pays=(Pays)session.get(Pays.class,id);
			session.getTransaction().commit();
			session.close();
			return pays;
		}else{
			System.out.println("\n ID cannot be inferior to 1\n");
			throw new InvalidParameterException();
		}
	}
	public List<Pays> getPays() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Pays";
		Query query = session.createQuery(hql);
		List<Pays> countries=query.list();
		session.getTransaction().commit();
		session.close();
		return countries;
	}
	public Pays getPays(String nomPays) {
		if(!("".equals(nomPays))){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from Pays where nom =:name";
			Query query = session.createQuery(hql);
			query.setText("name", nomPays);
			Pays p = (Pays) query.list().get(0);
			session.getTransaction().commit();
			session.close();
			return p;
		}else{
			System.out.println("\n Pays's name  cannot be empty\n");
			throw new InvalidParameterException();
		}
	}
	public void update(Pays p) throws InvalidParameterException{
		if(p != null){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			try{
				session.saveOrUpdate(p);
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
	public void delete(Pays p) throws InvalidParameterException{
		if(p != null){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			try{
				session.delete(p);
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
	public void refresh(Pays p) throws InvalidParameterException{
		if(p != null){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			try{
				session.refresh(p);	
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
