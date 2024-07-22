# U-Boot Script Execution Guide

This guide provides step-by-step instructions to create, convert, and execute a U-Boot script that checks for MMC and NIC interfaces, and loads files if MMC is available.

## Steps to Create and Execute the U-Boot Script

### 1. Create the U-Boot Script

Save the following content to `my_uboot_script.scr`:

```sh
# U-Boot script to check availability of mmc and nic interfaces, and load files if mmc is available

# Define the interfaces
# U-Boot script to check availability of mmc and nic interfaces, and load files if mmc is available

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

        setenv serverip 192.168.1.44

        ping $serverip

        if test $? -eq 0; then

            tftp $fdt_addr_r vexpress-v2p-ca9.dtb
            if test $? -eq 0; then
                echo ".dtb file loaded successfully"
            else
                echo "Failed to load .dtb file"
            fi

            tftp $kernel_addr_r zImage
            if test $? -eq 0; then
                echo ".img file loaded successfully"
            else
                echo "Failed to load .img file"
            fi
            
            exit
        else
            echo "NIC device not found"
        fi
    else
        echo "Unknown interface: ${interface}"
    fi
done
```
### 2. Convert the Script to a U-Boot Image
Use the mkimage tool to create a U-Boot script image:

```sh
mkimage -A arm -T script -C none -n 'My U-Boot Script' -d my_uboot_script.scr my_uboot_script.img
```

### 3. Copy the Script Image to MMC

Copy my_uboot_script.img to a known location on your MMC device, such as the root directory of the first partition (e.g., mmc 0:1).

### 4. Modify U-Boot Environment Variables
Configure U-Boot to load and execute the script image automatically:

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
```








