### Building and Linking Static Libraries

#### Example Code
`add.c`:
```c
int add(int a, int b) {
    return a + b;
}
```
- sub.c:

```c
int sub(int a, int b) {
    return a - b;
}
```
- Build Process
Compile the source files into object files:

```sh
gcc -c add.c -o add.o
gcc -c sub.c -o sub.o

- Create a static library:

```sh
ar -rcs libmylib.a add.o sub.o
```
- -r: Replace or add files.
- -c: Create the archive.
- -s: Create an index (symbol table).

- Link the static library with the main application:

```sh
gcc main.c -L. -lmylib -o my_program
```

### Building and Linking Dynamic Libraries
Build Process
Compile the source files into position-independent code (PIC):

```sh
gcc -c -fPIC add.c -o add.o
gcc -c -fPIC sub.c -o sub.o
```

- Create a dynamic library:

```sh
gcc -shared -o libmylib.so add.o sub.o
```

- Link the dynamic library with the main application:

```sh
gcc main.c -L. -lmylib -o my_program
```

- Running Applications with Dynamic Libraries

#### Methods to Load Shared Libraries
- During Compilation: Embed the library path in the executable.
-rpath : for system loader , -L for compiler
```sh
gcc main.c -L. -lmylib -Wl,-rpath=.
```
- Using LD_LIBRARY_PATH: Set the environment variable to specify the library path.

```sh
export LD_LIBRARY_PATH=.
./my_program
```
## Example Makefile
```makefile
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
```