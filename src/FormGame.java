import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class FormGame extends JFrame
{
	public JFrame frame;

	public JProgressBar progressDay;
	public JTextArea textMsg;
	public JScrollPane scrollPane;
	public JLabel labelVote;
	private JList listUsers;
	public DefaultListModel<String> listUsersModel;
	public JTextField inputMsg;
	public JButton buttonSend;


	public FormGame()
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
		frame.setBounds(new Rectangle(100, 100, 500, 500));
		frame.setTitle("PyGarou - Made by Woybi and Skyula");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[65%][10%][25%]", "[][][][grow][]"));

		progressDay = new JProgressBar();
		progressDay.setStringPainted(true);
		progressDay.setValue(50);
		frame.getContentPane().add(progressDay, "cell 0 0 3 1,grow");

		textMsg = new JTextArea();
		textMsg.setEditable(false);
		textMsg.setTabSize(4);

		DefaultCaret caret = (DefaultCaret)textMsg.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		scrollPane = new JScrollPane(textMsg);
		frame.getContentPane().add(scrollPane, "cell 0 1 2 3,grow");

		labelVote = new JLabel("You're voting: Nobody");
		frame.getContentPane().add(labelVote, "cell 2 2,alignx center");

		listUsersModel = new DefaultListModel<String>() {{
			addElement("Nobody");
			addElement("Lumixe");
			addElement("Doloony");
			addElement("Woybi");
			addElement("Skyula");
		}};

		listUsers = new JList<String>(listUsersModel);
		listUsers.setBorder(UIManager.getBorder("scrollPane.border"));
		frame.getContentPane().add(listUsers, "cell 2 3 1 2,grow");

		inputMsg = new JTextField();
		frame.getContentPane().add(inputMsg, "cell 0 4,grow");

		buttonSend = new JButton("Send");
		frame.getContentPane().add(buttonSend, "cell 1 4,grow");
	}
}
