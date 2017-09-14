require rpb-minimal-image.bb

SUMMARY = "Basic console image"

IMAGE_FEATURES += "package-management ssh-server-openssh hwcodecs"

CORE_IMAGE_BASE_INSTALL += " \
    packagegroup-rpb \
"

# docker pulls runc/containerd, which in turn recommend lxc unecessarily
BAD_RECOMMENDATIONS_append = " lxc"
