SUMMARY = "Basic Wayland image with Westeros"

IMAGE_FEATURES += "splash package-management debug-tweaks ssh-server-openssh hwcodecs tools-debug"

LICENSE = "MIT"

inherit core-image distro_features_check extrausers

# let's make sure we have a good image..
REQUIRED_DISTRO_FEATURES = "wayland pam systemd"

# FIXME Mali is currently added as a machine feature
# meta-qcom is using a different approach with
#   MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS
#   ${@bb.utils.contains('DISTRO_FEATURES', 'wifi', 'wcnss-config', '', d)}
# http://git.yoctoproject.org/cgit/cgit.cgi/meta-qcom/tree/conf/machine/dragonboard-410c.conf#n37
CORE_IMAGE_BASE_INSTALL += " \
    96boards-tools \
    alsa-utils-aplay \
    clutter-1.0-examples \
    networkmanager \
    networkmanager-nmtui \
    coreutils \
    gptfdisk \
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-good-meta \
    ${@bb.utils.contains("LICENSE_FLAGS_WHITELIST", "commercial_gstreamer1.0-libav", "gstreamer1.0-libav", "", d)} \
    hostapd \
    iptables \
    kernel-modules \
    sshfs-fuse \
    ${@bb.utils.contains("MACHINE_FEATURES", "optee", "optee-test optee-client", "", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "mali450", "mali450-userland", "", d)} \
    westeros \
"
