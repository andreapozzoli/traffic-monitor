package test;
import prog.*;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SegnalaDatabaseSTest {
	private DatoTraffico dato;
	private DatoTraffico dato2;
	private GestoreCentraline gest;
	
	@BeforeEach
	void setup() throws RemoteException, NotBoundException {
		dato=new DatoTraffico(new Posizione("via dante", 1, 1), "S10 Coda", 10);
		gest=GestoreCentraline.getInstance();
		gest.segnalaDatabaseS(dato);
		dato2=new DatoTraffico(new Posizione("via milano", 2, 2), "S10 Coda", 10);
		gest.segnalaDatabaseS(dato2);
		
		
	}

	@Test
	void test() throws RemoteException, NotBoundException {
		assertEquals(GestoreDatabase.getInstance().getTabellaTraffico().get(1).getPosizione().getLatitudine(), dato2.getPosizione().getLatitudine(), "La latitudine deve essere uguale.");
		
	}
	
	@Test
	void test2() throws RemoteException, NotBoundException {
		DatoTraffico dato3=new DatoTraffico(new Posizione("via milano", 2, 2), "S15 Coda", 15);
		gest.segnalaDatabaseS(dato3);
		assertEquals(GestoreDatabase.getInstance().getTabellaTraffico().get(1).getTipo(), dato3.getTipo(), "Il tipo dei due dati deve essere uguale.");
		
	}

}
