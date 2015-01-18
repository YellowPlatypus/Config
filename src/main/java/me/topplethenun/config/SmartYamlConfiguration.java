/*
 * This file is part of Config, licensed under the ISC License.
 *
 * Copyright (c) 2014 Richard Harrah
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
package me.topplethenun.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * An extension of {@link org.bukkit.configuration.file.YamlConfiguration} that can load and save itself.
 */
public class SmartYamlConfiguration extends YamlConfiguration implements SmartConfiguration {

    private File file;

    /**
     * Instantiates a new IvoryYamlConfiguration with a selected {@link java.io.File} to load/save from/to and
     * automatically loads the file.
     *
     * @param file file to load/save from/to
     */
    public SmartYamlConfiguration(File file) {
        this(file, '.');
    }


    /**
     * Instantiates a new IvoryYamlConfiguration with a selected {@link java.io.File} to load/save from/to and
     * automatically loads the file.
     *
     * @param file      file to load/save from/to
     * @param separator separator char
     */
    public SmartYamlConfiguration(File file, char separator) {
        super();
        this.file = file;
        options().pathSeparator(separator);
        load();
    }

    /**
     * Loads from the file passed into the constructor.
     *
     * Equivalent of using {@link #load(java.io.File)} on a {@link java.io.File}.
     */
    @Override
    public void load() {
        try {
            load(this.file);
        } catch (Exception e) {
            // do nothing
        }
    }

    /**
     * Saves to the file passed into the constructor.
     *
     * Equivalent of using {@link #save(java.io.File)} on a {@link java.io.File}.
     */
    @Override
    public void save() {
        try {
            save(this.file);
        } catch (Exception e) {
            // do nothing
        }
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public String getFileName() {
        return file != null ? file.getName() : "";
    }


}
