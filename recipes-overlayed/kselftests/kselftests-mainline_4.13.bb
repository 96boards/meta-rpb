SUMMARY = "Linux Kernel Selftests (mainline)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRC_URI = "https://www.kernel.org/pub/linux/kernel/v4.x/linux-${PV}.tar.xz"
# Patches inappropriate or not yet merged by upstream
# Some patches may have been submitted to upstream
SRC_URI += "\
    file://0001-selftests-lib-allow-to-override-CC-in-the-top-level-Makefile.patch \
    file://0001-selftests-breakpoints-re-order-TEST_GEN_PROGS-target.patch \
    file://0001-selftests-gpio-fix-build-error.patch \
    file://0001-selftests-gpio-use-pkg-config-to-determine-libmount-.patch \
    file://0001-selftests-net-use-LDLIBS-instead-of-LDFLAGS.patch;apply=no \
    file://0002-selftests-seccomp-use-LDLIBS-instead-of-LDFLAGS.patch;apply=no \
    file://0003-selftests-timers-use-LDLIBS-instead-of-LDFLAGS.patch \
    file://0001-selftests-splice-fix-installation-for-splice-test.patch \
"

SRC_URI[md5sum] = "ab1a2abc6f37b752dd2595338bec4e78"
SRC_URI[sha256sum] = "2db3d6066c3ad93eb25b973a3d2951e022a7e975ee2fa7cbe5bddf84d9a49a2c"

S = "${WORKDIR}/linux-${PV}"

export INSTALL_PATH = "/opt/kselftests/mainline"

require kselftests-common.inc
