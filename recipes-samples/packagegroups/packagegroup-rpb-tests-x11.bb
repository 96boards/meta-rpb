SUMMARY = "Test apps that can be used in X11 Desktop"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "\
    packagegroup-rpb-tests-x11 \
    "
RDEPENDS:packagegroup-rpb-tests-x11 = "\
    gst-devtools \
    libegl-dev \
    opengl-es-cts \
    parallel-deqp-runner \
    piglit \
    x11perf \
    xdpyinfo \
    xlsatoms \
    xlsclients \
    "
#    xserver-xorg-xvfb
