package io.netty.example.echo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LoadCertificateFile {
    static InputStream loadCertificateFile(String path) {
        final File file = new File(path);
        InputStream is;
        try {
            is = file.isFile() ? new FileInputStream(file)
                    : Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
            assert is != null;
        } catch (IOException io) {
            throw new RuntimeException("Failed to load certificate file", io);
        }
        return is;
    }
}
