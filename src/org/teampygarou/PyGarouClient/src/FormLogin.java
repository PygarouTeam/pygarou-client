package org.teampygarou.PyGarouClient.src;


import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class FormLogin extends JFrame
{
	public JFrame frame;

	public JLabel imgHome;
	public JLabel labelName;
	public JLabel labelPassword;
	public JTextField inputName;
	public JTextField inputPassword;
	public JButton buttonConnect;


	public FormLogin()
	{
		initTheme();
		initGui();
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
		frame = new JFrame();
		frame.setBounds(new Rectangle(100, 100, 492, 588));
		frame.setMinimumSize(new Dimension(492, 588));
		frame.setTitle("PyGarou - Made by Woybi and Skyula");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[5%][grow,75%][grow,20%]", "[10%]push[][]"));

		imgHome = new JLabel("");
		imgHome.setIcon(new ImageIcon(FormLogin.class.getResource("/org/teampygarou/PyGarouClient/res/login_home.png")));
		imgHome.setHorizontalAlignment(JLabel.CENTER);
		imgHome.setVerticalAlignment(JLabel.CENTER);
		frame.getContentPane().add(imgHome, "cell 0 0 3 1,grow");

		labelName = new JLabel("Username:");
		frame.getContentPane().add(labelName, "cell 0 2");

		labelPassword = new JLabel("Password:");
		frame.getContentPane().add(labelPassword, "cell 0 3");

		inputName = new JTextField("");
		frame.getContentPane().add(inputName, "cell 1 2,grow");

		inputPassword = new JTextField("");
		frame.getContentPane().add(inputPassword, "cell 1 3,grow");

		buttonConnect = new JButton("Connect");
		frame.getContentPane().add(buttonConnect, "cell 2 2 1 2,alignx center,grow");
	}
}
