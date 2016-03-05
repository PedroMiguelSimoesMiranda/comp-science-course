package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

import poo.Music;
import poo.PlayList;

		

	public class PlayListTest {
		
		PlayList p1 ;
		PlayList pl1;
		PlayList p2 ;
		Music m1 ;
		Music m2 ;
		Music m3 ;
		Music m4 ;
		Music mu1 ;
		Music mu2 ;
		Music mu3 ;
		Music mu4 ;
		Music m5 ;
		Music m6 ;
		Music m7 ;
		Music m8 ;
		Music m9 ;
		
		
		@Before
		public void setup() {
			pl1 = new PlayList("Rock",5);
			m1 = new Music("TNT", "ACDC", 214, Music.FORMAT_WAV, 3) ;
			m2 = new Music("This Love", "Maroon 5", 214, Music.FORMAT_AIFF, 2) ;
			m3 = new Music("Highway to Hell", "ACDC", 214, Music.FORMAT_MP3, 5) ;
			m4 = new Music("A Tua Imagem", "Fonzie", 312, Music.FORMAT_MP3, 5) ;
			m5 = new Music("Thunderstruck", "ACDC", 214, Music.FORMAT_MP3, 4) ;
			m6 = new Music("Bat Country", "Avenged Sevenfold", 345, Music.FORMAT_MP3, 2) ;
			m7 = new Music("She Will Be Loved", "Maroon 5", 666, Music.FORMAT_WAV, 4) ;
			m8 = new Music("One", "Metallica", 555, Music.FORMAT_MP3, 5) ;
			m9 = new Music("Can't Stop", "Red Hot Chilli Peppers", 666, Music.FORMAT_WAV, 4) ;
			p1 = new PlayList("Cool stuff", 1);
			p2 = new PlayList("LOL") ;
			mu1 = new Music("This Love", "Maroon 5", 240, Music.FORMAT_AIFF, 2);
			mu2 = new Music("Morfina", "Ma", 214, Music.FORMAT_AIFF, 2);
			mu3 = new Music("Fix You", "d", 220, Music.FORMAT_AIFF, 2);
			mu4 = new Music("Scream", "f", 214, Music.FORMAT_AIFF, 2);
		}
		
		@Test
		public void testFormatConstants() {
			assertFalse(PlayList.INITIAL_CAPACITY <= 0);
			assertFalse(PlayList.NOT_FOUND != -1);
		}

		@Test
		public void testSimpleConstructor() {
			assertEquals("p1", new Music("p1").getName());
			assertEquals("p2", new Music("p2").getName());
		}
		
		@Test
		public void testCapacity() {
			PlayList p1 = new PlayList("p1",3);
			assertTrue(p1.capacity() == 3);
			PlayList p3 = new PlayList("p2",-3);
			assertTrue(p3.capacity() == 256);
		}
		
		@Test
		public void testDelete() {
			p1.addMusic(m2) ;
			p1.addMusic(m3) ;
			assertTrue(p1.contains(m3)) ;
			p1.deleteMusic(m3) ;
			assertFalse(p1.contains(m3)) ;
			assertFalse(p1.deleteMusic(m1)) ;
		}
		
		@Test
		public void testAdd(){
			assertTrue(p1.indexOf(m1)==-1);
			p1.addMusic(m1);
			assertTrue(p1.indexOf(m1)==0);
			p1.addMusic(m1);
			assertFalse(p1.indexOf(m1)==1);
			assertTrue(p1.contains(m1));
			assertFalse(p1.contains(null));
		}
		
		@Test
		public void testContains() {
			p1.addMusic(m2) ;
			p1.addMusic(m3) ;
			assertFalse(p1.contains(m1)) ;
			assertTrue(p1.contains(m2)) ;
			assertTrue(p1.capacity()==2) ;
			p1.addMusic(m4) ;
			assertTrue(p1.contains(m4)) ;
			assertTrue(p1.capacity()==4) ;
			assertTrue(p1.getSize()==3) ;
			p1.deleteMusic(m4) ;
			assertFalse(p1.contains(m4)) ;
			assertTrue(p1.capacity()==4) ;
			assertTrue(p1.getSize()==2) ;
			m9 = null ;
			assertFalse(p1.contains(m9)) ;
		}
		
		@Test
		public void testIndexOf() {
			p1.addMusic(m1) ;
			p1.addMusic(m2) ;
			p1.addMusic(m3) ;
			p1.addMusic(m4) ;
			p1.addMusic(m5) ;
			p1.addMusic(m6) ;
			p1.addMusic(m7) ;
			p1.addMusic(m8) ;
			p1.addMusic(m9) ;
			assertTrue(p1.indexOf(m2)==1) ;
			assertTrue(p1.indexOf(m8)==7) ;
			p1.deleteMusic(m3) ;
			assertTrue(p1.indexOf(m8)==6) ;
			assertTrue(p1.indexOf(m3)==PlayList.NOT_FOUND) ;
		}
		
		@Test
		public void testNumMusics() {
			p1.addMusic(m1) ;
			p1.addMusic(m2) ;
			p1.addMusic(m3) ;
			p1.addMusic(m4) ;
			p1.addMusic(m5) ;
			p1.addMusic(m6) ;
			p1.addMusic(m7) ;
			p1.addMusic(m8) ;
			p1.addMusic(m9) ;
			assertTrue(p1.numMusics("ACDC")==3) ;
			assertTrue(p1.numMusics("Maroon 5")==2) ;
			assertTrue(p1.numMusics("")==0) ;
			assertTrue(p1.numMusics("Red")==0) ;
			assertTrue(p1.numMusics("Red Hot Chilli Peppers")==1) ;
		}
		
		
		@Test
		public void testNumArtists() {
			assertTrue(p1.numArtists()==0) ;
			p1.addMusic(m1) ;
			assertTrue(p1.numArtists()==1) ;
			p1.addMusic(m2) ;
			p1.addMusic(m3) ;
			assertTrue(p1.numArtists()==2) ;
			p1.addMusic(m4) ;
			p1.addMusic(m5) ;
			assertTrue(p1.numArtists()==3) ;
			p1.addMusic(m6) ;
			p1.addMusic(m7) ;
			p1.addMusic(m8) ;
			p1.addMusic(m9) ;
			assertTrue(p1.numArtists()== 6) ;
		}
		
		@Test
		public void testMostProductiveArtist1() {
			p1.addMusic(m1) ;
			p1.addMusic(m2) ;
			p1.addMusic(m3) ;
			p1.addMusic(m4) ;
			p1.addMusic(m5) ;
			p1.addMusic(m6) ;
			p1.addMusic(m7) ;
			p1.addMusic(m8) ;
			p1.addMusic(m9) ;
			assertTrue(p1.mostProductiveArtist().equals("ACDC")) ;
			p1.deleteMusic(m5) ;
			p1.deleteMusic(m3) ;
			assertFalse(p1.mostProductiveArtist().equals("ACDC")) ;
			assertTrue(p1.mostProductiveArtist().equals("Maroon 5")) ;
		}
		
		@Test
		public void testMostProductiveArtist2() {
			p1.addMusic(m2) ;
			p1.addMusic(m3) ;
			p1.addMusic(m1) ;
			p1.addMusic(m4) ;
			p1.addMusic(m6) ;
			p1.addMusic(m7) ;
			assertTrue(p1.mostProductiveArtist().equals("Maroon 5")) ;
		}
		
		@Test
		public void testMostProductiveArtist3() {
			p1.addMusic(m2) ;
			p1.addMusic(m3) ;
			p1.addMusic(m1) ;
			p1.addMusic(m4) ;
			p1.addMusic(m6) ;
			p1.addMusic(m7) ;
			m1 = new Music("TNT", "ACDC", 666, Music.FORMAT_WAV, 3) ;
			assertTrue(p1.mostProductiveArtist().equals("ACDC") || p1.mostProductiveArtist().equals("Maroon 5")) ;
		}
		
		@Test
		public void testMostProductiveArtist4() {
			p1.addMusic(m1) ;
			p1.addMusic(m2) ;
			p1.addMusic(m3) ;
			p1.addMusic(m5) ;
			p1.addMusic(m7) ;
			p1.addMusic(mu1) ;
			assertTrue(p1.mostProductiveArtist().equals("Maroon 5")) ;
		}
		
		@Test
		public void testMostProductiveArtist5() {
			p1.addMusic(m4) ;
			p1.addMusic(m5) ;
			assertTrue(p1.mostProductiveArtist().equals("Fonzie")) ;
		}
		
		@Test
		public void testMostProductiveArtist6() {
			p1.addMusic(m8) ;
			p1.addMusic(m9) ;
			assertTrue(p1.mostProductiveArtist().equals("Red Hot Chilli Peppers")) ;
		}
		
		@Test
		public void testAtCapacity() {
			p1 = new PlayList("Cool stuff", 9);
			p1.addMusic(m1) ;
			p1.addMusic(m2) ;
			p1.addMusic(m3) ;
			p1.addMusic(m4) ;
			p1.addMusic(m5) ;
			p1.addMusic(m6) ;
			p1.addMusic(m7) ;
			p1.addMusic(m8) ;
			p1.addMusic(m9) ;
			assertTrue(p1.atCapacity()) ;
			p1 = new PlayList("Empty", 0);
			assertFalse(p1.atCapacity()) ;
		}
		
		@Test
		public void testGetDuration() {
			p1.addMusic(m1) ;
			p1.addMusic(m2) ;
			p1.addMusic(m3) ;
			p1.addMusic(m4) ;
			p1.addMusic(m5) ;
			p1.addMusic(m6) ;
			p1.addMusic(m7) ;
			p1.addMusic(m8) ;
			p1.addMusic(m9) ;
			assertTrue(p1.getDuration()==3400) ;
		}
		
		@Test
		public void testElementAt() {
			p1.addMusic(m2) ;
			p1.addMusic(m3) ;
			assertTrue(p1.contains(m2)) ;
			assertTrue(p1.elementAt(0).equals(m2)) ;
			assertTrue(p1.elementAt(1).equals(m3)) ;
			p1.deleteMusic(m2) ;
			assertTrue(p1.elementAt(0).equals(m3)) ;
			assertTrue(p1.elementAt(8)== null) ;
			assertTrue(p1.elementAt(0)!=null) ;
		}
		
		@Test
		public void testElementAndIndexOf() {
			p1.addMusic(m1) ;
			p1.addMusic(m2) ;
			p1.addMusic(m3) ;
			p1.addMusic(m4) ;
			p1.addMusic(m5) ;
			p1.addMusic(m6) ;
			p1.addMusic(m7) ;
			p1.addMusic(m8) ;
			p1.addMusic(m9) ;
			assertTrue(p1.elementAt(p1.indexOf(m7))==m7) ;		
		}
		
		@Test
		public void testMooshak() {
			p1.addMusic(m2) ;
			p1.addMusic(m3) ;
			assertTrue(p1.getSize() <= p1.capacity());
		}
		
		@Test
		public void test() {
			p1.addMusic(m2) ;
			p1.addMusic(m3) ;
			assertTrue("D".equalsIgnoreCase("d")) ;
		}
				
			@Test
		public void testFullConstructor3(){
			pl1.addMusic(mu1);
			pl1.addMusic(mu2);
			pl1.addMusic(mu3);
			pl1.addMusic(mu4);
			assertEquals("Rock", pl1.getName());
			assertEquals(5, pl1.capacity());
			assertEquals(4, pl1.getSize());
			assertFalse(pl1.atCapacity());
			assertTrue(pl1.contains(mu2));
			pl1.deleteMusic(mu3);
			assertFalse(pl1.contains(mu3));
			assertEquals(pl1.indexOf(mu1), 0);
			assertEquals(pl1.indexOf(mu2), 1);
			assertEquals(pl1.indexOf(mu3), -1);
			assertEquals(pl1.numArtists(),3);
			assertEquals(pl1.mostProductiveArtist(),"Maroon 5");
		}

}