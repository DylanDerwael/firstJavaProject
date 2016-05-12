package FrontEnd.GUI;

import BackEnd.API.DataTable;

import javax.swing.*;
import java.awt.event.*;

public class DialogDatabaseAcces extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtDbUser;
    private JPasswordField txtDbPass;
    private String username;
    private String password;

    public DialogDatabaseAcces() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
// add your code here
        new UX("Testing", txtDbUser.getText(), String.valueOf(txtDbPass.getPassword()) );
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        DialogDatabaseAcces dialog = new DialogDatabaseAcces();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
