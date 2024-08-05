# Adding my own software package to a Buildroot Linux package

### Go to this directory buildroot/package/
### Create a new directory to represent your directory
```
#suppose my package is called demoapp
mkdir demoapp
```
### create some files that are requied by buidroot
```
touch demoapp.cpp
touch demoapp.md
touch Makefile
touch Config.in
```
### Develop your application inside demoapp.cpp
```
#include <iostream>
#include <thread>

void printHelloWorld(const unsigned int l_num) {
    std::cout << "This is thread: " << l_num << std::endl;
}

int main() {
    // Create three threads
    std::thread t1(printHelloWorld, 1);
    std::thread t2(printHelloWorld, 2);
    std::thread t3(printHelloWorld, 3);

    // Wait for all threads to finish
    t1.join();
    t2.join();
    t3.join();

    return 0;
}
```

### create your Makefile
```
CC=gcc

.PHONY: clean
.PHONY: demoapp

Demoapp: demoapp.cpp
    $(CC) -o '$@' '$<'

clean:
    -rm demoapp
```

### at Config.in
- we will inform menuconfig to insert an option for our package
```
config BR2_PACKAGE_DEMOAPP
	bool "demoapp"
	help
	  feel free to do what you want.

	  https://github.com/AhmedzAbdalla/Android_Automotive

```
## This is very Important
- open the Config.in in this path /package
- source your package in it to make the option appear in the menuconfig
- insert at section <menu "Target packages">
```
source "package/demoapp/Config.in"
```
### Modify .mk file. it conatin help info about your package
```
################################################################################
#
# demoapp Ahmed Abdalla
#
################################################################################

DEMOAPP_VERSION = 1.0
DEMOAPP_SITE = ./package/demoapp/src
DEMOAPP_SITE_METHOD = local

define DEMOAPP_BUILD_CMDS
    $(MAKE) CC="$(TARGET_CC)" LD="$(TARGET_LD)" -C $(@D)
endef

define DEMOAPP_INSTALL_TARGET_CMDS
    $(INSTALL) -D -m 0755 $(@D)/hello $(TARGET_DIR)/usr/bin
endef

$(eval $(generic-package))
```

