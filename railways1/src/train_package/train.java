package train_package;

import java.util.concurrent.TimeUnit;

import controlSystem.*;
import track_package.*;

public class train {
	String destination= new String();
	String trainID= new String();
	track Track =new track();
	String currentPlace=new String();
	controller control;
	
	public train(){ };
	
	public train(String currentPlace, String trainId){
		this.currentPlace=currentPlace;
		this.trainID =trainId;
		
		
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public void setCurrentPlace(String g) {
		this.currentPlace=g;
	}
	
	public void setTrack(track track) {
		Track = track;
	}

	public String getTrainID() {
		return trainID;
	}


	
	public void run() throws InterruptedException{
		
		
		
		while(currentPlace.compareTo( destination)!=0 ){
			
			control=new controller();
			control.requestTrack(currentPlace,destination,this);
			
			TimeUnit.SECONDS.sleep(Track.getTime());
			
			System.out.println(trainID+ " has arrived at "+ currentPlace);
			control.freeTrack(Track);
			
			
		}
		
		System.out.println("Thank you for travelling");
		
		
		
	}

	

	
}