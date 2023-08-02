#!/bin/bash

create_files() {
  for i in {1..5}; do
    touch "file$i.txt"
  done
  echo "5 text files created."
}

add_hello_world() {
  for i in {1..5}; do
    echo "Hello world" >> "file$i.txt"
  done
  echo "Added 'Hello world' to all files."
}

replace_with_bash() {
  for i in {1..5}; do
    sed -i 's/world/bash/g' "file$i.txt"
  done
  echo "Replaced 'world' with 'bash' in all files."
}

show_menu() {
  PS3="Enter your choice (1/2/3/4): "
  options=("Create 5 txt files" "Add 'Hello world' to all files" "Replace 'world' with 'bash' in all files" "Exit")

  select choice in "${options[@]}"; do
    case "$REPLY" in
      1) create_files ;;
      2) add_hello_world ;;
      3) replace_with_bash ;;
      4) echo "Exiting..."; break ;;
      *) echo "Invalid choice. Please select a valid option." ;;
    esac
  done
}

show_menu