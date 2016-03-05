package exams;
import dataStructures.Dictionary;

public class QuestionManager {
	Dictionary<Key,Question>QuestionList;
	OrderedDictionary<String,OrderedDictionary<String,Question>topics1>topicsBy1; // (1)
	OrderedDictionary<Integer,OrderedDictionary<String,Question>topics2>topicsby3; // (2)
	OrderedDictionary<Integer,OrderedDictionary<String,String>topics3>orderedTopics; // (3)

	QuestionManager(){
		topicsBy1=new OrderedDictionary<String,OrderedDictionary<String,Question>topics1>();
		topicsby3=new OrderedDictionary<Integer,OrderedDictionary<String,Question>topics2>();
		orderedTopics=new OrderedDictionary<Integer,OrderedDictionary<String,String>topics3>();
		QuestionList=new Dictionary<Key,Question>();
	}




	Void  insertQuestion(int numPergunta, String epoca, int ano, String disciplina, String curso, String tp1, String tp2, String tp3, String enunciado){
		
	}
	
	Void  removeQuestion(int numPergunta, String epoca, int ano, String disciplina, String curso){
		
	}
	
	Question  getQuestion(int numPergunta, String epoca, int ano, String disciplina, String curso){}//returner os topicos e o enuciado
	
	// (1)
	Iterator<Key> getQuestByOne(String top1){
		
	} 
	
	//(2)
	Iterator<Key> getQuestByThree (String top1, String top2, String top3){
		
	}
	
	// (3)
	Iterator<String> topMaisFreq(){
		
	}  


}
