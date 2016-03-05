package poo;

public class DrawGrid {

	private int s;
	private int t;
	private int n;

	public static final String PONTO = ".";
	public static final String ASTERISCO = "*";

	private int width;

	// S = is the size of smallest squares ---> .
	// T = is the thickness of drawing line ---> *
	// N = nember of squares

public DrawGrid(int si,int ti, int ni){
	s=si;
	t=ti;	
	n=ni;
	width = (n * s) + (n +1) * t;
}
	
	
	
	public String drawLines() {

		String answer = "";

		for (int i = 0; i < width; i++) {
			answer += ASTERISCO;
		}
		return answer;
	}

	public String drawSquares(){
		String draw="";

		for(int i=0;i<n;i++){

			for(int j=0;j<t;j++){
				draw+=ASTERISCO;
			}
			for(int k=0;k<s;k++){
				draw+=PONTO;
			}
		}
		for(int j=0;j<t;j++){
			draw+=ASTERISCO;
		}
		return draw;
	}


	public String draw(){
		String draw="";

		for(int i=0;i<n;i++){
			for(int j=0;j<t;j++){
				draw+=drawLines()+"\n";
			}
			for(int h=0;h<s;h++){
				draw+=drawSquares()+"\n";
			}
		}
		for(int j=0;j<t;j++){
			draw+=drawLines()+"\n";
		}

		return draw;
	}

	
}
