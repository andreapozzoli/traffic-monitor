package prog;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalcoloIntervalloTest {
	private CentralinaStradale centralina;

	@Test
	void test() {
		centralina=new CentralinaStradale(10,new Posizione("via a",0.0,0.0),"urbana");
		centralina.calcolaIntervallo(0);
		assertTrue("L'intervallo dovrebbe essere il doppio del precedente.",centralina.getIntervallo()==20);
	}

}
