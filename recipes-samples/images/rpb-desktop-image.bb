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
    packagegroup-rpb \
    packagegroup-rpb-x11 \
"

EXTRA_USERS_PARAMS = "\
useradd -p '' linaro; \
usermod -a -G video linaro; \
"
