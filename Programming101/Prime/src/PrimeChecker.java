
public class PrimeChecker {
	public static boolean isPrime(int n){
boolean prime;
int v;
v= n-1;
prime=true;
if((n==1) && (prime)){
	if((n%v)==0){
			prime=false;
			v--;}
}
return prime;
}
}
