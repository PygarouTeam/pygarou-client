import sys
sys.path.append("lib/miglayout-4.0.jar")
from net.miginfocom.swing import MigLayout

from javax.swing import JFrame

from javax.swing import JButton
from javax.swing import JTextField
from javax.swing import JScrollPane
from javax.swing.text import DefaultCaret
from javax.swing import JLabel
from javax.swing import JTextArea
from javax.swing import JList
from javax.swing import JProgressBar

from javax.swing import UIManager

class GUIGame:
	def __init__(self):
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

		self.frame = JFrame('PyGarou - Made by Woybi and Skyula',
					   defaultCloseOperation=JFrame.EXIT_ON_CLOSE,
					   size=(500, 500),
					   minimumSize=(361, 285))
		self.frame.getContentPane().setLayout(
			MigLayout("", "[65%][10%][25%]", "[][][][grow][]"))

		self.progress_day = JProgressBar(0)
		self.progress_day.setStringPainted(True)
		self.progress_day.setValue(50)
		self.frame.getContentPane().add(self.progress_day, "cell 0 0 3 1,grow")

		self.textMessages = JTextArea()
		self.textMessages.setEditable(False)
		self.textMessages.setTabSize(4)

		self.caret = self.textMessages.getCaret()
		self.caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE)

		scrollpane = JScrollPane(self.textMessages)
		self.frame.getContentPane().add(scrollpane, "cell 0 1 2 3,grow")

		self.label_vote = JLabel("You're voting: Nobody")
		self.frame.getContentPane().add(self.label_vote, "cell 2 2,alignx center")


		self.list_users = JList(("Nobody", "Cybi", "Lumixe", "Doloony", "Skyula"),
					  valueChanged=self.__listusers_valuechanged)
		self.list_users.setBorder(UIManager.getBorder("ScrollPane.border"))
		self.frame.getContentPane().add(self.list_users, "cell 2 3 1 2,grow")

		self.input_msg = JTextField()
		self.frame.getContentPane().add(self.input_msg, "cell 0 4,grow")

		self.button_send = JButton("Send")
		self.frame.getContentPane().add(self.button_send, "cell 1 4,grow")

	def __listusers_valuechanged(self, event):
		self.label_vote.setText("You're voting: " + str(self.list_users.getSelectedValue()))

	def set_visible(self, visible):
		self.frame.visible = visible