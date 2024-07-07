#!/bin/bash

option1="Display date and time"
option2="list files in current directory"
option3="Display current disk usage"
option4="Exit"

PS3="Enter your selection"
select choice in "$option1" "$option2" "$option3" "$option4"
do
    case $REPLY in
        1) date
           ;;
        2) ls
           ;;
        3) df -h
           ;;
        4) exit
           ;;
        *) echo "$REPLY is not one of the choices."
           ;;
    esac
done 