MALI_USERLAND_LIBARIES ?= "mali450-userland"
SGX_USERLAND_LIBARIES ?= "ti-sgx-ddk-um"

# Helper function for overloading the default EGL/GLES implementation.
# The Mali libraries provided by ARM are compatible with the Mesa headers
# and it is safe to use with user space applications linked against Mesa.

def get_egl_handler(d, target):
    """  Overloading the default EGL/GLES implementation."""
    features = d.getVar('MACHINE_FEATURES', True).split()

    mali_libs = d.getVar('MALI_USERLAND_LIBARIES', True);
    sgx_libs = d.getVar('SGX_USERLAND_LIBARIES', True);

    if 'mali450' in features:
        provider = mali_libs.split()[0]
    elif 'sgx' in features:
        provider = sgx_libs.split()[0]
    else:
        provider = "mesa"

    return provider;

PREFERRED_PROVIDER_virtual/egl = "${@get_egl_handler(d, 'egl')}"
PREFERRED_PROVIDER_virtual/libgles1 = "${@get_egl_handler(d, 'libgles1')}"
PREFERRED_PROVIDER_virtual/libgles2 = "${@get_egl_handler(d, 'libgles2')}"
