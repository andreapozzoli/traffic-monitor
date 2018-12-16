package test;
import prog.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.rmi.RemoteException;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistrazioneUtenteTest {
	private GestoreApplicazioni gest;
	private Utente utente;
	
	@BeforeEach
	void precondizioni() {
		try {
			gest=GestoreApplicazioni.getInstance();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		utente=new Utente("prova","prova password");
		gest.registraUtente(utente);
		
	}
	
	

	@Test
	void test1() {		
		assertTrue("L'utente deve essere riconosciuto.",gest.verificaAccesso("prova"));
		
	}
	
	@Test
	void test2() {		
		assertTrue("L'utente deve essere riconosciuto.",gest.verificaAccesso("prova","prova password"));
		
	}

}
