GCCVERSION ?= "linaro-5.2"

DISTRO_FEATURES_append = " systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"
PACKAGECONFIG_append_pn-systemd = " resolved networkd"

DISTRO_FEATURES_remove = "sysvinit"

IMAGE_FEATURES_append = " debug-tweaks ssh-server-dropbear"

DISTRO_FEATURES_append = " opengl pam"

INHERIT += "rm_work"
INHERIT += "buildhistory"
INHERIT += "image-buildinfo"
BUILDHISTORY_COMMIT = "1"
