#!/bin/bash

# Check if a source directory is provided
if [ -z "$1" ]; then
    echo "Please, Provide Source Directory"
    exit 1
fi

# Set the source directory
DIR_SRC="$1"

# Check if the source directory exists
if [ ! -d "$DIR_SRC" ]; then
    echo "Invalid directory "
    exit 1
fi

# Define the subdirectories
DIR_IMG="$DIR_SRC/images"
DIR_DOC="$DIR_SRC/documents"
DIR_OTHER="$DIR_SRC/others"

# Create the subdirectories if they don't exist
mkdir -p "$DIR_IMG" "$DIR_DOC" "$DIR_OTHER"

# Function to move files to the appropriate directory
move_file() {
    local file="$1"
    local extension="${file##*.}"
    case "$extension" in
        jpg|png|gif)
            mv "$file" "$DIR_IMG"
            ;;
        txt|doc|pdf)
            mv "$file" "$DIR_DOC"
            ;;
        .sh)
            #nothing
            ;;
        *)
            mv "$file" "$DIR_OTHER"
            ;;
    esac
}

# Iterate over the files in the source directory
for file in "$DIR_SRC"/*; do
    if [ -f "$file" ]; then
        move_file "$file"
    fi
done
