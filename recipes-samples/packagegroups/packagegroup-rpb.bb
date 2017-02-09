SUMMARY = "Organize packages to avoid duplication across all images"

inherit packagegroup

# contains basic dependencies, that can work without graphics/display
RDEPENDS_packagegroup-rpb = "\
    96boards-tools \
    alsa-utils-aplay \
    coreutils \
    cpufrequtils \
    docker \
    gptfdisk \
    hostapd \
    htop \
    iptables \
    kernel-modules \
    networkmanager \
    networkmanager-nmtui \
    ${@bb.utils.contains("MACHINE_FEATURES", "optee", "optee-test optee-client", "", d)} \
    rsync \
    sshfs-fuse \
    "
