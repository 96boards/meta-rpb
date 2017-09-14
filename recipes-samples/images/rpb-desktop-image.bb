require rpb-console-image.bb

SUMMARY = "Basic X11 graphics image"

IMAGE_FEATURES += "x11"
# let's make sure we have a good image..
REQUIRED_DISTRO_FEATURES += "x11"

# make sure we boot to desktop
# by default and without x11-base in IMAGE_FEATURES we default to multi-user.target
SYSTEMD_DEFAULT_TARGET = "graphical.target"

CORE_IMAGE_BASE_INSTALL += " \
    packagegroup-rpb-x11 \
"

EXTRA_USERS_PARAMS += "\
usermod -a -G video linaro; \
"
