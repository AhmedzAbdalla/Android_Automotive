# Makefile for compiling and managing a project with multiple source files

# Compiler to use
CC?=gcc

# Name of the final executable
projectName=task.exe

# List of directories to create if they do not exist

DIRS := Build Bin lib

.PHONY: create_dirs
create_dirs:
	@for dir in $(DIRS); do \
	    if [ ! -d "$$dir" ]; then \
	        mkdir -p "$$dir"; \
	        echo "Directory $$dir created."; \
	    else \
	        echo "Directory $$dir already exists."; \
	    fi; \
	done


SRC_DIRS := $(shell pwd)/src
DIR_APP := $(shell pwd)/app
DIR_BUILD := $(shell pwd)/Build
DIR_INCS := $(shell pwd)/include
DIR_Bin:= $(shell pwd)/Bin
DIR_LIB:= $(shell pwd)/lib


# Directories



# Collect all .c files from source directories
SRC_FILES := $(foreach dir, $(SRC_DIRS), $(wildcard $(dir)/*.c))
APP := $(DIR_APP)/main.c


# Generate object file names from source file names
OBJ=$(patsubst %.c, %.o, $(SRC_FILES))


# Default target: build the executable
all:$(projectName)


# Rule to compile each .c file into an object file in DIR_BUILD
$(DIR_BUILD)/%.o : %.c
	$(CC) -c -I $(DIR_INCS) $< -o $@


static:$(OBJ) create_dirs
	ar rcs libstaticlib.a $(OBJ)
	$(CC) $(APP) -I $(DIR_INCS) -o $(projectName) -L. -lstaticlib
	mv libstaticlib.a $(DIR_LIB)
	mv $(projectName) $(DIR_Bin)
	mv $(OBJ) $(DIR_BUILD)

dynamic:$(OBJ) create_dirs
	$(CC) -shared -o libdylib.so $(OBJ)
	$(CC) $(APP) -I $(DIR_INCS) -L. -ldylib -Wl,-rpath=$(shell pwd) -o $(projectName)
	mv libdylib.so $(DIR_LIB)
	mv $(projectName) $(DIR_Bin)
	mv $(OBJ) $(DIR_BUILD)

# Target to run the executable
run:$(DIR_Bin)/$(projectName)
	$(DIR_Bin)/$(projectName)
	
	
# Phony target to clean up generated files
.phony:clean
clean:
	@rm -rf $(DIR_Bin)
	@rm -rf $(DIR_BUILD)
	@rm -rf $(DIR_LIB)
	@echo Files deleted successfully
