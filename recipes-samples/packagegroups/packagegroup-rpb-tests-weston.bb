SUMMARY = "Test apps that can be used in Weston Desktop"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "\
    packagegroup-rpb-tests-weston \
    "
RDEPENDS:packagegroup-rpb-tests-weston = "\
    gst-devtools \
    "
