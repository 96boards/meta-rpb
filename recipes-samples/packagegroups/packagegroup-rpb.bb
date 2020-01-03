SUMMARY = "Organize packages to avoid duplication across all images"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

# There exist BSP layers which use custom names for their optee recipes.
# E.g. optee-client-qoriq from meta-freescale vs optee-client.
# The variable below can be overrided to make it possible to use
# such BSP layers with meta-rpb.
OPTEE_PACKAGES ?= "optee-test optee-client"

# contains basic dependencies, that can work without graphics/display
RDEPENDS_packagegroup-rpb = "\
    96boards-tools \
    alsa-utils-amixer \
    alsa-utils-aplay \
    coreutils \
    cpufrequtils \
    ${@bb.utils.contains("TARGET_ARCH", "arm", "", "docker", d)} \
    file \
    gptfdisk \
    hostapd \
    htop \
    ldd \
    lsof \
    iptables \
    kernel-modules \
    networkmanager \
    networkmanager-nmtui \
    openssh-sftp-server \
    ${@bb.utils.contains("MACHINE_FEATURES", "optee", d.getVar("OPTEE_PACKAGES", True), "", d)} \
    python-misc \
    python-modules \
    rsync \
    sshfs-fuse \
    strace \
    v4l-utils \
    "
