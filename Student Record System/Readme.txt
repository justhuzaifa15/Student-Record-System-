# Student Record System (Java Swing)

A simple **Student Management System** built with **Java Swing** that lets you:
- Add a student (with validation & duplicate ID prevention)
- View all students in a JTable
- Search student by ID
- Delete student by ID
- Exit safely

Records are stored in a text file named `Student.txt` in the app’s working directory.

## Screenshots
_Add screenshots or a GIF if you want (optional)._

## Requirements
- Java JDK 8+
- Git (only for cloning/contributing)

## How to Run (from source)
```bash
# compile
javac StudentRecordSystem1GUI.java
# run
java StudentRecordSystem1GUI
On first use, Student.txt is created automatically when you save a student.
Keep the executable working directory the same as the file location so the app can read/write Student.txt.