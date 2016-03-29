SUMMARY = "Basic X11 graphics image"

IMAGE_FEATURES += "splash package-management ssh-server-dropbear hwcodecs x11"

LICENSE = "MIT"

inherit core-image distro_features_check extrausers

# let's make sure we have a good image..
REQUIRED_DISTRO_FEATURES = "x11 pam systemd"

CORE_IMAGE_BASE_INSTALL += " \
    coreutils gptfdisk kernel-modules connman 96boards-tools \
    mesa-demos gtkperf openbox openbox-theme-clearlooks xterm xf86-video-modesetting \
"

EXTRA_USERS_PARAMS = "\
useradd -p '' linaro; \
"
