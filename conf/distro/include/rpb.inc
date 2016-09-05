DISTRO_VERSION = "2.0+linaro"

GCCVERSION ?= "linaro-5.2"

DISTRO_FEATURES_append = " systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"
PACKAGECONFIG_append_pn-systemd = " resolved networkd"
PACKAGECONFIG_remove_pn-gpsd = "qt"

# FIXME Mali is currently added as a distro config on a per-machine basis
# This should be automated with an anonymous python method
PREFERRED_PROVIDER_virtual/egl_hikey = "mali450-userland"
PREFERRED_PROVIDER_virtual/libgles1_hikey = "mali450-userland"
PREFERRED_PROVIDER_virtual/libgles2_hikey = "mali450-userland" 

DISTRO_FEATURES_remove = "sysvinit"

DISTRO_FEATURES_append = " opengl pam"

INHERIT += "rm_work"
INHERIT += "buildhistory"
INHERIT += "image-buildinfo"
BUILDHISTORY_COMMIT = "1"
