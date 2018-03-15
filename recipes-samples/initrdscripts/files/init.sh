#!/bin/sh

HOME=/root
PATH=/sbin:/bin:/usr/sbin:/usr/bin
PS1="linaro-test [rc=$(echo \$?)]# "
export HOME PS1 PATH

do_mount_fs() {
   grep -q "$1" /proc/filesystems || return
   test -d "$2" || mkdir -p "$2"
   mount -t "$1" "$1" "$2"
}

do_mknod() {
    test -e "$1" || mknod "$1" "$2" "$3" "$4"
}

early_setup() {
    mkdir -p /proc /sys /tmp /run
    mount -t proc proc /proc

    do_mount_fs sysfs /sys
    do_mount_fs debugfs /sys/kernel/debug
    do_mount_fs devtmpfs /dev
    do_mount_fs devpts /dev/pts
    do_mount_fs tmpfs /dev/shm

    ln -s /run /var/run

    chmod 0666 /dev/tty*
    chown root:tty /dev/tty*

    /sbin/udevd --daemon
}

read_args() {
    [ -z "$CMDLINE" ] && CMDLINE=`cat /proc/cmdline`
    for arg in $CMDLINE; do
        optarg=`expr "x$arg" : 'x[^=]*=\(.*\)'`
        case $arg in
            console=*)
                tty=${arg#console=}
                tty=${tty#/dev/}

                case $tty in
                    tty[a-zA-Z]* )
                        port=${tty%%,*}
                esac ;;
            debug) set -x ;;
        esac
    done
}

early_setup
read_args

setsid sh </dev/${port} >/dev/${port} 2>&1
