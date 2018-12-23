package test;
import prog.*;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.rmi.RemoteException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SegnalaDatabaseTest {
	private GestoreApplicazioni gest;
	private NotificaApplicazione notifica;
	private NotificaApplicazione notifica2;

	@BeforeEach
	void setup() throws RemoteException {
		notifica=new NotificaApplicazione("andrea", new Posizione("via dante", 1, 1), "M10 Coda");
		gest=GestoreApplicazioni.getInstance();
		gest.segnalaDatabase(notifica);
		notifica2=new NotificaApplicazione("samuele", new Posizione("via milano", 2, 2), "M15 Coda");
		gest.segnalaDatabase(notifica2);

	}

	@Test
	void test() throws RemoteException {
		assertEquals(GestoreDatabase.getInstance().getTabellaTraffico().get(1).getPosizione().getLatitudine(), notifica2.getPosizione().getLatitudine(), "ok dddd");


	}

	@Test
	void test2() throws RemoteException {
		NotificaApplicazione notifica3=new NotificaApplicazione("nicolo", new Posizione("via milano", 2, 2), "M8 Coda");
		gest.segnalaDatabase(notifica3);
		assertEquals(GestoreDatabase.getInstance().getTabellaTraffico().get(1).getTipo(), notifica3.getTipo(), "ok dddd");


	}

}
