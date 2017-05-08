SRC_URI += "file://0001-Forget-about-GBM_BO_MAP.patch \
            file://0002-Don-t-try-to-test-egl_mesa_platform_surfaceless.patch \
"
DEPENDS += "virtual/egl"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
