package test;
import prog.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.rmi.RemoteException;

import org.junit.jupiter.api.Test;

import jxl.read.biff.BiffException;

class PosizioneApplicazioneTest {

	@Test
	void test() throws BiffException, RemoteException, IOException {
		ApplicazioneMobile app=new ApplicazioneMobile();
		Posizione pos=app.getPosizione();
		app.setFissa(true);
		Posizione pos2=app.getPosizione();
		assertTrue(pos.equals(pos2), "posizioni devono essere uguali");
		
		
	}
	
	@Test
	void test2() throws BiffException, RemoteException, IOException {
		ApplicazioneMobile app=new ApplicazioneMobile();
		Posizione pos=app.getPosizione();
		app.setFissa(true);
		Posizione pos2=app.getPosizione();
		app.setFissa(false);
		Posizione pos3=app.getPosizione();
		assertFalse(pos.equals(pos3), "posizioni devono essere diverse");
		
		
	}

}
