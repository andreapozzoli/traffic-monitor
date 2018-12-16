package prog;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.border.*;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = 6048442678825828787L;
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JLabel lbUsername;
	private JLabel lbPassword;
	private JButton btnLogin;
	private JButton btnAnnulla;
	private boolean riuscito;

	public LoginDialog(Frame parent, String tipoLogin, IGestoreApplicazioni server) {
		super(parent, "Login", true);
		//
		JPanel panel = new JPanel(new GridBagLayout()); // disposizione degli elementi seguendo una griglia
		GridBagConstraints cs = new GridBagConstraints();

		cs.fill = GridBagConstraints.HORIZONTAL;

		lbUsername = new JLabel("Nome utente: ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(lbUsername, cs);

		tfUsername = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(tfUsername, cs);

		lbPassword = new JLabel("Password: ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(lbPassword, cs);

		pfPassword = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(pfPassword, cs);
		panel.setBorder(new LineBorder(Color.GRAY));

		btnLogin = new JButton("Login");

		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if(Login.authenticate(getUsername(), getPassword(), tipoLogin, server)) {
						JOptionPane.showMessageDialog(LoginDialog.this,
								"Benvenuto/a " + getUsername() + "! Login effettuato con successo.",
								"Login", // titolo della finestra
								JOptionPane.INFORMATION_MESSAGE); // messaggio di informazione
						riuscito = true;
						dispose();
					} else {
						JOptionPane.showMessageDialog(LoginDialog.this,
								"Le credenziali di accesso non sono valide.",
								"Login", // titolo della finestra
								JOptionPane.ERROR_MESSAGE); // messaggio di errore

						// reset dei campi username e password
						tfUsername.setText("");
						pfPassword.setText("");
						riuscito = false;

					}
				} catch (HeadlessException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		JPanel bp = new JPanel();
		bp.add(btnLogin);
		bp.add(btnAnnulla);

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}

	public String getUsername() {
		return tfUsername.getText().trim();
	}

	public String getPassword() {
		return new String(pfPassword.getPassword());
	}

	public boolean loginRiuscito() {
		return riuscito;
	}
}