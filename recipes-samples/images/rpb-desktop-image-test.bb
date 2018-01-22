require rpb-desktop-image.bb

CORE_IMAGE_BASE_INSTALL += " \
    packagegroup-core-buildessential \
    \
    ltp \
    \
    python \
    python-misc \
    python-multiprocessing \
    python-numpy \
    python-scons \
    python-shell \
    python-threading \
    \
    piglit \
    "
