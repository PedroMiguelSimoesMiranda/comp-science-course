package poo;
/**
 * 
 * @author Pedro 
 * 28289
 * Os objectos da classe music são aqui definidos que representam musicas
 */
public class Music implements Comparable<Music>{
	
//	Constantes
	public static final int FORMAT_AIFF=2;
	public static final int FORMAT_MP3=4;
	public static final int FORMAT_WAV=6;
	public static final int MINIMUM_RATING=1;
	public static final int MAXIMUM_RATING=5;


	private String musicName;
	private String artistName;
	private int duration;
	private int mformat;
	private int rating;

/**
 * @param musicName
 * @param artistName
 * @param duration
 * @param mformat
 * @param rating
 */
	//Construtor
	public Music(String name,String artist,int duration,int format,int rating){
		this(name);
		this.artistName=artist;
		this.duration=duration;
		if(format>MAXIMUM_RATING){
		format=MAXIMUM_RATING;
		}
		else if(format<MINIMUM_RATING){
		format=MINIMUM_RATING;	
		}
		else 
			this.mformat=format;
		this.rating=rating;	
	}

/**
 * 
 * @param musicName
 */	
	public Music(String name){
		this.musicName=name;
		
		
	}
/**
 * 
 * @return the name of the music
 */
	public String getName(){
		return musicName;
	}
/**
 * 
 * @return nameArtist
 */
	public String getArtist(){
		return artistName;
	}
/**
 * 
 * @return duration
 */
	public int getDuration(){
		return duration;
	}
/**
 * 
 * @return mformat
 */
	public int getFormat(){
		return mformat;
	}

	/**
	 * 
	 * @return rating
	 */
	public int getRating(){
		return rating;
	}
/**
 *@param compares a object to the actual and returns boolean answer
 */

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Music other = (Music) obj;
		if (artistName == null) {
			if (other.artistName != null)
				return false;
		} else if (!artistName.equals(other.artistName))
			return false;
		if (duration != other.duration)
			return false;
		if (musicName == null) {
			if (other.musicName != null)
				return false;
		} else if (!musicName.equals(other.musicName))
			return false;
		if (rating != other.rating)
			return false;
		return true;
	}
/**
 * 
 * @param fileFormat makes a new object of Music with the new format of the music
 * @return the new object
 */
	public Music convert(int fileFormat){
		Music m = new Music(musicName,artistName,duration,fileFormat,rating);
		return m;	
	}

//
//
//
	/**
	 * @param compares Music with the actual Music through the musicName
	 * @return zero if m is equals, -1 or 1 if it is less or greater that this String
	 * @see 
	 */
	@Override
	public int compareTo(Music m) {
		return musicName.compareTo(m.getName());
	}

}

