package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jxl.read.biff.BiffException;
import prog.*;

class CreaStatoVeicoloTest {

	CentralinaAuto cent;
	CentralinaAuto cent2;
	CentralinaAuto cent3;
	
	
	@BeforeEach
	void setup() throws BiffException, IOException {
		cent=new CentralinaAuto(new Posizione("via dante", 1,1), 28);
		cent2=new CentralinaAuto(new Posizione("via milano", 2,2), 15);
		cent3=new CentralinaAuto(new Posizione("via valleggio", 2,2), 35);
		cent.creaStatoVeicolo(cent.getVelocita(), cent.getPosizione());
		cent2.creaStatoVeicolo(cent2.getVelocita(), cent2.getPosizione());
		cent3.creaStatoVeicolo(cent3.getVelocita(), cent3.getPosizione());
	}

	@Test
	void test() {
		assertEquals(cent.getStatoVeicolo().getTipo(), "V28 Traffico elevato", "il tipo deve essere uguale.");
	}
	@Test
	void test2() {
		assertEquals(cent2.getStatoVeicolo().getTipo(), "V15 Coda", "il tipo deve essere uguale.");
	}
	@Test
	void test3() {
		assertEquals(cent3.getStatoVeicolo().getTipo(), "V35 Velocita lenta", "il tipo deve essere uguale.");
	}


}
