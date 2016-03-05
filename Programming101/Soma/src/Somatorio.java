
public class Somatorio {

	/*public static int somatorio(int value){
		int i=1;
		int res=0;	
		while(i<=value){
			res=res+i;
			i++;		
	}
	return res;*/

	
	public static int fibonacci(int n){
		int prim=0;
		int seg=1;
		int fibn=0;
		int count=2;
		
		if(n==0)
		fibn=0;
			
		if(n==1)
			fibn=1;			
		
		while(count<=n){
			fibn=prim+seg;
			prim=seg;
			seg=fibn;
			count ++;}
	}

return fibn;

	
	
	}
	
	
	

