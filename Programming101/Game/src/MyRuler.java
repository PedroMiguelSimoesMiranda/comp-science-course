import com.ibm.ruler.*;
import java.awt.Point;
import javax.swing.Timer; 
import java.util.TimerTask;
/**
 * This is the class that you must implement to enable your ruler within
 * the CodeRuler environment. Adding code to these methods will give your ruler
 * its personality and allow it to compete.
 */

public class MyRuler extends Ruler {
	Timer timer;
	boolean t=timer.equals(500);
	public static final String SCHOOL_NAME = "FCT UNL";
	public static final String RULER_NAME = "Pedro";



	public static final int MINIMUM_PEASANTS_WANTED = 50;
	public static final int MAX_DISTANCE = 4068;
	public static final int ZERO = 0;
	public static final int ONE = 1;

	ICastle[] enemyCastles;
	IKnight[] enemyKnights;
	IPeasant[] enemyPeasants;

	ICastle[] myCastles;
	IKnight[] myKnights;
	IPeasant[] myPeasants;

	private int enemyCastleSize;

	private int myCastleSize;
	private int myKnightSize;
	private int myPeasantSize;



	public String getSchoolName(){
		return "FCT / UNL";
	}

	public String getRulerName(){
		return "Pedro";
	}

	/* (non-Javadoc)
	 * @see com.ibm.ruler.Ruler#initialize()
	 */
	public void initialize() {

	}

	/* (non-Javadoc)
	 * @see com.ibm.ruler.Ruler#orderSubjects(int)
	 */
	public void orderSubjects(int lastMoveTime) {
		long inicio = System.currentTimeMillis();
		timer = new Timer(1000, null);
		 timer.getDelay();
timer.setDelay(5000);


		enemyCastles = World.getOtherCastles();
		enemyKnights = World.getOtherKnights();
		enemyPeasants = World.getOtherPeasants();
		enemyCastleSize = enemyCastles.length;

		myCastles = getCastles();
		myKnights = getKnights();
		myPeasants = getPeasants();

		myCastleSize = myCastles.length;
		myKnightSize = myKnights.length;
		myPeasantSize = myPeasants.length;


//		Order My Castles
		if(myCastleSize != ZERO){

			if(enemyCastleSize != ZERO || myPeasantSize >= MINIMUM_PEASANTS_WANTED){

				for(ICastle castle : myCastles){
					createKnights(castle);
				}
			}
			else
				for(ICastle castle : myCastles){
					createPeasants(castle);
				}					
		}



//		Order My Knights
		if(myKnightSize != ZERO){

			if(enemyCastleSize != ZERO){

				//Go Enemy Castles

				for(IKnight knight : myKnights){


					ICastle castleAim = this.getNearestCastle(knight);
					int direction = knight.getDirectionTo(castleAim.getX(), castleAim.getY());
					Point point = World.getPositionAfterMove(knight.getX(), knight.getY(), direction);
					IObject object = World.getObjectAt(point.x, point.y);

					if(object != null){

						if(!object.getRuler().equals(knight.getRuler())){
							capture(knight, direction);
						}

						else{

							for(int i=Ruler.MOVE_N; i <= Ruler.MOVE_NW ; i++){

								int dir = (int) (direction + Math.pow(-1,i)*(i/2));
								dir = ((dir+Ruler.MOVE_W)%8)+1;

								if(isOccupied(knight, dir) == null){
									move(knight, dir);
								}
							}
						}
					}

					else
						move(knight, direction);
				}
			}

			else if(enemyKnights.length != ZERO){

				//Go Enemy Knights

				for(IKnight knight : myKnights){

					IKnight knightAim = this.getNearestKnight(knight);
					int direction = knight.getDirectionTo(knightAim.getX(), knightAim.getY());
					Point point = World.getPositionAfterMove(knight.getX(), knight.getY(), direction);

					IObject object = World.getObjectAt(point.x, point.y);

					//There is An Object In the Given Direction
					if(object != null){

						if(!object.getRuler().equals(knight.getRuler())){

							capture(knight , direction);
						}
						else{

							for(int i=Ruler.MOVE_N; i <= Ruler.MOVE_NW ; i++){

								int dir = (int) (direction + Math.pow(-1,i)*(i/2));
								dir = ((dir+Ruler.MOVE_W)%8)+1;

								if(isOccupied(knight, dir) == null){
									move(knight, dir);
								}	
							}
						}
					}
					else
						move(knight, direction);
				}
			}	

			else if(enemyPeasants.length != ZERO){

				//Go enemy Peasants

				for(IKnight knight : myKnights){

					IPeasant peasantAim = this.getNearestPeasant(knight);
					int direction = knight.getDirectionTo(peasantAim.getX(), peasantAim.getY());
					Point point = World.getPositionAfterMove(knight.getX(), knight.getY(), direction);

					IObject object = World.getObjectAt(point.x, point.y);

					//There is An Object In the Given Direction
					if(object != null){

						if(!object.getRuler().equals(knight.getRuler())){

							capture(knight , direction);
						}

						else{

							for(int i=Ruler.MOVE_N; i <= Ruler.MOVE_NW ; i++){

								int dir = (int) (direction + Math.pow(-1,i)*(i/2));
								dir = ((dir+Ruler.MOVE_W)%8)+1;

								if(isOccupied(knight, dir) == null){
									move(knight, dir);
								}
							}
						}
					}

					else
						move(knight, direction);
				}
			}
			/*
			else{

				for(IKnight knight : myKnights){

					Point point = World.getPositionAfterMove(knight.getX(), knight.getY(), Ruler.MOVE_E);

					if(point != null){
						IObject object = World.getObjectAt(point.x, point.y);

						if(object != null){
							for(int i=Ruler.MOVE_N; i <= Ruler.MOVE_NW ; i++){

								int dir = (int) (Ruler.MOVE_E + Math.pow(-1,i)*(i/2));
								dir = ((dir+Ruler.MOVE_W)%8)+1;

								if(isOccupied(knight, dir) == null){
									move(knight, dir);
								}
							}
						}
						else
							move(knight, Ruler.MOVE_E);
					}
				}
			}

			 */
		}	

		long fim = System.currentTimeMillis() - inicio;  
		System.out.println(fim);
		if(fim <10){
//			Order My Peasants
			if(myPeasantSize != ZERO){

				for(IPeasant peasant : myPeasants){

					boolean notMoved = true;

					for(int i=Ruler.MOVE_N; i<=Ruler.MOVE_NW ; i++){

						Point point = World.getPositionAfterMove(peasant.getX(), peasant.getY(), i);

						//Point is In-Game
						if(point != null){

							IObject object = World.getObjectAt(point.x, point.y);
							//There Is No Object
							if(object == null){

								//Land is not Claimed
								if(World.getLandOwner(point.x, point.y) == null 
										|| !World.getLandOwner(point.x, point.y).equals(peasant.getRuler())){

									move(peasant, i);
									notMoved = false;
								}
							}
						}
					}

					if(notMoved){

						if(World.getLandOwner(World.WIDTH-ONE, ZERO) == null  || World.getLandOwner(World.WIDTH-ONE, ZERO) != this){
							int direction = peasant.getDirectionTo(World.WIDTH-ONE, ZERO);
							move(peasant, direction);
							notMoved=false;
						}
						else if(World.getLandOwner(World.WIDTH-ONE, World.HEIGHT-ONE) == null || World.getLandOwner(World.WIDTH-ONE, World.HEIGHT-ONE) != this ){
							int direction = peasant.getDirectionTo(World.WIDTH-ONE, World.HEIGHT-ONE);
							move(peasant, direction);
							notMoved=false;
						}
						else if(World.getLandOwner(ZERO, World.HEIGHT-ONE) == null || World.getLandOwner(ZERO, World.HEIGHT-ONE) != this ){
							int direction = peasant.getDirectionTo(ZERO, World.HEIGHT);
							move(peasant, direction);
							notMoved=false;
						}
						else if(World.getLandOwner(ZERO, ZERO) == null || World.getLandOwner(ZERO, ZERO) != this){
							int direction = peasant.getDirectionTo(ZERO, ZERO);
							move(peasant, direction);
							notMoved=false;
						}
						else{
							int direction = peasant.getDirectionTo((World.WIDTH/2), (World.HEIGHT/2));
							move(peasant, direction);
							notMoved=false;
						}
					}
				}
			}

		}
	}

	/**
	 * @param object
	 * @return
	 */
	public ICastle getNearestCastle(IObject object){

		ICastle nearestCastle = null;
		int shortestDistance = MAX_DISTANCE;
		for(ICastle castle : enemyCastles){
			int distance = castle.getDistanceTo(object.getX(),object.getY());
			if(distance < shortestDistance){
				shortestDistance = distance;
				nearestCastle = castle;
			}
		}
		return nearestCastle;
	}


	/**
	 * @param object
	 * @return
	 */
	public IKnight getNearestKnight(IObject object){
		IKnight knightAim = null;
		int shortestDistance = MAX_DISTANCE;
		for(IKnight knight : enemyKnights){
			int distance = knight.getDistanceTo(object.getX(), object.getY());
			if(distance < shortestDistance){
				shortestDistance = distance;
				knightAim = knight;
			}
		}
		return knightAim;
	}

	/**
	 * @param object
	 * @return
	 */
	public IPeasant getNearestPeasant(IObject object){
		IPeasant peasantAim = null;
		int shortestDistance = MAX_DISTANCE;
		for(IPeasant peasant : enemyPeasants){
			int distance = peasant.getDistanceTo(object.getX(), object.getY());
			if(distance < shortestDistance){
				shortestDistance = distance;
				peasantAim = peasant;
			}
		}
		return peasantAim;
	}

	public IObject isOccupied(IObject object, int direction){
		IObject occupiedBy = null;
		Point point = World.getPositionAfterMove(object.getX(), object.getY(), direction);
		//Point is In-Game
		if(point != null){
			IObject other = World.getObjectAt(point.x, point.y);
			if(other != null){
				occupiedBy = other;
			}
		}
		return occupiedBy;
	}


}
