SUMMARY = "96boards artwork files"
HOMEPAGE = "https://www.96boards.org/"
SECTION = "x11/graphics"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "git://github.com/96boards/96boards-artwork;protocol=https;branch=master"
SRCREV = "066d5b151cbd0d3595a16e5f28762d78389d3548"
PV = "0.0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit allarch update-alternatives

do_install () {
	install -d ${D}${datadir}/wallpapers/
	install -m 0644 usr/share/96boards/wallpapers/96Boardsbackground2HD1.jpg ${D}${datadir}/wallpapers
	ln -s 96Boardsbackground2HD1.jpg  ${D}${datadir}/wallpapers/96boards-default-wallpaper.jpg
}

FILES:${PN} = "${datadir}/wallpapers"

ALTERNATIVE:${PN} = "defaultjpg"
ALTERNATIVE_LINK_NAME[defaultjpg] = "${datadir}/wallpapers/default.jpg"
ALTERNATIVE_TARGET[defaultjpg] = "${datadir}/wallpapers/96Boardsbackground2HD1.jpg"
