SUMMARY = "Test apps that can be used in X11 Desktop"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "\
    packagegroup-rpb-tests-x11 \
    "
RDEPENDS_packagegroup-rpb-tests-x11 = "\
    chromium-chromedriver \
    piglit \
    "
