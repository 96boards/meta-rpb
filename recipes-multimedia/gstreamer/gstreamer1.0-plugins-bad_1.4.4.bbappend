# Disable introspection to fix gstinsertbin build issue
EXTRA_OECONF_append = " --enable-introspection=no"
