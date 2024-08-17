# Raspberry PI Image with Yocto
## task: Turn a led on/off based of the result of pinging any ip address
## You can the video of task - [here](https://drive.google.com/file/d/19ZNtNy4UI9Aw8b2WVQRmV1WqVEsMauwK/view?usp=sharing)

### We already have our build enviroment ready since the last task
- we need to add some modifications in order to complete this task
    1- we will create distro layer and name as almaza 
    ```
        ├── conf
        │   ├── bblayers.conf
        │   ├── distro
        │   │   └── almaza.conf
        │   └── layer.conf
        ├── COPYING.MIT
        ├── README
        └── recipes-example
            └── example
                ├── example_0.1.bb
                ├── example_%.bbappend
                ├── pingapp_0.1.bb
                └── task_app.sh

        4 directories, 9 files
    ```
    2-  add the meta-raspberrypi access the bblayers.conf
        ```
        # POKY_BBLAYERS_CONF_VERSION is increased each time build/conf/bblayers.conf
        # changes incompatibly
        POKY_BBLAYERS_CONF_VERSION = "2"

        BBPATH = "${TOPDIR}"
        BBFILES ?= ""


        #BBLAYERS[doc] = "Lists the layers to enable during the build. This variable is defined in      the bblayers.conf configuration file."
        BBLAYERS ?= " \
          /home/ahmed/poky/meta \
          /home/ahmed/poky/meta-poky \
          /home/ahmed/poky/meta-yocto-bsp \
          /home/ahmed/meta-raspberrypi \
          /home/ahmed/meta-iti \
          /home/ahmed/meta-openembedded/meta-networking \
          /home/ahmed/meta-openembedded/meta-python \


          "
        
        ```
    3- Configure our distro to be customized as we need then we will create under conf a    directory called "distro" and under distro we will add file "almaza.conf"

        ```
         include meta-poky/conf/distro/poky.conf


        DISTRO = "almaza"
        DISTRO_NAME = "almaza (Ahmed Abdalla)"
        #DISTRO_VERSION = "3.4+snapshot-${METADATA_REVISION}"
        DISTRO_VERSION = "1.0"
        DISTRO_CODENAME = "kirkstone"

        MAINTAINER = "ahmed.m.abdalla650@gmail.com"

        ```
    4- Now we need to add all our packages that will be inastaled to our rootfs
        - This will take place in the local.conf file in the build environment
    5- this is very important: we must select our target machine
    ```
    MACHINE ??= "raspberrypi3"
    ```
    ```
    DISTRO_FEATURES:append = " bluez5 bluetooth wifi ipv4 systemd"

    IMAGE_INSTALL:append = " apt bash git python3 \
                            python3-pip python3-setuptools \
                            linux-firmware-ralink \
                            linux-firmware-rtl8192culinux-firmware-rtl8192su \
                            linux-firmware-rpidistro-bcm43455 \
                            linux-firmware-rtl8192ce"

    IMAGE_INSTALL:append = " pingapp openssh \
                            connman connman-client kernel-module-r8188eu \
                            dhcpcd    coreutils iptables"

    IMAGE_INSTALL:append = " linux-firmware-bcm43430 bluez5 pi-blaster i2c-tools  \
                            wpa-supplicant    psplash-raspberrypi coreutils iw raspi-gpio"


    ENABLE_UART = "1"

    INHERIT = "rm_work"
    ```

    6- Our script will look like that
    ```
        #!/bin/bash


    RED_LED_PIN=2
    GREEN_LED_PIN=3

    # Function to display usage information
    show_usage() {
        echo "Usage: $0 <IP_ADDRESS>"
        exit 1
    }

    # Function to check if the provided IP address is valid
    validate_ip() {
        local ip="$1"
        if ! [[ "$ip" =~ ^[0-9]+\.[0-9]+\.[0-9]+\.[0-9]+$ ]]; then
            echo "Error: Invalid IP address format."
            show_usage
        fi
    }

    # Ensure an IP address is provided
    if [[ -z "$1" ]]; then
        echo "Error: No IP address provided."
        show_usage
    fi

    IP_ADDRESS="$1"
    validate_ip "$IP_ADDRESS"

    # Initialize GPIO pins
    initialize_gpio() {
        for pin in "$RED_LED_PIN" "$GREEN_LED_PIN"; do
            if [[ ! -d "/sys/class/gpio/gpio$pin" ]]; then
                echo "$pin" > /sys/class/gpio/export 2>/dev/null
                if [[ $? -ne 0 ]]; then
                    echo "Error: Failed to export GPIO pin $pin."
                    exit 1
                fi
                echo "out" > /sys/class/gpio/gpio$pin/direction 2>/dev/null
                if [[ $? -ne 0 ]]; then
                    echo "Error: Failed to set direction for GPIO pin $pin."
                    exit 1
                fi
            fi
        done
    }

    # Cleanup GPIO pins on exit
    release_gpio() {
        for pin in "$RED_LED_PIN" "$GREEN_LED_PIN"; do
            if [[ -d "/sys/class/gpio/gpio$pin" ]]; then
                echo "$pin" > /sys/class/gpio/unexport 2>/dev/null
            fi
        done
    }

    # Control LEDs based on the reachability of the IP address
    manage_leds() {
        if ping -c 1 "$IP_ADDRESS" &> /dev/null; then
            echo "1" > /sys/class/gpio/gpio$GREEN_LED_PIN/value 2>/dev/null
            echo "0" > /sys/class/gpio/gpio$RED_LED_PIN/value 2>/dev/null
        else
            echo "0" > /sys/class/gpio/gpio$GREEN_LED_PIN/value 2>/dev/null
            echo "1" > /sys/class/gpio/gpio$RED_LED_PIN/value 2>/dev/null
        fi
    }

    # Main loop
    monitor_network() {
        while true; do
            manage_leds
            sleep 10  # Adjust as needed
        done
    }


    trap release_gpio EXIT

    initialize_gpio
    monitor_network

    ```
    7- Now, we should create our recipe to do the magic 
    ```
    SUMMARY = "bitbake-layers recipe"
    DESCRIPTION = "Recipe created by bitbake-layers"
    LICENSE="MIT"
    LIC_FILES_CHKSUM="file://home/ahmed/poky/meta/files/common-licenses/MIT;    md5=0835ade698e0bcf8506ecda2f7b4f302"
    FILESPATH:append=":${THISDIR}:"

    python do_display_banner(){
        bb.plain("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        bb.plain("!!!!!!!!!!!!!! Hello, My name is Ahmed Abdalla !!!!!!!!!!!!!!!!!")
        bb.plain("!!!!!!!!!!!!!! This is ping application !!!!!!!!!!!!!!!!!")
        bb.plain("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
    }

    LIC_FILES_CHKSUM="file://${COREBASE}/meta/files/common-licenses/MIT;    md5=0835ade698e0bcf8506ecda2f7b4f302"


    SRC_URI="file://task_app.py"
    PYPI_PACKAGE = "RPi.GPIO"

    do_install(){
        mkdir -p ${D}${bindir}
        cp ${WORKDIR}/task_app.py ${D}/usr/bin
    }



    # Ensure Python runtime dependency
    DEPENDS = "python3"
    RDEPENDS:${PN} = "python3"

    addtask display_banner before do_build

    ```


## Configuring WiFi
1- After building the image and flashing it to the sdcard got dir /etc
2- create a file called "wpa_supplicant.conf" and fill it with this 
```
ctrl_interface=/var/run/wpa_supplicant
ctrl_interface_group=0
update_config=1
```
3- after booting your machine. To disable RF-kill for the wlan0 interface on a Raspberry Pi, you can use the following commands:
```
#List all devices affected by RF-kill:
sudo rfkill list

#Unblock Wi-Fi (disable RF-kill for wlan0):
sudo rfkill unblock wifi

```
4- Assign an IP address to wlan0
```
ip addr add 192.168.1.100/24 dev wlan0
```
5- Bring the interface up (if it's not already up):
```
sudo ip link set wlan0 up
```
6- Follow the guide at this link to add network
[link](https://wiki.archlinux.org/title/Wpa_supplicant)


7- edit this file /etc/resolv.conf with the following
```
nameserver 8.8.8.8
nameserver 8.8.4.4
```

# Now, Enjoy