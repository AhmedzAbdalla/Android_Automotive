#!/bin/bash

# Define the username and group name
USERNAME="ahmed_abdalla"
GROUPNAME="FamilyName"

# Check if the group exists; if not, create it
if grep -q "^$GROUPNAME:" /etc/group; then
    echo "Group '$GROUPNAME' already exists."
else
    sudo groupadd "$GROUPNAME"
    echo "Group '$GROUPNAME' created."
fi

# Check if the user exists; if not, create it and add to the group
if id -u "$USERNAME" >/dev/null 2>&1; then
    echo "User '$USERNAME' already exists."
else
    sudo useradd -m -g "$GROUPNAME" "$USERNAME"
    echo "User '$USERNAME' created and added to group '$GROUPNAME'."
fi

# Display user information
echo "User information:"
id "$USERNAME"

# Display group information
echo "Group information:"
getent group "$GROUPNAME"