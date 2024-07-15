#!/bin/bash

# Check if the .bashrc file exists in the user's home directory
if [ -f "$HOME/.bashrc" ]; then
  # Append environment variables to the .bashrc file
  echo "export HELLO=\$HOSTNAME" >> "$HOME/.bashrc"
  echo "LOCAL=\$(whoami)" >> "$HOME/.bashrc"
  echo "export LOCAL" >> "$HOME/.bashrc"

  # Open a new terminal
  gnome-terminal &

  echo ".bashrc file found and updated. A new terminal has been opened."
else
  echo ".bashrc file does not exist in the home directory."
fi
