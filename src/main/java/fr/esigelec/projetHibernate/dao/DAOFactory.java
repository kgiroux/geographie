package fr.esigelec.projetHibernate.dao;

public abstract class DAOFactory {
	public static final int HIBERNATE = 1;
	public static final int JDBC = 2;
	
	public abstract IPaysDAO getPaysDAO();
	public abstract IVilleDAO getVilleDAO();
	
	public static DAOFactory getDAOFactory(int model){
		switch(model){
		case HIBERNATE : 
			return new HibernateDAOFactory();
		case JDBC : 
			return new JDBCDAOFactory();
		default :
			return new HibernateDAOFactory();
		}
	}
}
