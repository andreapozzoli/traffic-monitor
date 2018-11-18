
public class GestoreCentraline {
	private CentralinaAutomobilistica[] listaCentralineAuto;
	private CentralinaStradale[] listaCentralineStradali;
	private static GestoreCentraline istance=null;
    private GestoreCentraline() {
    	this.listaCentralineAuto=new CentralinaAutomobilistica[8000];
    	this.listaCentralineStradali=new CentralinaStradale[100];
    }
    public static GestoreCentraline getIstance() {
            if(istance==null)
                    istance = new GestoreCentraline();
            return istance;
    }
    public void aggiungiCentralinaAuto(CentralinaAutomobilistica centralina) {
    	for (int i=0; i<8000; i++) {
    		if (this.listaCentralineAuto[i]==null) {
    			this.listaCentralineAuto[i]=centralina;
    			break;
    		}
    	}
    	if (i==8000) {
    		System.out.println("impossibile aggiungere una nuova cetralina");
    	}
    }
    public void aggiungiCentralinaStradale(CentralinaStradale centralina) {
    	int i=0;
    	for (i=0; i<100; i++) {
    		if (this.listaCentralineStradali[i]==null) {
    			this.listaCentralineStradali[i]=centralina;
    			break;
    		}
    	}
    	if (i==100) {
    		System.out.println("impossibile aggiungere una nuova cetralina");
    	}
    }
    public void rimuoviCentralinaAuto(int id) {
    	int i=0;
    	for (i=0; i<8000; i++) {
    		if (this.listaCentralineAuto[i].getIdVeicolo()==id) {
    			this.listaCentralineAuto[i]=null;
    			break;
    		}
    	}
    	if(i==8000) {
    		System.out.println("non esiste nessuna centralina corrispondente all'id");
    	}
    }
    public void rimuoviCentralinaStradale(int id) {
    	int i=0;
    	for (i=0; i<100; i++) {
    		if (this.listaCentralineStradali[i].getIdCentralinaStradale()==id) {
    			this.listaCentralineStradali[i]=null;
    			break;
    		}
    	}
    	if(i==100) {
    		System.out.println("non esiste nessuna centralina corrispondente all'id");
    	}
    }
    public void segnalaDatabaseS(DatoTraffico dato) {
    	GestoreDatabase.getIstance().aggiungiDatoTraffico(dato);
    }
    public void segnalaDatabaseA(StatoVeicolo dato) {
    	GestoreDatabase.getIstance().aggiungiStatoVeicolo(dato);
    }
    
    
    

}
