package lfm;
import fanzone.RemoteResourceInfo;
import fanzone.ResourceInfo;
import images.Image;
import images.Lfm;
import images.Size;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.*;


public class LastFMImages {

	public static List<ResourceInfo> getLFMImages(String query){
		List<ResourceInfo> theImages = new ArrayList<ResourceInfo>();
		try {
			URI uri = new URI("http",
					"ws.audioscrobbler.com",
					"/2.0/",
					"method=artist.getimages&artist=" + query + "&api_key=b25b959554ed76058ac220b7b2e0a026",
					null);

			URL url = uri.toURL();
			InputStream is = url.openStream();

			JAXBContext jc = JAXBContext.newInstance( "images" );
			Unmarshaller u = jc.createUnmarshaller();

			Lfm results = (Lfm)u.unmarshal( is );

			// Iterator<Artist> it = results.getResults().getArtistmatches().getArtist().iterator();
			Iterator<Image> it =results.getImages().getImage().iterator();

			while( it.hasNext()) {
				Image img= it.next();
				Iterator<Size> itt=img.getSizes().getSize().iterator();
				while(itt.hasNext()){
					Size s= itt.next();
					URL url1 = new URL( s.getValue());		
					System.out.println(url1.getPath());
					
				ResourceInfo ri=new RemoteResourceInfo(url1,getName(url1.getPath()),"",query);
				theImages.add(ri);
				}
			}
		} catch( Exception e) {
			e.printStackTrace();
		}
		return theImages;
	}


	public static InputStream readURL( URL u) throws IOException {
		return u.openStream();
	}

	public static String getName(String s){
		String[]newS=s.split("/");
		return newS[newS.length-1];
	}

}
