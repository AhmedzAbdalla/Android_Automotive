# Task 

## Desc.
    - try load from tftp 
    - if not load from mmc

## 1- make a uboot script
```

# U-Boot script to check availability of mmc and nic interfaces, and load files if mmc is available

setenv ipaddr 192.168.1.55
setenv serverip 192.168.1.44


# Define the interfaces
setenv interfaces "mmc nic"

# Loop over each interface
for interface in ${interfaces}; do
    if test "${interface}" = "mmc"; then
        # Check MMC interface
        mmc dev 0
        if test $? -eq 0; then
            echo "MMC device found"
            
            # Load the .dtb file to DRAM address fdt_addr_r
            fatload mmc 0:1 $fdt_addr_r vexpress-v2p-ca9.dtb
            if test $? -eq 0; then
                echo ".dtb file loaded successfully"
            else
                echo "Failed to load .dtb file"
            fi
            
            # Load the .img file to DRAM address kernel_addr_r
            fatload mmc 0:1 $kernel_addr_r zImage
            if test $? -eq 0; then
                echo ".img file loaded successfully"
            else
                echo "Failed to load .img file"
            fi
            
            exit
        else
            echo "MMC device not found"
        fi
    elif test "${interface}" = "nic"; then
        # Check NIC interface


        ping $serverip

        if test $? -eq 0; then

            echo "Loading from server... "
            
            exit
        else
            echo "NIC device not found"
        fi
    else
        echo "Unknown interface: ${interface}"
    fi
done

```

## 2- Convert script into binary so taht it can be loaded in DRAM
```
sudo mkimage -A arm -T script -d script.src ~/sdcard/boot/script.img
```



## 3- Modify U-Boot Environment Variables

```sh
setenv script_addr 0x60000000
setenv load_script 'fatload mmc 0:1 ${script_addr} /my_uboot_script.img'
setenv run_script 'source ${script_addr}'

setenv bootcmd 'run load_script; run run_script'
saveenv
script_addr: Address in DRAM where the script image will be loaded.
load_script: Command to load the script image from the MMC device.
run_script: Command to execute the loaded script.
bootcmd: Modified to run both the load_script and run_script commands during boot.

#then boot your qemu
sudo qemu-system-arm -M vexpress-a9 -m 128 -nographic -net nic -net tap,script=./mynetwork -kernel /home/ahmed/u-boot/u-boot -sd /home/ahmed/sd.img
- Then reboot the qemu
