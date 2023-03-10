package main;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.Locale;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        } else {
            return (file.getName().toLowerCase(Locale.ROOT).endsWith(".html") ||
                file.getName().toLowerCase(Locale.ROOT).endsWith(".htm"));
        }
    }

    @Override
    public String getDescription() {
        return "HTML and HTL files";
    }
}
