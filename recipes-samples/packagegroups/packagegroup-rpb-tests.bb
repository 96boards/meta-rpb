SUMMARY = "Organize test packages to avoid duplication across all images"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "\
    packagegroup-rpb-tests \
    packagegroup-rpb-tests-console \
    packagegroup-rpb-tests-python \
    "

# contains basic dependencies, that don't need graphics/display
RDEPENDS_packagegroup-rpb-tests = "\
    packagegroup-core-buildessential \
    packagegroup-rpb-tests-console \    
    packagegroup-rpb-tests-python \
    "

SUMMARY_packagegroup-rpb-tests-python = "Python support for running tests"
RDEPENDS_packagegroup-rpb-tests-python = "\
    python \
    python-misc \
    python-modules \
    python-numpy \
    python-pexpect \
    python-pip \
    python-pyyaml \
    "

SUMMARY_packagegroup-rpb-tests-console = "Test apps that can be used in console (no graphics)"
RDEPENDS_packagegroup-rpb-tests-console = "\
    alsa-utils-speakertest \
    ltp \
    stress-ng \
    ptest-runner \
    "
