SUMMARY = "Test apps that can be used in X11 Desktop"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "\
    packagegroup-rpb-tests-x11 \
    "
RDEPENDS_packagegroup-rpb-tests-x11 = "\
    gst-devtools \
    opengl-es-cts \
    parallel-deqp-runner \
    piglit \
    xserver-xorg-xvfb \
    "
