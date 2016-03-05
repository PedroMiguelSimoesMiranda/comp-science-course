
public class PlayerTeam {
public static final int MAX_PLAYERS=100;
	
	private Player[]players;
	//private int point;	private int counter;
	private int numberOfElements;

	
	public PlayerTeam(int n){
		//counter=0;
		players=new Player[MAX_PLAYERS];
		//point=0;
		numberOfElements=n;
	}

	
	public boolean searchIndex(Player p){
		int i = 0; 
		boolean find = false; 
		while ((i < counter) && (!find))
			if (players[i].getName().equals(p))
				find = true;
			else
				i++;
				find=false;
	
	return find;
}

	public int countPlayersWithScore(int point){
	int i=0;
	int find=0;
	while(i<numberOfElements)
	if(players[i].getPoints()==point){
		find++;
		i++;
	}
	else
		i++;
	return find;
}
	

		
	
	