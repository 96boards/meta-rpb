PACKAGE_ARCH = "${MACHINE_ARCH}"

# kernel selftests dependencies
DEPENDS = "fuse libcap libcap-ng pkgconfig-native popt rsync-native util-linux \
    ${@bb.utils.contains("TARGET_ARCH", "arm", "", "numactl", d)} \
"

inherit kernel-arch

KSELFTESTS_ARGS = "-C ${S}/tools/testing/selftests INSTALL_PATH=${D}${INSTALL_PATH} CC="${CC}" LD="${LD}" ARCH=${ARCH}"

do_compile() {
    # Make sure to install the user space API used by some tests
    # but not properly declared as a build dependency
    ${MAKE} -C ${S} ARCH=${ARCH} headers_install
    ${MAKE} ${KSELFTESTS_ARGS}
}

do_install() {
    ${MAKE} ${KSELFTESTS_ARGS} install
    chown -R root:root ${D}
    # fixup run_kselftest.sh due to spurious lines starting by "make[1]:"
    sed -i '/^make/d' ${D}${INSTALL_PATH}/run_kselftest.sh
}

FILES_${PN} = "${INSTALL_PATH}"
FILES_${PN}-dbg = "${INSTALL_PATH}/*/.debug"

RDEPENDS_${PN} = "bash bc ethtool fuse-utils iproute2 libc6-utils ncurses sudo"

INSANE_SKIP_${PN} = "already-stripped"
