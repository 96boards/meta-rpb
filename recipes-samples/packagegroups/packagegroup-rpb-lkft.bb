SUMMARY = "Organize packages to avoid duplication across all images"

inherit packagegroup

# contains basic dependencies for LKFT
RDEPENDS_packagegroup-rpb-lkft = "\
    git \
    haveged \
    kernel-selftests \
    kselftests-mainline \
    kselftests-next \
    ${@bb.utils.contains("TUNE_ARCH", "arm", "", "numactl", d)} \
    s-suite \
    tzdata \
    "
