package BackEnd.API;


import BackEnd.Models.Table;

import java.util.ArrayList;

/**
 * Created by dylan on 25.12.14.
 */
public interface DataTable  {

    public static DataTable newTable(String username, String password){
        return new Table(username, password);
    }
    /**
     * Begin : Col functions
     */
    public Object getColValue(int idRow, int idCol);
    public void setColValue(int idRow, int idCol, Object value);
    public Class getColClass(int idRow, int idCol);
    public int getColCountOfRow(int idRow);
    public ArrayList<String> getColNames();
    /**
     * End : Col functions
     */

    /**
     * Begin : Row functions
     */
    public DataRow getRow(int idRow);
    public void deleteRow(int idRow);
    public int getRowCount();
    public void addRow(DataRow r);
    /**
     * End : Row functions
     */
}
