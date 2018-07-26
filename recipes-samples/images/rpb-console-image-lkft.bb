require rpb-console-image.bb

SUMMARY = "Basic console image for LKFT"

IMAGE_ROOTFS_EXTRA_SPACE = "1048576"

# Add 256 MB on X15; more than that might exceed the
# userdata partition capacity.
IMAGE_ROOTFS_EXTRA_SPACE_am57xx-evm = "262144"

CORE_IMAGE_BASE_INSTALL += " \
    packagegroup-rpb-tests \
    packagegroup-rpb-lkft \
    "
