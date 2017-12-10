SUMMARY = "Organize packages to avoid duplication across all images"

inherit packagegroup

# contains basic dependencies for LKFT
RDEPENDS_packagegroup-rpb-lkft = "\
    cpupower \
    git \
    kernel-selftests \
    kselftests-mainline \
    kselftests-next \
    libhugetlbfs-tests \
    mosh-server \
    ${@bb.utils.contains("TARGET_ARCH", "arm", "", "numactl", d)} \
    "
