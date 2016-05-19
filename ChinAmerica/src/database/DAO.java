package database;

import org.hibernate.Session;

public abstract class DAO {
	
	private Session session;
	
	/**
	 * IMplementar a preparação da session, rollback, transaction, fechar a session
	 */
	
	protected Session initSession(){
		if(session == null){
			session = HibernateUtil.getSessionFactory().openSession();
		}
		
		return session;
	}
	
	protected void endSession(){
        session.flush();
        session.close();
	}
	
	/**
	 * 
	 * Before e after em todas as DAOs???
	 * 
	 * begin Transaction
	 * 
	 * end transaction
	 * Rollback
	 * catch
	 * stackTrace
	 * 
	 */
	
}
