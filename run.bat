@echo off

:: Create the bin directory if it doesn't exist
if not exist bin mkdir bin

:: Compile the project
javac -d bin src\*.java

:: Run the game
java -cp bin RunGame

pause
