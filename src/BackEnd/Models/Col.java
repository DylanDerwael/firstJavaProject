package BackEnd.Models;

import BackEnd.API.DataCol;
import BackEnd.Handlers.DatabaseHandler;

import java.awt.*;
import java.util.Vector;

/**
 * Created by dylan on 25.12.14.
 */
public class Col implements DataCol {

    private Object value;
    private String colName;
    private int id;
    private DatabaseHandler db;

    public Col() {

    }

    public Col(Object value, String colName) {
        this.value = value;
        this.colName = colName;
    }
    public Col(Object value, String colName, int rowId) {
        this.value = value;
        this.colName = colName;
        this.id = rowId;
    }

    @Override
    public void setColValue(Object value) {
        this.value = value;

    }

    @Override
    public int getRowId() {
        return id;
    }

    @Override
    public Object getColValue() {
        return value;
    }

    @Override
    public Class getColClass() {
        return value.getClass();
    }

    @Override
    public String getColName() {
        return colName;
    }
}
