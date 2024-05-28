DESCRIPTION = "Simple init script for RPB initramfs image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://init.sh"

S = "${UNPACKDIR}"

do_install() {
        install -m 0755 ${UNPACKDIR}/init.sh ${D}/init
}

inherit allarch

FILES:${PN} += " /init "
