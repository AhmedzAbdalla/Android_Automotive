#!/bin/bash

# Define the compilers
GCC_COMPILER="gcc"
ARM_COMPILER="neon-a9-gcc"

# Check for the correct number of arguments
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 {static|dynamic|clean} {gcc|arm}"
    exit 1
fi

# Set the compiler based on the provided argument
case $2 in
    gcc)
        export CC=$GCC_COMPILER
        ;;
    arm)
        export CC=$ARM_COMPILER
        ;;
    *)
        echo "Invalid compiler. Use gcc or arm."
        exit 1
        ;;
esac

# Run the corresponding Makefile target
case $1 in
    static)
        make static
        ;;
    dynamic)
        make dynamic
        ;;
    clean)
        make clean
        ;;
    *)
        echo "Invalid target. Use static, dynamic, or clean."
        exit 1
        ;;
esac