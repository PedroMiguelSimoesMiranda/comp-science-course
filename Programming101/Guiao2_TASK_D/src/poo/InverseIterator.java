package poo;
/**
 * 
 * @author Pedro 28289
 *
 */
public class InverseIterator extends AbstractIterator {


	private PlayList playList;
	private int nMusic;

	public  InverseIterator(PlayList pl){
		super(pl);
		playList=pl;
		nMusic=pl.getSize()-1;
	}

	public boolean hasNext(){
		return nMusic>=0;
	}

	public Music next(){
		Music m=null;
		if(hasNext())
			m=playList.elementAt(nMusic--);
		return m;
	}


}