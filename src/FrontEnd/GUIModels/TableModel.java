package FrontEnd.GUIModels;

import BackEnd.API.DataTable;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel{

    private DataTable data;

    public TableModel(){}


    public void setData(DataTable t){
        this.data = t;
    }

    public DataTable getData(){return this.data;}

    @Override
    public boolean isCellEditable(int i, int i1) {
        return true;
    }

    @Override
    public int getRowCount() {
        return data.getRowCount();
    }

    @Override
    public int getColumnCount() {
        return data.getColCountOfRow(0);
    }

    @Override
    public Class getColumnClass( int col)
    {
        return data.getColClass(0,col);
    }

    @Override
    public Object getValueAt(int i, int i1) {
        return data.getColValue(i, i1);
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        data.setColValue(row, col, value);
    }

    @Override
    public void fireTableCellUpdated(int i, int i1) {
        super.fireTableCellUpdated(i, i1);
    }

    public JTableHeader setTableHeader(JTableHeader header){
        ArrayList<String> colNames = data.getColNames();
        for(int i = 0; i < data.getColCountOfRow(0); i++)
        {
            TableColumn column = header.getColumnModel().getColumn(i);

            column.setHeaderValue(colNames.get(i));
        }
        return header;
    }

}
