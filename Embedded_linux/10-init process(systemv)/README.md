### add this to inittab
```
rc2:2:wait:/etc/init.d/hello
rc3:3:wait:/etc/init.d/rc 3
```
## make script in init.d
```
cd /etc/init.d
touch deamonapp
```
# write this to deamonapp
```
#! /bin/sh
case "$1" in
      start)
           echo "Starting deamonapp........."
           start-stop-daemon -S -n deamonapp -a /bin/deamonapp &
           ;;
     stop)
           echo "Stopping deamonapp........."
           start-stop-daemon -K -n deamonapp
           ;;
     *)
           echo "Usage: $0 {start|stop}"
           exit 1
esac
exit 0
```
### make the script executable
```
chmod +x deamonapp
```
![cat](file:///home/ahmed/Pictures/Screenshots/Screenshot%20from%202024-08-05%2012-28-50.png)

### go back to /etc and create two directories for the two runlevels
```
mkdir rc2.d
mkdir rc3.d
```
### at each directory(runlevel), create symbolic link to the deamonapp
```
# for runlevel 2
ln -s /etc/init.d/deamonapp /etc/rc2.d/S1deamonapp.sh
ln -s /etc/init.d/deamonapp /etc/rc2.d/K1deamonapp.sh

# for runlevel 3
ln -s /etc/init.d/deamonapp ./etc/rc3.d/K1deamonapp.sh   
```
### Remember the real application is stored at /bin, so we need to create our appication there
```
#!/bin/sh
while true
     do
         echo "Daemonapp is running!" >> /tmp/daemon.test
         sleep 10
     done
```
### I made the application as bash script so we need to give it execution permission
```
chmod +x deamonapp
```

### We need to create our rc script in init.d
```
cd /etc/init.d
```
### Add this to the rc script
```
#!/bin/sh
# Check if one argument is provided
if [ $# -ne 1 ]; then
    echo "Usage: $0 <runlevel>"
    exit 1
fi
# Define the folder path based on the argument
folder="rc$1.d"
# Kill scripts starting with K
for i in /etc/$folder/K??* ;do
     # Ignore dangling symlinks (if any).
     [ ! -f "$i" ] && continue
     case "$i" in
        *.sh)
            # Source shell script for speed.
            (
                trap - INT QUIT TSTP
                set stop
                . $i
            )
            ;;
        *)
            # No sh extension, so fork subprocess.
            $i stop
            ;;
    esac
done
# Start scripts starting with S
for i in /etc/$folder/S??* ;do
     # Ignore dangling symlinks (if any).
     [ ! -f "$i" ] && continue
     case "$i" in
        *.sh)
            # Source shell script for speed.
            (
                trap - INT QUIT TSTP
                set start
                . $i
            )
            ;;
        *)
            # No sh extension, so fork subprocess.
            $i start
            ;;
    esac
done
```
