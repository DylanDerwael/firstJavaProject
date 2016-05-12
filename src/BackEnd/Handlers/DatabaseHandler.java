package BackEnd.Handlers;



import BackEnd.API.DataCol;
import BackEnd.API.DataTable;
import BackEnd.Models.Col;
import Services.FotoHandler;

import java.awt.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

/**
 * Created by dylan on 25.12.14.
 */
public class DatabaseHandler {
    private Connection con;
    private Statement stmt;
    private DataTable table;
    private String strQuery;
    private ResultSet rs;


    public DatabaseHandler(String userName, String password) {
        con = con(userName,password);
    }

    private Connection con(String user, String password) {

        String url = "jdbc:mysql://localhost:3306/javatest";

        try{
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    private void closeConnection(){
        try {
            stmt.close();
        } catch (SQLException e) {
            //
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getAllIDs(){
        ArrayList<Integer> ids = new ArrayList<>();
        strQuery = "SELECT id FROM javadata";
        try{
            stmt = con.createStatement();

            rs = stmt.executeQuery(strQuery);
            while (rs.next()) {
                ids.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
            return ids;
        }
    }

    public void createUser(ArrayList<DataCol> cols){
        int count = 0;
        String strInsert = "INSERT INTO javadata (";
        for(DataCol c : cols){
            strInsert += (count++ != 0 ? ",": "");
            strInsert+=  c.getColName();
        }
        count = 0;
        strInsert += ") VALUES (";
        for(DataCol c : cols){
            strInsert += (count++ != 0 ? ", ?": "?");
        }
        strInsert += ")";
        try {
            PreparedStatement pStmt = con.prepareStatement(strInsert);
            count = 0;
            for (DataCol c : cols) {
                Object value = c.getColValue();
                count++;
                if (value instanceof String) {
                    pStmt.setString(count, (String) value);
                }
                if (value instanceof Integer) {
                    pStmt.setInt(count, (int) value);
                }
                if (value instanceof FotoHandler) {
                    FotoHandler f = (FotoHandler) value;
                    pStmt.setBlob(count, f.stream());
                }
                if (value instanceof Color) {
                    Color color = (Color) value;
                    pStmt.setString(count, String.valueOf(color.getRGB()));
                }
            }
            pStmt.executeUpdate();
            pStmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public ArrayList<DataCol> readUser(int id)  {
        ArrayList<DataCol> cols = new ArrayList<>();
        FotoHandler f;
        strQuery = "SELECT * FROM javadata WHERE id="  + id;
        try{
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(strQuery);
            while (rs.next()) {
                cols.add(new Col(rs.getInt("id"),"id", id));
                cols.add(new Col(rs.getString("firstName"), "firstName", id));
                cols.add(new Col(rs.getString("lastName"), "lastName", id));
                cols.add(new Col(rs.getInt("age"), "age",id));
                cols.add(new Col(new Color(Integer.parseInt(rs.getString("color"))),"color", id));
                try {
                    Blob b = rs.getBlob("pic");
                    int blobLength = (int) b.length();
                    byte[] blobAsBytes = b.getBytes(1, blobLength);
                    f = new FotoHandler(blobAsBytes);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    f = new FotoHandler("/Assets/default.png");
                }
                cols.add(new Col(f, "pic", id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           closeConnection();
        }
        return cols;
    }

    public void updateUserCol(int id, String colName, Object value){
        try{
            String strQueryUpdate = "UPDATE javadata SET "+colName+" = ?  WHERE id = ?";
            PreparedStatement pStmt = con.prepareStatement(strQueryUpdate);

            if(value instanceof String){
                pStmt.setString(1, (String)value);
            }
            if (value instanceof Integer){
                pStmt.setInt(1, (int)value);
            }
            if (value instanceof FotoHandler){
                FotoHandler f = (FotoHandler) value;
                pStmt.setBlob(1, f.stream());
            }
            if (value instanceof Color){
                Color c = (Color) value;
                pStmt.setString(1, String.valueOf(c.getRGB()));
            }
            pStmt.setInt(2,id);
            pStmt.executeUpdate();
            pStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id){
        try{
            String strQueryDelete = "Delete  FROM javadata WHERE id ="+ id;
            Statement pStmt = con.createStatement();

            pStmt.execute(strQueryDelete);
            pStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
