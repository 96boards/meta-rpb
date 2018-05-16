FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://gyp-fix-node-build-failure-with-long-build-directory-path-on-Linux.patch \
    "
