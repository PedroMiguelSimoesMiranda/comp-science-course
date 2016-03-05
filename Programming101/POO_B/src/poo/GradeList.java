package poo;

public class GradeList {
	private int[]gradelist;
	private static final int max= 100;
	private int number;

	GradeList(){
		gradelist= new int[max];
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



	/*public int tabFrequencias(int t){
		int freq=0;
		int i=0;
		while(i<number){
			if(gradelist[i]==t)
				freq++;
		}
		
		return freq;
	}*/

	public int tabFrequencias(int f){
		int freq=0;
		for(int i=0; i<number;i++){
			if(gradelist[i]==f){
				freq++;
			}
		}
		return freq;
	}

}
