SUMMARY = "Linux Kernel Selftests"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRC_URI = "https://www.kernel.org/pub/linux/kernel/v4.x/linux-${PV}.tar.xz"

# Patches from upstream linux-kselftest repository, queued in next branch
# https://git.kernel.org/pub/scm/linux/kernel/git/shuah/linux-kselftest.git/log/?h=next
SRC_URI += "\
    file://0001-selftests-futex-print-testcase-name-and-PASS-FAIL-ER.patch \
    file://0002-selftests-vm-Fix-test-for-virtual-address-range-mapp.patch \
    file://0003-selftests-sync-Skip-the-test-if-kernel-support-is-no.patch \
    file://0004-selftests-Make-test_harness.h-more-generally-availab.patch \
    file://0005-selftests-Cosmetic-renames-in-kselftest_harness.h.patch \
    file://0006-Documentation-dev-tools-Add-kselftest.patch \
    file://0007-Documentation-dev-tools-Use-reStructuredText-markups.patch \
    file://0008-selftests-Remove-the-TEST_API-wrapper-from-kselftest.patch \
    file://0009-Documentation-dev-tools-Add-kselftest_harness-docume.patch \
    file://0010-selftests-seccomp-Force-rebuild-according-to-depende.patch \
    file://0011-selftests-ftrace-Skip-full-glob-matching-filter-test.patch \
    file://0012-selftests-ftrace-Reduce-trace-buffer-checking-overhe.patch \
    file://0013-ftrace-kprobes-selftests-Check-kretprobe-maxactive-i.patch \
    file://0014-selftests-ftrace-Reset-ftrace-filter-on-older-kernel.patch \
    file://0015-selftests-ftrace-Add-instance-indication-in-test-log.patch \
    file://0016-selftests-ftrace-Use-top-level-available_filter_func.patch \
    file://0017-selftests-ftrace-Return-unsupported-if-it-detects-ol.patch \
    file://0018-ksefltest-MAINTAINERS-git-tree-entry-is-incorrect.patch \
    file://0019-kselftest-MAINTAINERS-git-tree-entry-update-files-an.patch \
    file://0020-selftests-kselftest_harness-Fix-compile-warning.patch \
    file://0021-kselftest-add-TAP13-conformant-versions-of-ksft_-fun.patch \
    file://0022-kselftest-membarrier-convert-to-TAP13-output.patch \
    file://0023-kselftest-breakpoints-convert-breakpoint_test-to-TAP.patch \
    file://0024-kselftest-breakpoints-convert-step_after_suspend_tes.patch \
    file://0025-kselftest-convert-get_size-to-use-stricter-TAP13-for.patch \
    file://0026-kselftest-make-ksft_exit_skip-output-a-reason-for-sk.patch \
    file://0027-kselftest-make-callers-of-ksft_exit_skip-output-the-.patch \
    file://0028-kselftest-membarrier-make-test-names-more-informativ.patch \
    file://0029-selftests-lib-Skip-tests-on-missing-test-modules.patch \
    file://0030-selftest-memfd-Makefile-Fix-build-error.patch \
    file://0031-selftest-intel_pstate-aperf-Use-LDLIBS-instead-of-LD.patch \
    file://0032-selftest-net-Makefile-Specify-output-with-OUTPUT.patch \
    file://0033-kselftest.rst-do-some-adjustments-after-ReST-convers.patch \
    file://0034-tools-testing-selftests-sysctl-Add-pre-check-to-the-.patch \
    file://0035-selftests-ftrace-Use-md5sum-to-take-less-time-of-che.patch \
    file://0036-selftests-typo-correction-for-memory-hotplug-test.patch \
    file://0037-selftests-check-hot-pluggagble-memory-for-memory-hot.patch \
    file://0038-selftests-check-percentage-range-for-memory-hotplug-.patch \
    file://0039-selftests-add-missing-test-name-in-memory-hotplug-te.patch \
    file://0040-selftests-fix-memory-hotplug-test.patch \
    file://0041-selftests-intel_pstate-add-.gitignore.patch \
    file://0042-selftests-capabilities-Fix-the-test_execve-test.patch \
"

# Patches inappropriate or not yet merged by upstream
# Some patches mayb have been submitted to upstream
SRC_URI += "\
    file://0001-selftests-lib-allow-to-override-CC-in-the-top-level-Makefile.patch \
    file://0001-selftests-breakpoints-re-order-TEST_GEN_PROGS-target.patch \
    file://0001-selftests-gpio-fix-build-error.patch \
    file://0001-selftests-gpio-use-pkg-config-to-determine-libmount-.patch \
    file://0001-selftests-net-use-LDLIBS-instead-of-LDFLAGS.patch \
    file://0002-selftests-seccomp-use-LDLIBS-instead-of-LDFLAGS.patch \
    file://0003-selftests-timers-use-LDLIBS-instead-of-LDFLAGS.patch \
    file://0001-selftests-splice-fix-installation-for-splice-test.patch \
"

SRC_URI[md5sum] = "fc454157e2d024d401a60905d6481c6b"
SRC_URI[sha256sum] = "a45c3becd4d08ce411c14628a949d08e2433d8cdeca92036c7013980e93858ab"

S = "${WORKDIR}/linux-${PV}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "fuse libcap libcap-ng pkgconfig-native popt rsync-native util-linux \
    ${@bb.utils.contains("TARGET_ARCH", "arm", "", "numactl", d)} \
"

inherit kernel-arch

EXTRA_OEMAKE += "V=1 -C ${S}/tools/testing/selftests INSTALL_PATH=${D}${bindir}/kselftests CC="${CC}" LD="${LD}""

do_compile () {
	# Make sure to install the user space API used by some tests
	# but not properly declared as a build dependency
	${MAKE} -C ${S} headers_install
	oe_runmake
}

do_install () {
	# Ignore install errors to workaround breakpoints build errors and
	# continue to install step_after_suspend_test
	oe_runmake --ignore-errors install
	chown -R root:root ${D}
	# fixup run_kselftest.sh due to spurious lines starting by "make[1]:"
	sed -i '/^make/d' ${D}${bindir}/kselftests/run_kselftest.sh
}

PACKAGE_BEFORE_PN = " \
	${PN}-bpf \
	${PN}-breakpoints \
	${PN}-capabilities \
	${PN}-cpufreq \
	${PN}-cpu-hotplug \
	${PN}-efivarfs \
	${PN}-exec \
	${PN}-firmware \
	${PN}-ftrace \
	${PN}-futex \
	${PN}-gpio \
	${PN}-intel-pstate \
	${PN}-ipc \
	${PN}-kcmp \
	${PN}-lib \
	${PN}-membarrier \
	${PN}-memfd \
	${PN}-memory-hotplug \
	${PN}-mount \
	${PN}-mqueue \
	${PN}-net \
	${PN}-nsfs \
	${PN}-powerpc \
	${PN}-pstore \
	${PN}-ptrace \
	${PN}-seccomp \
	${PN}-sigaltstack \
	${PN}-size \
	${PN}-splice \
	${PN}-static-keys \
	${PN}-sync \
	${PN}-sysctl \
	${PN}-timers \
	${PN}-user \
	${PN}-vm \
	${PN}-x86 \
	${PN}-zram \
"

FILES_${PN}-bpf = "${bindir}/kselftests/bpf"
FILES_${PN}-breakpoints = "${bindir}/kselftests/breakpoints"
FILES_${PN}-capabilities = "${bindir}/kselftests/capabilities"
FILES_${PN}-cpufreq = "${bindir}/kselftests/cpufreq"
FILES_${PN}-cpu-hotplug = "${bindir}/kselftests/cpu-hotplug"
FILES_${PN}-efivarfs = "${bindir}/kselftests/efivarfs"
FILES_${PN}-exec = "${bindir}/kselftests/exec"
FILES_${PN}-firmware = "${bindir}/kselftests/firmware"
FILES_${PN}-ftrace = "${bindir}/kselftests/ftrace"
FILES_${PN}-futex = "${bindir}/kselftests/futex"
FILES_${PN}-gpio = "${bindir}/kselftests/gpio"
FILES_${PN}-intel-pstate = "${bindir}/kselftests/intel_pstate"
FILES_${PN}-ipc = "${bindir}/kselftests/ipc"
FILES_${PN}-kcmp = "${bindir}/kselftests/kcmp"
FILES_${PN}-lib = "${bindir}/kselftests/lib"
FILES_${PN}-membarrier = "${bindir}/kselftests/membarrier"
FILES_${PN}-memfd = "${bindir}/kselftests/memfd"
FILES_${PN}-memory-hotplug = "${bindir}/kselftests/memory-hotplug"
FILES_${PN}-mount = "${bindir}/kselftests/mount"
FILES_${PN}-mqueue = "${bindir}/kselftests/mqueue"
FILES_${PN}-net = "${bindir}/kselftests/net"
FILES_${PN}-nsfs = "${bindir}/kselftests/nsfs"
FILES_${PN}-powerpc = "${bindir}/kselftests/powerpc"
FILES_${PN}-pstore = "${bindir}/kselftests/pstore"
FILES_${PN}-ptrace = "${bindir}/kselftests/ptrace"
FILES_${PN}-seccomp = "${bindir}/kselftests/seccomp"
FILES_${PN}-sigaltstack = "${bindir}/kselftests/sigaltstack"
FILES_${PN}-size = "${bindir}/kselftests/size"
FILES_${PN}-splice = "${bindir}/kselftests/splice"
FILES_${PN}-static-keys = "${bindir}/kselftests/static_keys"
FILES_${PN}-sync = "${bindir}/kselftests/sync"
FILES_${PN}-sysctl = "${bindir}/kselftests/sysctl"
FILES_${PN}-timers = "${bindir}/kselftests/timers"
FILES_${PN}-user = "${bindir}/kselftests/user"
FILES_${PN}-vm = "${bindir}/kselftests/vm"
FILES_${PN}-x86 = "${bindir}/kselftests/x86"
FILES_${PN}-zram = "${bindir}/kselftests/zram"
FILES_${PN}-dbg += "${bindir}/kselftests/*/.debug"

# FIXME bpf target is failing to build and need to be fixed:
# In file included from test_verifier.c:23:0:
# ../../../../usr/include/linux/bpf_perf_event.h:14:17: error: field 'regs' has incomplete type
#   struct pt_regs regs;
#                  ^~~~
# make[1]: *** [test_verifier] Error 1
ALLOW_EMPTY_${PN}-bpf = "1"

# FIXME net target builds most of the binaries, but reuseport_bpf_numa depends on libnuma,
# which is not availbale on ARM, failing entire test case
ALLOW_EMPTY_${PN}-net = "1"

RDEPENDS_${PN}-cpufreq += "bash"
RDEPENDS_${PN}-cpu-hotplug += "bash"
RDEPENDS_${PN}-efivarfs += "bash"
RDEPENDS_${PN}-futex += "bash ncurses"
RDEPENDS_${PN}-gpio += "bash"
RDEPENDS_${PN}-intel-pstate += "bash"
RDEPENDS_${PN}-memfd += "fuse-utils"
RDEPENDS_${PN}-memory-hotplug += "bash"
RDEPENDS_${PN}-net += "bash ethtool iproute2"
RDEPENDS_${PN}-vm += "bash sudo"
RDEPENDS_${PN}-zram += "bash bc"
RDEPENDS_${PN} += "bash \
	${PN}-bpf \
	${PN}-capabilities \
	${PN}-cpufreq \
	${PN}-cpu-hotplug \
	${PN}-efivarfs \
	${PN}-exec \
	${PN}-firmware \
	${PN}-ftrace \
	${PN}-futex \
	${PN}-gpio \
	${PN}-kcmp \
	${PN}-lib \
	${PN}-membarrier \
	${PN}-memfd \
	${PN}-memory-hotplug \
	${PN}-mount \
	${PN}-mqueue \
	${PN}-net \
	${PN}-nsfs \
	${PN}-pstore \
	${PN}-ptrace \
	${PN}-seccomp \
	${PN}-sigaltstack \
	${PN}-size \
	${PN}-splice \
	${PN}-static-keys \
	${PN}-sync \
	${PN}-sysctl \
	${PN}-timers \
	${PN}-user \
	${PN}-vm \
	${PN}-zram \
"

RDEPENDS_${PN}_append_aarch64 = " ${PN}-breakpoints ${PN}-ipc"
RDEPENDS_${PN}_append_x86 = " ${PN}-breakpoints ${PN}-intel-pstate ${PN}-ipc ${PN}-x86"
RDEPENDS_${PN}_append_x86-64 = " ${PN}-breakpoints ${PN}-intel-pstate ${PN}-ipc ${PN}-x86"
RDEPENDS_${PN}_append_powerpc = " ${PN}-powerpc"
RDEPENDS_${PN}_append_powerpc64 = " ${PN}-powerpc"

INSANE_SKIP_${PN} = "already-stripped"
