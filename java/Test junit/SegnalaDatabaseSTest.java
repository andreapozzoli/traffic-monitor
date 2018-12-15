package prog;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.jupiter.api.Test;

class SegnalaDatabaseSTest {

	@Test
	void test() throws RemoteException, NotBoundException {
		DatoTraffico dato=new DatoTraffico(new Posizione("via dante", 1, 1), "S10 Coda", 10);
		GestoreCentraline.getInstance().segnalaDatabaseS(dato);
		DatoTraffico dato2=new DatoTraffico(new Posizione("via ciao", 2, 2), "S10 Coda", 10);
		GestoreCentraline.getInstance().segnalaDatabaseS(dato2);
		assertEquals(GestoreDatabase.getInstance().getTabellaTraffico().get(1).getPosizione().getLatitudine(), dato2.getPosizione().getLatitudine(), "ok dddd");
		
	}
	
	@Test
	void test2() throws RemoteException, NotBoundException {
		DatoTraffico dato=new DatoTraffico(new Posizione("via dante", 1, 1), "S10 Coda", 10);
		GestoreCentraline.getInstance().segnalaDatabaseS(dato);
		DatoTraffico dato2=new DatoTraffico(new Posizione("via ciao", 2, 2), "S10 Coda", 10);
		GestoreCentraline.getInstance().segnalaDatabaseS(dato2);
		DatoTraffico dato3=new DatoTraffico(new Posizione("via ciao", 2, 2), "S15 Coda", 15);
		GestoreCentraline.getInstance().segnalaDatabaseS(dato3);
		assertEquals(GestoreDatabase.getInstance().getTabellaTraffico().get(1).getTipo(), dato3.getTipo(), "ok dddd");
		
	}

}
