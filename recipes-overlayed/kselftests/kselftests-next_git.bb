SUMMARY = "Linux Kernel Selftests (linux-kselftest next)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
SRCREV = "43c6437453f2e777da306c81c1cb47612e21e972"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/shuah/linux-kselftest.git;protocol=https;branch=next;name=kernel"
# Patches inappropriate or not yet merged by upstream
# Some patches may have been submitted to upstream
SRC_URI += "\
    file://0001-selftests-lib-allow-to-override-CC-in-the-top-level-Makefile.patch \
    file://0001-selftests-breakpoints-re-order-TEST_GEN_PROGS-target.patch \
    file://0001-selftests-gpio-fix-build-error.patch \
    file://0001-selftests-gpio-use-pkg-config-to-determine-libmount-.patch \
    file://0001-selftests-net-use-LDLIBS-instead-of-LDFLAGS.patch \
    file://0002-selftests-seccomp-use-LDLIBS-instead-of-LDFLAGS.patch \
    file://0003-selftests-timers-use-LDLIBS-instead-of-LDFLAGS.patch;apply=no \
    file://0001-selftests-splice-fix-installation-for-splice-test.patch;apply=no \
"

S = "${WORKDIR}/git"

export INSTALL_PATH = "/opt/kselftests/next"

require kselftests-common.inc
