SUMMARY = "Organize test packages to avoid duplication across all images"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "\
    packagegroup-rpb-tests \
    packagegroup-rpb-tests-console \
    packagegroup-rpb-tests-python \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'packagegroup-rpb-tests-x11', '', d)} \
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
    python-pyyaml \
    "

SUMMARY_packagegroup-rpb-tests-console = "Test apps that can be used in console (no graphics)"
RDEPENDS_packagegroup-rpb-tests-console = "\
    ltp \
    "

SUMMARY_packagegroup-rpb-tests-x11 = "Test apps that can be used in X11 Desktop"
RDEPENDS_packagegroup-rpb-tests-x11 = "\
    chromium-chromedriver \
    piglit \
    "
