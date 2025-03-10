package org.yumsh.club;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class HttpServer {
    private static final Path WWW_ROOT = Path.of("www");

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(5333, 3)) {
            int i = 0;
            while (true) {
                Socket socket = server.accept();
                Thread thread = new Thread(
                    () -> handleClientSocket(socket),
                    "MyThread-" + (++i)
                );
                thread.setDaemon(true);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private static void handleClientSocket(Socket socket) {
        try (socket) {
            InputStream in = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(reader);
            OutputStream out = socket.getOutputStream();

            String methodLine = br.readLine();
            if (methodLine == null) {
                return;
            }
            if (
                !methodLine.startsWith("GET ")
                || !methodLine.endsWith(" HTTP/1.1")
            ) {
                writeError(out, "400 Bad Request");
                return;
            }

            String resourceName = methodLine.substring(4, methodLine.length() - 9);
            if (resourceName.equals("/") || resourceName.equals("/index")) {
                resourceName = "index.html";
            }
            if (resourceName.startsWith("/")) {
                resourceName = resourceName.substring(1);
            }
            try {
                Path path = WWW_ROOT.resolve(resourceName).toRealPath(LinkOption.NOFOLLOW_LINKS);
                if (!path.startsWith(WWW_ROOT.toAbsolutePath())) {
                    writeError(out, "404 Not Found");
                    return;
                }

                String contentType = getContentType(resourceName);
                byte[] fileContent = transform(resourceName, Files.readAllBytes(path));

                String answerHeaders = """
                    HTTP/1.1 200 Ok\r
                    content-type: %s\r
                    content-language: ru\r
                    cache-control: no-cache\r
                    content-length: %d\r
                    \r
                    """.formatted(contentType, fileContent.length);
                out.write(answerHeaders.getBytes(StandardCharsets.US_ASCII));
                out.write(fileContent);
                out.flush();
            } catch (IOException e) {
                writeError(out, "404 Not Found");
            }
        } catch (final Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private static String getContentType(String resourceName) {
        if (resourceName.endsWith(".ico")) {
            return "image/x-icon";
        }
        if (resourceName.endsWith(".png")) {
            return "image/png";
        } else {
            return "text/html; charset=utf-8";
        }
    }

    private static byte[] transform(String resourceName, byte[] bytes) {
        if (!resourceName.endsWith(".html")) {
            return bytes;
        }
        String content = new String(bytes, StandardCharsets.UTF_8);
        String currentTimestamp = "%tF %<tT %<tz".formatted(System.currentTimeMillis());
        String newContent = content.replaceAll("\\$\\{CURRENT_DATE}", currentTimestamp);
        return newContent.getBytes(StandardCharsets.UTF_8);
    }

    private static void writeError(OutputStream out, String errorStatus) throws IOException {
        String answer = "HTTP/1.1 %s\r\n\r\n".formatted(errorStatus);
        out.write(answer.getBytes(StandardCharsets.US_ASCII));
        out.flush();
    }
}