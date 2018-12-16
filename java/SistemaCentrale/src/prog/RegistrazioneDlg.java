package prog;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

// La struttura e' analoga a LoginDialog

public class RegistrazioneDlg extends JDialog {

	private static final long serialVersionUID = -3407157363359093068L;
	private JTextField fieldUsername;
	private JPasswordField fieldPassword;
	private JLabel labelUsername;
	private JLabel labelPassword;
	private JButton btnRegistrazione;
	private JButton btnAnnulla;
	private boolean riuscito;

	public RegistrazioneDlg(Frame parent, String tipoRegistrazione) {
		super(parent, "Registrazione", true);
		//
		JPanel pannelloInformazioni = new JPanel(new GridBagLayout());
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

		btnRegistrazione = new JButton("Registrazione");

		btnRegistrazione.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String nome=getUsername();
				String pass=getPassword();
				if (nome.equals("") || pass.equals("")) {
					JOptionPane.showMessageDialog(RegistrazioneDlg.this,
							"Campo username o password vuoto.",
							"Registrazione",
							JOptionPane.ERROR_MESSAGE);

					fieldUsername.setText("");
					fieldPassword.setText("");
					riuscito = false;
				}

				else if(!(Login.autenticazione(nome, tipoRegistrazione))) {
					JOptionPane.showMessageDialog(RegistrazioneDlg.this,
							"Benvenuto/a " + getUsername() + "! Registrazione effettuata con successo.",
							"Registrazione",
							JOptionPane.INFORMATION_MESSAGE);
					riuscito = true;
					dispose();
				} else {
					JOptionPane.showMessageDialog(RegistrazioneDlg.this,
							"Username gi� in uso.",
							"Registrazione",
							JOptionPane.ERROR_MESSAGE);

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
		pannelloBottoni.add(btnRegistrazione);
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

	public boolean registrazioneRiuscita() {
		return riuscito;
	}
}