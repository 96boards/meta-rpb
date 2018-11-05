#
# Based in part on buidhistory, testlab.bbclass and packagehistory.bbclass
#
# Copyright (C) 2017 Koen Kooi <koen.kooi@linaro.org>
# Copyright (C) 2011-2016 Intel Corporation
# Copyright (C) 2007-2011 Koen Kooi <koen@openembedded.org>
#

LKFTMETADATA_FEATURES ?= "image package sdk"
#LKFTMETADATA_DIR ?= "${TOPDIR}/buildhistory"
LKFTMETADATA_DIR ?= "${TOPDIR}/lkftmetadata"
LKFTMETADATA_DIR_IMAGE = "${LKFTMETADATA_DIR}/images/${MACHINE_ARCH}/${TCLIBC}/${IMAGE_BASENAME}"
LKFTMETADATA_DIR_PACKAGE = "${LKFTMETADATA_DIR}/packages/${MULTIMACH_TARGET_SYS}/${PN}"

python lkftmetadata_eventhandler() {
    if e.data.getVar('LKFTMETADATA_FEATURES', True).strip():
        reset = e.data.getVar("LKFTMETADATA_RESET", True)
        olddir = e.data.getVar("LKFTMETADATA_OLD_DIR", True)
        if isinstance(e, bb.event.BuildStarted):
            if reset:
                import shutil
                # Clean up after potentially interrupted build.
                if os.path.isdir(olddir):
                    shutil.rmtree(olddir)
                rootdir = e.data.getVar("LKFTMETADATA_DIR", True)
                entries = [ x for x in os.listdir(rootdir) if not x.startswith('.') ]
                bb.utils.mkdirhier(olddir)
                for entry in entries:
                    os.rename(os.path.join(rootdir, entry),
                              os.path.join(olddir, entry))
        elif isinstance(e, bb.event.BuildCompleted):
            if reset:
                import shutil
                shutil.rmtree(olddir)
            if e.data.getVar("LKFTMETADATA_COMMIT", True) == "1":
                bb.note("Writing lkftmetadata")
}

addhandler lkftmetadata_eventhandler
lkftmetadata_eventhandler[eventmask] = "bb.event.BuildCompleted bb.event.BuildStarted"

do_fetch[postfuncs] += "write_srcuri"
#do_fetch[vardepsexclude] += "write_srcuri"
python write_srcuri() {
    bb.note("Gathering lkftmetadata")
    import re
    pkghistdir = d.getVar('LKFTMETADATA_DIR_PACKAGE', True)
    if not os.path.exists(pkghistdir):
        bb.utils.mkdirhier(pkghistdir)

    srcuri = re.sub('\s+', ' ', d.getVar('SRC_URI', True)).replace(' ', '\n')
    # Filter out empty lines
    srcuri = os.linesep.join([s for s in srcuri.splitlines() if s])

    if srcuri:
        with open(os.path.join(pkghistdir, 'srcuri'), 'w') as f:
            f.write('%s' % srcuri)
        pn = d.getVar('PN', True).upper().replace('-', '_')
        pv = d.getVar('PV', True)
        srcrev = d.getVar('SRCREV', True)
        with open(os.path.join(pkghistdir, 'metadata'), 'w') as f:
            f.write('%s_URL=%s\n' % (pn, srcuri.splitlines()[0].split(';')[0]))
            f.write('%s_VERSION=%s\n' % (pn, pv))
            f.write('%s_REVISION=%s\n' % (pn, srcrev))
}
