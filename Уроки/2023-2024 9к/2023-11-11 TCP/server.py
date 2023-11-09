import socket

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:
    server_socket.bind(("127.0.0.1", 55555))
    server_socket.listen()

    client_socket, address = server_socket.accept()
    with client_socket:
        print(f"Connected by {address}")
        while True:
            received_bytes = client_socket.recv(1024)
            if not received_bytes:
                break
            client_socket.sendall(received_bytes)