package FrontEnd.GUI;

import BackEnd.API.DataCol;
import BackEnd.API.DataRow;
import BackEnd.API.DataTable;
import FrontEnd.GUIModels.TableModel;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DialogDelete extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel pnlTexts;
    private JLabel txtWaring;
    private JLabel txtUserName;
    private String firstName;
    private  String lastName;
    private TableModel model;
    private DataRow row;
    DataTable data;
    private int rowId;

    public DialogDelete() {
    }

    public DialogDelete(int rowId, TableModel model) {
        this.model = model;
        data = model.getData();
        this.rowId = rowId;
        this.row = data.getRow(rowId);
        firstName = (String)row.getColValueByName("firstName");
        lastName = (String)row.getColValueByName("lastname");

        txtUserName.setText(firstName + " " + lastName);
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
        data.deleteRow(rowId);
        model.fireTableDataChanged();
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        DialogDelete dialog = new DialogDelete();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
