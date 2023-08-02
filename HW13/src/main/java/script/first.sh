#!/bin/bash

file_path_incomplete="todo_incomplete.txt"
file_path_completed="todo_completed.txt"
file_path_removed="removed_tasks.txt"

if [ ! -f "$file_path_incomplete" ]; then
    touch "$file_path_incomplete"
fi

if [ ! -f "$file_path_completed" ]; then
    touch "$file_path_completed"
fi

if [ ! -f "$file_path_removed" ]; then
    touch "$file_path_removed"
fi

function view_incomplete_todo_list {
    echo "Incomplete Todo List:"
    cat "$file_path_incomplete" | nl
}

function view_completed_todo_list {
    echo "Completed Todo List:"
    cat "$file_path_completed" | nl
}

function view_removed_tasks {
    echo "Removed Tasks:"
    cat "$file_path_removed" | nl
}

function add_task {
    echo "$1" >> "$file_path_incomplete"
}

function remove_task {
    task_number=$1
    task=$(sed -n "${task_number}p" "$file_path_incomplete")
    sed -i "${task_number}d" "$file_path_incomplete"
    echo "x $task" >> "$file_path_removed"
}

function complete_task {
    task_number=$1
    task=$(sed -n "${task_number}p" "$file_path_incomplete")
    sed -i "${task_number}d" "$file_path_incomplete"
    echo "x $task" >> "$file_path_completed"
}

while true; do
    echo "---------------"
    echo "Todo List App"
    echo "---------------"
    echo "1. View Incomplete Todo List"
    echo "2. View Completed Todo List"
    echo "3. View Removed Tasks"
    echo "4. Add Task"
    echo "5. Remove Task"
    echo "6. Mark Task as Completed"
    echo "7. Exit"
    echo "Enter your choice: "
    read choice

    case $choice in
        1)
            view_incomplete_todo_list
            ;;
        2)
            view_completed_todo_list
            ;;
        3)
            view_removed_tasks
            ;;
        4)
            echo "Enter task description: "
            read task_description
            echo "Enter priority (1 to 3): "
            read priority
            while [[ ! "$priority" =~ ^[1-3]$ ]]; do
                echo "Invalid priority. Please enter a number between 1 and 3."
                read priority
            done
            read -p "Enter a date (YYYY-MM-DD HH:MM:SS): " user_date
            add_task "($priority) $user_date $task_description"
            ;;
        5)
            view_incomplete_todo_list
            echo "Enter the task number to remove: "
            read task_number
            remove_task "$task_number"
            ;;
        6)
            view_incomplete_todo_list
            echo "Enter the task number to mark as completed: "
            read task_number
            complete_task "$task_number"
            ;;
        7)
            echo "Exiting Todo List App."
            exit 0
            ;;
        *)
            echo "Invalid choice. Please try again."
            ;;
    esac
done