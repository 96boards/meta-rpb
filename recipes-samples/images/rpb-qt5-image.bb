require rpb-console-image.bb

SUMMARY = "Basic QT5 image"

# let's make sure we have a good image..
REQUIRED_DISTRO_FEATURES += "opengl"

# by default and without x11-base in IMAGE_FEATURES we default to multi-user.target
SYSTEMD_DEFAULT_TARGET = "multi-user.target"

CORE_IMAGE_BASE_INSTALL += " \
	96boards-tools \
	alsa-utils-aplay \
	networkmanager \
	networkmanager-nmtui \
	coreutils \
	gps-utils \
	gpsd \
	gptfdisk \
	gstreamer1.0-plugins-bad-meta \
	gstreamer1.0-plugins-base-meta \
	gstreamer1.0-plugins-good-meta \
	${@bb.utils.contains("LICENSE_FLAGS_WHITELIST", "commercial_gstreamer1.0-libav", "gstreamer1.0-libav", "", d)} \
	hostapd \
	iptables \
	kernel-modules \
	sshfs-fuse \
	cinematicexperience \
	qt5everywheredemo \
	qtbase-examples \
	${@bb.utils.contains("MACHINE_FEATURES", "optee", "optee-test optee-client", "", d)} \
"
