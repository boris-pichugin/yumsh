package org.yumsh.club;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
            InputStreamReader reader = new InputStreamReader(in, StandardCharsets.US_ASCII);
            BufferedReader br = new BufferedReader(reader);
            OutputStream out = socket.getOutputStream();

            String methodLine = br.readLine();
            if (methodLine == null) {
                return;
            }
            if (!methodLine.endsWith(" HTTP/1.1")) {
                writeError(out, "400 Bad Request");
                return;
            }

            try {
                if (methodLine.startsWith("GET ")) {
                    handleGetRequest(methodLine, out);
                } else if (methodLine.startsWith("POST ")) {
                    handlePostRequest(methodLine, br, out);
                } else {
                    writeError(out, "400 Bad Request");
                }
            } catch (IOException e) {
                writeError(out, "404 Not Found");
            }
        } catch (final Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private static void handleGetRequest(String methodLine, OutputStream out) throws IOException {
        String resourceName = getResourceName(methodLine);
        Path path = getResourcePath(resourceName);
        String contentType = getContentType(resourceName);
        byte[] fileContent = transform(resourceName, Files.readAllBytes(path));

        writeOk(out, contentType, fileContent);
    }

    private static void handlePostRequest(String methodLine, BufferedReader br, OutputStream out) throws IOException {
        String resourceName = getResourceName(methodLine);
        Path path = getResourcePath(resourceName);
        String contentType = getContentType(resourceName);
        byte[] fileContent = transform(resourceName, Files.readAllBytes(path));

        Map<String, String> headers = parseHeaders(br);
        if (headers == null) {
            return;
        }

        int inContentLength = Integer.parseInt(headers.getOrDefault("content-length", "0"));
        char[] postParams = readParams(br, inContentLength);
        if (postParams == null) {
            return;
        }

        Map<String, String> params = parseParams(postParams);
    }

    private static Map<String, String> parseHeaders(BufferedReader br) throws IOException {
        Map<String, String> headers = new HashMap<>();
        while (true) {
            String header = br.readLine();
            if (header == null) {
                return null;
            }
            header = header.trim();
            if (header.isEmpty()) {
                break;
            }
            int separator = header.indexOf(':');
            if (separator >= 0) {
                String key = header.substring(0, separator).toLowerCase(Locale.ROOT);
                String value = header.substring(separator + 2);
                headers.put(key, value);
            }
        }
        return headers;
    }

    private static char[] readParams(BufferedReader br, int inContentLength) throws IOException {
        char[] postParams = new char[inContentLength];
        int offset = 0;
        while (offset < inContentLength) {
            int readSize = br.read(postParams, offset, inContentLength - offset);
            if (readSize == -1) {
                return null;
            }
            offset += readSize;
        }
        return postParams;
    }

    private static Map<String, String> parseParams(char[] postParams) {
        return null;
    }

    private static String getResourceName(String methodLine) {
        int start = methodLine.indexOf(' ') + 1;
        String resourceName = methodLine.substring(start, methodLine.length() - 9);
        if (resourceName.equals("/") || resourceName.equals("/index")) {
            resourceName = "index.html";
        }
        if (resourceName.startsWith("/")) {
            resourceName = resourceName.substring(1);
        }
        return resourceName;
    }

    private static Path getResourcePath(String resourceName) throws IOException {
        Path path = WWW_ROOT.resolve(resourceName).toRealPath(LinkOption.NOFOLLOW_LINKS);
        if (!path.startsWith(WWW_ROOT.toAbsolutePath())) {
            throw new IOException();
        }
        return path;
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

    private static void writeOk(OutputStream out, String contentType, byte[] fileContent) throws IOException {
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
    }
}