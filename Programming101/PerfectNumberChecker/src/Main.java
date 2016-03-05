public class Main {
	
	
static final int NUMBER_CHECKER=100;
	

	
	public static void main(String[] args) {
		int number=1;
		PerfectNumberChecker numberChecker=new PerfectNumberChecker();
		
		while(number<=100){
			if(numberChecker.isPerfectNumber(number)) {
			System.out.println(number);
			}
			number=number+1;
		
		}
		
		

	}

}

