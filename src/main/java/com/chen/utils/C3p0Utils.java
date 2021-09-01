package com.chen.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Utils {

    public static ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");

    public static Connection getConnection(){
        try {
            Connection connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection con, Statement statement){
        try {
            if (con!= null && statement!= null){
                statement.close();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection con, Statement statement, ResultSet resultSet){
        try {
            if (con!= null && statement!= null && resultSet!=null){
                resultSet.close();
                statement.close();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

