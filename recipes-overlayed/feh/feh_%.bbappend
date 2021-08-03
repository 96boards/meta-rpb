FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://fehbg file://fehbg.desktop"

do_install:append() {
    install -d ${D}${sysconfdir}/xdg/autostart
    install -m 0644 ${WORKDIR}/fehbg.desktop ${D}${sysconfdir}/xdg/autostart

    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/fehbg ${D}${bindir}
}

PACKAGE_BEFORE_PN += "${PN}-autostart"
RDEPENDS:${PN}-autostart += "${PN}"
FILES:${PN}-autostart += " \
    ${sysconfdir}/xdg/autostart \
    ${bindir}/fehbg \
"
