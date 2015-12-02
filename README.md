Meta-rpb
================================

Introduction
-------------------------

The official OpenEmbedded/Yocto Project BSP layer the 96boards Reference Platform Build distro.

The meta-rpb layer depends on:

	URI: git://git.openembedded.org/openembedded-core
	layers: meta
	branch: master

	URI: git://git.openembedded.org/meta-openembedded
	layers: meta-oe
	branch: master

Please follow the recommended setup procedures of your OE distribution. For Angstrom that is http://www.angstrom-distribution.org/building-angstrom, other distros should have similar online resources.

Notes
-------------------------

Please note that the support for Qualcomm based rpb.org, such as the
DragonBoard 410c, is done through the meta-qualcomm BSP layer:
https://github.com/ndechesne/meta-qualcomm not meta-rpb.

Contributing
-------------------------

Please use github for pull requests: https://github.com/96boards/meta-rpb/pulls

Reporting bugs
-------------------------

The github issue tracker (https://github.com/96boards/meta-rpb/issues) is being used to keep track of bugs.

Maintainers
-------------------------

* Koen Kooi <koen.kooi@linaro.org>
* Nicolas Dechesne <nicolas.dechesne@linaro.org>
* Fathi Boudra <mailto:fathi.boudra@linaro.org>
