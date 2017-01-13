require rpb-desktop-image.bb

CORE_IMAGE_BASE_INSTALL += " \
    packagegroup-rpb-tests \
    packagegroup-rpb-tests-x11 \
    "
