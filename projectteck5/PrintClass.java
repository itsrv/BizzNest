/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectteck5;

import Sql.sqliteconnection;
import java.awt.HeadlessException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import static org.codehaus.groovy.ast.tools.GeneralUtils.params;
import org.fxutils.viewer.JasperViewerFX;

/**
 *
 * @author rohan
 */
public class PrintClass extends JFrame{
    
   Connection conn = sqliteconnection.Connector();// Database Connection
    PreparedStatement pst = null;
    ResultSet rs = null;   
    

    public PrintClass() throws HeadlessException {
    }
   
    public void showReport(String s){
         try{
             
            InputStream jasperStream = getClass().getResourceAsStream(s);
            //JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);
          
            JasperReport report = (JasperReport) JRLoader.loadObject(jasperStream);
            
            //Map data = new HashMap();
            //JasperPrint JasperPrint = new JasperPrint();
            JasperPrint JasperPrint =  JasperFillManager.fillReport(report, null, conn);
            JRViewer viewer = new JRViewer(JasperPrint);
            
            viewer.setOpaque(true);
            viewer.setVisible(true);
            
            this.add(viewer);
            this.setSize(1066, 708); // JFrame size
            this.setVisible(true);
            this.toFront();
            
            /*
            InputStream jasperStream = getClass().getResourceAsStream(s);
            //JasperReport report = (JasperReport) JRLoader.loadObject(jasperStream);
            JasperPrint JasperPrint = JasperFillManager.fillReport(jasperStream, null, conn);
            JRViewer viewer = new JRViewer(JasperPrint);
              //viewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
             
             viewer.setOpaque(true);
             viewer.setVisible(true);
             
             this.add(viewer);
             this.setSize(900,500); // JFrame size
             this.setVisible(true);
           */ 
         } 
         catch (Exception e) {
             JOptionPane.showMessageDialog(rootPane, e.getMessage());
         }
    }
    public void Priview(String s){
        JasperViewerFX viewer;
        viewer = new JasperViewerFX(null,"Invoice",s,null,conn);
        viewer.show();
    }
    
    
}

