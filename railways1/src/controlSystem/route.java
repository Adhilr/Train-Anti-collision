package controlSystem;

class route {
	String ID;
	String current;
	String next;
	String destination;
	

	public route() {
		
	}

	public route(String current, String next, String destination,String id) {
		// TODO Auto-generated constructor stub
		this.current=current;
		this.next=next;
		this.destination=destination;
		this.ID=id;
	}

	public String getCurrent() {
		return current;
	}

	public String getNext() {
		return next;
	}

	public String getDestination() {
		return destination;
	}
	

}
