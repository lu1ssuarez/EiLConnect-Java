package GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 * @author EiL
 */
public class GUI extends JFrame {

    Font $fontBasic = new Font("Calibri", 3, 16);
    Font $fontText = new Font("Calibri", 0, 16);
    Font $fontButton = new Font("Calibri", 1, 16);

    Border $Border = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.MAGENTA, Color.BLUE);
    Border $BorderButton = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.BLACK);

    public JLabel _jLabel(String $string) {
        JLabel $_jLabel = new JLabel($string);

        $_jLabel.setBounds(15, 50, 80, 30);
        $_jLabel.setFont(this.$fontBasic);
        $_jLabel.setForeground(Color.BLACK);

        return $_jLabel;
    }

    public JTextField _jTextField(String $string) {
        JTextField $_jTextField = new JTextField($string);

        $_jTextField.setBounds(120, 50, 200, 30);
        $_jTextField.setFont(this.$fontText);
        $_jTextField.setBorder(this.$Border);

        return $_jTextField;
    }

    public JButton _jButton(String $string) {
        JButton $_jButton = new JButton($string);

        $_jButton.setBounds(50, 250, 100, 40);
        $_jButton.setBackground(Color.WHITE);
        $_jButton.setFont(this.$fontButton);
        $_jButton.setBorder(this.$BorderButton);
        $_jButton.setForeground(Color.BLACK);

        return $_jButton;
    }

}
