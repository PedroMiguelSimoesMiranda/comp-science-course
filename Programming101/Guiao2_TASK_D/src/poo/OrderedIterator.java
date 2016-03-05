package poo;
/**
 * 
 * @author Pedro 28289
 *
 */
public class OrderedIterator extends AbstractIterator {


	private PlayList playList;
	private StraightforwardIterator s;

	public OrderedIterator(PlayList pl){
		super(pl);
		playList=pl.sort();
		s=new StraightforwardIterator(playList);

	}


	public Music next(){
		return s.next();
	}

	public boolean hasNext(){
		return s.hasNext();
	}
}
