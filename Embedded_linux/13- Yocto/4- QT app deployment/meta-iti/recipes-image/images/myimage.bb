
SUMMARY = "Ahmed Abdalla created this image recipe."

#IMAGE_FEATURES += "splash  hwcodecs "
#IMAGE_FEATURES += "splash hwcodecs"

IMAGE_FEATURES += "splash package-management x11-base x11-sato ssh-server-dropbear ssh-server-openssh hwcodecs"


IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_INSTALL:append = " apt bash git python3 python3-pip python3-setuptools linux-firmware-ralink linux-firmware-rtl8192cu linux-firmware-rtl8192su linux-firmware-rpidistro-bcm43455 linux-firmware-rtl8192ce"
IMAGE_INSTALL:append = " example pingapp openssh connman connman-client kernel-module-r8188eu dhcpcd coreutils iptables"
IMAGE_INSTALL:append = " linux-firmware-bcm43430 bluez5 pi-blaster i2c-tools  wpa-supplicant psplash-raspberrypi coreutils iw raspi-gpio"
#QT configuration
IMAGE_INSTALL:append = " make cmake"
IMAGE_INSTALL:append = " qtbase-tools qtbase qtdeclarative qtimageformats qtmultimedia qtquickcontrols2 qtquickcontrols qtbase-plugins cinematicexperience liberation-fonts qtbase-dev curl wget userland gstreamer1.0-plugins-bad qtsvg"


inherit core-image

#PACKAGE_EXCLUDE = "rust"

# I used these two lines to build core-image-miniml
#IMAGE_ROOTFS_SIZE ?= "8192"
#IMAGE_ROOTFS_EXTRA_SPACE:append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "", d)}"

# this found here /home/ahmed/poky/meta/recipes-sato/images
TOOLCHAIN_HOST_TASK:append = " nativesdk-intltool nativesdk-glib-2.0"
TOOLCHAIN_HOST_TASK:remove:task-populate-sdk-ext = " nativesdk-intltool nativesdk-glib-2.0"

QB_MEM = '${@bb.utils.contains("DISTRO_FEATURES", "opengl", "-m 512", "-m 256", d)}'
QB_MEM:qemuarmv5 = "-m 256"
QB_MEM:qemumips = "-m 256"

