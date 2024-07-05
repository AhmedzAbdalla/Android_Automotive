CC=gcc


projectName=task.exe

SRC_DIRS := $(shell pwd)/src
DIR_BUILD := $(shell pwd)/Build
DIR_INCS := $(shell pwd)/include
DIR_Bin:= $(shell pwd)/Bin


SRC_FILES := $(foreach dir, $(SRC_DIRS), $(wildcard $(dir)/*.c))

#SRC_FILES := $(subst /,\,$(SRC_FILES))
#DIR_BUILD := $(subst /,\,$(DIR_BUILD))

OBJ=$(patsubst %.c, %.o, $(SRC_FILES))

all:$(projectName)
	echo $(SRC_FILES)
#	@echo $(DIR_BUILD)



$(DIR_BUILD)/%.o : %.c
	$(CC) -c -I $(DIR_INCS) $< -o $@


$(projectName): $(OBJ)
	$(CC) $(OBJ) -o $(projectName)
	mv $(OBJ) $(DIR_BUILD)
	mv $(projectName) $(DIR_Bin)
	

.phony:clean
clean:
	rm *.o
	rm *.exe