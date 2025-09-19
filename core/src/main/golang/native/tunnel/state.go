package tunnel

import (
	"github.com/ForestL18/mihomo/tunnel"
)

func QueryMode() string {
	return tunnel.Mode().String()
}
