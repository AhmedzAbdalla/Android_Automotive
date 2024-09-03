# Deployment of QT application to myimage 
## meta-iti
- This is our layer that contains our recipes which specifies that distrobution features.

```
./meta-iti/
├── conf
│   ├── bblayers.conf
│   ├── distro
│   │   └── almaza.conf
│   └── layer.conf
├── COPYING.MIT
├── README
├── recipes-example
│   └── example
│       ├── example_0.1.bb
│       ├── example_%.bbappend
│       ├── main.cpp
│       ├── mainwindow.cpp
│       ├── mainwindow.h
│       ├── mainwindow.ui
│       ├── pingapp_0.1.bb
│       ├── rbpiqt.pro
│       └── task_app.py
├── recipes-image
│   └── images
│       └── myimage.bb
└── recipes-kernel
    ├── hello-mod
    │   ├── files
    │   │   ├── COPYING
    │   │   ├── hello.c
    │   │   └── Makefile
    │   └── hello-mod_0.1.bb
    └── linux
        ├── linux-yocto-custom
        │   ├── 0001-linux-version-tweak.patch
        │   ├── feature.scc
        │   └── smp.cfg
        └── linux-yocto-custom.bb

```

### Run th QT app
- you will find the application at /use/bin/ with name "rbpiqt"

## Problems regarding rust compiler
```
sudo apt install g++-14
sudo apt install g++-12
```