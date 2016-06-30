SUMMARY = "Basic Wayland image with Weston"

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
    coreutils gptfdisk kernel-modules connman connman-client 96boards-tools \
    weston weston-examples clutter-1.0-examples \
    sshfs-fuse hostapd iptables \
    alsa-utils-aplay gstreamer1.0-plugins-bad-meta gstreamer1.0-plugins-base-meta gstreamer1.0-plugins-good-meta \
    ${@bb.utils.contains("MACHINE_FEATURES", "optee", "optee-test optee-client", "", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "mali450", "mali450-userland", "", d)} \
"

EXTRA_USERS_PARAMS = "\
useradd -p '' linaro; \
usermod -a -G weston-launch linaro; \
"
