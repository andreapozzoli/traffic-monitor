package test;
import prog.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreaDatoTrafficoTest {
	CentralinaStradale cent;
	CentralinaStradale cent2;
	CentralinaStradale cent3;
	
	
	@BeforeEach
	void setup() {
		cent=new CentralinaStradale(10, new Posizione("via dante", 1,1), "urbana");
		cent2=new CentralinaStradale(15, new Posizione("via milano", 2,2), "extraurbana");
		cent3=new CentralinaStradale(18, new Posizione("via valleggio", 2,2), "superstrada");
		cent.setVelocita(45);
		cent.creaDatoTraffico();
		cent2.setVelocita(45);
		cent2.creaDatoTraffico();
		cent3.setVelocita(45);
		cent3.creaDatoTraffico();
	}

	@Test
	void test() {
		assertEquals(cent.getDatoTraffico().getTipo(), "S45 Traffico nella norma", "il tipo deve essere uguale.");
	}
	@Test
	void test2() {
		assertEquals(cent2.getDatoTraffico().getTipo(), "S45 Velocita lenta", "il tipo deve essere uguale.");
	}
	@Test
	void test3() {
		assertEquals(cent3.getDatoTraffico().getTipo(), "S45 Traffico elevato", "il tipo deve essere uguale.");
	}

}
