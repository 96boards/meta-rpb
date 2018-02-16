require rpb-weston-image.bb

IMAGE_ROOTFS_EXTRA_SPACE = "1048576"
EXTRA_IMAGE_FEATURES += "ptest-pkgs"

CORE_IMAGE_BASE_INSTALL += " \
    packagegroup-rpb-tests \
    "
