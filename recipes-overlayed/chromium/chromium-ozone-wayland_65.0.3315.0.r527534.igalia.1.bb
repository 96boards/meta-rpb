require chromium-ozone-wayland-tarball.inc
require chromium-gn.inc

SRC_URI += " \
 file://v8-qemu-wrapper.version-6.5.73.patch \
 file://0001-Rotate-gcc-toolchain-s-build-flags.patch \
 file://workaround-vcvt_f16_f32-is-not-declared-in-this-scope.patch \
 file://0001-GCC-fixes-avoid-symbol-collision-between-Element-Com.patch \
"

REQUIRED_DISTRO_FEATURES = "wayland"

DEPENDS += "\
        libxkbcommon \
        virtual/egl \
        wayland \
"

GN_ARGS += "\
        enable_mus=true \
        use_ozone=true \
        ozone_auto_platforms=false \
        ozone_platform_headless=true \
        ozone_platform_wayland=true \
        ozone_platform_x11=false \
        use_xkbcommon=true \
"

# The chromium binary must always be started with those arguments.
CHROMIUM_EXTRA_ARGS_append = " --mus --ozone-platform=wayland"
