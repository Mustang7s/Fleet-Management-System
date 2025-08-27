package fleetmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private final JTextField txtUser = new JTextField(15);
    private final JPasswordField txtPass = new JPasswordField(15);
    private final JButton btnLogin = new JButton("Login");

    public LoginFrame() {
        setTitle("Fleet Management - Login");
        setSize(360, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(3,2,8,8));
        form.add(new JLabel("Username:"));
        form.add(txtUser);
        form.add(new JLabel("Password:"));
        form.add(txtPass);
        form.add(new JLabel());
        form.add(btnLogin);

        add(form, BorderLayout.CENTER);

        btnLogin.addActionListener((ActionEvent e) -> onLogin());

        getRootPane().setDefaultButton(btnLogin);
    }

    private void onLogin() {
        String user = txtUser.getText().trim();
        String pass = new String(txtPass.getPassword());
        if (DatabaseConnection.checkLogin(user, pass)) {
            JOptionPane.showMessageDialog(this, "Login successful");
            SwingUtilities.invokeLater(() -> {
                dispose();
                new MainDashboard().setVisible(true);
            });
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
