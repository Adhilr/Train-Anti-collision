package controlSystem;

import track_package.*;
import train_package.train;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;

import org.hibernate.criterion.Restrictions;



public class controller {
	
	Criteria crit;
	Session session;
	
	
	
	public controller(){
		
		try{
			Configuration configuration = new Configuration().configure();
		
			SessionFactory sessionFactory = configuration.buildSessionFactory();

			session = sessionFactory.openSession();
		}
		catch(HibernateException e){
			System.out.print(e.getMessage());
			System.out.println(" 3 ");
		}
		
	}
	
	
	public void requestTrack(String currentPlace,String destination,train Train){
		String trainID = Train.getTrainID();
		track Track = new track();
		String nextPlace = new String();
		try {
			nextPlace = routeFinder(currentPlace,destination);
			//System.out.println(nextPlace);
			Track = req(currentPlace,nextPlace);
			}
		catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println(" 1");
		}
		
		
		
		Track.setTrainId(trainID);
		session.saveOrUpdate(Track);
		Train.setCurrentPlace( nextPlace);
		
		Train.setTrack(Track);
		session.saveOrUpdate(Train);
		session.beginTransaction().commit();
		
	}
	
	
	private track req(String currentPlace,String nextPlace){
		track Track=new track();
		
		String end1,end2;
		if(currentPlace.compareTo(nextPlace)>0){
			end2=currentPlace;
			end1=nextPlace;
		}
		else{
			end1=currentPlace;
			end2=nextPlace;
		}
		//System.out.println(end1+" "+end2);
		while(Track==null){
			System.out.println("Waitin for track");
			crit = session.createCriteria(track.class);
			crit.add(Restrictions.eq("end1", end1));
			crit.add(Restrictions.eq("end2", end2));
			crit.add(Restrictions.eq("TrainID", null));
			crit.addOrder(Order.desc("time"));
			crit.setMaxResults(1);
			Track=(track) crit.uniqueResult();
		}
		
		if(Track == null){
			System.out.print("Track is null");
		}
		else
			System.out.println("Track allotted is "+Track.getTrackID());
		//System.out.println(t.size());
		
		
		return Track;
	}
	
	
	private String routeFinder(String currentPlace, String destination){
		String result=new String();
		
		crit=session.createCriteria(route.class);
		
		crit.add(Restrictions.eq("destination", destination));
		crit.add(Restrictions.eq("current", currentPlace));
		crit.setMaxResults(1);
		route r= (route) crit.uniqueResult();
		
		result = r.getNext();
		//System.out.println(result+ " hi ");
		return result;
	}
	
	public void freeTrack(track Track){
		Track.setTrainId(null);
		session.saveOrUpdate(Track);
		session.beginTransaction().commit();
	}

}
