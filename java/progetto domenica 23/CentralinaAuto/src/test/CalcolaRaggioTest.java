package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jxl.read.biff.BiffException;
import prog.*;


class CalcolaRaggioTest {

	CentralinaAuto cent;
	CentralinaAuto cent2;
	CentralinaAuto cent3;
	
	
	@BeforeEach
	void setup() throws BiffException, IOException {
		FunzionamentoCentralinaA funz=new FunzionamentoCentralinaA();
		funz.main(null);
		cent=new CentralinaAuto(new Posizione("via Valleggio", 1, 1), 20);
		cent2=new CentralinaAuto(new Posizione("via Valleggio", 1, 1), 0);
		cent3=new CentralinaAuto(new Posizione("via Castelnuovo", 2, 2), 20);
		cent.setUltimaPosizione(new Posizione("via Castelnuovo", 2, 2));
		cent2.setUltimaPosizione(new Posizione("via Castelnuovo", 2, 2));
		cent3.setUltimaPosizione(new Posizione("via Castelnuovo", 2, 2));
		cent.calcolaRaggio();
		cent2.calcolaRaggio();
		cent3.calcolaRaggio();
	}
	
	@Test
	void test() {
		assertEquals(cent.getRaggio(), 0.5,"il raggio deve essere uguale al valore calcolato");
	}
	
	@Test
	void test2() {
		assertEquals(cent2.getRaggio(), 0,"il raggio deve essere zero");
	}
	
	@Test
	void test3() {
		assertEquals(cent3.getRaggio(), 3.0,"il raggio deve essere il doppio");
	}

}
