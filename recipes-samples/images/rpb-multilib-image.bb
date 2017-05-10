require rpb-minimal-image.bb

SUMMARY = "Minimal multilib image"

CORE_IMAGE_BASE_INSTALL += " \
    bash lib32-bash \
"
