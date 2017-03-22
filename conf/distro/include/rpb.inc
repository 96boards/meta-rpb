DISTRO_VERSION = "2.0+linaro"

TARGET_VENDOR = "-linaro"

require conf/distro/include/arm-defaults.inc
require conf/distro/include/egl.inc

# Enable multilib conditionally, only for aarch64 with default toolchain combination
# Other combinations aren't supported
def get_multilib_handler(d):
    features = d.getVar('TUNE_FEATURES', True).split()
    tcmode = d.getVar('TCMODE', True)
    if ('aarch64' in features) and (tcmode == "default"):
        distro_multilib = "conf/distro/include/distro-multilib.inc"
    else:
        distro_multilib = "conf/distro/include/file-cannot-be-found.inc"
    return distro_multilib

# Use a weak include to avoid to produce an error when the file cannot be found.
# It is the case when we don't want multilib enabled (e.g. on 32bit machines).
include ${@get_multilib_handler(d)}

GCCVERSION ?= "linaro-6.2"

DISTRO_FEATURES_append = " opengl pam systemd"
DISTRO_FEATURES_remove = "sysvinit"
VIRTUAL-RUNTIME_init_manager = "systemd"
PACKAGECONFIG_append_pn-systemd = " resolved networkd"
PACKAGECONFIG_append_pn-qtbase = " gles2 fontconfig examples"
PACKAGECONFIG_remove_pn-gpsd = "qt"

LICENSE_FLAGS_WHITELIST += "commercial_gstreamer1.0-libav commercial_ffmpeg commercial_x264"

# Avoid to duplicate the rootfs tarball by generating both tar.gz/tar.xz
IMAGE_FSTYPES_remove = "tar.gz"

INHERIT += "rm_work"
INHERIT += "buildhistory"
INHERIT += "image-buildinfo"
BUILDHISTORY_COMMIT = "1"
