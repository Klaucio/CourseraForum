/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class UsuariosDAO {
    
    public boolean login(String username, String password){
        try{
        String sql = "SELECT login, name "
                + "FROM usuario "
                + "WHERE login = ? and password = ?";
        
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection conn = databaseConnection.connect();
        
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                return true;
            }
        }catch(Exception e){
            return false;
        }
        return false;
    }
    
}
