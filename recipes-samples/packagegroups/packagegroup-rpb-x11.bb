SUMMARY = "Organize packages to avoid duplication across all images (with X11)"

inherit packagegroup features_check
REQUIRED_DISTRO_FEATURES = "x11"

PACKAGES += "${PN}-utils"

SUMMARY_${PN} = "Apps that can be used in X11 Desktop"
RDEPENDS_${PN} = "\
    96boards-artwork \
    feh-autostart \
    ffmpeg \
    glmark2 \
    gstreamer1.0-plugins-bad-meta \
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-good-meta \
    ${@bb.utils.contains("LICENSE_FLAGS_WHITELIST", "commercial_gstreamer1.0-libav", "gstreamer1.0-libav", "", d)} \
    gtkperf \
    kmscube \
    mesa-demos \
    openbox \
    openbox-theme-clearlooks \
    xf86-video-modesetting \
    xterm \
    "

SUMMARY_${PN}-utils = "Additional utils that can be used in X11 Desktop"
RDEPENDS_${PN}-utils = "\
    iceauth \
    sessreg \
    setxkbmap \
    xauth \
    xclock \
    xgamma \
    xlsfonts \
    xmag \
    xmessage \
    xrandr \
    xrdb \
    xrefresh \
    xsetmode \
    xsetroot \
"
