DISTRO_VERSION = "2.0+linaro"

TARGET_VENDOR = "-linaro"

require conf/distro/include/arm-defaults.inc
require conf/distro/include/egl.inc
require conf/distro/include/distro-multilib.inc

GCCVERSION ?= "linaro-6.2"

DISTRO_FEATURES_append = " systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"
PACKAGECONFIG_append_pn-systemd = " resolved networkd"
PACKAGECONFIG_append_pn-qtbase = " gles2 fontconfig examples"
PACKAGECONFIG_remove_pn-gpsd = "qt"

LICENSE_FLAGS_WHITELIST += "commercial_gstreamer1.0-libav commercial_ffmpeg commercial_x264 non-commercial"

DISTRO_FEATURES_remove = "sysvinit"

DISTRO_FEATURES_append = " opengl pam"

# Fastboot expects an ext4 image, which needs to be 4096 aligned
IMAGE_FSTYPES_append = " ext4.gz"
IMAGE_ROOTFS_ALIGNMENT = "4096"

INHERIT += "rm_work"
INHERIT += "buildhistory"
INHERIT += "image-buildinfo"
BUILDHISTORY_COMMIT = "1"
