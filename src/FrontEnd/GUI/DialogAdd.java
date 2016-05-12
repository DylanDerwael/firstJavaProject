package FrontEnd.GUI;

import BackEnd.API.DataCol;
import BackEnd.API.DataRow;
import BackEnd.API.DataTable;
import FrontEnd.GUIModels.TableModel;
import Services.FotoHandler;
import Services.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class DialogAdd extends JDialog /*implements ActionListener */ {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private Color currentColor;
    private JTextField txtAge;
    private JButton btnColorChooserDialog;
    private JButton btnColor;
    private JButton btnPicture;
    private JDialog dialog;
    private JColorChooser colorEditor;
    private TableModel model;
    private FotoHandler f;
    protected static final String EDIT = "edit";

    public DialogAdd() {

        final JFileChooser fc = new JFileChooser();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        btnColor.setBackground(Color.red);
        f = new FotoHandler();

        btnPicture.setIcon(new ImageIcon(f.getScaledFoto()));

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

        btnColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                colorEditor = new JColorChooser();
                dialog = JColorChooser.createDialog(btnColorChooserDialog,
                        "Pick a Color",
                        true,
                        colorEditor,
                        colorListner(),
                        null);

                dialog.setVisible(true);
            }
        });

        btnPicture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fcResponse = fc.showDialog(getParent(), "kies een foto");
                if (fcResponse == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    f.read(file.getPath());
                    btnPicture.setIcon(new ImageIcon(f.getScaledFoto()));
                }
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        Color errorColor = new Color(240, 80, 34);
        ArrayList<DataCol> cols = new ArrayList<>();
        boolean validate = true;

        if (!Validator.isNotNull(txtFirstName.getText())) {
            validate = false;
            txtFirstName.setBackground(errorColor);
        }
        if (!Validator.isNotNull(txtLastName.getText())) {
            validate = false;
            txtLastName.setBackground(errorColor);
        }
        if (!Validator.isNumeric(txtAge.getText())) {
            validate = false;
            txtAge.setBackground(errorColor);
        }
        if (!Validator.isColor(btnColor.getBackground())) {
            validate = false;
        }

        if (validate) {
            cols.add(DataCol.newCol(txtFirstName.getText(), "firstName"));
            cols.add(DataCol.newCol(txtLastName.getText(), "lastName"));
            cols.add(DataCol.newCol(txtAge.getText(), "age"));
            cols.add(DataCol.newCol(btnColor.getBackground(), "color"));
            DataRow x = DataRow.newRow(UX.username, UX.password);
            cols.add(DataCol.newCol(f, "pic"));
            x.saveRow(cols);
            DataTable test = DataTable.newTable(UX.username, UX.password);
            model.setData(test);
            model.fireTableDataChanged();
            dispose();
        }
    }

    private void onCancel() {
        dispose();
    }

    public ActionListener colorListner() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (EDIT.equals(e.getActionCommand())) {
                    //The user has clicked the cell, so
                    //bring up the dialog.
                    btnColor.setBackground(currentColor);
                    colorEditor.setColor(currentColor);
                    dialog.setVisible(true);
                } else { //User pressed dialog's "OK" button.
                    currentColor = colorEditor.getColor();
                    btnColor.setBackground(currentColor);
                }
            }
        };
    }

    public void setTableModel(TableModel model) {
        this.model = model;
    }
}
