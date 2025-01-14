# Task Manager App

## Overview
This is a simple Task Manager application built for Android using Kotlin. The app allows users to add tasks, categorize them, view tasks in a list, and delete tasks. It's an introductory Android app to demonstrate the use of **Fragments**, **RecyclerView**, **AlertDialog**, and **Spinner** components.

## Features Implemented

- **Display Task List**: A RecyclerView to display a list of tasks.
- **Add Tasks**: Add tasks using a FloatingActionButton (FAB) that opens an AlertDialog with a text input for the task and a Spinner for selecting the category (Work, Personal, Shopping, etc.).
- **Delete Tasks**: Delete a task with confirmation via an AlertDialog.
- **Categorize Tasks**: Tasks can be categorized (e.g., Work, Personal) using a Spinner when adding them.
- **RecyclerView with Adapter**: Use a RecyclerView to efficiently display the list of tasks and handle user interactions like deletions.

## Screenshots
![Screenshot 1](Screenshots/HomeScreen.png)
![Screenshot 2](Screenshots/AddTask)


## Prerequisites

To run this project, you need:
- Android Studio installed on your computer.
- Android SDK with **Kotlin** support.
- A physical or virtual device running **Android 5.0 (API 21)** or higher.

## How to Run the App

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/task-manager-app.git
