SUMMARY = "Basic Wayland image with Weston"

IMAGE_FEATURES += "splash package-management debug-tweaks ssh-server-openssh hwcodecs tools-debug"

LICENSE = "MIT"

inherit core-image distro_features_check extrausers

# let's make sure we have a good image..
REQUIRED_DISTRO_FEATURES = "wayland pam systemd"

# FIXME : adding "mali450-userland" to MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS
#   if @bb.utils.contains('DISTRO_FEATURES', 'opengl',... might be a better
#   approach than defining the "mali450" MACHINE_FEATURE
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
