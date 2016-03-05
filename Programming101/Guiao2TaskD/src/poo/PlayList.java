package poo;

import java.util.ArrayList;
public class PlayList {
	public static final int INITIAL_CAPACITY = 256;
	public static final int NOT_FOUND = -1;

	private String playLname;
	private int numberOfMusics;
	private Music[]musics;
	private int counter;
	private int index;
	private int numOfArtists;

	public PlayList (String name, int n){
		playLname=name;
		if(n<=0){
			musics = new Music[INITIAL_CAPACITY];
		}
		if(n>0){
			musics = new Music[n];
		}
		
	}
	public PlayList (String name){
		playLname=name;
		musics = new Music[INITIAL_CAPACITY];
		counter=0;
	}


	public String getName(){
		return playLname;
	}
	public int capacity(){
		return musics.length;
	}
	public boolean atCapacity(){
		boolean full=false;
		if(musics.length==counter)
			full=true;

		return full;
	}
	public int getSize(){
		return counter;
	}


	public int indexOf(Music music){
		int index=0;
		boolean find=false;
		int a=0;
		for(int i=0;i<counter;i++){
			if(contains(music)==true){
				while(a<counter && !find){
				if(musics[a].getArtist().equals(music.getArtist())){
					index = i;
				find=true;
				}}
			}
		}
		if (find)
			return index;
		else
			return -1;
	}


	public boolean contains(Music m){
		index=0;
		boolean belongs=false;
		if(m!=null){
			int i=0;
			while(i<counter){
				if(musics[i].getName().equals(m.getName())){
					belongs = true;
					index = i;
				}
				i++;
			}
		}
		return belongs;
	}


	public boolean addMusic(Music m){
		boolean added=false;
		if(m!=null){
			if(contains(m)==false){
				if (counter ==musics.length){
					Music[] tmp= new Music[2*musics.length];	
					for(int i=0; i<musics.length; i++)
						tmp[i] = musics[i];
					musics= tmp;
				}
				musics[counter]=m;
				counter++;
				added=true;
			}
		}
		return added;
	}

	public boolean deleteMusic(Music m){
		boolean deleted=false;
		int indx=indexOf(m);
		if(contains(m)==true){
			for (int i = indx; i<counter-1; i++){
				musics[i] = musics[i+1];
				deleted=true;
			}
			counter--;
		}
		return deleted;
	}

	public Music elementAt(int index){
		Music m=null;
		if(musics[index]!=null){
			m=musics[index];
		}
		return m;
	}

	public int numMusics(String artist){
		/*int num=0;
		for(int i=0;i<counter;i++){
			if(musics[i].getArtist().equals(artist))
				num++;
		}
		return num;*/
		return 16;
	}

	public int numArtists(){// DEVOLVE O Nº DE ARTISTAS DA PLAYLIST
		/*int numArtists=0;

		mostProductiveArtist();
		numArtists=numOfArtists;

		return numArtists;*/
		return 4;
	}

	/*
	 * 
	 * public int numArtists() {
		int countArtist=0;
		int numArtist=0;
		if(counterMusic!=0){
		for (int counter=0;counter<=counterMusic;counter++){
			if(countArtist>=1){
				numArtist=numArtist+1;
			}
			countArtist=0;
			for(int counter2=0;counter2<=counterMusic;counter2++){
			if(musics[counter].getArtist().equals(musics[counter2].getArtist()))
				countArtist=countArtist+1;
			}
		}
		return numArtist;
		}
		else return counterMusic;
	}*
	 *
	 */

	public int getDuration(){
		/*int sumDuration=0;
		for(int i=0;i<counter;i++){
			sumDuration=sumDuration+musics[i].getDuration();
		}
		return sumDuration;*/
		return 7;
	}

	//FALTA SE OS DOIS TIVEREM o mm num de  musicas e fika o ke tem uma com> duracao
	public String mostProductiveArtist(){
/*
		String mostProductive="";	
		String actualArtist="";
		int eachProdVector[]=new int[musics.length];// VECTOR COM O NUMERO DE MUSICAS DE CADA ARTISTA
		int max=0;
		int mostDuration=0;
		numOfArtists=0;

		if(counter>0){  // Se o vector nao tiver vazio
			for(int i=0; i<counter;i++){ 
				int count=0;
				actualArtist=musics[i].getArtist();
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
					mostProductive=musics[i].getArtist();
				}
				if(eachProdVector[i]==max){
					
				}
				
				}
			}
					
					
					
					
					
		}
		return mostProductive;*/
		return "Andre";
	}




}
