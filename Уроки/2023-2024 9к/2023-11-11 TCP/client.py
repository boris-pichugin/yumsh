import socket

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect(("127.0.0.1", 55555))
    bytes_to_send = "Привет!".encode("utf-8")
    s.sendall(bytes_to_send)
    received_bytes = s.recv(len(bytes_to_send))

print(f"Received: {received_bytes.decode('utf-8')}")