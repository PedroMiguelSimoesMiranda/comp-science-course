package poo;

public class GradeList {
	private Grade[]gradelist;
	public static final int max= 100;
	private int number;
	private Grade[]tmpList;
	//private int counter;

	GradeList(){
		gradelist= new Grade[max];
		number=0;
		
	}

	public void addGrade(int num,int grade){		
		if(number==gradelist.length){
			Grade [] tmp = new Grade[2*gradelist.length];
			for(int i=0; i<gradelist.length; i++)
				tmp[i] = gradelist[i];
			gradelist = tmp;
		}
		gradelist[number]=new Grade(num,grade);
		number++;
	}


	public void sortByGrade(){
		for(int i=1; i<number; i++)
			for(int j=number-1; j>=i; j--){
				if(gradelist[j-1].getGrade() < gradelist[j].getGrade()) {
					Grade tmp = gradelist[j-1];
					gradelist[j-1] = gradelist[j];
					gradelist[j] = tmp;
				}
				else if(gradelist[j-1].getGrade()==gradelist[j].getGrade()){
					if(gradelist[j-1].getNAluno() < gradelist[j].getNAluno()){
						Grade tmpII = gradelist[j-1];
						gradelist[j-1] = gradelist[j];
						gradelist[j] = tmpII;
					}
				}	
			}
		
	}
	public int getNumber() {
		return number;
	}
	public int getNaluno(int n){
		int numAluno=0;
		numAluno=gradelist[n].getNAluno();
		return numAluno;
	}

	public int getRes(int r){
		int res=0;
		res=tmpList[r].getGrade();

		return res;
	}



	String getHonor() {
		String list ="";
		int i = 0;
		sortByGrade();
		if (number > 0){
			while ((i<10) && (i<number)) {
				if ((gradelist[i].getGrade()>16)  && (i<number)) {
						list += gradelist[i].getGrade() + "\t" + gradelist[i].getNAluno() + "\n";
				}
				i++;
			}
		}
		if (list.equals("")) list = ("----------\n");	
		return list;
	}
}
