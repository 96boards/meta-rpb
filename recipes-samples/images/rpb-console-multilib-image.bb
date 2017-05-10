require rpb-console-image.bb

SUMMARY = "Basic console multilib  image"

CORE_IMAGE_BASE_INSTALL += " \
    bash lib32-bash \
"
