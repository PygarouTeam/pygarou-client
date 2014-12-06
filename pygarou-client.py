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


UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

frame = JFrame('PyGarou - Made by Woybi and Skyula',
			defaultCloseOperation=JFrame.EXIT_ON_CLOSE,
			size=(500, 500),
			minimumSize=(361, 285))
frame.getContentPane().setLayout(
	MigLayout("", "[65%][10%][25%]", "[][][][grow][]"))

dayProgress = JProgressBar(0)
dayProgress.setStringPainted(True)
dayProgress.setValue(50)
frame.getContentPane().add(dayProgress, "cell 0 0 3 1,grow")

textMessages = JTextArea()
textMessages.setEditable(False)
textMessages.setTabSize(4)

caret = textMessages.getCaret()
caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE)

scrollPane = JScrollPane(textMessages)
frame.getContentPane().add(scrollPane, "cell 0 1 2 3,grow")

labelVote = JLabel("You're voting: Nobody")
frame.getContentPane().add(labelVote, "cell 2 2,alignx center")


def listusers_valuechanged(event):
	labelVote.setText("You're voting: " + str(listUsers.getSelectedValue()))

listUsers = JList(("Nobody", "Cybi", "Lumixe", "Doloony", "Skyula"),
	valueChanged=listusers_valuechanged)
listUsers.setBorder(UIManager.getBorder("ScrollPane.border"))
frame.getContentPane().add(listUsers, "cell 2 3 1 2,grow")


inputMessage = JTextField()
frame.getContentPane().add(inputMessage, "cell 0 4,grow")

buttonSend = JButton("Send")
frame.getContentPane().add(buttonSend, "cell 1 4,grow")

frame.visible = True
