package fleetmanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/fleetdb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found. Add mysql-connector-j to Libraries.", e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static boolean checkLogin(String username, String password) {
        String sql = "SELECT 1 FROM users WHERE username=? AND password=?";
        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Fetch vehicles and map to domain objects (Car/Truck) for polymorphism
    public static List<Vehicle> fetchFleet() {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT type, model, plate, seats, capacity FROM vehicles";
        try (Connection conn = connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                String type = rs.getString("type");
                String model = rs.getString("model");
                String plate = rs.getString("plate");
                Integer seats = (Integer) rs.getObject("seats");
                Double capacity = (Double) rs.getObject("capacity");

                if ("Car".equalsIgnoreCase(type)) {
                    list.add(new Car(plate, model, seats == null ? 0 : seats));
                } else if ("Truck".equalsIgnoreCase(type)) {
                    list.add(new Truck(plate, model, capacity == null ? 0.0 : capacity));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void addVehicle(String type, String model, String plate, Integer seats, Double capacity) throws SQLException {
        String sql = "INSERT INTO vehicles(type, model, plate, seats, capacity) VALUES(?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, type);
            pst.setString(2, model);
            pst.setString(3, plate);
            if (seats != null) pst.setInt(4, seats); else pst.setNull(4, java.sql.Types.INTEGER);
            if (capacity != null) pst.setDouble(5, capacity); else pst.setNull(5, java.sql.Types.DOUBLE);
            pst.executeUpdate();
        }
    }

    public static void deleteVehicleByPlate(String plate) throws SQLException {
        String sql = "DELETE FROM vehicles WHERE plate=?";
        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, plate);
            pst.executeUpdate();
        }
    }
}
