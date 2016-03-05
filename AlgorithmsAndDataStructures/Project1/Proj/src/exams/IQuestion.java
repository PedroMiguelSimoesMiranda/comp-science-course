package exams;

import dataStructures.Iterator;

public interface IQuestion {
	
	public void insertInTopcs(String topico);
	
	public void removeFromTopcs(String topico);
	
	public String getEnunciaddo();
	
	public Iterator<String>getTopics();
}
