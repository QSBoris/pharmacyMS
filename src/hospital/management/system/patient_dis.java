package hospital.management.system;

import com.mysql.cj.jdbc.result.ResultSetImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class patient_dis extends JFrame {
    patient_dis() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 790, 390);
        panel.setBackground(new Color(116, 164, 140));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("Check-Out");
        label.setBounds(100, 20, 150, 20);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setForeground(Color.white);
        panel.add(label);

        JLabel label2 = new JLabel("Customer ID");
        label2.setBounds(30, 80, 150, 20);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        label2.setForeground(Color.white);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(200, 80, 150, 20);
        panel.add(choice);

        try {
            Connect1 c = new Connect1();
            ResultSet resultSet = c.statement.executeQuery("select * from patien_info");
            while (resultSet.next()) {
                choice.add(resultSet.getString("number"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number");
        label3.setBounds(30, 130, 150, 20);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        label3.setForeground(Color.white);
        panel.add(label3);


        JLabel RN = new JLabel();
        RN.setBounds(200, 130, 150, 20);
        RN.setFont(new Font("Tahoma", Font.BOLD, 14));
        RN.setForeground(Color.white);
        panel.add(RN);


        JLabel label4 = new JLabel("In Time");
        label4.setBounds(30, 180, 150, 20);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        label4.setForeground(Color.white);
        panel.add(label4);

        JLabel INTime = new JLabel();
        INTime.setBounds(200, 180, 150, 20);
        INTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        INTime.setForeground(Color.white);
        panel.add(INTime);

        JLabel label5 = new JLabel("Out Time");
        label5.setBounds(30, 230, 150, 20);
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        label5.setForeground(Color.white);
        panel.add(label5);

        Date date = new Date();


        JLabel OUTime = new JLabel("" + date);
        OUTime.setBounds(200, 230, 250, 20);
        OUTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        OUTime.setForeground(Color.white);
        panel.add(OUTime);

        JButton discharge = new JButton("Discharge");
        discharge.setBounds(30, 300, 120, 30);
        discharge.setBackground(Color.black);
        discharge.setForeground(Color.white);
        panel.add(discharge);
        discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connect1 c = new Connect1();
                try {

                    c.statement.executeUpdate("delete from patien_info where number = '" + choice.getSelectedItem() + "'");
                    c.statement.executeUpdate("update roo, set Availability = 'Available' where room_no= '" + RN.getText() + "'");
                    JOptionPane.showMessageDialog(null, "Done");
                    setVisible(false);

                } catch (Exception E) {

                    E.printStackTrace();

                }
            }
        });

        JButton Check = new JButton("Check");
        Check.setBounds(300, 300, 120, 30);
        Check.setBackground(Color.black);
        Check.setForeground(Color.white);
        panel.add(Check);
        Check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connect1 c = new Connect1();
                try {
                    ResultSet resultSet = c.statement.executeQuery("select * from patien_info where number = '" + choice.getSelectedItem() + "'");
                    while (resultSet.next()) {
                        RN.setText(resultSet.getString("Room_Number"));
                        INTime.setText(resultSet.getString("Time"));
                    }
                } catch (Exception E) {

                }
            }
        });

        JButton Back = new JButton("Back");
        Back.setBounds(170, 300, 120, 30);
        Back.setBackground(Color.black);
        Back.setForeground(Color.white);
        panel.add(Back);
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(800, 400);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);

    }

    public static void main(String[] args) {
        new patient_dis();

    }
}
