/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jramos
 */

public class DBConnector {
    private Connection connection;
    
    // Cambiar estos datos, segun los de cada computadora, o pasarlos via parametros.
    private final String username = "root";
    private final String password = "P455w0rd.";
    private final String host     = "localhost";
    private final String port     = "32769";
    private String       database = "sistema_lavadora";
    private String       url      = "";

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Connection getConnection() {
        return connection;
    }

    @SuppressWarnings("null")
    public void executeUpdate(String query) {
        try {
            int output = this.getStatement().executeUpdate(query);
            System.out.println("Resultado: " + output);
        } catch (SQLException ex) {
            System.out.println("=== DBConnector:Execute:SQLException --> " + ex);
        }
    }
    
    @SuppressWarnings("null")
    public ResultSet executeQuery(String query) {
        ResultSet resultados = null;

        try {
            resultados = this.getStatement().executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("=== DBConnector:Execute:SQLException --> " + ex);
        }

        return resultados;
    }

    public void connect() {
        if(connection != null) return;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.buildConnectionUrl();
            connection = DriverManager.getConnection(this.url, this.username, this.password);
            System.out.println("=== [SUCCESS] === DBConnector:Connected in --> " + this.url);
        } catch (ClassNotFoundException e) {
            System.out.println("=== DBConnector:Connect:ClassException --> ");
        } catch (SQLException e) {
            System.out.println("=== DBConnector:Connect:SQLException --> " + e);
        }
    }

    public void close() {
        if(connection != null) {
            try {
                connection.close();
                System.out.println("=== [SUCCESS] === DBConnector:Disconnected out --> " + this.url);
            } catch (SQLException e) {
                System.out.println("=== DBConnector:Close:SQLException --> " + e);
            }
        }
    }
    
    public Statement getStatement() {
        Statement statement = null;

        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println("=== DBConnector:GetStatement:SQLException" + ex);
        }
        return statement;
    }
    
    private void buildConnectionUrl() {
        this.url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true&useSSL=false";
    }
}
