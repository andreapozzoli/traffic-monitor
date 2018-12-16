package prog;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.border.*;
// La struttura e' analoga a LoginDialog

public class RegistrazioneDlg extends JDialog {

	private static final long serialVersionUID = -3407157363359093068L;
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JLabel lbUsername;
	private JLabel lbPassword;
	private JButton btnRegistrazione;
	private JButton btnAnnulla;
	private boolean riuscito;

	public RegistrazioneDlg(Frame parent, String tipoRegistrazione, IGestoreApplicazioni server) {
		super(parent, "Registrazione", true);
		//
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

		btnRegistrazione = new JButton("Registrazione");

		btnRegistrazione.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if(!(Login.authenticate(getUsername(), tipoRegistrazione, server))) {
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
		bp.add(btnRegistrazione);
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

	public boolean registrazioneRiuscita() {
		return riuscito;
	}
}