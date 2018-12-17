package test;
import prog.*;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class FunzionamentoSistemaCentraleTest {

	@Test
	void testVisualizzazioneMappaBase() {
		
		// Vedere se la mappa e' effettivamente visibile
		assertEquals("La mappa deve essere visibile se la funzione visualizzazioneMappaBase viene chiamata", true, FunzionamentoSistemaCentrale.visualizzazioneMappaBase().isVisible());
	}

}
