#!/bin/bash

if [ -f "/home/ahmed/.bashrc" ]; then
    export "HELLO=$(uname)" >> ~/.bashrc
    echo "$HELLO"
    export LOCAL=$(whoami)
    env | grep LOCAL
    echo $LOCAL
fi
gnome-terminal
