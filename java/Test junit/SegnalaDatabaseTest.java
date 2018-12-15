package prog;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.rmi.RemoteException;

import org.junit.jupiter.api.Test;

class SegnalaDatabaseTest {

	@Test
	void test() throws RemoteException {
		NotificaApplicazione notifica=new NotificaApplicazione("io", new Posizione("via dante", 1, 1), "M10 Coda");
		GestoreApplicazioni.getInstance().segnalaDatabase(notifica);
		NotificaApplicazione notifica2=new NotificaApplicazione("tu", new Posizione("via ciao", 2, 2), "M15 Coda");
		GestoreApplicazioni.getInstance().segnalaDatabase(notifica2);
		assertEquals(GestoreDatabase.getInstance().getTabellaTraffico().get(1).getPosizione().getLatitudine(), notifica2.getPosizione().getLatitudine(), "ok dddd");
		
		
	}

	@Test
	void test2() throws RemoteException {
		NotificaApplicazione notifica=new NotificaApplicazione("io", new Posizione("via dante", 1, 1), "M10 Coda");
		GestoreApplicazioni.getInstance().segnalaDatabase(notifica);
		NotificaApplicazione notifica2=new NotificaApplicazione("tu", new Posizione("via ciao", 2, 2), "M15 Coda");
		GestoreApplicazioni.getInstance().segnalaDatabase(notifica2);
		NotificaApplicazione notifica3=new NotificaApplicazione("egli", new Posizione("via ciao", 2, 2), "M8 Coda");
		GestoreApplicazioni.getInstance().segnalaDatabase(notifica3);
		assertEquals(GestoreDatabase.getInstance().getTabellaTraffico().get(1).getTipo(), notifica3.getTipo(), "ok dddd");
		
		
	}
	
}
