GCCVERSION = "linaro-5.2"

DISTRO_FEATURES_append = " systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"

DISTRO_FEATURES_remove = "sysvinit"
