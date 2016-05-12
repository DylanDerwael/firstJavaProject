package BackEnd.Models;

import BackEnd.API.DataRow;
import BackEnd.API.DataTable;
import BackEnd.Handlers.DatabaseHandler;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by dylan on 25.12.14.
 */
public class Table implements DataTable{

    private DatabaseHandler db;
    private ArrayList<DataRow> rows;
    private static String username;
    private static String password;

    public Table(String username, String password) {
        this.username = username;
        this.password = password;
        this.db = new DatabaseHandler(this.username, this.password);
        rows =  new ArrayList<>();
        initialDbFil();
    }

    public void initialDbFil(){
        DataRow r;
        for (int id : db.getAllIDs()){

            addRow(r =new Row(db.readUser(id),this.db,id));
        }

    }

    @Override
    public Object getColValue(int idRow, int idCol) {
        return rows.get(idRow).getColValue(idCol);
    }

    @Override
    public void setColValue(int idRow, int idCol, Object value) {
        rows.get(idRow).setColValue(idCol,value);
    }

    @Override
    public Class getColClass(int idRow, int idCol) {
        return  rows.get(idRow).getColClass(idCol);
    }

    @Override
    public int getColCountOfRow(int idRow) {
        return rows.get(idRow).getColCount();
    }

    @Override
    public DataRow getRow(int idRow) {
        return rows.get(idRow);
    }

    @Override
    public void deleteRow(int idRow) {
        int id = (int)getColValue(idRow,0);
        db.deleteUser(id);
        rows.remove(idRow);
    }

    @Override
    public ArrayList<String> getColNames() {
        ArrayList<String> colNames = new ArrayList<>();
        DataRow r =  getRow(0);
        for(int i = 0; i<r.getColCount(); i++){

            String output = r.getColNameByLocation(i).replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2");
            colNames.add(output.toLowerCase());
        }
        return colNames;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public void addRow(DataRow r) {
        rows.add(r);
    }


}
