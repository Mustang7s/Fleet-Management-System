package fleetmanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MainDashboard extends JFrame {
    private final DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Type", "Model", "Plate", "Seats", "Capacity (t)"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable table = new JTable(model);

    public MainDashboard() {
        setTitle("Fleet Dashboard");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnRefresh = new JButton("Refresh");
        JButton btnAdd = new JButton("Add Vehicle");
        JButton btnDelete = new JButton("Delete Selected");
        JButton btnLogout = new JButton("Logout");

        JPanel top = new JPanel();
        top.add(btnRefresh);
        top.add(btnAdd);
        top.add(btnDelete);
        top.add(btnLogout);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnRefresh.addActionListener((ActionEvent e) -> loadFleet());
        btnAdd.addActionListener((ActionEvent e) -> new AddVehicleDialog(this, this::loadFleet).setVisible(true));
        btnDelete.addActionListener((ActionEvent e) -> deleteSelected());
        btnLogout.addActionListener((ActionEvent e) -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        loadFleet();
    }

    private void loadFleet() {
        model.setRowCount(0);
        List<Vehicle> fleet = DatabaseConnection.fetchFleet();
        for (Vehicle v : fleet) {
            if (v instanceof Car) {
                Car c = (Car) v;
                model.addRow(new Object[]{"Car", c.getModel(), c.getPlateNumber(), c.getSeats(), null});
            } else if (v instanceof Truck) {
                Truck t = (Truck) v;
                model.addRow(new Object[]{"Truck", t.getModel(), t.getPlateNumber(), null, t.getCapacity()});
            } else {
                model.addRow(new Object[]{"Vehicle", v.getModel(), v.getPlateNumber(), null, null});
            }
        }
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to delete.");
            return;
        }
        String plate = (String) model.getValueAt(row, 2);
        int confirm = JOptionPane.showConfirmDialog(this, "Delete vehicle with plate " + plate + "?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                DatabaseConnection.deleteVehicleByPlate(plate);
                loadFleet();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
