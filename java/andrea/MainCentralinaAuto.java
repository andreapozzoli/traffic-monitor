import java.util.*;

public class MainCentralinaAuto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int idVeicolo=0;
		System.out.println("Scegliere operazione da eseguire: aggiungiCentralina, rimuoviCentralina");
		Scanner input=new Scanner(System.in);
		String risposta=input.nextLine();
		if (risposta.equals("aggiungiCentralina")) {
			System.out.println("Scegliere il periodo di invio dati del veicolo");
			int periodo=input.nextInt();
			new CentralinaAutomobilistica(periodo, idVeicolo);
		}
		else if (risposta.equals("rimuoviCentralina")) {
			if (GestoreCentraline.getInstance().getListaCentralineAuto().isEmpty()) {
				System.out.println("errore, la lista è vuota"); //fare con exception
			}
		}
		else {
			System.out.println("hai sbagliato a scrivere"); //fare con exception
		}
		for (CentralinaAutomobilistica auto: GestoreCentraline.getInstance().getListaCentralineAuto()) {
			System.out.println(auto.getIdVeicolo());
		}
		
	}

}
