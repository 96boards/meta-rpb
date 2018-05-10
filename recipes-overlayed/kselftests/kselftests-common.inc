PACKAGE_ARCH = "${MACHINE_ARCH}"

# kernel selftests dependencies
DEPENDS = "fuse libcap libcap-ng pkgconfig-native popt rsync-native util-linux clang-native \
    ${@bb.utils.contains("TARGET_ARCH", "arm", "", "numactl", d)} \
"

inherit kernel-arch

KSELFTESTS_ARGS = "-i -C ${S}/tools/testing/selftests INSTALL_PATH=${D}${INSTALL_PATH} CC="${CC}" LD="${LD}" ARCH=${ARCH}"

do_compile() {
    # Make sure to install the user space API used by some tests
    # but not properly declared as a build dependency
    ${MAKE} -C ${S} ARCH=${ARCH} headers_install

    # Specifying sysroot may help Clang find appropriate headers
    export CLANG="clang --sysroot ${STAGING_DIR_TARGET}"
    ${MAKE} ${KSELFTESTS_ARGS}
}

do_install() {
    ${MAKE} ${KSELFTESTS_ARGS} install
    chown -R root:root ${D}
    # fixup run_kselftest.sh due to spurious lines starting by "make[1]:"
    sed -i '/^make/d' ${D}${INSTALL_PATH}/run_kselftest.sh
}

FILES_${PN} = "${INSTALL_PATH}"
# The bpf tests depend on that the object files are installed as well.
# OE wont throw an error if files do not exist.
FILES_${PN} += "${INSTALL_PATH}/bpf/*.o"
FILES_${PN}-dbg = "${INSTALL_PATH}/*/.debug /usr/src/debug/*"

RDEPENDS_${PN} = "bash bc ethtool fuse-utils iproute2 glibc-utils ncurses sudo"
RDEPENDS_${PN} =+ "python3-argparse python3-datetime python3-json python3-pprint python3-subprocess"
RDEPENDS_${PN}_append_x86 = " cpupower"
RDEPENDS_${PN}_append_x86-64 = " cpupower"

INSANE_SKIP_${PN} = "already-stripped"
# Ignore the QA error because kselftests/bpf/ requires object files
# ERROR message:
# ERROR: kselftests-mainline-4.14-r0 do_package_qa: QA Issue: Architecture did not match (Unknown (0), expected AArch64) on /work/hikey-linaro-linux/kselftests-mainline/4.14-r0/packages-split/kselftests-mainline/opt/kselftests/mainline/bpf/test_tcp_estats.o
ERROR_QA_remove = "arch"
