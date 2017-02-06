# The libprovision turned off, depends on restricted cppsdk component
PROVISIONING = ""

# these plugins are commercial licensed & removed from build
RDEPS_EXTRA_remove = " \
                      gstreamer1.0-plugins-ugly-mpg123 \
                      gstreamer1.0-plugins-bad-hls \
                     "
                     
