DISTRO_VERSION = "2.0+linaro"

TARGET_VENDOR = "-linaro"

require conf/distro/include/arm-defaults.inc
require conf/distro/include/egl.inc

# Python3 isn't /lib64 safe
#require conf/distro/include/distro-multilib.inc

GCCVERSION ?= "linaro-5.2"

DISTRO_FEATURES_append = " systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"
PACKAGECONFIG_append_pn-systemd = " resolved networkd"
PACKAGECONFIG_append_pn-qtbase = " gles2 fontconfig examples"
PACKAGECONFIG_remove_pn-gpsd = "qt"

LICENSE_FLAGS_WHITELIST += "commercial_gstreamer1.0-libav commercial_ffmpeg commercial_x264"

DISTRO_FEATURES_remove = "sysvinit"

DISTRO_FEATURES_append = " opengl pam"

INHERIT += "rm_work"
INHERIT += "buildhistory"
INHERIT += "image-buildinfo"
BUILDHISTORY_COMMIT = "1"
