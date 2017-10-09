package br.com.insidegroup.insidecomerce.Controles;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Lucas on 08/10/2017.
 */

public class Conexao {

    private static String server = "insidegroup.no-ip.biz";
    private static String port = "1444";
    private static String database = "InsideComerce_Concrelagos";
    private static String user = "rm";
    private static String password = "rm";


    @SuppressWarnings("NewApi")
    public static Connection getConnection(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionUrl = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");

            connectionUrl = "jdbc:jtds:sqlserver://"+server+":"+port+"/"+database;
            connection = DriverManager.getConnection(connectionUrl, "rm", "rm");
        }
        catch(SQLException se)
        {
            Log.e("Erro 1: ", se.getMessage());
        }
        catch(ClassNotFoundException e)
        {
            Log.e("Erro 2: ", e.getMessage());
        }
        catch(Exception e)
        {
            Log.e("Erro 3", e.getMessage());
        }

        return connection;
    }
}
