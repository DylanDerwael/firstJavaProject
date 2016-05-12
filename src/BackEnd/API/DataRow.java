package BackEnd.API;

import BackEnd.Models.Row;

import java.util.ArrayList;

/**
 * Created by dylan on 25.12.14.
 */
public interface DataRow {

    public static DataRow newRow(ArrayList<DataCol> cols, String username, String password){
        return new Row(cols, username, password);
    }
    public static DataRow newRow(String username, String password){ return new Row(username, password);}
    public Class getColClass(int idCol);
    public Object getColValue(int idCol);
    public void setColValue(int idCol, Object value);
    public int getColCount();
    public void saveRow(ArrayList<DataCol> cols);
    public Object getColValueByName(String colName);
    public String getColNameByLocation(int i);
}
