package org.teampygarou.PyGarouClient.src;


import org.yaml.snakeyaml.Yaml;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Connection extends Thread
{
	FormLogin formLogin;

	SocketAddress socketAddress;
	Socket socket;

	PrintWriter out;
	BufferedReader in;
	Scanner input;

	String Username;
	String Password;

	public Connection(FormLogin _formLogin, String username, String password)
	{
		System.out.println("username: " + username + "\npassword: " + password);
		try
		{
			formLogin = _formLogin;

			socketAddress = new InetSocketAddress("127.0.0.1", 2778); //localhost
			socket = new Socket();

			input = new Scanner(System.in);

			Username = username;
			Password = password;
		}
		catch (Exception ex) { System.out.println(ex.toString()); }
	}

	public void run()
	{
//		formLogin.labelLoading.setVisible(true);
//		formLogin.labelState.setText("Waiting for the server...");

		int nbAttemps = 3;
		for (int i = 1; i != nbAttemps + 1; i++)
		{
			try
			{
				Thread.sleep(1000);
				System.out.println("Trying to connect... (" + i + "/" + nbAttemps + ")");
				socket.connect(socketAddress, 3000);

			}
			catch (Exception ex)
			{
				System.out.println("...failed.");
				continue;
			}

			System.out.println("...successful!");
			break;
		}

		if (!socket.isConnected())
		{
//			formLogin.labelState.setText("ERROR_CPRO-5: Cannot connect to the server");
//			formLogin.labelLoading.setVisible(false);
			formLogin.inputName.setEnabled(true);
			formLogin.inputPassword.setEnabled(true);
			formLogin.buttonConnect.setEnabled(true);

			System.out.println("Failed to connect");

			return;
		}

		List<Object> msg;
		try
		{
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			send(new Object[]{0x1, "Hey Bey", "BEEEEY :D"});

			msg = receive();

			if ((Integer)msg.get(0) == 0x2)
//				formLogin.labelState.setText("Authentication...");
				System.out.println("Auth..");
			else
				throw new Exception("ERROR_CPRO-1: READY was expected");

			send(new Object[]{0x3 + Username});
			msg = receive();
			if ((Integer)msg.get(0) == 0x2)
				if ((Integer)msg.get(0) == 0x4)
					throw new Exception("ERROR_CPRO-3: Unregistered username");
				else
					throw new Exception("ERROR_CPRO-2: OK was expected");

			send(new Object[]{0x5 + sha256(Password)});
			msg = receive();
			if ((Integer)msg.get(0) == 0x2)
				if ((Integer)msg.get(0) == 0x6)
					throw new Exception("ERROR_CPRO-4: Wrong password");
				else
					throw new Exception("ERROR_CPRO-2: OK was expected");

//			formLogin.labelState.setText("Connected :)");

//			formLogin.labelLoading.setVisible(false);
			/*formLogin.frame.setVisible(false);

			FormChat formChat = new FormChat(socket, Username);
			formChat.frame.setVisible(true);

			while(formChat.frame.isVisible()) { Thread.sleep(500); }

			formLogin.frame.setVisible(true);
			formLogin.inputUsername.setEnabled(true);
			formLogin.inputPassword.setEnabled(true);
			formLogin.buttonConnection.setEnabled(true);
			System.out.println("--- FormLogin opened");

			send(new Object[]{"/CLOSE_CONNECTION"});
			System.out.println("--- Request connection closing sent...");
			formLogin.labelState.setText("Waiting for user informations...");*/
		}
		catch (Exception ex)
		{
//			formLogin.labelLoading.setVisible(false);
			formLogin.inputName.setEnabled(true);
			formLogin.inputPassword.setEnabled(true);
			formLogin.buttonConnect.setEnabled(true);

//			formLogin.labelState.setText(ex.getMessage());
			System.out.println("WARNING: " + ex.getMessage());
		}
	}

	public void send(Object packet[])
	{
		try
		{
			List<Object> yamlMsg = Arrays.asList(packet);

			out.printf(new Yaml().dump(yamlMsg));
			System.out.println("<<< " + new Yaml().dump(yamlMsg));
		}
		catch (Exception ex) { System.out.println(ex.toString()); }
	}

	public List<Object> receive()
	{
		try
		{
			String msg = in.readLine();

			System.out.println(">>> " + msg);

			return (List<Object>) new Yaml().load(msg);
		}
		catch (Exception ex) { System.out.println(ex.toString()); }

		return null;
	}

	private String sha256(String base) {
		try {
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
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
