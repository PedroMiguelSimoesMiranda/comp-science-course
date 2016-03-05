public class PerfectNumberChecker {
	
	//Este é um construtor por defeito
	PerfectNumberChecker(){
	}
	
	//Esta função devolve verdadeiro se um número number, argumento da função, for perfeito
	public boolean isPerfectNumber(int number){
		int divisor=1;
		int sum=0;
	
		//Este ciclo coloca a soma dos divisores na variável sum.

		while(divisor<number){
			if((number%divisor)==0){
				sum=sum+divisor;
			}
			divisor=divisor+1;
		}
		return sum==number;
		
	}

}


