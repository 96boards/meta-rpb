FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://menu.xml"

do_install:append:rpb() {
    install -m 0644 ${WORKDIR}/menu.xml ${D}${sysconfdir}/xdg/openbox
}

RDEPENDS:${PN} += "openbox-xdgmenu"
