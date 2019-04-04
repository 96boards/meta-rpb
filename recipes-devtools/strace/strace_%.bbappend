FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-xlat-workaround-V4L2_CID_USER_IMX_BASE-Linux-kernel-.patch \
            file://0002-xlat-update-V4L2_CID_USER_-_BASE-constants.patch \
            file://0001-xlat-update-header-to-fix-build-failure.patch"
