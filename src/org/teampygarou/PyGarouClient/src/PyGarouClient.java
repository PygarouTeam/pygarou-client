package org.teampygarou.PyGarouClient.src;

import java.lang.Thread;

public class PyGarouClient
{
	public static void main(String[] args)
	{
		FormLogin formLogin = new FormLogin();
		formLogin.frame.setVisible(true);

		while (formLogin.frame.isVisible())
		{
			try
			{
				Thread.sleep(300);
			}
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
			}
		}

		if (!formLogin.isConnectionSuccessful)
		{
			System.exit(1);
		}

		System.out.println("CONNECTION IS !OK!");

//		FormGame formGame = new FormGame();
//		formGame.frame.setVisible(true);
//
//		while (formGame.frame.isVisible())
//		{
//			try
//			{
//				Thread.sleep(1000);
//			}
//			catch (InterruptedException ex)
//			{
//				ex.printStackTrace();
//			}
//		}

		System.exit(0);
	}
}
