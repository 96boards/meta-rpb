DISTRO_VERSION = "2.4+linaro"

# These default to 'oecore' and 'nodistro'
SDK_NAME_PREFIX = "${DISTRO}"
SDK_VERSION = "${DISTRO_VERSION}"

TARGET_VENDOR = "-linaro"

require conf/distro/include/arm-defaults.inc
require conf/distro/include/egl.inc

# defaultsetup.inc gets includes after ${DISTRO}.conf, so we need to set it here
# to make the python below work. Local, site and auto.conf will override it.
TCMODE ?= "default"

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

GCCVERSION ?= "linaro-7.2"

DISTRO_FEATURES_append = " opengl pam systemd ptest"
DISTRO_FEATURES_remove = "3g sysvinit"
VIRTUAL-RUNTIME_init_manager = "systemd"
PACKAGECONFIG_append_pn-systemd = " resolved networkd"
PACKAGECONFIG_append_pn-qtbase = " gles2 fontconfig examples"
PACKAGECONFIG_remove_pn-gpsd = "qt"
PACKAGECONFIG_append_pn-gstreamer1.0-plugins-bad = " kms"
PACKAGECONFIG_append_pn-ffmpeg = " sdl2"

PREFERRED_PROVIDER_iasl-native = "acpica-native"
PREFERRED_PROVIDER_iasl = "acpica"

LICENSE_FLAGS_WHITELIST += "commercial_gstreamer1.0-libav commercial_ffmpeg commercial_x264 non-commercial"

# Avoid to duplicate the rootfs tarball by generating both tar.gz/tar.xz
IMAGE_FSTYPES_remove = "tar.gz"

# By default, meta-ti and TI SDK ship tar.xz rootfs tarball for SD card deployment
# fastboot is preferred for deployment in automation
IMAGE_FSTYPES_append_omap-a15 = " ext4.gz"
IMAGE_FSTYPES_append_beaglebone = " ext4.gz"

INHERIT += "buildhistory"
INHERIT += "image-buildinfo"
BUILDHISTORY_COMMIT = "1"
