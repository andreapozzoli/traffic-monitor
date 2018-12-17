package test;
import prog.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.rmi.RemoteException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jxl.read.biff.BiffException;

public class SegnalaApplicazioneTest {
	private ApplicazioneMobile app;
	private Posizione pos;

	@BeforeEach
	void setup() throws BiffException, RemoteException, IOException {
		app=new ApplicazioneMobile();
		pos=app.getPosizione();
		app.aggiungiNotificaInCoda(app.creaNotificaApplicazione(pos, "M10 Coda"));
		pos=app.getPosizione();
		app.aggiungiNotificaInCoda(app.creaNotificaApplicazione(pos, "M25 Traffico Elevato"));
		pos=app.getPosizione();
		app.aggiungiNotificaInCoda(app.creaNotificaApplicazione(pos, "M8 Coda"));

	}

	@Test
	void test() throws BiffException, RemoteException, IOException {
		assertEquals(app.getListaNotifiche().size(), 3,"devono esserci tre notifiche");

	}

	@Test
	void test2() throws BiffException, RemoteException, IOException {
		app.svuotaLista();
		assertEquals(app.getListaNotifiche().size(), 0,"devono esserci zero notifiche");

	}

}
