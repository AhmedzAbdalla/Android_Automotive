SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Recipe created by bitbake-layers"
LICENSE="MIT"
LIC_FILES_CHKSUM="file://home/ahmed/poky/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
FILESPATH:append=":${THISDIR}:"

python do_display_banner(){
    bb.plain("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
    bb.plain("!!!!!!!!!!!!!!Hello, My name is Ahmed Abdalla !!!!!!!!!!!!!!!!!")
    bb.plain("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
}

LIC_FILES_CHKSUM="file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI="git://github.com/FadyKhalil/DemoApp.git;protocol=https;branch=main"
SRCREV="720c663c5fd7246b4b42c5205d74db7d9784b5b2"
S = "${WORKDIR}/git"


inherit cmake

python do_configure() {
    import subprocess
    
    # Retrieve the source and build directories
    src = d.getVar('S')
    dst = d.getVar('B')

    # Ensure the build directory exists
    bb.utils.mkdirhier(dst)

    # Run CMake to configure the build
    subprocess.check_call(['cmake', '-S', src, '-B', dst])
}

do_compile(){
    oe_runmake -C ${B}

}
#
do_install(){
    mkdir -p ${D}/usr/bin
    cp ${B}/calculator ${D}/usr/bin
}

addtask display_banner before do_build
