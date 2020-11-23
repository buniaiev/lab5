/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasimpledbcrud;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Ilya
 */
public class DbHelper {
    
    private static DbHelper dbhelper;
    
    private static Connection conn;
    private static String scheme;
    
    public static void setConnectionSettings(String user, String password, String server, String scheme) throws SQLException {
        
        DbHelper.scheme = scheme;
        
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setServerName(server);

        conn = dataSource.getConnection();
    }
    
    public static DbHelper getConnection(
            String user, String password, String server, String scheme) throws SQLException {
        
        if (dbhelper == null) {
            setConnectionSettings(user, password, server, scheme);
        }
        return dbhelper;
    }
    
    public static String[] getTables() {
        ArrayList<String> ls = new ArrayList<>();
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(
                    "SELECT TABLE_NAME FROM information_schema.tables "
                    + "WHERE TABLE_TYPE = \"BASE TABLE\" "
                    + "AND TABLE_SCHEMA = \""+scheme+"\"");
            
            while(res.next()) {
                ls.add(scheme + "." + res.getString("TABLE_NAME"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ls.toArray(new String[0]);
    }
    
    public static DefaultTableModel getTableModel(String table) throws SQLException {

        Statement stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + table);
        DefaultTableModel model =buildTableModel(rs); 
        
        return model; 
    }

    static void update(String table, String id, String idColName,String col, String val) throws SQLException {
        
        Statement stmt = conn.createStatement();
        
        String query = "UPDATE "+table+"" +
                " SET "+col+" = \""+val+"\"" +
                " WHERE " + idColName + " = \""+id+"\"";
        
        stmt.executeUpdate(query);
    }
    
    public static void delete(String idColName, String[] ids, String table) throws SQLException {
        Statement stmt = conn.createStatement();
        String query = "DELETE FROM "+table+" WHERE "+idColName+" = \""+ids[0]+"\" ";
        
        for (int i = 1; i < ids.length; i++) {
            query += "OR "+idColName+" = \""+ids[i]+"\"";
        }
        
        stmt.executeUpdate(query);
    }
    
    public static void insert(HashMap<String, String> entity, String table) throws SQLException {
        Statement stmt = conn.createStatement();
        
        String query = "INSERT INTO "+table+"("+String.join(",", entity.keySet().toArray(new String[0]))+") "
                + "VALUES (\""+String.join("\",\"", entity.values())+"\")";
        
        stmt.executeUpdate(query);
    }
    
    private static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }
}
