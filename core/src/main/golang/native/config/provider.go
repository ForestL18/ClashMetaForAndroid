package config

import (
	"io"

	"github.com/metacubex/mihomo/config"
)

func forEachProviders(rawCfg *config.RawConfig, fun func(index int, total int, key string, provider map[string]any)) {
	total := rawCfg.ProxyProvider.Len() + len(rawCfg.RuleProvider)
	index := 0

	for k, v := range rawCfg.ProxyProvider {
		fun(index, total, k, v)

		index++
	}
	for pair := rawCfg.ProxyProvider.Oldest(); pair != nil; pair = pair.Next() {
            k := pair.Key
            v := pair.Value
            fun(index, total, k, v)
            index++
        }

func destroyProviders(cfg *config.Config) {
	for pair := cfg.Providers.Oldest(); pair != nil; pair = pair.Next() {
            p := pair.Value
            if closer, ok := p.(io.Closer); ok {
               _ = closer.Close()
            }
        }

	for _, p := range cfg.RuleProviders {
		_ = p.(io.Closer).Close()
	}
}
