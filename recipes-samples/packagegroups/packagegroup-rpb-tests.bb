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
RDEPENDS:packagegroup-rpb-tests = "\
    packagegroup-core-buildessential \
    packagegroup-rpb-tests-console \    
    packagegroup-rpb-tests-python3 \
    "

SUMMARY:packagegroup-rpb-tests-python3 = "Python3 support for running tests"
RDEPENDS:packagegroup-rpb-tests-python3 = "\
    python3 \
    python3-misc \
    python3-modules \
    python3-pexpect \
    python3-pip \
    python3-pyyaml \
    "

SUMMARY:packagegroup-rpb-tests-console = "Test apps that can be used in console (no graphics)"
RDEPENDS:packagegroup-rpb-tests-console = "\
    alsa-utils-alsaucm \
    alsa-utils-speakertest \
    ${@oe.utils.conditional("PREFERRED_PROVIDER_virtual/kernel", "linux-dummy", "", "cpupower", d)} \
    cryptsetup \
    git \
    i2c-tools \
    igt-gpu-tools-tests \
    libdrm-tests \
    libgpiod-tools \
    ltp \
    net-snmp \
    s-suite \
    stress-ng \
    ptest-runner \
    usbutils \
    "
RDEPENDS:packagegroup-rpb-tests-console:remove:libc-musl = "igt-gpu-tools-tests"
