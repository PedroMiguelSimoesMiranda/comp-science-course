package poo;

public class Music {

	public static final int FORMAT_AIFF = 1;
	public static final int FORMAT_MP3 = 2;
	public static final int FORMAT_WAV = 3;
	public static final int MINIMUM_RATING = 1;
	public static final int MAXIMUM_RATING = 5;
	
	private String musicName, artist;
	private int duration, rating, fileFormat;
	
	
	public Music(String musicName) {
		this.musicName = musicName;
	}
	
	public Music(String musicName, String artist, int duration, int fileFormat, int rating) {
		this(musicName);
		this.artist = artist;
		this.duration = duration;
		this.fileFormat = fileFormat;
		this.rating = rating;
	}
	
	
	public String getName() {
		return musicName;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getFormat() {
		return fileFormat;
	}
	
	public int getRating() {
		return rating;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Music other = (Music) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
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
	
	public Music convert(int fileFormat) {
		Music test = new Music(musicName,artist,duration,fileFormat,rating);
		return test;
	}
	
}