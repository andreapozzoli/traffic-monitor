package prog;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AggiuntaUtentiTest {
	private GestoreUtenti gest;

	@Test
	void test() {
		gest=GestoreUtenti.getInstance();
		Utente a=new Utente("1","1p");
		Utente b=new Utente("2","2p");
		Utente c=new Utente("3","3p");
		
		gest.aggiungiUtente(a);
		gest.aggiungiUtente(b);
		gest.aggiungiUtente(c);
		
		gest.rimuoviUtente("1");
		gest.rimuoviUtente("2");
		gest.rimuoviUtente("3");
		
		assertTrue("La lista deve essere vuota.",gest.getListaUtenti().isEmpty());




	}

}
