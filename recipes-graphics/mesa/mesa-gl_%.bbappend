PACKAGES_remove = "${@bb.utils.contains("MACHINE_FEATURES", "sgx", "libgbm libgbm-dev", "", d)}"
