package prog;

import junit.framework.TestCase;

public class FunzionamentoSistemaCentraleTest extends TestCase {

	public void testVisualizzazioneMappaBase() {
		
		// Vedere se la mappa è effettivamente visibile
		assertEquals("La mappa deve essere visibile se la funzione visualizzazioneMappaBase viene chiamata", true, FunzionamentoSistemaCentrale.visualizzazioneMappaBase().isVisible());
	}

}
