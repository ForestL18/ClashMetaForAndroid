package tunnel

import (
	"github.com/forestl18/mihomo/tunnel"
)

func QueryMode() string {
	return tunnel.Mode().String()
}
