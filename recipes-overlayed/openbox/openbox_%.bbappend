FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://menu.xml"

do_install_append_rpb() {
    install -m 0644 ${WORKDIR}/menu.xml ${D}${sysconfdir}/xdg/openbox
}

RDEPENDS_${PN} += "openbox-xdgmenu"
