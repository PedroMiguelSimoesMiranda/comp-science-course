package poo;
// TAREFA A
public class Grade {
	private int[]gradelist;
	public  static final int MAX_G= 100;
	private int number;
	private double media;
	public Grade(){
		gradelist= new int[MAX_G];
		number=0;
	}

	public void addGrade(int grade){		
		if(number==gradelist.length){
			int [] tmp = new int[2*gradelist.length];
			for(int i=0; i<gradelist.length; i++)
				tmp[i] = gradelist[i];
			gradelist = tmp;
		}
		gradelist[number++]=grade;
	}


	
	public int getModa(){
		int[]maximo;
		maximo=new int[21];
		int valorMaximo=0;
		int moda = 0;
		int Count=0;
		for(int i=0;i<21;i++){
			Count=0;
			for(int j=0;j<number;j++){
				if(gradelist[j]==i)
					Count++;
			}
			maximo[i]=Count;
		}
		for(int t=0;t<21;t++){
			if(maximo[t]>=valorMaximo) {
				valorMaximo=maximo[t];
				moda = t;
			}
		}
		return moda;
	}

	/*public int getModa(){
		int countTMP=0;
		int nota=0;
		int max=0;
		for(int n =0;n <21;n++){
			int i=0;
			while( i<number){
				nota=gradelist[i];
				if(nota >max){
					if(countTMP!=0)
						countTMP=0;
					max=gradelist[i];
					countTMP++;
					i++;
				}
				else if(nota==max){
					countTMP++;
					i++;
				}
				else // se nota < max
					i++;

			}
		}
		return max;

	}*/

	public double media(){
		double sum=0;
		media=0;
		int i=0;
		while(i<number){
			sum=sum+gradelist[i];
			i++;
		}
		if(sum!=0)
			media=sum/number;

		return media;
	}

	public double desvioPadrao(){
		double desvio=0;
		double sum=0;
		for(int i=0;i<number;i++)
			sum+= Math.pow(gradelist[i]-media,2);

		desvio=Math.sqrt(sum/number);
		return desvio;
	}

}