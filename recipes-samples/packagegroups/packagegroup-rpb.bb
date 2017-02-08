SUMMARY = "Organize packages to avoid duplication across all images"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "\
    packagegroup-rpb \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'packagegroup-rpb-x11', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'packagegroup-rpb-weston', '', d)} \
    "

# contains basic dependencies, that can work without graphics/display
RDEPENDS_packagegroup-rpb = "\
    96boards-tools \
    alsa-utils-aplay \
    coreutils \
    cpufrequtils \
    gptfdisk \
    gps-utils \
    gpsd \
    gstreamer1.0-plugins-bad-meta \
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-good-meta \
    ${@bb.utils.contains("LICENSE_FLAGS_WHITELIST", "commercial_gstreamer1.0-libav", "gstreamer1.0-libav", "", d)} \
    hostapd \
    htop \
    iptables \
    kernel-modules \
    networkmanager \
    networkmanager-nmtui \
    ${@bb.utils.contains("MACHINE_FEATURES", "optee", "optee-test optee-client", "", d)} \
    rsync \
    sshfs-fuse \
    "

SUMMARY_packagegroup-rpb-x11 = "Apps that can be used in X11 Desktop"
RDEPENDS_packagegroup-rpb-x11 = "\
    glmark2 \
    gtkperf \
    mesa-demos \
    openbox \
    openbox-theme-clearlooks \
    xf86-video-modesetting \
    xterm \
    "

SUMMARY_packagegroup-rpb-weston = "Apps that can be used in Weston Desktop"
RDEPENDS_packagegroup-rpb-weston = "\
    clutter-1.0-examples \
    weston \
    weston-examples \
    weston-init \
    ${@bb.utils.contains("MACHINE_FEATURES", "mali450", "mali450-userland", "", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "sgx", "libgbm ti-sgx-ddk-km ti-sgx-ddk-um pru-icss", "", d)} \
    "

