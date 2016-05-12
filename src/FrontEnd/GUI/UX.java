package FrontEnd.GUI;

import BackEnd.API.DataTable;
import FrontEnd.GUIModels.TableModel;
import Services.ColorEditor;
import Services.ColorRenderer;
import Services.FotoEditor;
import Services.FotoHandler;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by dylan on 25.12.14.
 */
public class UX extends JFrame{
    private JPanel pnlMain;
    private JButton btnAdd;
    private JTable table1;
    private JLabel lblTest;
    private JButton btnDelete;
    private TableModel tableModel =  new TableModel();
    public static String username;
    public static String password;

    public UX(String title, String username, String password){
        super(title);
        this.username = username;
        this.password = password;

        tableModel.setData(DataTable.newTable(this.username, this.password));
        table1.setModel(tableModel);
        setContentPane(pnlMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setTitle(title);

        table1.setDefaultRenderer(Color.class, new ColorRenderer(true));
        table1.setDefaultEditor(Color.class, new ColorEditor());
        table1.setDefaultRenderer(FotoHandler.class, new ColorRenderer(true));
        table1.setDefaultEditor(FotoHandler.class, new FotoEditor());

        table1.setTableHeader(tableModel.setTableHeader(table1.getTableHeader()));

        table1.setRowHeight(50);
        setVisible(true);

        btnAdd.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                DialogAdd dialog = new DialogAdd();
                dialog.pack();
                dialog.setTableModel(tableModel);
                dialog.setVisible(true);
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow() != -1){
                    DialogDelete dialog = new DialogDelete(table1.getSelectedRow(),tableModel);
                    dialog.pack();
                    dialog.setVisible(true);}
                }
        });


    }
    public static void main(String[] args) {

        DialogDatabaseAcces dialog = new DialogDatabaseAcces();
        dialog.pack();
        dialog.setVisible(true);

    }
}
