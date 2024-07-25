### 1 through disk application
- make boot 500 mb fat32
- make rootfs the rest ext4
 sudo mount /media/ahmed/sdcard/boot/    /dev/mmcblk0p1
 sudo mount /media/ahmed/sdcard/rootfs/  /dev/mmcblk0p2

### 2 make these files
- sudo touch config.txt
- sudo touch cmdline.txt


## 3 install toolchain
- sudo apt-get install gcc-aarch64-linux-gnu


## 4 build the uboot
- export ARCH=arm64
- export CROSS_COMPILE=/usr/bin/aarch64-linux-gnu-
- ls -l configs/ | grep rpi
- make make rpi_3_b_plus_defconfig
- make -j4

## 5 edit config.txt and add
```
kernel=u-boot.bin
enable_uart=1
device_tree=bcm2837-rpi-3-b-plus.dtb # download it

```
## 6 edit cmdline.txt and add
```
arm_64bit=1
console=serial0 
```

## 7 connect tty
```
sudo picocom -b 115200 /dev/ttyUSB0
```
## 8 ping
```
ping 192.168.1.50 

```
## 9 lab
```
echo $kernel_addr_r 
ls mmc 0:1 
fatload mmc 0:1 $kernel_addr_r zImage.txt
md $kernel_addr_r  

```