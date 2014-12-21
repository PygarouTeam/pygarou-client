package org.teampygarou.PyGarouClient.src;


import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.security.MessageDigest;

public class FormLogin extends JFrame
{
	public boolean isConnectionSuccessful;

	public JFrame frame;

	public JLabel imgHome;
	public JLabel labelName;
	public JLabel labelPassword;
	public JTextField inputName;
	public JTextField inputPassword;
	public JButton buttonConnect;


	public FormLogin()
	{
		isConnectionSuccessful = false;

		initTheme();
		initGui();
		initEvent();
	}

	private void initTheme()
	{
		try
		{
			/*for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
			{
				if ("Nimbus".equals(info.getName()))
				{
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}*/

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception ex)
		{
			System.out.println(ex.toString());
		}
	}

	private void initGui()
	{
		frame = new JFrame() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				// TODO: Resize the "login_home.png" image automaticaly or/and add a background
			}
		};
		frame.setBounds(new Rectangle(100, 100, 492, 588));
		frame.setMinimumSize(new Dimension(492, 588));
		frame.setTitle("PyGarou - Made by Woybi and Skyula");
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[5%][grow,75%][grow,20%]", "[10%]push[][]"));

		imgHome = new JLabel();
		imgHome.setIcon(new ImageIcon(FormLogin.class.getResource("/org/teampygarou/PyGarouClient/res/login_home.png")));
		imgHome.setHorizontalAlignment(JLabel.CENTER);
		imgHome.setVerticalAlignment(JLabel.CENTER);
		frame.getContentPane().add(imgHome, "cell 0 0 3 1,grow");

		labelName = new JLabel("Username:");
		frame.getContentPane().add(labelName, "cell 0 2");

		labelPassword = new JLabel("Password:");
		frame.getContentPane().add(labelPassword, "cell 0 3");

		inputName = new JTextField();
		frame.getContentPane().add(inputName, "cell 1 2,grow");

		inputPassword = new JPasswordField();
		frame.getContentPane().add(inputPassword, "cell 1 3,grow");

		buttonConnect = new JButton("Connect");
		frame.getContentPane().add(buttonConnect, "cell 2 2 1 2,grow");
	}

	private void initEvent()
	{
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				frame.setVisible(false);
			}
		});

		buttonConnect.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				buttonConnect_clicked(e);
			}
		});
	}

	private void buttonConnect_clicked(ActionEvent e)
	{
		inputName.setEnabled(false);
		inputPassword.setEnabled(false);
		buttonConnect.setEnabled(false);
		try
		{
//			Thread threadConnection = new Thread(new Connection(this, inputUsername.getText(), String.valueOf(inputPassword.getPassword())));
//			threadConnection.start();
			new Connection(this, "inputName.getText()", "inputPassword.getText()").start();
		}
		catch (Exception ex) { ex.toString(); }
	}

	private String sha256(String base) {
		try
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();

			for (byte aHash : hash)
			{
				String hex = Integer.toHexString(0xff & aHash);
				if (hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();
		}
		catch (Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}
}
