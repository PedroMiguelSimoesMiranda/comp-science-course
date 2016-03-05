import java.io.*;
import java.util.Scanner;

import dataStructures.*;

public class Main {
	
	static File fileName = new File("uni.txt");
	static Universidade uni = new Universidade();
	
	public static void Uni(Universidade uni){
		Scanner in = new Scanner(System.in);
		
		//Universidade uni = new Universidade();

		while(in.hasNext()){

			try{

				String command = in.next();

				if(command.equals("CC")){//Criação de um curso
					Disciplina[] disciplinasObrigatorias;
					Disciplina[] disciplinasOpcionais;
					int creditos = in.nextInt();
					String nomeCurso = in.next();
					int nDisciplinasObrigatorias = in.nextInt();
					disciplinasObrigatorias = new Disciplina[nDisciplinasObrigatorias];
					for(int i=0; i<nDisciplinasObrigatorias;i++)
						disciplinasObrigatorias[i]= new Disciplina(in.nextFloat(),in.next());
					int nDisciplinasOpcionais = in.nextInt();
					disciplinasOpcionais = new Disciplina[nDisciplinasOpcionais];
					for(int i=0; i<nDisciplinasOpcionais;i++)
						disciplinasOpcionais[i]= new Disciplina(in.nextFloat(),in.next());
					uni.CC(creditos, nomeCurso, nDisciplinasObrigatorias, disciplinasObrigatorias, nDisciplinasOpcionais, disciplinasOpcionais);
					System.out.println("Criacao do curso com sucesso.");
					save(uni, fileName);
				}
				else if(command.equals("IA")){//Inscrever aluno
					uni.IA(in.next(), in.next());
					System.out.println("Inscricao do aluno com sucesso.");
					save(uni, fileName);
				}
				else if(command.equals("RA")){//Remover aluno
					uni.RA(in.nextLine().replace("\n", "").substring(1));
					System.out.println("Remocao do aluno com sucesso.");
					save(uni, fileName);
				}
				else if(command.equals("LA")){//Lançar aprovação
					uni.LA(in.nextLine().replace("\n", "").substring(1), in.nextInt(), in.next());
					System.out.println("Lancamento da aprovacao com sucesso.");
					save(uni, fileName);
				}
				else if(command.equals("LM")){//Lançar melhoria
					uni.LM(in.nextLine().replace("\n", "").substring(1), in.nextInt(), in.next());
					System.out.println("Lancamento da melhoria com sucesso.");
					save(uni, fileName);
				}
				else if(command.equals("CA")){//Curriculum de um aluno
					CurriculumAluno saida = uni.CA(in.next());
					String aux = saida.next();
					System.out.println(aux);
					aux = saida.next();
					System.out.println(aux);
					aux = saida.next();
					while(aux!=null){
						System.out.println(aux);
						aux = saida.next();
					}
				}
				else if(command.equals("PD")){//Diplomados de um curso
					Iterator<Entry<KeyDip, Aluno>> x = uni.PD(in.next());
					Aluno aux;
					while(x.hasNext()){
						aux = x.next().getValue();
						System.out.println(aux.mediaAluno()+" "+aux.getNome());
					}
				}

			}catch (Exception e){
				System.out.println(e.getMessage());
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args) {
		//Universidade uni = new Universidade();
		uni = load(uni, fileName);
		Uni(uni);
		//save(uni, fileName);
	}
	
	private static Universidade load(Universidade uni, File fileName){
		try
		{
			ObjectInputStream file = new ObjectInputStream( new FileInputStream(fileName) );
			uni = (Universidade) file.readObject();
			file.close();
			return uni;
			//System.out.println("loadED");
		}
		catch ( IOException e )
		{/*System.out.println("IOError load");*/}
		catch ( ClassNotFoundException e )
		{/*System.out.println("class not found");*/}
		return uni;
	}
	
	private static void save(Universidade uni, File fileName){
		try
		{
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(fileName) );
			file.writeObject(uni);
			file.flush();
			file.close();
			//System.out.println("saveED");
		}
		catch ( IOException e ){/*System.out.println("IoError save");*/}
	}

}