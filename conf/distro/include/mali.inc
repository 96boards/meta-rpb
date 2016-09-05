MALI_USERLAND_LIBARIES ?= "mali450-userland"

# Helper function for overloading the default EGL/GLES implementation.
# The Mali libraries provided by ARM are compatible with the Mesa headers
# and it is safe to use with user space applications linked against Mesa.

def get_mali_handler(d, target):
    """  Overloading the default EGL/GLES implementation."""
    features = d.getVar('MACHINE_FEATURES', True).split()

    mali_libs = d.getVar('MALI_USERLAND_LIBARIES', True);

    if(mali_libs):
        mali_libs = mali_libs.split()

    if 'mali450' in features and mali_libs:
        provider = mali_libs[0]
    else:
        provider = "mesa"

    return provider;

PREFERRED_PROVIDER_virtual/egl := "${@get_mali_handler(d, 'egl')}"
PREFERRED_PROVIDER_virtual/libgles1 = "${@get_mali_handler(d, 'libgles1')}"
PREFERRED_PROVIDER_virtual/libgles2 = "${@get_mali_handler(d, 'libgles2')}"