# Task
## cahnge directory to 
```
/etc/systemd/system
```
## create 2 units called myservice.service and mynetwork.network
```
sudo touch myservice.service
sudo nano  myservice.service
```
### content of myservice.service
```
[Unit]
	Wants=mynetwork.network

[Service]
	Type=simple
	ExecStart=/usr/bin/ping 192.168.1.10 -c 3
	Restart=always
	RestartSec=3
	WorkingDirectory=/usr/bin

[Install]
	WantedBy=graphical.target
```
### content of mynetwork.network
```
[Unit]
	Name="wlp0s20f3"

[Network]
	Address=192.168.100.58/24
```

### Enable the units
```
systemctl enable myservice.service
systemctl enable mynetwork.network
```