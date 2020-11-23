/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasimpledbcrud;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
/**
 *
 * @author Ilya
 */
public class MainJFrame extends javax.swing.JFrame {
    
    
    private ArrayList<Integer> newRows;
    /**
     * Creates new form MaibJFrame
     */
    public MainJFrame() {
        initComponents();
    }
    
    private void updateFromTable(String tableName) throws SQLException{
        
        TableModel model = DbHelper.getTableModel(tableName);
        viewTable.setModel(model);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        viewTable = new javax.swing.JTable();
        btnDeleteSelectedRows = new javax.swing.JButton();
        btnNewRow = new javax.swing.JButton();
        btnApplyNewRows = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        listTableNames = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        viewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(viewTable);
        viewTable.getAccessibleContext().setAccessibleName("");
        viewTable.getAccessibleContext().setAccessibleDescription("");

        btnDeleteSelectedRows.setText("Delete selected rows");
        btnDeleteSelectedRows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSelectedRowsActionPerformed(evt);
            }
        });

        btnNewRow.setText("Add new row");
        btnNewRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewRowActionPerformed(evt);
            }
        });

        btnApplyNewRows.setText("Apply new rows");
        btnApplyNewRows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyNewRowsActionPerformed(evt);
            }
        });

        listTableNames.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listTableNames.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listTableNamesValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(listTableNames);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDeleteSelectedRows)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNewRow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnApplyNewRows)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteSelectedRows)
                    .addComponent(btnApplyNewRows)
                    .addComponent(btnNewRow))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteSelectedRowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSelectedRowsActionPerformed
        
        int[] rowIds = this.viewTable.getSelectedRows();
        String[] ids = new String[rowIds.length];
        
        int i = 0;
        for (int row : rowIds) {
            ids[i++] = viewTable.getModel()
                    .getValueAt(row, 0).toString();
        }
        
        String idColName = viewTable.getModel()
                .getColumnName(0).toString();
        
        String table = listTableNames.getSelectedValue().toString();

        try {
            DbHelper.delete(idColName, ids, table);
            updateFromTable(table);
        } catch (SQLException ex) {
            String m = ex.toString();
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteSelectedRowsActionPerformed

    private void btnNewRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewRowActionPerformed
        DefaultTableModel m = ((DefaultTableModel)viewTable.getModel());
        m.addRow(new String[0]);
    }//GEN-LAST:event_btnNewRowActionPerformed

    private void btnApplyNewRowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyNewRowsActionPerformed
        String table = listTableNames.getSelectedValue();
        
        for (int row : newRows) {
            HashMap<String, String> entity = new HashMap<>();
            
            int colc = viewTable.getModel().getColumnCount();
            for (int col = 0; col < colc; col++) {
                
                String colname = viewTable.getColumnName(col);
                Object val = viewTable.getModel()
                        .getValueAt(row, col);
                
                if (val == null) {
                    continue;
                }
                
                if (!"".equals(val.toString())) {
                    entity.put(colname, val.toString());
                }
            }
            
            try {
                DbHelper.insert(entity, table);
            } catch (SQLException ex) {
                String m = ex.toString();
                m.toString();
            }
        }
        try {
            updateFromTable(table);
        } catch (SQLException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        newRows = new ArrayList<Integer>();
    }//GEN-LAST:event_btnApplyNewRowsActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File("nbproject\\connectionsettings.properties")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String server = props.getProperty("server");
        String scheme = props.getProperty("scheme");

        try {
            DbHelper.setConnectionSettings(user, password, server, scheme);
        } catch (SQLException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        Logger.getLogger(MainJFrame.class.getName())
                .log(Level.SEVERE, String.join(",", new String[] {user, password, server, scheme}));
        
        String[] tables = DbHelper.getTables();
        
        listTableNames.setListData(tables);
    }//GEN-LAST:event_formWindowOpened

    private void listTableNamesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listTableNamesValueChanged
        
        String table = listTableNames.getSelectedValue().toString();
        try {
            TableModel tm = DbHelper.getTableModel(table);
            tm.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {

                    if (e.getType() == TableModelEvent.UPDATE){

                        if (newRows.contains(e.getFirstRow())) {
                            return;
                        }

                        String col = viewTable.getColumnName(e.getColumn());

                        //get new val
                        String val = viewTable.getModel()
                        .getValueAt(e.getFirstRow(), e.getColumn())
                        .toString();

                        String id = viewTable.getModel()
                        .getValueAt(e.getFirstRow(), 0)
                        .toString();

                        String idColName = viewTable.getModel()
                        .getColumnName(0)
                        .toString();

                        String table = listTableNames.getSelectedValue().toString();

                        try {
                            //do query
                            DbHelper.update(table, id, idColName, col, val);

                        } catch (SQLException ex) {
                            String m = ex.toString();
                            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    if (e.getType() == TableModelEvent.INSERT) {
                        int row = e.getFirstRow();

                        if (newRows == null) {
                            newRows = new ArrayList<Integer>();
                        }

                        newRows.add(row);
                    }
                }
            });

            viewTable.setModel(tm);
        } catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_listTableNamesValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApplyNewRows;
    private javax.swing.JButton btnDeleteSelectedRows;
    private javax.swing.JButton btnNewRow;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listTableNames;
    private javax.swing.JTable viewTable;
    // End of variables declaration//GEN-END:variables
}
