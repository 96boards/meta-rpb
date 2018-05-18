SUMMARY = "Small collection of benchmarks for storage I/O"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b529aaa6a0c50f15d29f89609b5c22f3"

SRCREV = "79698f645bfb28d0d966484ddad3a1efb562246d"
SRC_URI = "git://github.com/Algodev-github/S.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

DEPENDS = "rsync"
RDEPENDS_${PN} = "bash bc coreutils gawk g++ gcc fio libaio libaio-dev sysstat"

do_install() {
    mkdir -p ${D}/opt/
    cp -Rd --preserve=mode,timestamps ${S} ${D}/opt/S-suite
    rm -rf ${D}/opt/S-suite/.git
}

FILES_${PN} = "/opt/S-suite/"

INSANE_SKIP_${PN} += "dev-deps"
