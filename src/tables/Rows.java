package tables;

public class Rows {
	private String station;
	private int timedelay;
	private int busnummer;
	private int line;
	private int order;
	private String rem;
	
	public Rows(int line, String station, int timedelay, int busnummer, int order, String rem){
		this.line = line;
		this.station = station;
		this.timedelay = timedelay;
		this.busnummer = busnummer;
		this.order = order;
		this.rem = rem;
	}
	
	public void setBusline(int line){
		this.line = line;
	}
		
	public int getBusline(){
		return this.line;
	}
	
	public void setStation(String station){
		this.station = station;
	}
	
	public String getStation(){
		return this.station;
	}
	
	public void setTimedelay(int timedelay){
		this.timedelay = timedelay;
	}
	
	public int getTimedelay(){
		return this.timedelay;
	}
	
	public void setBusnummer(int busnummer){
		this.busnummer = busnummer;
	}
	
	public int getBusnummer(){
		return this.busnummer;
	}
	
	public void setOrder(int order){
		this.order = order;
	}
	
	public int getOrder(){
		return this.order;
	}
	
	public void setRem(String rem){
		this.rem = rem;
	}
	
	public String getRem(){
		return this.rem;
	}
	
}
