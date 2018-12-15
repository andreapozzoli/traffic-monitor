package prog;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class GestoreAmministratoriTest {
;
	private Amministratore admin = new Amministratore("admin", "password");


	@Test
	public void testRiconoscimentoAmministratori() {
		
		GestoreAmministratori.getInstance().aggiungiAmministratore(admin);
	
		assertEquals("Amministratore riconosciuto", true, GestoreAmministratori.getInstance().riconosciAmministratore(admin.getNomeUtente(), admin.getPassword()));
	}
	

}
