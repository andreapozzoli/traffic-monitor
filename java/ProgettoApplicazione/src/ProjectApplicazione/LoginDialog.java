package ProjectApplicazione;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.border.*;

//basato su http://www.zentut.com/java-swing/simple-login-dialog/

public class LoginDialog extends JDialog {

	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JLabel lbUsername;
	private JLabel lbPassword;
	private JButton btnLogin;
	private JButton btnCancel;
	private boolean succeeded;
	private IGestoreApplicazioni iGestoreApplicazioni;

	public LoginDialog(Frame parent, String tipoLogin, IGestoreApplicazioni iGestoreApplicazioni) {
		super(parent, "Login", true);
		//
		this.iGestoreApplicazioni=iGestoreApplicazioni;
		JPanel panel = new JPanel(new GridBagLayout());
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
				Login login=new Login();
				try {
					if(login.authenticate(getUsername(), getPassword(), tipoLogin, iGestoreApplicazioni)) {
						JOptionPane.showMessageDialog(LoginDialog.this,
								"Benvenuto/a " + getUsername() + "! Login effettuato con successo.",
								"Login",
								JOptionPane.INFORMATION_MESSAGE);
						succeeded = true;
						dispose();
					} else {
						JOptionPane.showMessageDialog(LoginDialog.this,
								"Le credenziali di accesso non sono valide.",
								"Login",
								JOptionPane.ERROR_MESSAGE);
						// reset username and password
						tfUsername.setText("");
						pfPassword.setText("");
						succeeded = false;

					}
				} catch (HeadlessException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCancel = new JButton("Annulla");
		btnCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		JPanel bp = new JPanel();
		bp.add(btnLogin);
		bp.add(btnCancel);

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

	public boolean isSucceeded() {
		return succeeded;
	}
}