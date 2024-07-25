## Emulator (QEMU) vs Simulator (Proteus)
- Simulator -> Just SW app doesn't take from processor
- Emulator  -> emulates the resources of the device 

## QEMU
- sudo apt install qemu-system-arm
- qemu-system-arm -M vexpress-a9 -sd .img

## Emulating SD card
- touch sd.img
- dd if=/dev/zero of=sd.img bs=1M count=1024
- cfdisk sd.img
- loop device -> make file as block storage
	- losetup -f --show--partscan sd.img
		- f = find  ,  show : which loop used , partscan : mount header of storage 

```sh
touch sd.img
dd if=/dev/zero of=sd.img bs=1M count=1024
cfdisk sd.img
	#choose dos , primary , bootable , type : FAT16 , write
	#New  primary   , type : Linux , write


sudo mkfs.vfat -F 16 -n boot /dev/loop40p1
sudo mkfs.ext4  -L rootfs /dev/loop40p2

sudo losetup -f --show --partscan sd.img

sudo mount /dev/loop18p1 sdcard/boot
sudo mount /dev/loop18p2 sdcard/rootfs

sudo umount /home/nahass/source/sdcard/boot
sudo umount /home/nahass/source/sdcard/rootfs

 
sudo losetup -d /dev/loopXX # -d deattached

```