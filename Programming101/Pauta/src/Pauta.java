public class Pauta {

	static final int DEFAULT_SIZE=100;
	
	private int counter;
	private double [] grades;
	private int [] numbers;
	private String [] alunos;
	private int neg=0;
	private int pos=0;
	private double soma;
	
	Pauta() {
		counter=0;
		numbers=new int[DEFAULT_SIZE];
		grades= new double[DEFAULT_SIZE];
	}

	
	
	public double getGrade(int number) {
		boolean found = false;
		double grade= -1.0;
		int i = 0;
			while(i<counter && (!found)){
				if(numbers[i]==number){
					found=true;
					grade=grades[i];}
						i++;}
	return grade;
	}
	
	
	public void addGrade(int number, double grade) {
		double g;
		g = this.getGrade(number);
		if(g<0){
			numbers[counter]=number;
			grades[counter]=grade;
			counter++;
		}
	}
	
	public void setGrade(int number, double grade){
		for(int i=0; i<counter;i++){
			if(number==numbers[i] && grade==grades[i]){
				numbers[i]=number;
				grades[i]=grade;
			}
		}
	}
	
	public int getNumberOfPositiveGrades(){
		int pos=0;
		for(int a=0; a < counter; a++){
		if(grades[a] >= 10){
			pos=pos+1;
		}
		return pos;
		}
	}
	public int getNumberOfNegativeGrades(){
		int neg=0;
		for(int a=0; a < counter; a++){
		if(grades[a] < 10){
			neg=neg+1;
		}
		return pos;
		}
	}
	
	public double getMaxGrade(){
		double max=0;

		for(int i=0; i<counter; i++){
			if (i==0){
				max=grades[i];
				}
			
		   if (grades[i]>=max){
			   max=grades[i];
		   }
		}
		return max;
		}
	
	public double getMinGrade(){
		double min=0;

		for(int i=0; i<counter; i++){
			if (i==0){
				min=grades[i];
				}
			
		   if (grades[i]<=min){
			   min=grades[i];
		   }
		}
		return min;
		}
	
	public double getAverageGrade(){
		
		for(int i = 0; i<counter;i++){
			soma=soma+grades[i];}
		return soma/counter;
		
	}
	
	public int getNumberOfGrades(){
		int number=0;

		for(int i=0; i<counter; i++){
			if (i==0){
				number=number + 1;
				}
			
		}
		return number;
		}
	
	


	public double getApprovalRate(){
		double percentagem=0.0;
		percentagem=(this.getNumberOfPositiveGrades()/this.getNumberOfGrades());
		return percentagem;
	}
	public double getFailRate(){
		double fail=0.0;
		fail=(this.getNumberOfNegativeGrades()/this.getNumberOfGrades());
		return fail;
	}
	public int getNumberEqualGrade(double grade){
		int equal=0;
		for(int i = 0; i<counter;i++){
			if(grades[i]==grade )
				equal++;
		}
		return equal;
	}
	
	/*public void removeGrade(int number)
	*/
	
	public String listGrades(){
		String result="";
		for(int i = 0; i<counter - 1; i++)
			result += alunos[i] + " " + grades[i] + "\n" ;
		return result;
	}
	
	
	
	
}
