# This file should be split into distro-${ARCH}-multilib.inc and have a top-level include do 'require  distro-${ARCH}-multilib.inc'.
# Since BSPs suck pay attentions to the arm-defaults.inc comment below

# Install 64bit into ${prefix}/lib64 and ${prefix}/lib32
# FIXME: use debian multi-arch style paths
BASELIB_tune-aarch64 = "lib64"
BASELIB_tune-armv7ahf-neon = "lib32"

require conf/multilib.conf
MULTILIBS = "multilib:lib32"

# Merge with conf/distro/inclue/arm-defaults.inc?
DEFAULTTUNE_virtclass-multilib-lib32 = "armv7ahf-neon"
