# Mon User Guide

## Overview

Mon is a simple command-line task manager that allows you to create, view, mark, unmark, and delete tasks, as well as manage tasks based on specific criteria such as deadlines, events, and schedules. The app supports reading from and writing to a file and also allows searching for tasks using keywords.

### Key Features:
- **Add Tasks**: Create tasks of various types (To-Do, Deadline, Event).
- **Mark/Unmark Tasks**: Track task completion status.
- **Delete Tasks**: Remove tasks from the list.
- **View All Tasks**: List all tasks in your task manager.
- **Search Tasks**: Find tasks based on keywords.
- **Scheduled Tasks**: View tasks scheduled for a specific date.

## Commands

### General Commands
1. **list** - Display all tasks in the list.
    - Example: 
      ```sh
      list
      ```

2. **todo <task>** - Add a new To-Do task.
    - Example:
      ```sh
      todo Finish homework
      ```

3. **deadline <task> /by <time>** - Add a task with a deadline.
    - Example:
      ```sh
      deadline Submit assignment /by 2025-03-01
      ```

4. **event <task> /from <start> /to <end>** - Add an event with a start and end time.
    - Example:
      ```sh
      event Team meeting /from 2025-02-27T10:00 /to 2025-02-27T12:00
      ```

5. **mark <number>** - Mark a task as completed. `<number>` is the task number (starting from 1).
    - Example:
      ```sh
      mark 1
      ```

6. **unmark <number>** - Unmark a task (revert its completion status). `<number>` is the task number (starting from 1).
    - Example:
      ```sh
      unmark 1
      ```

7. **delete <number>** - Delete a task by its number.
    - Example:
      ```sh
      delete 1
      ```

8. **find <keyword>** - Search for tasks that contain the specified keyword.
    - Example:
      ```sh
      find homework
      ```

9. **bye** - Exit the application and save all tasks to the data file.
    - Example:
      ```sh
      bye
      ```

10. **schedule <date>** - View tasks scheduled for a specific date. The date should be in the format `YYYY-MM-DD`.
    - Example:
      ```sh
      schedule 2025-03-01
      ```

### Special Cases
- **Invalid Command**: If you enter an invalid command, the system will provide a helpful error message with guidance on valid commands.
    - Example error message:
      ```sh
      Invalid command. Please use the correct format:
      - mark <number> – Mark an item as completed
      - unmark <number> – Unmark a completed item
      - list – Show all items
      - todo <item> – Add a to-do item
      - deadline <task> /by <time> – Add a task with a deadline
      - event <task> /from <start> /to <end> – Add an event with a start and end time
      - schedule <date> – Displays all tasks scheduled for the specified date (format: YYYY-MM-DD)
      - find <keyword> - Find all matching tasks
      ```

- **Invalid Task Number**: When trying to mark, unmark, or delete a task, the task number must be valid (i.e., a positive number that refers to a task in the list). An error message will be shown if you try to refer to a non-existent task.
    - Example error message:
      ```sh
      Please enter a valid task ID. There are currently 2 items in the list.
      ```

### Task Formats
- **To-Do**: A basic task that needs to be done.
    - Example: 
      ```sh
      [T] [ ] Finish homework
      ```
    - Where:
      - `[T]` indicates this is a To-Do task.
      - `[ ]` indicates the task is not completed.

- **Deadline**: A task with a deadline.
    - Example:
      ```sh
      [D] [ ] Submit assignment /by 2025-03-01
      ```
    - Where:
      - `[D]` indicates this is a Deadline task.
      - `/by 2025-03-01` indicates the task deadline.

- **Event**: A task with a start and end time.
    - Example:
      ```sh
      [E] [ ] Team meeting /from 2025-02-27 10:00 /to 2025-02-27 12:00
      ```
    - Where:
      - `[E]` indicates this is an Event task.
      - `/from 2025-02-27 10:00 /to 2025-02-27 12:00` specifies the event’s timing.
