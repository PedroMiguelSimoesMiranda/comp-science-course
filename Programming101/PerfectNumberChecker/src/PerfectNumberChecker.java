public class PerfectNumberChecker {
	
	//Este � um construtor por defeito
	PerfectNumberChecker(){
	}
	
	//Esta fun��o devolve verdadeiro se um n�mero number, argumento da fun��o, for perfeito
	public boolean isPerfectNumber(int number){
		int divisor=1;
		int sum=0;
	
		//Este ciclo coloca a soma dos divisores na vari�vel sum.

		while(divisor<number){
			if((number%divisor)==0){
				sum=sum+divisor;
			}
			divisor=divisor+1;
		}
		return sum==number;
		
	}

}


