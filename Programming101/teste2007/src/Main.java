import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	private static final String FILE = "players.txt";

	public static void main(String[] args)throws Exception {
		PlayerTeam team;
		team = new PlayerTeam();
		Scanner in = new Scanner(System.in);
		read(FILE,team);
		write(FILE,team);
	}

	public static String getCMD(Scanner cmdin) {
		String cmd=cmdin.nextLine();
		return cmd;
	}

	public static void cmdInterpreter(Scanner in , PlayerTeam team){
		System.out.print("> ");
		String comm=getCMD(in);
		while(!comm.equals("Quit")){
			if(comm.equals("addplayer"))
				addPlayer(in,team);
			/*else if (comm.equals("countscore"))
				countScore(in,team);
			 */else if (comm.equals("totalplayers"))
				 countPlayers(in,team);
			 else System.out.println("error");

			System.out.print(">");
			comm=getCMD(in);
		}
	}

	public static void addPlayer(Scanner in , PlayerTeam team){
		String name = in.nextLine();
		if(team.searchIndex(name)==false){
			Player player = new Player(name);
			team.addPlayer(player);
			System.out.println("OK");}
		else System.out.println("error");

	}

	public static void countPlayers(Scanner in , PlayerTeam team){
		System.out.println("Total: " + team.count());
	}

	public static void write(String file, PlayerTeam pt) throws FileNotFoundException{
		PrintWriter pw = new PrintWriter (file);
		pw.println(pt.count());
		int i = 0 ;
		while(i < pt.count()){
			pw.print("ponto:" + i + "->" + pt.countPlayersWithScore(i));			
		}
		pw.close();
	}

	public static void read(String file, PlayerTeam pt) throws FileNotFoundException{
		FileReader f = new FileReader(file);
		Scanner s = new Scanner(f);

	}
}
