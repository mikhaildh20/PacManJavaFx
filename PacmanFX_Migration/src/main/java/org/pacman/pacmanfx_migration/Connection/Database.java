package org.pacman.pacmanfx_migration.Connection;

import java.sql.*;

public class Database {
    public Connection conn;
    public Statement stat;
    public ResultSet result;
    public PreparedStatement pstat;

    public Database(){
        try{
            String url = "jdbc:sqlserver://localhost;database=PacMan;user=sa;password=polman";
            conn = DriverManager.getConnection(url);
            stat = conn.createStatement();
        }catch(Exception e){
            System.out.println("Error saat connect database: "+e);
        }
    }

    public static void main(String[] args) {
        Database connect = new Database();

    }
}
