import zmq
import cv2
import numpy as np

context = zmq.Context()
socket = context.socket(zmq.SUB)
socket.connect("tcp://127.0.0.1:5555")
socket.setsockopt_string(zmq.SUBSCRIBE, "")

# Create VideoWriter object to save the received frames
fourcc = cv2.VideoWriter_fourcc(*'XVID')
out = cv2.VideoWriter('received_output.avi', fourcc, 20.0, (640, 480))

while True:
    encoded_frame = socket.recv()
    frame = cv2.imdecode(np.frombuffer(encoded_frame, dtype=np.uint8), cv2.IMREAD_COLOR)

    # Write the frame to the file
    out.write(frame)

    cv2.imshow("Receiver", frame)
    if cv2.waitKey(1) == ord('q'):
        break

# Release everything
out.release()
cv2.destroyAllWindows()