SUMMARY = "Organize packages to avoid duplication across all images (with X11)"

inherit packagegroup distro_features_check
REQUIRED_DISTRO_FEATURES = "x11"

SUMMARY_packagegroup-rpb-x11 = "Apps that can be used in X11 Desktop"
RDEPENDS_packagegroup-rpb-x11 = "\
    chromium \
    glmark2 \
    gps-utils \
    gpsd \
    gstreamer1.0-plugins-bad-meta \
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-good-meta \
    ${@bb.utils.contains("LICENSE_FLAGS_WHITELIST", "commercial_gstreamer1.0-libav", "gstreamer1.0-libav", "", d)} \
    gtkperf \
    mesa-demos \
    openbox \
    openbox-theme-clearlooks \
    xf86-video-modesetting \
    xterm \
    "
