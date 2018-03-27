SUMMARY = "Linux Kernel Selftests (linux-kselftest next)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
PV = "4.15+git${SRCPV}"
SRCREV = "${AUTOREV}"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/next/linux-next.git;protocol=https;branch=master;name=kernel"
# Patches inappropriate or not yet merged by upstream
# Some patches may have been submitted to upstream
SRC_URI += "\
    file://0001-selftests-lib-allow-to-override-CC-in-the-top-level-Makefile.patch \
    file://0001-selftests-gpio-fix-build-error.patch \
    file://0001-selftests-gpio-use-pkg-config-to-determine-libmount-.patch \
    file://0001-selftests-net-use-LDLIBS-instead-of-LDFLAGS.patch \
    file://0002-selftests-next-seccomp-use-LDLIBS-instead-of-LDFLAGS.patch \
    file://0003-selftests-next-timers-use-LDLIBS-instead-of-LDFLAGS.patch \
"

S = "${WORKDIR}/git"

export INSTALL_PATH = "/opt/kselftests/next"

require kselftests-common.inc
