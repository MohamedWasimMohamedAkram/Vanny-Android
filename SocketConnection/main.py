import zmq
import cv2

context = zmq.Context()
socket = context.socket(zmq.PUB)
socket.bind("tcp://127.0.0.1:5555")

cap = cv2.VideoCapture(0)

# Define the codec and create VideoWriter object
fourcc = cv2.VideoWriter_fourcc(*'XVID')
out = cv2.VideoWriter('output.avi', fourcc, 20.0, (640, 480))

while True:
    ret, frame = cap.read()
    if ret:
        # Write the frame to the file
        out.write(frame)

        # Encode and send the frame over ZeroMQ
        encoded_frame = cv2.imencode('.jpg', frame)[1].tobytes()
        socket.send(encoded_frame)

        cv2.imshow("Sender", frame)
        if cv2.waitKey(1) == ord('q'):
            break
    else:
        break

# Release everything
cap.release()
out.release()
cv2.destroyAllWindows()