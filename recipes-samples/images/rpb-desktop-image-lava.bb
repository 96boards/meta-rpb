require rpb-desktop-image.bb

IMAGE_ROOTFS_EXTRA_SPACE = "1048576"

CORE_IMAGE_BASE_INSTALL += " \
    packagegroup-rpb-tests \
    packagegroup-rpb-tests-x11 \
    "
