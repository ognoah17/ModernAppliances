# Modern Appliance Management System  

---

## Overview  
The Modern Appliance Management System is a console-based Java application designed to manage appliance data efficiently. Developed as part of Lab 0 for "Introduction to Java Programming in Eclipse," the system allows employees and customers to search, list, and purchase appliances from a predefined data file. The application supports four appliance types: refrigerators, vacuums, microwaves, and dishwashers.

---

## Key Features  
1. **Search and Filter:**  
   - Find appliances by brand (case-insensitive).  
   - Filter appliances by type (e.g., refrigerators, vacuums).  
2. **Checkout System:**  
   - Allows customers to purchase appliances by entering the item number.  
   - Validates stock availability before decrementing the appliance quantity.  
3. **Random Appliance Selection:**  
   - Displays a random list of appliances.  
4. **Data Persistence:**  
   - Loads appliance data from a `appliances.txt` file on startup.  
   - Saves changes back to the file upon exit.  
5. **Console Menu:**  
   - Intuitive text-based menu to interact with the system.

---

## System Design  
- **Class Hierarchy:**  
  - **Appliance (Abstract):** Base class with shared attributes like `itemNumber`, `brand`, `quantity`, etc.  
  - **Subclasses:**  
    - `Refrigerator`: Includes `numberOfDoors`, `height`, `width`.  
    - `Vacuum`: Includes `grade`, `batteryVoltage`.  
    - `Microwave`: Includes `capacity`, `roomType`.  
    - `Dishwasher`: Includes `feature`, `soundRating`.  
- **Key Methods:**  
  - Parse `appliances.txt` into a list of appliances.  
  - Purchase appliances by item number.  
  - Display random appliances or filter by brand/type.  
  - Save changes back to the file in the correct format.

---

## How to Run  
1. Open the project in Eclipse.  
2. Ensure you have the correct Java version configured (JDK).  
3. Run the `Main` class to start the application.  
4. Follow the console menu to interact with the system.  

---

## Credits  
This application was developed collaboratively by the following contributors:  
- **Noah Jobse**  
- **Christin Racicot**  
- **Parth Dave**  

Each team member contributed to various aspects of the design, development, and testing of the system.
