
package poo;

import java.util.ArrayList;
import java.util.Collections;

public class PlayList {

	public static final int NOT_FOUND = -1;
	public static final int INITIAL_CAPACITY = 256;

	private ArrayList <Music> temp;
	private ArrayList <Music> musics;
	private String playListName;
	private int capacity;


	public PlayList (String name) {
		playListName = name;
		capacity = INITIAL_CAPACITY;
		musics = new ArrayList<Music>();
		temp= new ArrayList<Music>();
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
		if (contains(m) == true) return false;
		if (m == null) return false;
		if (atCapacity()) capacity*=2;
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
		return 0;
	}

	public int numMusics(String artist){
		return 0;
	}
	public int getDuration(){
		return 0;
	}

	public String mostProductiveArtist(){
		return "";
	}
}

