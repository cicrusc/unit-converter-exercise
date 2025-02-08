![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Object_Oriented_Programming-FF6F00?style=for-the-badge&logo=java&logoColor=white)
![CLI](https://img.shields.io/badge/CLI-Console_Application-4EAA25?style=for-the-badge&logo=gnu-bash&logoColor=white)
---

# Unit Converter Exercise

A simple Kotlin-based unit converter that supports conversions between **Length**, **Weight**, and **Temperature** units.

## Features
- Convert between various units (e.g., meters, kilometers, kilograms, pounds, Celsius, Fahrenheit).
- Handles multiple aliases for each unit (e.g., "m" for meter, "kg" for kilogram).

## How to Use
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/unit-converter-exercise.git
   ```

2. **Compile the Kotlin code**:
   ```bash
   kotlinc Main.kt -include-runtime -d UnitConverter.jar
   ```

3. **Run the application**:
   ```bash
   java -jar UnitConverter.jar
   ```

4. **Input format**:
   Enter the conversion in the format:
   ```
   <value> <unit_from> to <unit_to>
   ```

   Example:
   ```
   10 meters to kilometers
   ```

5. **Exit**:
   Type `exit` to quit the program.

---
