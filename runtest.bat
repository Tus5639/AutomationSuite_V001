
@echo off
cd C:\Users\HP\eclipse-workspace\automation_suite
set /p testClass=Enter the TestNG class to run (e.g., com.example.TestClass): 

mvn test -P%testClass%