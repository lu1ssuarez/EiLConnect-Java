package GUI;

import DB.Postgres;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author EiL
 */
public class Main extends GUI {

    JLabel $jlID, $jlName, $jlDocument, $jlBirthDate, $jlHeight, $jlConnectStatus;
    JTextField $jtfID, $jtfName, $jtfDocument, $jtfBirthDate, $jtfHeight;
    JButton $jbRegister;
    DefaultTableModel $tmPerson;
    JTable $jTable;

    private Connection $db;

    public Main() {
        this.setSize(800, 460);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("EiL ⪀ Database Connect");

        this._build_gui();

        Postgres $db = new Postgres();
        this.$db = $db.verify($jlConnectStatus);

        this._get_unique_id();
        this._get_persons();
    }

    public String _get_unique_id() {
        String $jlIDUnique = "U01";

        try {
            PreparedStatement $statement = this.$db.prepareStatement("SELECT id FROM person ORDER BY id DESC LIMIT 1");
            ResultSet $resultSet = $statement.executeQuery();

            while ($resultSet.next()) {
                Integer $IDUnique = (Integer.parseInt($resultSet.getObject("id").toString()) + 1);
                $jlIDUnique = "U" + (($IDUnique > 9) ? $IDUnique : "0" + $IDUnique);
            }

            this.$jtfID.setText($jlIDUnique);
        } catch (SQLException $exception) {
            $jlConnectStatus.setToolTipText($exception.getMessage());
            $jlConnectStatus.setForeground(Color.RED);
        }

        return $jlIDUnique;
    }

    public void _register_person() {
        try {
            PreparedStatement $statement = this.$db.prepareStatement("INSERT INTO person (code, name, document, birthday, height, status) VALUES (?, ?, ?, ?, ?, ?) RETURNING code");
            $statement.setString(1, this._get_unique_id());
            $statement.setString(2, this.$jtfName.getText());
            $statement.setString(3, this.$jtfDocument.getText());
            $statement.setDate(4, Date.valueOf(this.$jtfBirthDate.getText()));
            $statement.setFloat(5, Float.parseFloat($jtfHeight.getText()));
            $statement.setBoolean(6, true);

            $statement.executeQuery();
        } catch (SQLException $exception) {
            $jlConnectStatus.setToolTipText($exception.getMessage());
            $jlConnectStatus.setForeground(Color.RED);
        }

        this._get_unique_id();
        this._get_persons();
    }

    public void _get_persons() {
        try {
            PreparedStatement $statement = this.$db.prepareStatement("SELECT id, code, name, document, birthday, height, status FROM person ORDER BY id ASC");
            ResultSet $resultSet = $statement.executeQuery();

            while ($resultSet.next()) {
                Object[] $object = new Object[5];
                $object[0] = $resultSet.getObject("code");
                $object[1] = $resultSet.getObject("name");
                $object[2] = $resultSet.getObject("document");
                $object[3] = $resultSet.getObject("birthday");
                $object[4] = $resultSet.getObject("height");
                
                this.$tmPerson.addRow($object);
            }
        } catch (SQLException $exception) {
            $jlConnectStatus.setToolTipText($exception.getMessage());
            $jlConnectStatus.setForeground(Color.RED);
        }
    }

    public void _build_gui() {
        this.$jlID = this._jLabel("ID:");
        this.$jlID.setBounds(15, 10, 300, 30);
        this.add(this.$jlID);

        this.$jtfID = this._jTextField(null);
        this.$jtfID.setBounds(120, 10, 200, 30);
        this.$jtfID.setEditable(false);
        this.add(this.$jtfID);

        this.$jlConnectStatus = this._jLabel(null);
        this.$jlConnectStatus.setBounds(650, 10, 300, 30);
        this.add(this.$jlConnectStatus);

        this.$jlName = this._jLabel("Nombre:");
        this.add(this.$jlName);

        this.$jtfName = this._jTextField(null);
        this.add(this.$jtfName);

        this.$jlDocument = this._jLabel("Cédula:");
        this.$jlDocument.setBounds(15, 90, 80, 30);
        this.add(this.$jlDocument);

        this.$jtfDocument = this._jTextField(null);
        this.$jtfDocument.setBounds(120, 90, 200, 30);
        this.add(this.$jtfDocument);

        this.$jlBirthDate = this._jLabel("Fecha naci...:");
        this.$jlBirthDate.setBounds(15, 140, 140, 30);
        this.$jlBirthDate.setToolTipText("Formato: AÑO-MES-DIA");
        this.add(this.$jlBirthDate);

        this.$jtfBirthDate = this._jTextField(null);
        this.$jtfBirthDate.setBounds(120, 140, 200, 30);
        this.add(this.$jtfBirthDate);

        this.$jlHeight = this._jLabel("Altura:");
        this.$jlHeight.setBounds(15, 180, 80, 30);
        this.add(this.$jlHeight);

        this.$jtfHeight = this._jTextField(null);
        this.$jtfHeight.setBounds(120, 180, 200, 30);
        this.add(this.$jtfHeight);

        this.$jbRegister = this._jButton("Registrar");
        this.add(this.$jbRegister);
        this.$jbRegister.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                _register_person();
            }
        });

        this.$tmPerson = new DefaultTableModel();
        this.$tmPerson.addColumn("Código");
        this.$tmPerson.addColumn("Nombre");
        this.$tmPerson.addColumn("C.I.");
        this.$tmPerson.addColumn("Fecha Nac.");
        this.$tmPerson.addColumn("Altura");

        this.$jTable = new JTable(this.$tmPerson);
        this.$jTable.setBounds(350, 35, 400, 350);
        this.$jTable.setFont(new Font("Calibri", 0, 12));
        this.add(this.$jTable);
    }

}
