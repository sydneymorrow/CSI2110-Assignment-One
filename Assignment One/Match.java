/**
* Name: Sydney Morrow
* Student Num: 300119030
**/
public class Match implements Comparable<Match>{

	public int x;
	public int y;


	public Match(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getX(){
		return this.x;
	}

	
	public String toString(){
		return "(" + x + "," + y + ")";
	}
	 
	public int compareTo(Match b){
		if(this.x < b.x){
			return -1;
		}else if(this.x > b.x){
			return 1;
		}else{
			return 0;
		}
		
	}

}