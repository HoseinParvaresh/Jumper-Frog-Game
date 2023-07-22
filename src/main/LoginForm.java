package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LoginForm extends JFrame {
    GamePanel gp;
    public JTextField nameField,usernameField,ageField,genderField,nationalCodeField;
    public JPasswordField passwordField;
    public JLabel textLabel;
    public JButton submitButton;
    JPanel panel = new JPanel();

    public LoginForm(GamePanel gp) { this.gp = gp;}

    public void signUp() {

        setTitle("Sign Up");
        setSize(450, 400);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        panel.setLayout(null);

        textLabel = new JLabel("Sign Up");
        textLabel.setFont(new Font("Arial", Font.BOLD, 24));
        textLabel.setBounds(200, 20, 200, 30);
        panel.add(textLabel);

        int xLabel = 50;
        int yLabel = 70;
        int xField = 160;
        int yField = 70;

        textLabel = new JLabel("Name:");
        textLabel.setBounds(xLabel, yLabel, 80, 20);
        yLabel += 40;
        panel.add(textLabel);
        nameField = new JTextField();
        nameField.setBounds(xField, yField, 200, 25);
        yField += 40;
        panel.add(nameField);

        textLabel = new JLabel("Username:");
        textLabel.setBounds(xLabel, yLabel, 80, 20);
        yLabel += 40;
        panel.add(textLabel);
        usernameField = new JTextField();
        usernameField.setBounds(xField, yField, 200, 25);
        yField += 40;
        panel.add(usernameField);

        textLabel = new JLabel("Password:");
        textLabel.setBounds(xLabel, yLabel, 80, 20);
        yLabel += 40;
        panel.add(textLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(xField, yField, 200, 25);
        yField += 40;
        panel.add(passwordField);

        textLabel = new JLabel("Age:");
        textLabel.setBounds(xLabel, yLabel, 80, 20);
        yLabel += 40;
        panel.add(textLabel);
        ageField = new JTextField();
        ageField.setBounds(xField, yField, 200, 25);
        yField += 40;
        panel.add(ageField);

        textLabel = new JLabel("Gender:");
        textLabel.setBounds(xLabel, yLabel, 80, 20);
        yLabel += 40;
        panel.add(textLabel);
        genderField = new JTextField();
        genderField.setBounds(xField, yField, 200, 25);
        yField += 40;
        panel.add(genderField);

        textLabel = new JLabel("National Code:");
        textLabel.setBounds(xLabel, yLabel, 100, 20);
        yLabel += 40;
        panel.add(textLabel);
        nationalCodeField = new JTextField();
        nationalCodeField.setBounds(xField, yField, 200, 25);
        yField += 40;
        panel.add(nationalCodeField);

        submitButton = new JButton("Sign Up");
        submitButton.setBounds(xLabel + 150, yLabel, 80, 30);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitSignUpForm();
            }
        });
        panel.add(submitButton);

        add(panel);
        setVisible(true);
    }
    public void logIn() {

        setSize(400, 250);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        panel.setLayout(null);

        textLabel = new JLabel("Login");
        textLabel.setFont(new Font("Arial", Font.BOLD, 24));
        textLabel.setBounds(160, 20, 200, 30);
        panel.add(textLabel);

        textLabel = new JLabel("Username:");
        textLabel.setBounds(50, 70, 80, 20);
        panel.add(textLabel);

        usernameField = new JTextField();
        usernameField.setBounds(140, 70, 200, 25);
        panel.add(usernameField);

        textLabel = new JLabel("Password:");
        textLabel.setBounds(50, 110, 80, 20);
        panel.add(textLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(140, 110, 200, 25);
        panel.add(passwordField);

        submitButton = new JButton("Login");
        submitButton.setBounds(160, 160, 80, 30);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitLoginForm();
            }
        });
        panel.add(submitButton);

        add(panel);
        setVisible(true);
    }

    public void submitSignUpForm() {
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String gender = genderField.getText();
        String nationalCode = nationalCodeField.getText();

        int age;
        try {
            age = Integer.parseInt(ageField.getText());
        } catch (NumberFormatException e) {
            showMessage("Invalid age! Please enter a valid age.");
            return;
        }
        // بررسی محدودیت سن
        if (age < 5 || age > 110) {
            showMessage("Invalid age! Age must be between 5 and 110.");
            return;
        }
        // بررسی محدودیت جنسیت
        if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")) {
            showMessage("Invalid gender! Gender must be either 'male' or 'female'.");
            return;
        }
        // بررسی محدودیت نام کاربری و نام
        if (username.matches("^\\d.*") || name.matches("^\\d.*")) {
            showMessage("Invalid input! Username and name must not start with a digit.");
            return;
        }
        // بررسی محدودیت رمز عبور
        if (password.length() < 8) {
            showMessage("Invalid password! Password must be at least 8 characters long.");
            return;
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("login-info.txt", true));
            writer.write("Name: " + name + "\n");
            writer.write("Username: " + username + "\n");
            writer.write("Password: " + password + "\n");
            writer.write("Age: " + age + "\n");
            writer.write("Gender: " + gender + "\n");
            writer.write("National Code: " + nationalCode + "\n");
            writer.write("----------------------------------\n");
            writer.close();

        } catch (IOException e) {
            showMessage("An error occurred while saving the data.");
            return;
        }
        showMessage("Sign Up successful!");
        LoginForm loginForm = new LoginForm(gp);
        loginForm.logIn();
        dispose();
    }
    public void submitLoginForm() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        boolean user = false;
        boolean paas = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("login-info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.startsWith("Username: ") && line.substring(10).equals(username)) {user = true;}
                if (line.startsWith("Password: ") && line.substring(10).equals(password)) {paas = true;}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (user && paas) {
            showMessage("Login successful!");
            gp.ui.titleScreenState = 1;
            dispose();
        } else {showMessage("Invalid username or password!");}
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}