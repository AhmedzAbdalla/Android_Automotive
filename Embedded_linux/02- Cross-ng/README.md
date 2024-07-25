## 1. Setting Up the Toolchain

- Before diving into cross-compilation, let's ensure you have the necessary prerequisites. 
- Run the following commands to install essential tools:

```sh
sudo apt-get install autoconf automake bison bzip2 cmake flex g++ gawk gcc gettext git gperf help2man libncurses5-dev libstdc++6 libtool libtool-bin make patch python3-dev rsync texinfo unzip wget xz-utils
```


- Now, let's clone the crosstool-NG repository, configure, build, and install it:
```sh
git clone https://github.com/crosstool-ng/crosstool-ng.git 
cd crosstool-ng 
./bootstrap
 ./configure --prefix=${PWD} 
make 
make install
```



## 2 Finding a Toolchain
- You can either use a pre-built toolchain or compile your own through crosstool-NG. For the latter, follow these steps:

```sh
ct-ng distclean 
ct-ng list-samples 
ct-ng arm-cortexa9_neon-linux-gnueabihf
ct-ng menuconfig
#enable make , strace and choose musl lib
#disable gdb 
#sudo init 3 -> if you want to make the compilation fster
#select the kernel version for which will compiled using the generated toolcahin
#select verion the linux version on which the cross-ng tool is running.
ct-ng build
```

- The output path for your toolchain will be ~/x-tools
    - ~/x-tools/arm-cortexa9_neon-linux-musleabihf

