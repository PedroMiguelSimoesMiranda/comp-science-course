package poo;

/**
 * 
 * @author Pedro 28289
 * 
 */
import java.util.ArrayList;
import java.util.Collections;
public class PlayList {



	public static final int NOT_FOUND = -1;
	public static final int INITIAL_CAPACITY = 256;

	private ArrayList <Music> musics;
	private String playListName;
	private int capacity;


	public PlayList (String name) {
		playListName = name;
		capacity = INITIAL_CAPACITY;
		musics = new ArrayList<Music>();
	}

	public PlayList(ArrayList<Music> temp, String name, int capacity){
		playListName = name;
		this.capacity= capacity;
		musics=temp;
	}

	public PlayList (String name, int n) {
		playListName = name;
		if (n <= 0) {
			capacity = INITIAL_CAPACITY;
		}
		else capacity = n;
		musics = new ArrayList<Music>();
	}


	public String getName() {
		return playListName;
	}

	public int capacity(){
		return capacity;
	}

	public boolean atCapacity(){
		return musics.size() == capacity;
	}

	public int getSize() {
		return musics.size();
	}

	public boolean contains(Music m){
		return musics.contains(m);
	}

	public boolean addMusic(Music m) {
		if (contains(m) == true)
			return false;
		if (m == null)
			return false;
		if (atCapacity())
			capacity*=2;
		musics.add(m);
		return true;
	} 

	public boolean deleteMusic(Music m) {
		return musics.remove(m);
	}

	public int indexOf(Music music) {
		return musics.indexOf(music);
	}

	public Music elementAt(int index){
		try {return musics.get(index);}
		catch (Exception e) { return null; }
	}



	public int numArtists(){
		int res=0;
		ArrayList <String>artist=new ArrayList<String>();
		for(Music music : musics){
			if(!artist.contains(music.getArtist())){
				res++;
				artist.add(music.getArtist());
			}
		}
		return res;
	}




	public int numMusics(String artist){
		int num=0;
		for(int i=0;i<musics.size();i++){
			if(musics.get(i).getArtist().equals(artist))
				num++;
		}
		return num;

	}

	public int getDuration(){
		int sumDuration=0;
		for(int i=0;i<musics.size();i++){
			sumDuration=sumDuration+musics.get(i).getDuration();
		}
		return sumDuration;
	}

	private int longestMusic(String artist){
		int longest=0;
		
		for(int i=0;i<getSize();i++){
			if(musics.get(i).getArtist().equals(artist)){
				if(longest<=musics.get(i).getDuration())
				longest=musics.get(i).getDuration();
			}
		}
		return longest;
	}

	public String mostProductiveArtist(){
		String mostProductive="";;
		String artist="";
		int nSong=0;
		int mostNSong=0;
		int nArtists;
		
		nArtists=numArtists();
		
		if(getSize()>0){
			for(int i=0; i<nArtists;i++){
				for (int j=0;j<getSize();j++){
					if(!(artist.equals(musics.get(i).getArtist()))){
						artist = musics.get(i).getArtist();
						nSong=numMusics(artist);
					}
					if(nSong==mostNSong){
						if(longestMusic(artist)>=longestMusic(mostProductive)){
							mostProductive=artist;
							nSong=mostNSong;					
						}
					}
					else if(nSong>mostNSong){
						mostProductive=artist;
						mostNSong=nSong;
					}
					 
						
				}
						
			}
		}
		return mostProductive;
	}





	public PlayList sort(){
		ArrayList <Music> temp = (ArrayList<Music>) musics.clone();
		Collections.sort(temp);
		return new PlayList (temp, getName(), capacity());
	}
}

