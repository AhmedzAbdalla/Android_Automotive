# Makefile for compiling and managing a project with multiple source files

# Compiler to use
CC=gcc

# Name of the final executable
projectName=task.exe

# Directories

SRC_DIRS := $(shell pwd)/src
DIR_BUILD := $(shell pwd)/Build
DIR_INCS := $(shell pwd)/include
DIR_Bin:= $(shell pwd)/Bin

# Collect all .c files from source directories
SRC_FILES := $(foreach dir, $(SRC_DIRS), $(wildcard $(dir)/*.c))


# Generate object file names from source file names
OBJ=$(patsubst %.c, %.o, $(SRC_FILES))


# Default target: build the executable
all:$(projectName)


# Rule to compile each .c file into an object file in DIR_BUILD
$(DIR_BUILD)/%.o : %.c
	$(CC) -c -I $(DIR_INCS) $< -o $@


# Rule to link all object files into the final executable in DIR_Bin
$(projectName): $(OBJ)
	$(CC) $(OBJ) -o $(projectName)
	mv $(OBJ) $(DIR_BUILD)
	mv $(projectName) $(DIR_Bin)

# Target to run the executable
run:$(DIR_Bin)/$(projectName)
	$(DIR_Bin)/$(projectName)
	
	
# Phony target to clean up generated files
.phony:clean
clean:
	@rm $(DIR_BUILD)/*.o
	@rm $(DIR_Bin)/*.exe
	@echo Files deleted successfully
