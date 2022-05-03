FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://try-make-rule.patch"

do_compile:prepend() {
    ls -l ${S}
}
