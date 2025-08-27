package fleetmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddVehicleDialog extends JDialog {
    private final JComboBox<String> cmbType = new JComboBox<>(new String[]{"Car", "Truck"});
    private final JTextField txtModel = new JTextField();
    private final JTextField txtPlate = new JTextField();
    private final JSpinner spSeats = new JSpinner(new SpinnerNumberModel(4, 1, 60, 1));
    private final JSpinner spCapacity = new JSpinner(new SpinnerNumberModel(1.0, 0.0, 200.0, 0.5));

    public interface OnSaved {
        void saved();
    }

    public AddVehicleDialog(Frame owner, OnSaved callback) {
        super(owner, "Add Vehicle", true);
        setSize(420, 260);
        setLocationRelativeTo(owner);

        JPanel form = new JPanel(new GridLayout(6,2,8,8));
        form.add(new JLabel("Type:"));
        form.add(cmbType);
        form.add(new JLabel("Model:"));
        form.add(txtModel);
        form.add(new JLabel("Plate:"));
        form.add(txtPlate);
        form.add(new JLabel("Seats (Car):"));
        form.add(spSeats);
        form.add(new JLabel("Capacity tons (Truck):"));
        form.add(spCapacity);

        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");
        JPanel actions = new JPanel();
        actions.add(btnSave);
        actions.add(btnCancel);

        add(form, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);

        cmbType.addActionListener(e -> toggleFields());
        toggleFields();

        btnSave.addActionListener((ActionEvent e) -> {
            try {
                String type = (String) cmbType.getSelectedItem();
                String model = txtModel.getText().trim();
                String plate = txtPlate.getText().trim();
                Integer seats = "Car".equalsIgnoreCase(type) ? (Integer) spSeats.getValue() : null;
                Double capacity = "Truck".equalsIgnoreCase(type) ? ((Number) spCapacity.getValue()).doubleValue() : null;

                if (model.isEmpty() || plate.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Model and Plate are required.");
                    return;
                }

                DatabaseConnection.addVehicle(type, model, plate, seats, capacity);
                JOptionPane.showMessageDialog(this, "Vehicle saved.");
                if (callback != null) callback.saved();
                dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving vehicle: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> dispose());
    }

    private void toggleFields() {
        boolean isCar = "Car".equalsIgnoreCase((String) cmbType.getSelectedItem());
        spSeats.setEnabled(isCar);
        spCapacity.setEnabled(!isCar);
    }
}
