#!/bin/bash

# Check if a source directory is provided
if [ -z "$1" ]; then
    echo "Usage: $0 <source_directory>"
    exit 1
fi

# Set the source directory
SOURCE_DIR="$1"

# Check if the source directory exists
if [ ! -d "$SOURCE_DIR" ]; then
    echo "The specified source directory does not exist."
    exit 1
fi

# Define the subdirectories
IMAGE_DIR="$SOURCE_DIR/images"
DOC_DIR="$SOURCE_DIR/documents"
OTHER_DIR="$SOURCE_DIR/others"

# Create the subdirectories if they don't exist
mkdir -p "$IMAGE_DIR" "$DOC_DIR" "$OTHER_DIR"

# Function to move files to the appropriate directory
move_file() {
    local file="$1"
    local extension="${file##*.}"
    case "$extension" in
        jpg|png|gif)
            mv "$file" "$IMAGE_DIR"
            ;;
        txt|doc|pdf)
            mv "$file" "$DOC_DIR"
            ;;
        .sh)
            #nothing
            ;;
        *)
            mv "$file" "$OTHER_DIR"
            ;;
    esac
}

# Iterate over the files in the source directory
for file in "$SOURCE_DIR"/*; do
    if [ -f "$file" ]; then
        move_file "$file"
    fi
done
