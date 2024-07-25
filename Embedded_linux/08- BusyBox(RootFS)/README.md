### Busy Box


## menuconfig
```
git clone https://github.com/mirror/busybox.git
cd busybox
make menuconfig
# from settings choose -> Build static binary (no shared libs)

#there will be error so make this
sudo gedit .config -> search for this paramater
CONFIG_TC=n

make install
cd _install/bin
sudo cp busybox /home/ahmed/source/sdcard/rootfs

sudo ln -s busybox init
sudo ln -s busybox sh

setenv bootargs "8250.nr_uarts=1 console=ttyS0,115200 root=/dev/mmcblk0p2 rw init=/bin/sh" 
```

### steps
```
mkdir rootfs

cp -rp ./busybox/_install/* ./rootfs

# Copy every thing inside sysroot to you rootfs

cd rootfs


mkdir -p ./dev ./etc

touch ./etc/inittab

mkdir proc sys

mkdir mnt boot home media root srv

sudo chown -R root:root *

```

- vim ./etc/inittab
```

# inittab file 
::sysinit:/etc/init.d/rcS

# Start an "askfirst" shell on the console (whatever that may be)
ttyAMA0::askfirst:-/bin/sh

# Stuff to do when restarting the init process
::restart:/sbin/init
```


## etc/init.d/rcS
```
mkdir ./etc/init.d
cd ./etc/init.d
vim rcS    
```

add the following content to this script 
```
#!/bin/sh
# mount a filesystem of type `devtmpfs` to /dev
mount -t devtmpfs nodev /dev
# mount a filesystem of type `proc` to /proc
mount -t proc nodev /proc
# mount a filesystem of type `sysfs` to /sys
mount -t sysfs nodev /sys
```
then make it excutable
chmod 777 rcS

## copy files to sdcard/rootfs
sudo cp -r * ~/source/sdcard/rootfs/

## from uboot

editenv bootargs 
edit: console=tty0 console=ttyAMA0,38400n8 root=/dev/mmcblk0p2 rw init=/sbin/init





