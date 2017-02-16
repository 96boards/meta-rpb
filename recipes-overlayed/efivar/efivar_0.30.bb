SUMMARY = "Tools to manipulate UEFI variables"
DESCRIPTION = "efivar provides a simple command line interface to the UEFI variable facility"
HOMEPAGE = "https://github.com/rhinstaller/efivar"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=6626bb1e20189cfa95f2c508ba286393"

DEPENDS = "popt"
DEPENDS_append_class-target = " efivar-native"

COMPATIBLE_HOST = "(i.86|x86_64|arm|aarch64).*-linux"

SRCREV = "270205d88598d60d4e75f9cec13b8d25d82ee550"
SRC_URI = "git://github.com/rhinstaller/efivar.git \
"
SRC_URI_append_class-target = " file://0001-efivar-fix-for-cross-compile.patch \
                                file://0003-efivar-fix-for-cross-compile.patch \
                              "
SRC_URI_append_class-native = " \ 
                                file://allow-multi-definitions-for-native.patch \
                              "

S = "${WORKDIR}/git"

# Setting CROSS_COMPILE breaks pkgconfig, so just set AR
EXTRA_OEMAKE = "AR=${TARGET_PREFIX}gcc-ar"

do_compile_prepend() {
    sed -i -e s:-Werror::g ${S}/gcc.specs
}

do_install() {
    oe_runmake install DESTDIR=${D}
}

do_install_append_class-native() {
    install -D -m 0755 ${B}/src/makeguids ${D}${bindir}/makeguids
}

BBCLASSEXTEND = "native"

