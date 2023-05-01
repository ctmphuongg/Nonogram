# CSCI 205 -Software Engineering and Design
Bucknell University  Lewisburg, PA

### Course Info
Instructor: Brian King
Semester: Spring 2023

## Team Information
Nga Vu - Chemical Engineering 2025 - Project Owner
Linh Nguyen - 
An 
List 1-2 relevant sentences about each member, including year and major

*Write a few sentences about your project. This can be done later, as you may not quite know all the details yet.*



## Project Overall Information
The project is called Nonogram. Nonogram is a game where you use digits to create a pattern of filled-in squares in the empty grid provided. Each number on the lines outside the grid represents a block of squares to be colored out in that row or column. (Restricted to maximum 4 colors). Players can choose the dimension of the matrix and have three hints.

## Project Package Structure
Our package strictly follow the MVC pattern. We have a Model folder that store all the code as the logic of the game and other folder serves as different scene. The GameManager package contains the code that controls the scene transition between the GUI packages  
model: Store all the logic of the game
Round: Manage the state of all square + state of a round (win/loss)
Puzzle Factory: Create puzzle
GameManager: Manage the master application for the whole program
SceneManager: Store and manage the use of all scenes
gameMenu: contain the view and controller for the menu
GameOver: contain the view and controller for the game result
Instruction Scene: contain the view and controller for the instruction
Nonogram Game: contain the view and controller for the nonogram puzzle
Round Scene: contain the view and controller for the round scene

## 3rd Party Libraries
javafx {
version = "19"
modules = [ 'javafx.controls', 'javafx.fxml', 'javafx.media']
}

## URL Video Presentation
• A URL to your video presentation (your URL should point to a video placed on Google Drive or Bucknell's media space. See details on the presentation.)
