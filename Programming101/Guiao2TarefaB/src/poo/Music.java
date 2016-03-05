package poo;

public class Music {
	public static final int FORMAT_AIFF=1;
	public static final int FORMAT_MP3=2;
	public static final int FORMAT_WAV=3;
	private String musicName;
	private String artistName;
	private int duration;
	private int mformat;
	private int rating;
	public static final int MINIMUM_RATING=1;
	public static final int MAXIMUM_RATING=5;

	public Music(String mName){
		musicName=mName;
		artistName="";
		duration=0;
		mformat=0;
		rating=MINIMUM_RATING;
	}

	public Music(String name,String artist,int duration,int format,int rating){
		this(name);
		this.artistName=artist;
		this.duration=duration;
		if(format>MAXIMUM_RATING){
		mformat=MAXIMUM_RATING;
		}
		else if(format<MINIMUM_RATING){
		mformat=MINIMUM_RATING;	
		}
		else 
			mformat=format;
		this.rating=rating;	
	}
	
	public String getName() {
		return musicName;
	}

	public String getArtist() {
		return artistName;
	}


	public int getDuration() {
		return duration;
	}

	public int getFormat() {
		return mformat;
	}

	public int getRating() {
		return rating;
	}

	public Music convert(int f){
		Music m = new Music(musicName,artistName,duration,f,rating);
		return m;
	}
	
	/*public boolean equals(Object m){
		return m != null &&
		m instanceof Music &&
		musicName == ((Music)m).getName() &&
		artistName == ((Music)m).getArtist() &&
		duration == ((Music)m).getDuration()&&
		rating == ((Music)m).getRating();
	}*/
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Music other = (Music) obj;
		if ( artistName== null) {
			if (other.artistName != null)
				return false;
		} else if (!artistName.equals(artistName))
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


}