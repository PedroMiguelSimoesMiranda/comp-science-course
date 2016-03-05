package lfm;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import lfm.rest.Event;
import lfm.rest.Lfm;
import fanzone.EventClass;
import fanzone.EventInfo;
import fanzone.ResourceInfo;



public class LastFMEvents {
	
	public static List<EventInfo> getLFMEvents(String query){
		List<EventInfo> theEvents = new ArrayList<EventInfo>();
		
		try {
			URI uri = new URI("http",
					"ws.audioscrobbler.com",
					"/2.0/",
					"method=artist.getevents&artist=" + query + "&api_key=b25b959554ed76058ac220b7b2e0a026",
					null);

			URL url = uri.toURL();
			InputStream is = url.openStream();

			JAXBContext jc = JAXBContext.newInstance( "lfm.rest" );
			Unmarshaller u = jc.createUnmarshaller();

			Lfm results = (Lfm)u.unmarshal( is );
	        
			
	        Iterator<Event> it = results.getEvents().getEvent().iterator();
	        while( it.hasNext()) {
	        	Event e = it.next();
	        	String description = "DATE: " + e.getStartDate() + "\n\n" +
	        	"TITLE: " + e.getTitle() + "\n\n" +
	        	"DESCRIPTION: " + e.getDescription() + "\n\n" + 
	        	"URL: " + e.getUrl() + "\n\n" +
	        	"WEBSITE: " + e.getWebsite();
	        	EventInfo event = new EventClass( e.getId().toString(), e.getTitle(), description, new ArrayList<ResourceInfo>());
	        	theEvents.add(event);
	        }
		} catch( Exception e) {
			e.printStackTrace();
		}
		return theEvents;
	}
	
}
