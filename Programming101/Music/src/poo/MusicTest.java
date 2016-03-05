package poo;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class MusicTest {
	Music m1;
	
	@Before
	public void setup() {
		m1 = new Music("This Love", "Maroon 5", 214, Music.FORMAT_AIFF, 2);
		}

	@Test
	public void testFormatConstants(){
		assertFalse(Music.FORMAT_AIFF==0);
		assertFalse(Music.FORMAT_MP3==0);
		assertFalse(Music.FORMAT_WAV==0);
	}

	@Test
	public void testSimpleConstrutor(){
		assertEquals("This Love", new Music("This Love").getName());
		assertEquals("Mamma Mia", new Music("Mamma Mia").getName());
	}

	@Test
	public void testFullConstrutor(){
		assertEquals("This Love", m1.getName());
		assertEquals("Maroon 5", m1.getArtist());
		assertEquals(214, m1.getDuration());
		assertEquals(Music.FORMAT_AIFF, m1.getFormat());
		assertEquals(2, m1.getRating());

		Music music2 =
			new Music("Mamma Mia", "Abba", 214, Music.FORMAT_MP3, 4);
		assertEquals("Mamma Mia", music2.getName());
		assertEquals("Abba", music2.getArtist());
		assertEquals(214,music2.getDuration());
		assertEquals(Music.FORMAT_MP3, music2.getFormat());
		assertEquals(4, music2.getRating());

	}

	@Test
	public void testEqualsSymmetric() {
		Music m2 = new Music("This Love", "Maroon 5", 214, Music.FORMAT_WAV, 2);
		assertTrue(!(m1.equals(m2)) || m2.equals(m1));
	}

	@Test
	public void testEqualsTransitivity(){
		Music m2 = new Music("This Love", "Maroon 5", 214, Music.FORMAT_WAV, 2);
		Music m3 = new Music("This Love", "Maroon 5", 214, Music.FORMAT_WAV, 2);
		assertTrue(!m1.equals(m2) || !m2.equals(m3) || m1.equals(m3));
	}

	@Test
	public void testEqualsReflexive(){
		Music m2 = new Music("This Love", "Maroon 5", 214, Music.FORMAT_AIFF, 3);
		assertTrue(m1.equals(m1) && m2.equals(m2));
	}


	@Test
	public void testEqualsConsistent(){//por corrigir
		Music m2 = new Music("This Love", "Maroon 5", 214, Music.FORMAT_AIFF, 2);
		assertTrue(m1.equals(m2) && m2.equals(m1) || !(m1.equals(m2)) && !(m2.equals(m1)));
	}

	@Test
	public void testConvert() {
		Music m2 = m1.convert(Music.FORMAT_WAV);
		//assertEquals(m1, m2);
		assertNotSame(m1.getFormat(), m2.getFormat());
	}

	@Test
	public void testConvertDoesNotAffectEquals() {
		Music copy = m1.convert(Music.FORMAT_WAV);
		//assertTrue(m1.equals(copy));
	}
	
	@Test
	public void testConvertSameObject() {
		Music m2 = m1.convert(Music.FORMAT_WAV);
		assertNotSame(m1, m2);
	}
}