package app

import (
	"strings"

	"github.com/forestl18/mihomo/dns"
)

func NotifyDnsChanged(dnsList string) {
	var addr []string
	if len(dnsList) > 0 {
		addr = strings.Split(dnsList, ",")
	}
	dns.UpdateSystemDNS(addr)
	dns.FlushCacheWithDefaultResolver()
}
