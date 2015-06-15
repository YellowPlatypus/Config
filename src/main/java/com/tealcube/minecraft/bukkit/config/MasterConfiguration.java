/*
 * This file is part of Config, licensed under the ISC License.
 *
 * Copyright (c) 2014-2015 Richard Harrah
 *
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted,
 * provided that the above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF
 * THIS SOFTWARE.
 */
package com.tealcube.minecraft.bukkit.config;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.MemoryConfiguration;

import java.util.ArrayList;
import java.util.List;

public final class MasterConfiguration extends MemoryConfiguration {

    public MasterConfiguration() {
        super();
    }

    public MasterConfiguration(Configuration defaults) {
        super(defaults);
    }

    public static MasterConfiguration loadFromFiles(SmartYamlConfiguration... yamlConfigurations) {
        MasterConfiguration masterConfiguration = new MasterConfiguration();
        masterConfiguration.load(yamlConfigurations);
        return masterConfiguration;
    }

    public void load(SmartYamlConfiguration... yamlConfigurations) {
        if (yamlConfigurations == null) {
            return;
        }
        for (SmartYamlConfiguration yc : yamlConfigurations) {
            String name = yc.getFileName().replace(".yml", "");
            for (String key : yc.getKeys(true)) {
                if (yc.isConfigurationSection(key)) {
                    continue;
                }
                Object value;
                if (yc.isBoolean(key) || yc.isDouble(key) || yc.isInt(key) || yc.isLong(key) || yc
                        .isString(key)) {
                    value = yc.getString(key);
                } else {
                    value = yc.get(key);
                }
                set(name + "." + key, value);
            }
        }
    }

    public List<String> getStringList(String key, List<String> fallback) {
        if (!contains(key)) {
            return fallback;
        }
        Object val = get(key);
        if (!(val instanceof List)) {
            return fallback;
        }
        List<?> valList = (List) val;
        List<String> ret = new ArrayList<>();
        for (Object o : valList) {
            ret.add(String.valueOf(o));
        }
        return ret;
    }

}
