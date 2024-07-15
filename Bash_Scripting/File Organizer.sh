#!/bin/bash

# Check if a source directory is provided
if [ -z "$1" ]; then
    echo "Please, Provide a Directory"
    exit 1
fi

# Set the source directory
DIR_SOURCE="$1"

# Check if the source directory exists
if [ ! -d "$DIR_SOURCE" ]; then
    echo "Invalid directory."
    exit 1
fi

# Define the subdirectories
DIR_IMAGE="$DIR_SOURCE/images"
DIR_DOC="$DIR_SOURCE/documents"
DIR_OTHER="$DIR_SOURCE/others"

# Create the subdirectories if they don't exist
mkdir -p "$DIR_IMAGE" "$DIR_DOC" "$DIR_OTHER"

# Function to move files to the appropriate directory
move_file() {
    local file="$1"
    local extension="${file##*.}"
    case "$extension" in
        jpg|png|gif)
            mv "$file" "$DIR_IMAGE"
            ;;
        txt|doc|pdf)
            mv "$file" "$DIR_DOC"
            ;;
        *)
            mv "$file" "$DIR_OTHER"
            ;;
    esac
}

# Iterate over the files in the source directory
for file in "$DIR_SOURCE"/*; do
    if [ -f "$file" ]; then
        move_file "$file"
    fi
done

