package prog;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class GestoreDatabaseTest {
	private DatoGenerico aspettato;
	private DatoTraffico dato;
	
	@Before
	void creaDatoGenericoTest() {
		Posizione pos=new Posizione();
		dato=new DatoTraffico(pos,"S10 Coda",10);
		aspettato=new DatoGenerico(pos,"S10 Coda", dato.getData(),dato.getOra(), dato.getMinA(),dato.getOraA());
	}
	

	@Test
	void test() {
		Posizione pos=new Posizione();
		dato=new DatoTraffico(pos,"S10 Coda",10);
		aspettato=new DatoGenerico(pos,"S10 Coda", dato.getData(),dato.getOra(), dato.getMinA(),dato.getOraA());
		DatoGenerico nuovo=GestoreDatabase.getInstance().creaDatoGenerico(dato.getPosizione(), dato.getTipo(),dato.getData(),dato.getOra(),dato.getMinA(), dato.getOraA());
		assertEquals("I due dati devono essere uguali.",aspettato,nuovo);
	}
	
	@After
	void chiudiTest() {
		aspettato=null;
		dato=null;
		
	}
	

}
