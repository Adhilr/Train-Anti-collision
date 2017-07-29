package main;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import train_package.train;

public class railwayMain {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//After GPS implementation Main Function Will be formulated accordingly
		train Train=new train();
				
		
		try{
			Configuration configuration = new Configuration().configure();
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			Session session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(train.class);
			
			
			crit.add(Restrictions.eq("trainID", "CH4321"));
			crit.setMaxResults(1);
			Train=(train) crit.uniqueResult();
			System.out.println(Train.getTrainID());
			Train.setDestination("MUMBAI");
			Train.setCurrentPlace("BANGALORE");
			Train.run();
			
			
			
		}
		catch(HibernateException e){
			System.out.print(e.getMessage());
			System.out.print("    2 ");
		}
		
		
	

	}
	

}



