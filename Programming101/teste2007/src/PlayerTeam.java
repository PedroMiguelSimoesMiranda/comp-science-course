
public class PlayerTeam {
	public static final int MAX_PLAYERS=100;

	private Player [] players;
	//private int point;	private int counter;
	private int numberOfElements;


	public PlayerTeam(){
		//counter=0;
		players=new Player[MAX_PLAYERS];
		//point=0;
		numberOfElements=0;
	}


	public boolean searchIndex(String p){
		int i = 0; 
		boolean find = false; 
		while ((i < numberOfElements) && (!find))
			if (players[i].getName().equals(p))
				find = true;
			else
				i++;
		find=false;

		return find;
	}

	public void addPlayer(Player p){
		Player[] tmp=null;
		if(searchIndex(p.getName())==false){
			if(numberOfElements==players.length){
				tmp = new Player[2*players.length];}
			int i = 0;
			while (i<numberOfElements){
				tmp[i]=players[i];
				i++;
			}
			players=tmp;
		}
		players[numberOfElements++]=p;
	}


	public int count(){
		return numberOfElements;
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

}


