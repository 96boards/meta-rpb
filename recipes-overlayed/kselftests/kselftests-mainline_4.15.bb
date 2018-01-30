SUMMARY = "Linux Kernel Selftests (mainline)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRC_URI = "https://www.kernel.org/pub/linux/kernel/v4.x/linux-${PV}.tar.xz"
# Patches inappropriate or not yet merged by upstream
# Some patches may have been submitted to upstream
SRC_URI += "\
    file://0001-selftests-lib-allow-to-override-CC-in-the-top-level-Makefile.patch \
    file://0001-selftests-gpio-fix-build-error.patch \
    file://0001-selftests-gpio-use-pkg-config-to-determine-libmount-.patch \
    file://0001-selftests-net-use-LDLIBS-instead-of-LDFLAGS.patch \
    file://0002-selftests-seccomp-use-LDLIBS-instead-of-LDFLAGS.patch \
    file://0003-selftests-timers-use-LDLIBS-instead-of-LDFLAGS.patch \
"

SRC_URI[md5sum] = "0d701ac1e2a67d47ce7127432df2c32b"
SRC_URI[sha256sum] = "5a26478906d5005f4f809402e981518d2b8844949199f60c4b6e1f986ca2a769"

S = "${WORKDIR}/linux-${PV}"

export INSTALL_PATH = "/opt/kselftests/mainline"

require kselftests-common.inc
