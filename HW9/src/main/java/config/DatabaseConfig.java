package config;

import util.Constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public Connection getCreatedConnection(){
        try{
            return DriverManager.getConnection(Constant.URL,Constant.USERNAME,Constant.PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getErrorCode());
        }
        return null;
    }
}
