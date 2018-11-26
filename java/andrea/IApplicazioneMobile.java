import java.rmi.*;

public interface IApplicazioneMobile extends Remote {
	public int getIdentificativo ();
	public NotificaApplicazione creaNotificaApplicazione(Posizione pos, String tipo);
	public Posizione getPosizione();
	public void aggiungiNotificaInCoda(NotificaApplicazione notifica);
	public void setIdentificativo(int id);

}
