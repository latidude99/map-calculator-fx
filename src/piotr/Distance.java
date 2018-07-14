/**Copyright (C) 2017  Piotr Czapik.
* @author Piotr Czapik <latitude1001101@gmail.com>
* @version 4.01
* @license GPLv2
*/ 

package piotr;

public class Distance {
	    private double dist = 0; //distance in metres

	public double getDist() {
		return dist;
	}
	public void setDist(double dist) {
		this.dist = dist;
	}
    
	public double distKm(double dist){
		return dist / 1000;
	}
	
	public double distMileStatue(double dist){
		return dist / 1609.3440;
	}
    	
	public double distMileNautical(double dist){
		return dist / 1852; //this is US/Int Naut Mile, the UK Naut Mile is 1853.184m 
	}
	
	public double distMetre(double dist){
		return dist;
	}
	
	public double distFt(double dist){
		return dist * 3.280840; 
	}
    
}














