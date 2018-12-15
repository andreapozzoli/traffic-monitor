package prog;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import org.junit.jupiter.api.Test;

class RiconoscimentoUtenteTest {
	private GestoreUtenti gest;

	@Before
	void provaInserimento() {
		gest=GestoreUtenti.getInstance();
		Utente utente=new Utente("prova nome","prova password");
		gest.aggiungiUtente(utente);

	}

	@Test
	void riconoscimentoUsernameTest() {
		gest=GestoreUtenti.getInstance();
		Utente utente=new Utente("prova nome","prova password");
		gest.aggiungiUtente(utente);
		assertTrue("L'utente deve essere riconosciuto.",gest.riconosciUtente("prova nome"));

	}
	
	@Test
	void riconoscimentoUtenteTest() {
		gest=GestoreUtenti.getInstance();
		Utente utente=new Utente("prova nome","prova password");
		gest.aggiungiUtente(utente);
		assertTrue("L'utente deve essere riconosciuto.",gest.riconosciUtente("prova nome","prova password"));

	}



}
