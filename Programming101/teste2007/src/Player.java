
public class Player {

	private String playerName;
	private int point;
	
	public Player(String name){
		playerName=name;
		point=0;
	}
	public Player(String name,int points){
		playerName=name;
		point=points;
	}
	
	public String getName(){
		return playerName;
	}
	
	public int getPoints(){
		return point;
	}
	public void setPoints(int points){
		point=points;
	}
	public boolean equals(Player c){
		boolean equal=false;
		if(getName().equals(c)){
			equal=true;
			
		
	}else
		equal=false;
	
	return equal;
}
}
