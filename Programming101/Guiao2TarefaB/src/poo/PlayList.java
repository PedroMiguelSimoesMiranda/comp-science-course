package poo;

import java.util.ArrayList;
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
		String nameArtist="";
		ArrayList <Music> tmpArraylist = new ArrayList<Music>();
		if(musics.size()>0){
			for(int i=0;i<musics.size();i++){
				nameArtist=musics.get(i).getArtist();
				for(int k=0;k<musics.size();k++){
					if(! musics.get(k).getArtist().equals(nameArtist)){
						for(int j=0; j<tmpArraylist.size();j++){
							if(! tmpArraylist.get(k).getArtist().equals(nameArtist)){
								tmpArraylist.add(musics.get(k));
							}
						}
					}
				}
			}
		}
		return tmpArraylist.size();
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
		int sum=0;
		for(int i=0;i<musics.size();i++){
			sum=sum+musics.get(i).getDuration();
		}
		return sum;
	}

	private String longestDuration(){
		String longest="";
		int longestDur=0;
		int actualDur=0;

		if(musics.size()>0){
			longestDur=musics.get(0).getDuration();
			for(int i=0;i<musics.size();i++){
				actualDur=musics.get(i).getDuration();
				if(longestDur<actualDur){
					longestDur=actualDur;
					longest=musics.get(i).getArtist();
				}
			}
		}
		return longest;
	}


	public String mostProductiveArtist(){
		String mostProductive="";
		String artist="";
		int nSong=0;
		int mostNSong=0;
		int nArtists;

		nArtists=numArtists();

		for(int i=0; i<nArtists;i++){
			for (int j=0;j<musics.size();j++){
				if(!(artist.equals(musics.get(j).getArtist()))){
					artist = musics.get(i).getArtist();
					nSong=numMusics(artist);
			}					
				if(nSong==mostNSong){
					mostProductive=longestDuration();
						nSong=mostNSong;					
				}			
				else if(nSong>mostNSong){
					mostProductive=artist;
					mostNSong=nSong;
				}
		}

//ESTE AKI N INTERESSA N OLHES PARA O K TA COMENTADO A BAIXO
		/*if(counter>0){  // Se o vector nao tiver vazio
			for(int i=0; i<counter;i++){ 
				int count=0;
				actualArtist=musics[i].getArtist();
				ArrayList<arraylist>=new r<String>;
				for(int j=0; j<counter;j++){  // VAI PERCORRER O VECTOR 
					if(musics[j].getArtist().equals(actualArtist)){ // QD ENCONTRA INCREMENTA
						count++;
					}
					eachProdVector[i]=count;// INTRODUZ NO VECTOR TEMPORARIO O NUMERO DE MUSICAS DO ARTISTA ACTUAL
					numOfArtists++;
					if(count>max){
						max=count;
					}
				}

				for(int y=0; y>numOfArtists;y++){	
					if(eachProdVector[i]>max){
						mostProductive=musics.get(i)getArtist();
					}
					if(eachProdVector[i]==max){
						mostProductive=longestDuration();
					}
				}

			}


		}
		return mostProductive;
	}
}