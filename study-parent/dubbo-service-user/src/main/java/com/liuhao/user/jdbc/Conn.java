package com.liuhao.user.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;


/**
 * @author liuhao
 * @com.cpic.shz.db
 * @InformixJDBC.java
 * @2015��7��30�� @����10:28:58
 * @version
 * @comments jdbc ���� informix
 */
public class Conn {

    private Logger log = Logger.getLogger(Conn.class);

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url ="jdbc:mysql://127.0.0.1:3306/liuhao?useUnicode=true";
    private static final String userName ="root";
    private static final String pwd = "root";
    private Connection c =null;

    public Conn(){
        try {
            Class.forName(driver);
            c = DriverManager.getConnection(url,userName,pwd);
            log.info(" connection success!");
        } catch (Exception e) {
            log.error(" connection failure!");
            e.printStackTrace();
        }
    }

    public Connection getConn(){
        return this.c;
    }

    public static void main(String[] args) {
        new Conn().getConn();
    }
}
