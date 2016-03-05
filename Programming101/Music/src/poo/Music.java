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
	

	public Music(String mName){
		musicName=mName;
		artistName="";
		duration=0;
		mformat=0;
		rating=0;
	}

	public Music(String mName, String artist, int dur, int fileFormat, int rate){
		this(mName);
		artistName=artist;
		duration=dur;
		mformat=fileFormat;
		rating=rate;
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
	
	
}
