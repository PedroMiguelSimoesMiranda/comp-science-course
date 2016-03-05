package exams;
import dataStructures.Iterator;
import dataStructures.OrderedDictionary;

public class Question implements IQuestion{
	
	
	private String enunciado;
	private OrderedDictionary<String,String>topics;
	
	Question(Key key, String tp1, String tp2, String tp3, String enunciado){
		topics=new private OrderedDictionary<String,String>();
	}






	public void insertInTopcs(String topico){}
	public void removeFromTopcs(String topico){}
	public String getEnunciaddo(){}
	public Iterator<String>getTopics(){}


}
