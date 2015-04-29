package util;

public class ReadableSRC {
	public String name;
	public String pack;
	public String file;
	public ReadableSRC(){
		name = "";
		pack = "";
		file = "";
	}
	public ReadableSRC setName(String t){
		name = t;
		return(this);
	}
	public ReadableSRC setPack(String t){
		pack = t;
		return(this);
	}
	public ReadableSRC setFile(String t){
		file = t;
		return(this);
	}
	
}
