#!/bin/bash

# Define the username and group names
USERNAME="ahmed_abdalla"
PRIMARY_GROUPNAME="FamilyName"
SECONDARY_GROUPNAME="pgroup"
SECONDARY_GROUP_ID=30000

# Create the primary group if it doesn't exist
if grep -q "^$PRIMARY_GROUPNAME:" /etc/group; then
    echo "Group '$PRIMARY_GROUPNAME' already exists."
else
    sudo groupadd "$PRIMARY_GROUPNAME"
    echo "Group '$PRIMARY_GROUPNAME' created."
fi

# Create the secondary group if it doesn't exist
if grep -q "^$SECONDARY_GROUPNAME:" /etc/group; then
    echo "Group '$SECONDARY_GROUPNAME' already exists."
else
    sudo groupadd -g "$SECONDARY_GROUP_ID" "$SECONDARY_GROUPNAME"
    echo "Secondary group '$SECONDARY_GROUPNAME' with GID $SECONDARY_GROUP_ID created."
fi

# Create the user if it doesn't exist, and add to primary and secondary groups
if id -u "$USERNAME" >/dev/null 2>&1; then
    echo "User '$USERNAME' already exists."
else
    sudo useradd -m -g "$PRIMARY_GROUPNAME" -G "$SECONDARY_GROUPNAME" "$USERNAME"
    echo "User '$USERNAME' created and added to groups '$PRIMARY_GROUPNAME' and '$SECONDARY_GROUPNAME'."
fi

# Lock the user account
sudo passwd -l "$USERNAME"
echo "User '$USERNAME' has been locked."

# Display user information
echo "User information:"
id "$USERNAME"

# Display group information
echo "Primary group information:"
getent group "$PRIMARY_GROUPNAME"

echo "Secondary group information:"
getent group "$SECONDARY_GROUPNAME"

# Delete the user account
sudo userdel "$USERNAME"
echo "User '$USERNAME' has been deleted."

# Delete the groups
sudo groupdel "$PRIMARY_GROUPNAME"
echo "Primary group '$PRIMARY_GROUPNAME' has been deleted."

sudo groupdel "$SECONDARY_GROUPNAME"
echo "Secondary group '$SECONDARY_GROUPNAME' has been deleted."