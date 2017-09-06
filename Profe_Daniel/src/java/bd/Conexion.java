/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {
    
    private Connection conex; //objeto que permite ejecutar una sentencia sql
    //Statement stmt=null;
   // boolean b;
    private String puerto = "jdbc:postgresql://localhost:5432/credito";
   // private String BD="credito";//nombre de la base de datos
    private String usuario="postgres";
    private String contra="isabel";//contraseña de postgresql
    boolean b=true;
    
   public Connection conectar(){
        try {
           // Class.forName("org.postgresql.Driver");
            Class.forName("org.postgresql.Driver");
            conex = DriverManager.getConnection(puerto,usuario,contra);
            //System.out.println("Conexion Realizada");
            //stmt=conex.createStatement();
            JOptionPane.showMessageDialog(null,"conectado");
            b=true;
        }catch (Exception e){
            System.out.println("Error al Conectarse"+e.getMessage());
            b=false;
        }
        return conex;
   }

 
   public void  ingresaDatos(String nombree,String edadd,String direccionn,String empresaa ) //throws SQLException 
    {
        //int f=Integer.valueOf(ff).intValue();//conviero a entero
        //String insertando= "insert into registro(nombre,edad,direccion,empresa) values ('"+nombree+"',"+f+",'"+direccionn+"','"+empresaa+"');";
       System.out.println("bien ingresaDatos");
        conex = conectar();      
             Statement ingresa;
        try {
            ingresa = (Statement) conex.createStatement();
        /*} catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }*/
              int ed=Integer.valueOf(edadd).intValue();
             String insertando= "insert into cliente (nombre,edad,direccion,empresa) values ('"+nombree+"',"+ed+",'"+direccionn+"','"+empresaa+"');";
             //try{
                 ingresa.execute(insertando);
                 ingresa.close();
                 conex.close();
                 System.out.println("bien");
                 
             }catch(SQLException ex){
            try {
                System.err.println("error al insertar: "+ex.getMessage());
                ex.printStackTrace();
                conex.close();
            }
            //return insertando;
            catch (SQLException ex1) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
             }
             
        
    }
   
   
   public String ver_datos() throws SQLException
   {
       String datos = "select nombre,edad,direccion,empresa from cliente;";
       String s = "";
       conectar();
       Statement st = (Statement) conex.createStatement();
       ResultSet tabla = st.executeQuery(datos);
       
       s+="<th>Nombre</th><th>Edad</th><th>Direccion</th><th>Empresa</th>";
       while(tabla.next()){
           s=s+"<tr>"
                   +"<td>"+tabla.getString("nombre")+"</td>"
                   +"<td>"+tabla.getString("edad")+"</td>"
                   +"<td>"+tabla.getString("direccion")+"</td>"
                   +"<td>"+tabla.getString("empresa")+"</td>"
                   +"</tr>";
       }
       st.close();
       tabla.close();
       conex.close();
       return s;
   }
   
}