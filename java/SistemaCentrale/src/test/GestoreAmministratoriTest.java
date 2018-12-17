package test;
import prog.*;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GestoreAmministratoriTest {
;
	private Amministratore admin = new Amministratore("admin", "password");
	private GestoreAmministratori gest;
	
	@BeforeEach
	void setup() {
		gest=GestoreAmministratori.getInstance();
		gest.aggiungiAmministratore(admin);
		
	}


	@Test
	public void testRiconoscimentoAmministratori() {	
		assertEquals("Amministratore riconosciuto", true, GestoreAmministratori.getInstance().riconosciAmministratore(admin.getNomeUtente(), admin.getPassword()));
	}
	

}
