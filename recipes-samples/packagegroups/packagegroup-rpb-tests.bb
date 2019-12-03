SUMMARY = "Organize test packages to avoid duplication across all images"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "\
    packagegroup-rpb-tests \
    packagegroup-rpb-tests-console \
    packagegroup-rpb-tests-python \
    packagegroup-rpb-tests-python3 \
    "

# contains basic dependencies, that don't need graphics/display
RDEPENDS_packagegroup-rpb-tests = "\
    packagegroup-core-buildessential \
    packagegroup-rpb-tests-console \    
    packagegroup-rpb-tests-python \
    packagegroup-rpb-tests-python3 \
    "

SUMMARY_packagegroup-rpb-tests-python = "Python support for running tests"
RDEPENDS_packagegroup-rpb-tests-python = "\
    python \
    python-misc \
    python-modules \
    python-pexpect \
    python-pip \
    python-pyyaml \
    "

SUMMARY_packagegroup-rpb-tests-python3 = "Python3 support for running tests"
RDEPENDS_packagegroup-rpb-tests-python3 = "\
    python3 \
    python3-misc \
    python3-modules \
    "

SUMMARY_packagegroup-rpb-tests-console = "Test apps that can be used in console (no graphics)"
RDEPENDS_packagegroup-rpb-tests-console = "\
    alsa-utils-speakertest \
    cpupower \
    git \
    libhugetlbfs-tests \
    ltp \
    s-suite \
    stress-ng \
    ptest-runner \
    usbutils \
    "
