# install Linux Kernel for raspberry pi for 64-bit system
- Download linux v6.5 from raspberry repo on github

```sh
make defconfig

make menuconfig
# configs
#Enable devtmpfs // enable all points for tmpfs
#Change kernel compression to XZ //search for XZ
#make sure to enable booti
export CROSS_COMPILE=/usr/bin/aarch64-linux-gnu-

export ARCH=arm64

make -j11

output : arch/arm/boot

```
# load kernel to dram using tftp

- copy image and .dtb files to /srv/tftp
- create 2 variables in uboot and store this value in it

```sh
setenv load=tftp 0x50000 Image; tftp 0x00004000 bcm2837-rpi-3-b-plus.dtb

setenv boot=booti 0x50000 - 0x4000

```