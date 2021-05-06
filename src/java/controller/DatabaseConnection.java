/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.media.jfxmedia.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection
{
    private static DatabaseConnection instance;
    // init database constants
    private static final String DATABASE_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost/forum_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "secret";
//    private static final String MAX_POOL = "250";

    // init connection object
    private Connection connection;
    
    // init properties object
    private Properties properties;

    // init the statement
    private Statement statement;
    
    // create properties
    private Properties getProperties()
    {
        if (properties == null)
        {
            properties = new Properties();
        
            properties.setProperty("user", USERNAME);
            
            properties.setProperty("password", PASSWORD);
            
//            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        
        return properties;
    }

    /**
     * Connect to the database  
     * 
     * @return Connection
     */
    public Connection connect()
    {
        if (connection == null)
        {         
            try
            {
                Class.forName(DATABASE_DRIVER);
                
                connection = (Connection) DriverManager.getConnection(DATABASE_URL, getProperties());
            }
            catch (ClassNotFoundException | SQLException e)
            {
                System.err.println("Error conecting to database");
            }
        }
        return connection;
    }

    /**
     * Disconnect database
     */
    public void disconnect()
    {
        if (connection != null)
        {
            try
            {
                connection.close();
             
                connection = null;
                
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Return the result set when a correct SQL statement is provided
     * 
     * @param query
     * @return
     * @throws SQLException 
     */
    public ResultSet select(String query) throws SQLException
    {        
        statement = connection.createStatement();
        
        ResultSet resultSet = statement.executeQuery(query);
        
        return resultSet;        
    }
    
    /**
     * Return the status when a SQL query is provided for INSERT, UPDATE or DELETE
     * 
     * @param query
     * @return
     * @throws SQLException 
     */
    public int createOrUpdateOrDelete(String query) throws SQLException
    {        
            statement = connection.createStatement();
            
            int result = statement.executeUpdate(query);
            
            return result;       
    }
    
}