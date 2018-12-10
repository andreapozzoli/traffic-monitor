package ProjCentralina;

import java.util.*;

import jxl.*;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class FunzionamentoCentralinaS {

	public static void main(String[] args) {
		IGestoreCentraline iGestoreCentraline = (IGestoreCentraline)Naming.lookup("GestoreCentraline");

		System.setSecurityManager(new RMISecurityManager());
		try {

			System.out.println("Security Manager loaded");
			String url="rmi://localhost/GestoreCentraline";
			IGestoreCentraline iGestoreCentraline = (IGestoreCentraline)Naming.lookup(url);
			System.out.println("Got remote object");





			Workbook wb;
			wb = Workbook.getWorkbook(new File("C://Users//Matteo//Desktop//prova finale//traffic-monitor//java//samuele//gestione traffico 2//vie3.xls"));
			Sheet sheet = wb.getSheet(0);
			Cell cella;
			int i=0;
			boolean trovato=false;
			String comando="";
			Scanner sc;
			while(!trovato) {
				System.out.println("Inserire via :");
				sc= new Scanner(System.in);
				comando=sc.nextLine();
				i=0;
				for(i=0;i<543;i++) {
					cella=sheet.getCell(0,i);
					if(cella.getContents().toLowerCase().equals(comando)) {
						trovato=true;
						break;
					}
				}
				if(!trovato) {
					System.out.println("La via inserita non esiste a Como");
				}
			}
			Posizione posizione=new Posizione(comando,Double.valueOf(sheet.getCell(1,i).getContents()),Double.valueOf(sheet.getCell(2,i).getContents()));
			String tipoStrada;
			while(true) {
				System.out.println("Inserire tipo strada :");
				sc= new Scanner(System.in);
				tipoStrada=sc.nextLine();
				if (tipoStrada.equals("urbana")||tipoStrada.equals("autostrada")||tipoStrada.equals("extraurbana")||tipoStrada.equals("superstrada")) {
					break;
				}
				else {
					System.out.println("Tipo strada non valido");
				}
			}
			int intervallo=0;
			while(true) {
				System.out.println("Inserire intervallo di tempo di partenza :");
				sc= new Scanner(System.in);
				intervallo=Integer.valueOf(sc.nextLine());
				if(intervallo>10) {
					break;
				}
				else {
					System.out.println("L'intervallo deve essere positivo e maggiore di 10 secondi");
				}
			}

			CentralinaStradale centralina=new CentralinaStradale(intervallo,posizione,tipoStrada);
			//thread rilevatore veicoli
			Thread t1=new Thread(centralina.getRilevatoreVeicoli());
			t1.start();

			//deve solo aspettare che scada l'intervallo di tempo corrente
			//scaduto l'intervallo calcola immediatamente quello successivo e crea il dato di traffico
			Thread t2=new Thread(centralina);
			t2.run();

		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (RemoteException exc) {
			System.out.println("Error in lookup: "+exc.toString());
		}
		catch (java.net.MalformedURLException exc) {
			System.out.println("malformed URL: "+exc.toString());
		}
		catch (NotBoundException exc) {
			System.out.println("NotBound: "+exc.toString());
		}

	}

}
