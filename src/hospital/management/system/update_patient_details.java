package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class update_patient_details extends JFrame {

    update_patient_details() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 940, 490);
        panel.setBackground(new Color(116, 164, 140));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(560, 60, 300, 300);
        panel.add(label);

        JLabel label1 = new JLabel("Update Patient Details");
        label1.setBounds(124, 11, 250, 25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20));
        label1.setForeground(Color.white);
        panel.add(label1);

        JLabel label2 = new JLabel("Name:");
        label2.setBounds(25, 88, 100, 16);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label2.setForeground(Color.white);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(248, 85, 100, 25);
        panel.add(choice);

        try {
            Connect1 c = new Connect1();
            ResultSet resultSet = c.statement.executeQuery("select * from patien_info");
            while (resultSet.next()) {
                choice.add(resultSet.getString("Name"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number:");
        label3.setBounds(25, 129, 100, 16);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label3.setForeground(Color.white);
        panel.add(label3);

        JTextField textFieldR = new JTextField();
        textFieldR.setBounds(248, 129, 140, 20);
        panel.add(textFieldR);

        JLabel label4 = new JLabel("In-Time:");
        label4.setBounds(25, 174, 100, 16);
        label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label4.setForeground(Color.white);
        panel.add(label4);

        JTextField textFieldINTT = new JTextField();
        textFieldINTT.setBounds(248, 174, 140, 20);
        panel.add(textFieldINTT);

        JLabel label5 = new JLabel("Amount Paid($):");
        label5.setBounds(25, 216, 200, 16);
        label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label5.setForeground(Color.white);
        panel.add(label5);

        JTextField textFieldAmount = new JTextField();
        textFieldAmount.setBounds(248, 216, 140, 20);
        panel.add(textFieldAmount);


        JLabel label6 = new JLabel("Pending Amount($):");
        label6.setBounds(25, 261, 200, 16);
        label6.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label6.setForeground(Color.white);
        panel.add(label6);

        JTextField textFieldPAmount = new JTextField();
        textFieldPAmount.setBounds(248, 261, 140, 20);
        panel.add(textFieldPAmount);

        JButton check = new JButton("Check");
        check.setBounds(281, 378, 89, 23);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.white);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = choice.getSelectedItem();
                String q = "select * from patien_info where Name = '" + id + "'";
                try {
                    Connect1 c = new Connect1();
                    ResultSet resultSet = c.statement.executeQuery(q);
                    while (resultSet.next()) {
                        textFieldR.setText(resultSet.getString("Room_Number"));
                        textFieldINTT.setText(resultSet.getString("Time"));
                        textFieldAmount.setText(resultSet.getString("Deposite"));


                    }
                    ResultSet resultSet1 = c.statement.executeQuery("select * from room where room_no='" + textFieldR.getText() + "'");
                    while (resultSet.next()) {

                        String price = resultSet1.getString("Price");
                        int amountPaid = Integer.parseInt(price) - Integer.parseInt(textFieldAmount.getText());
                        textFieldPAmount.setText("" + amountPaid);

                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton update = new JButton("Update");
        update.setBounds(56, 378, 89, 23);
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        panel.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Connect1 c = new Connect1();
                    String q = choice.getSelectedItem();
                    String room = textFieldR.getText();
                    String time = textFieldINTT.getText();
                    String amount = textFieldAmount.getText();
                    c.statement.executeUpdate("update patien_info set Room_Number='" + room + ", Time='" + time + "', Deposite='" + amount + "' where name =" + q + "'");
                    JOptionPane.showMessageDialog(null, "Updated Succesfully");
                    setVisible(false);

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(168, 378, 89, 23);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


        setSize(950, 500);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);

    }

    public static void main(String[] args) {
        new update_patient_details();
    }
}
