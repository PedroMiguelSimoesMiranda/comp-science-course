import java.io.Serializable;

public class KeyDip implements Comparable<KeyDip>, Serializable{

	private static final long serialVersionUID = 1L;
	
	int media;
	String nome;
	
	KeyDip(int m, String n){
		media = m;
		nome = n;
	}
	
	public int getMedia(){
		return media;
	}
	public String getName(){
		return nome;
	}

	public int compareTo(KeyDip arg) {
		if(this.getMedia()==arg.getMedia()){
			if(this.getName().compareTo(arg.getName())==0)
				return 0;
			if(this.getName().compareTo(arg.getName())>0)
				return 1;
			if(this.getName().compareTo(arg.getName())<0)
				return -1;
		}
		else if(this.getMedia()<arg.getMedia())
			return 1;
		else if(this.getMedia()>arg.getMedia())
			return -1;
		return -1;
	}
	
}
