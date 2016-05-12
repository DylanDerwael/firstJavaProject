package BackEnd.API;

import BackEnd.Models.Col;

/**
 * Created by dylan on 25.12.14.
 */
public interface DataCol {

    public static DataCol newCol(){
        return new Col();
    }
    public static DataCol newCol(Object value, String colName){
        return new Col(value, colName);
    }
    public static DataCol newCol(Object value, String colName, int rowId) {
        return new Col(value, colName, rowId);
    }
    public void setColValue(Object value);
    public Object getColValue();
    public Class getColClass();
    public String getColName();
    public int getRowId();
}
