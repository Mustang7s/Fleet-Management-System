# FleetManagement (Java + MySQL + NetBeans)

**Author:** Evans Rutto  
**Admission:** 139691  

---

## Features
- **Login system** connected to MySQL (`users` table).  
- **Fleet management** with CRUD operations:  
  - View vehicles in a JTable.  
  - Add new vehicles (Car/Truck).  
  - Delete selected vehicles.  
- **OOP principles covered**:
  - **Encapsulation**: private fields + getters/setters (`User`, `Vehicle`).  
  - **Abstraction**: `Vehicle` is abstract with abstract method `info()`.  
  - **Inheritance**: `Car` and `Truck` extend `Vehicle`.  
  - **Polymorphism**: `info()` overridden in subclasses.  
- **Objects communicating**: GUI ↔ `DatabaseConnection` ↔ `Vehicle` subclasses.  
- Runs in **NetBeans** with **MySQL backend**.  

---

## ⚙️ Setup Instructions

### 1. Database
1. Open XAMPP → Start **Apache** and **MySQL**.  
2. Go to: [http://localhost/phpmyadmin](http://localhost/phpmyadmin)  
3. Create database `fleetdb`.  
4. Import `fleetdb.sql` from the project folder.  

Default user:  
```
Username: admin
Password: 1234
```

---

### 2. NetBeans Project
1. Open **NetBeans** → Create a new **Java Application** project → name it `FleetManagement`.  
2. Copy the `src/fleetmanagement/` folder from this project into your project’s `src/`.  
3. Add the MySQL JDBC driver:
   - Download `mysql-connector-j-<version>.jar`.  
   - In NetBeans → Project Properties → Libraries → Add JAR/Folder → select the jar.  

---

### 3. Run the App
1. Run `fleetmanagement.LoginFrame`.  
2. Login using:  
   ```
   Username: admin
   Password: 1234
   ```
3. The **Fleet Dashboard** opens:  
   - **Refresh** → Load vehicles from DB.  
   - **Add Vehicle** → Add a new Car or Truck (saved to DB).  
   - **Delete Selected** → Remove a vehicle by plate number.  
   - **Logout** → Return to login screen.  

---

##  Project Structure
```
FleetManagement/
 ├─ src/fleetmanagement/
 │    ├─ Vehicle.java           (Abstract base class)
 │    ├─ Car.java               (Subclass)
 │    ├─ Truck.java             (Subclass)
 │    ├─ FleetManager.java      (Handles vehicle collection)
 │    ├─ User.java              (Encapsulates login info)
 │    ├─ DatabaseConnection.java (MySQL connectivity)
 │    ├─ LoginFrame.java        (Login GUI)
 │    ├─ MainDashboard.java     (Fleet GUI with JTable)
 │    └─ AddVehicleDialog.java  (Dialog for new vehicles)
 ├─ fleetdb.sql                 (Database schema + sample data)
 ├─ lib/                        (Put mysql-connector-j.jar here)
 └─ README.md                   (This file)
```

---

##  Coverage
1. **4+ classes** → ✅ (`Vehicle`, `Car`, `Truck`, `FleetManager`, `User`, etc.)  
2. **3 communicating objects** → ✅ (`MainDashboard` ↔ `DatabaseConnection` ↔ `Vehicle`)  
3. **Encapsulation** → ✅ (`User`, `Vehicle` fields private)  
4. **Inheritance** → ✅ (`Car`, `Truck` extend `Vehicle`)  
5. **Polymorphism** → ✅ (`info()` overridden in subclasses)  
6. **Abstraction** → ✅ (`Vehicle` abstract class)  
7. **Database + Login** → ✅ (`users` table + authentication)  
8. **GUI** → ✅ Swing login + dashboard with JTable  

---

##  Notes
- Runs best on **Java 8+**.  
- Requires **XAMPP MySQL running on localhost:3306**.  
- If you use a different DB user/password, update them in `DatabaseConnection.java`.  
