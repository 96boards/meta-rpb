SUMMARY = "Basic X11 graphics image"

IMAGE_FEATURES += "splash package-management debug-tweaks ssh-server-openssh hwcodecs x11 tools-debug"

LICENSE = "MIT"

inherit core-image distro_features_check extrausers

# let's make sure we have a good image..
REQUIRED_DISTRO_FEATURES = "x11 pam systemd"

# make sure we boot to desktop
# by default and without x11-base in IMAGE_FEATURES we default to multi-user.target
SYSTEMD_DEFAULT_TARGET = "graphical.target"

CORE_IMAGE_BASE_INSTALL += " \
    96boards-tools \
    alsa-utils-aplay \
    networkmanager \
    networkmanager-nmtui \
    coreutils \
    cpufrequtils \
    glmark2 \
    gps-utils \
    gpsd \
    gptfdisk \
    gstreamer1.0-plugins-bad-meta \
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-good-meta \
    ${@bb.utils.contains("LICENSE_FLAGS_WHITELIST", "commercial_gstreamer1.0-libav", "gstreamer1.0-libav", "", d)} \
    gtkperf \
    hostapd \
    htop \
    iptables \
    kernel-modules \
    mesa-demos \
    openbox \
    openbox-theme-clearlooks \
    sshfs-fuse \
    xf86-video-modesetting \
    xterm \
    ${@bb.utils.contains("MACHINE_FEATURES", "optee", "optee-test optee-client", "", d)} \
"

EXTRA_USERS_PARAMS = "\
useradd -p '' linaro; \
usermod -a -G video linaro; \
"
