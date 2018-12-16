package prog;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.border.*;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = 6048442678825828787L;
	private JTextField fieldUsername;
	private JPasswordField fieldPassword;
	private JLabel labelUsername;
	private JLabel labelPassword;
	private JButton btnLogin;
	private JButton btnAnnulla;
	private boolean riuscito;

	public LoginDialog(Frame contenitore, String tipoLogin) {
		super(contenitore, "Login", true);
		//
		JPanel pannelloInformazioni = new JPanel(new GridBagLayout()); // disposizione degli elementi seguendo una griglia
		GridBagConstraints disposizioneGriglia = new GridBagConstraints();

		disposizioneGriglia.fill = GridBagConstraints.HORIZONTAL;

		labelUsername = new JLabel("Nome utente: ");
		disposizioneGriglia.gridx = 0;
		disposizioneGriglia.gridy = 0;
		disposizioneGriglia.gridwidth = 1;
		pannelloInformazioni.add(labelUsername, disposizioneGriglia);

		fieldUsername = new JTextField(20);
		disposizioneGriglia.gridx = 1;
		disposizioneGriglia.gridy = 0;
		disposizioneGriglia.gridwidth = 2;
		pannelloInformazioni.add(fieldUsername, disposizioneGriglia);

		labelPassword = new JLabel("Password: ");
		disposizioneGriglia.gridx = 0;
		disposizioneGriglia.gridy = 1;
		disposizioneGriglia.gridwidth = 1;
		pannelloInformazioni.add(labelPassword, disposizioneGriglia);

		fieldPassword = new JPasswordField(20);
		disposizioneGriglia.gridx = 1;
		disposizioneGriglia.gridy = 1;
		disposizioneGriglia.gridwidth = 2;
		pannelloInformazioni.add(fieldPassword, disposizioneGriglia);

		pannelloInformazioni.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));

		btnLogin = new JButton("Login");

		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(Login.autenticazione(getUsername(), getPassword(), tipoLogin)) {
					JOptionPane.showMessageDialog(LoginDialog.this,
							"Benvenuto/a " + getUsername() + "! Login effettuato con successo.",
							"Login", // titolo della finestra
							JOptionPane.INFORMATION_MESSAGE); // messaggio di informazione
					riuscito = true;
					dispose(); // chiusura della finestra
				} else {
					JOptionPane.showMessageDialog(LoginDialog.this,
							"Le credenziali di accesso non sono valide.",
							"Login", // titolo della finestra
							JOptionPane.ERROR_MESSAGE); // messaggio di errore

					// reset dei campi username e password
					fieldUsername.setText("");
					fieldPassword.setText("");
					riuscito = false;

				}

			}
		});
		btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		JPanel pannelloBottoni = new JPanel();
		pannelloBottoni.add(btnLogin);
		pannelloBottoni.add(btnAnnulla);

		pannelloBottoni.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

		getContentPane().add(pannelloInformazioni, BorderLayout.CENTER);
		getContentPane().add(pannelloBottoni, BorderLayout.PAGE_END);

		pack();
		setResizable(false);
	}

	public String getUsername() {
		return fieldUsername.getText().trim();
	}

	public String getPassword() {
		return new String(fieldPassword.getPassword());
	}

	public boolean loginRiuscito() {
		return riuscito;
	}
}