Student Management System (SMS)
Project Overview
The Student Management System (SMS) is a Java-based console application designed to manage student records, including registration, module marks, and assessment grading. This system allows educational institutions to keep track of student information, academic performance, and generate reports.
Features

Check available seats for student registration
Register new students with unique IDs
Delete existing student records
Find students by their ID
Store student details to a file
Load student details from a file
View students sorted alphabetically by name
Record and manage module marks for students
Calculate grades based on module performance
Generate comprehensive student reports
Sort students by academic performance

Technical Details
The system is built using object-oriented programming principles in Java with three main classes:

Student: Manages student personal information
Module: Handles academic marks and grade calculations
StudentManagement: Controls the main program flow and user interface

Requirements

Java Runtime Environment (JRE) 11 or higher
Text file system access for data persistence

How to Use

Compile the Java files using javac StudentManagement.java
Run the program using java StudentManagement
Follow the on-screen menu options to interact with the system

Grading System
The system uses the following grading criteria:

Distinction: Average mark ≥ 80
Merit: Average mark ≥ 70
Pass: Average mark ≥ 40
Fail: Average mark < 40

Data Persistence
The system stores student data in a text file named "Records.txt" which can be loaded back into the system when needed.

Future Enhancements
Graphical user interface
Database integration
Advanced reporting features
User authentication system
