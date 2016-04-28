SUMMARY = "Basic X11 graphics image"

IMAGE_FEATURES += "splash package-management debug-tweaks ssh-server-openssh hwcodecs x11 tools-debug"

LICENSE = "MIT"

inherit core-image distro_features_check extrausers

# let's make sure we have a good image..
REQUIRED_DISTRO_FEATURES = "x11 pam systemd"

CORE_IMAGE_BASE_INSTALL += " \
    coreutils gptfdisk kernel-modules connman connman-client 96boards-tools \
    mesa-demos gtkperf openbox openbox-theme-clearlooks xterm xf86-video-modesetting \
    sshfs-fuse hostapd iptables \
    alsa-utils-aplay gstreamer1.0-plugins-bad-meta gstreamer1.0-plugins-base-meta gstreamer1.0-plugins-good-meta \
"

EXTRA_USERS_PARAMS = "\
useradd -p '' linaro; \
"
