# FleetManagement (Java, Swing, MySQL, NetBeans-ready)
#139691-Rutto Evans

A simple **Fleet Management System** demonstrating core OOP principles and a working **MySQL-backed** login and fleet CRUD UI.

## Features
- **OOP**: Abstraction (`Vehicle`), Inheritance (`Car`, `Truck`), Encapsulation (private fields + getters/setters), Polymorphism (`info()` overridden).
- **Objects communicating**: `MainDashboard` ⇄ `DatabaseConnection` ⇄ `FleetManager` ⇄ `Vehicle` subclasses.
- **Login with MySQL** (`users` table).
- **Fleet stored in MySQL** (`vehicles` table).
- **GUI (Swing)**: view, add, and delete vehicles in a JTable.

## Run Instructions (NetBeans)
1. Import the database:
   ```bash
   mysql -u root -p -e "SOURCE fleetdb.sql"
   ```
   (Press Enter when prompted if you have **no password**.)
2. Create a new **Java Application** project in NetBeans (e.g., name it `FleetManagement`).
3. Copy the `src/fleetmanagement` folder **from this zip** into your project's `src/`.
4. Add **MySQL Connector/J** to Libraries:
   - Download the jar from Oracle/MySQL (e.g., `mysql-connector-j-8.4.x.jar`).
   - NetBeans: *Right-click Project → Properties → Libraries → Add JAR/Folder*.
5. Run `fleetmanagement.LoginFrame`.
6. Login with: **admin / 1234**.

## Database Schema
See `fleetdb.sql`. It creates:
- `users` with default `admin/1234`.
- `vehicles` with sample data.

## Marking Rubric Mapping
1. **Four classes+**: Vehicle, Car, Truck, FleetManager, User, DatabaseConnection, LoginFrame, MainDashboard, AddVehicleDialog.
2. **Three communicating objects**: GUI ↔ DatabaseConnection ↔ Vehicle/FleetManager.
3. **Encapsulation**: private fields + getters/setters across domain classes.
4. **Inheritance**: Car, Truck extend Vehicle.
5. **Polymorphism**: `info()` overridden in Car/Truck; `fetchFleet()` builds subclass instances and UI renders accordingly.
6. **Abstraction**: `Vehicle` is abstract with abstract `info()`.
7. **DB Login**: `DatabaseConnection.checkLogin()` against MySQL.
8. **GUI**: Swing-based login + dashboard, JTable, add/delete dialog.

## Notes
- Keep your MySQL running on `localhost:3306` and database `fleetdb`.
- If you use a different DB user/password, edit them in `DatabaseConnection.java`.

