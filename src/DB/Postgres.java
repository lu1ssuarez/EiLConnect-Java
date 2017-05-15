package DB;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JLabel;

/**
 * @author EiL
 */
public class Postgres {

    private Connection $connect;

    public Postgres() {
        
    }

    public Connection verify(JLabel $jlConnectStatus) {
        try {
            this.$connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/eilconnection", "postgres", "123456");
            $jlConnectStatus.setText("Connection OK");
            $jlConnectStatus.setForeground(Color.GREEN);
        } catch (SQLException $exception) {
            $jlConnectStatus.setText("Connection Error :(");

            String $error = "";
            //$error += " SQLState = " + $exception.getSQLState();
            //$error += " SQLErrorCode = " + $exception.getErrorCode();
            $error += " Message = " + $exception.getMessage();

            $jlConnectStatus.setToolTipText($error);
            $jlConnectStatus.setForeground(Color.RED);
        }

        return this.$connect;
    }

}
