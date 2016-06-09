DISTRO_VERSION = "2.0+linaro"

GCCVERSION ?= "linaro-5.2"

DISTRO_FEATURES_append = " systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"
PACKAGECONFIG_append_pn-systemd = " resolved networkd"
PACKAGECONFIG_remove_pn-gpsd = "qt"

DISTRO_FEATURES_remove = "sysvinit"

DISTRO_FEATURES_append = " opengl pam"

INHERIT += "rm_work"
INHERIT += "buildhistory"
INHERIT += "image-buildinfo"
BUILDHISTORY_COMMIT = "1"
