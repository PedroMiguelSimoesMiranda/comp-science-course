import com.ibm.ruler.*;
import java.awt.Point;
import java.util.Random;
/**
 * This is the class that you must implement to enable your ruler within
 * the CodeRuler environment. Adding code to these methods will give your ruler
 * its personality and allow it to compete.
 */
public class MyRuler extends Ruler {
	/* (non-Javadoc)
	 * @see com.ibm.ruler.Ruler#getRulerName()
	 */
	public String getRulerName() {
		return "XXX_420_XXX";
	}

	/* (non-Javadoc)
	 * @see com.ibm.ruler.Ruler#getSchoolName()
	 */
	public String getSchoolName() {
		return "Team 55";
	}

	/* (non-Javadoc)
	 * @see com.ibm.ruler.Ruler#initialize()
	 */
	public void initialize() {
		// put implementation here		
	}
	
	protected Random rand = new Random();

	/* (non-Javadoc)
	 * @see com.ibm.ruler.Ruler#orderSubjects(int)
	 */
	public void orderSubjects(int lastMoveTime) {
		// put implementation here
		
		IKnight[] ourKnights = getKnights();
		IPeasant[] ourPeasants = getPeasants();
		ICastle[] ourCastles = getCastles();
		
		ICastle[] castles = World.getOtherCastles();
		
		int kSize = ourKnights.length;
		int cSize = castles.length;
		int pSize = ourPeasants.length;
		int ocSize = ourCastles.length;
		
		int closestCastle = 500;
		int index1;
		int index2;
		index1 = index2 = 0;		
		int secndX = 0;
		int secndY = 0;
		int goX = 0;
		int goY = 0;	
		


		for (int w = 0; w < ocSize; ++w )
		{	
			if(cSize < ocSize - 1)
				createPeasants(ourCastles[w]);
			else
				createKnights(ourCastles[w]);				
		}	
		
		// move peasants
		for( int k = 0; k < pSize; ++k)
		{
			IPeasant pn = ourPeasants[k];
			boolean brand = true;
			
			for(int x = 1; x < 9; ++x)
			{
				Point np = World.getPositionAfterMove(pn.getX(), pn.getY(), x);
				if(np != null)
				// point is on board
				{
					if(World.getLandOwner(np.x, np.y) == null || !World.getLandOwner(np.x, np.y).equals(pn.getRuler()))
					{				
						if(World.getObjectAt(np.x, np.y) == null)
						{
							move(pn, x); 
							brand = false;
						} 
						else
							continue;	
					}
				}										
			}
			if(brand)
			{
				int far = 0;
				//move(pn, rand.nextInt(8) + 1);
				int farther;
				for (int i = 0; i < cSize; ++i)
				{			
					farther = pn.getDistanceTo(castles[i].getX(), castles[i].getY());
					if (farther > far)
					{
						far = farther;					
						goX = castles[i].getX();
						goY = castles[i].getY();
					}
				}					
				move(pn, pn.getDirectionTo(goX, goY));
			}							
		}
		
		
		// move knights
		for (int j = 0; j < kSize; ++j)
		{
			IKnight kn = ourKnights[j];			
			// stand and protect			
			if(cSize == 0)
			{	
				
				boolean didntcapture = false;
				for(int x = 1; x < 9; ++x)
				{					
					Point np = World.getPositionAfterMove(kn.getX(), kn.getY(), x);
					if(np != null)
					// point is on board
					{
						IObject obj = World.getObjectAt(np.x, np.y);
						if( obj != null && !obj.getRuler().equals(kn.getRuler()) )
						{
							capture(kn, x);
							didntcapture  = true;
						}						
					}									
				}
				if(didntcapture)
				{   int close;
					int far = 0;
					for (int i = 0; i < cSize; ++i)
					{
									
						int farther = kn.getDistanceTo(castles[i].getX(), castles[i].getY());
						if (farther > far)
						{
							far = farther;					
							goX = castles[i].getX();
							goY = castles[i].getY();
						}
					}					
					move(kn, kn.getDirectionTo(goX, goY));
				}				
			}
			
			// swarm to castle
			else 
			{
				closestCastle = 500;
				int close;
				for (int i = 0; i < cSize; ++i)
				{			
					close = kn.getDistanceTo(castles[i].getX(), castles[i].getY());
					if (close < closestCastle)
					{
						closestCastle = close;					
						goX = castles[i].getX();
						goY = castles[i].getY();
					}
				}
				boolean dont_move = false;
				//swarm to closest castle			 
				for(int x = 1; x < 9; ++x)
				{
					Point np = World.getPositionAfterMove(kn.getX(), kn.getY(), x);
					if(np != null)
					// point is on board
					{
						IObject obj = World.getObjectAt(np.x, np.y);
						if( obj != null && !obj.getRuler().equals(kn.getRuler()) )
						{
							capture(kn, x);
							dont_move = true;
							break;				
						}				
					}								
				}
				if(!dont_move)
				{
					int dir = kn.getDirectionTo(goX, goY);
					Point np = World.getPositionAfterMove(kn.getX(), kn.getY(), dir);				  
					if(np != null)
					{
						IObject obj = World.getObjectAt(np.x, np.y);
						if( obj == null)
						{
								move(kn, dir);
						}
						else 
						{
							Point npleft = World.getPositionAfterMove(kn.getX(), kn.getY(), dir+1);
							if(npleft != null)
							{
								IObject obj1 = World.getObjectAt(npleft.x, npleft.y);
								if( obj1 == null)
								{
										move(kn, dir+1);
								}
								else
								{
									Point nprt = World.getPositionAfterMove(kn.getX(), kn.getY(), dir-1);
									if(nprt != null)
									{
										IObject obj2 = World.getObjectAt(npleft.x, npleft.y);
										if( obj2 == null)
										{
											move(kn, dir-1);
										}										
									}
								}
							}
							
						} 
					}
				}			 
			 }											
		}
		
		return;		
	}
}