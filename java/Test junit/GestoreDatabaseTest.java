package prog;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GestoreDatabaseTest {
	private DatoGenerico aspettato;
	private DatoTraffico dato;
	
	@BeforeEach
	void creaDatoGenericoTest() {
		Posizione pos=new Posizione("via a",0.0,0.0);
		dato=new DatoTraffico(pos,"S10 Coda",10);
		aspettato=new DatoGenerico(pos,"S10 Coda", dato.getData(),dato.getOra(), dato.getMinA(),dato.getOraA());
	}
	

	@Test
	void test() {
		DatoGenerico nuovo=GestoreDatabase.getInstance().creaDatoGenerico(dato.getPosizione(), dato.getTipo(),dato.getData(),dato.getOra(),dato.getMinA(), dato.getOraA());
		assertEquals("I due dati devono essere uguali.",aspettato,nuovo);
	}
	
	@AfterEach
	void chiudiTest() {
		aspettato=null;
		dato=null;
		
	}
	

}
