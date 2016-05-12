package BackEnd.Models;

import BackEnd.API.DataCol;
import BackEnd.API.DataRow;
import BackEnd.Handlers.DatabaseHandler;

import java.util.ArrayList;

/**
 * Created by dylan on 25.12.14.
 */
public class Row implements DataRow {

    private ArrayList<DataCol> cols;
    private int rowId;
    private DatabaseHandler db;

    public Row(String username, String password){
        db = new DatabaseHandler(username, password);
    }
    public Row(ArrayList<DataCol> cols, String username, String password){
        this.cols = cols;
        db = new DatabaseHandler(username, password );
    }

    public Row(ArrayList<DataCol> cols, DatabaseHandler db, int id){
        this.rowId = id;
        this.cols = cols;
        this. db = db;
    }
    @Override
    public Class getColClass(int idCol) {
        return cols.get(idCol).getColClass();
    }

    @Override
    public Object getColValue(int idCol) {
        return cols.get(idCol).getColValue();
    }

    @Override
    public void setColValue(int idCol, Object value) {
        DataCol col = cols.get(idCol);
        col.setColValue(value);
        db.updateUserCol(rowId, col.getColName(), col.getColValue());
    }

    @Override
    public int getColCount() {
        return cols.size();
    }

    @Override
    public void saveRow(ArrayList<DataCol> cols) {
        db.createUser(cols);
    }

    @Override
    public String getColNameByLocation(int i) {
        DataCol c = cols.get(i);
        return c.getColName();
    }

    @Override
    public Object getColValueByName(String colName) {
        for(DataCol c :cols){
            if(c.getColName().equalsIgnoreCase(colName)){
                return c.getColValue();
            }
        }
        return null;
    }
}
