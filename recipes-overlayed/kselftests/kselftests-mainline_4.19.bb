SUMMARY = "Linux Kernel Selftests (mainline)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "https://www.kernel.org/pub/linux/kernel/v4.x/linux-${PV}.tar.xz"
# Patches inappropriate or not yet merged by upstream
# Some patches may have been submitted to upstream
SRC_URI += "\
    file://0001-selftests-lib-allow-to-override-CC-in-the-top-level-Makefile.patch \
    file://0001-selftests-gpio-fix-build-error-next-20180906.patch \
    file://0001-selftests-gpio-use-pkg-config-to-determine-libmount-.patch \
    file://0001-selftests-net-use-LDLIBS-instead-of-LDFLAGS-next-20180416.patch \
    file://0002-selftests-seccomp-use-LDLIBS-instead-of-LDFLAGS.patch \
    file://0003-selftests-timers-use-LDLIBS-instead-of-LDFLAGS.patch \
"

SRC_URI[md5sum] = "740a90cf810c2105df8ee12e5d0bb900"
SRC_URI[sha256sum] = "0c68f5655528aed4f99dae71a5b259edc93239fa899e2df79c055275c21749a1"

S = "${WORKDIR}/linux-${PV}"

export INSTALL_PATH = "/opt/kselftests/mainline"

require kselftests-common.inc
