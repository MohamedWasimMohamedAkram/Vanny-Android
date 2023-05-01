from typing import List

import cv2
import sys
import time

from tflite_support.task import vision
import numpy as np
from tflite_support.task import processor


class Camera:
    def __int__(self):
        pass

    @staticmethod
    def visualize(image: np.ndarray, detection_result: processor.DetectionResult) -> np.ndarray:
        """Draws bounding boxes on the input image and return it.

        Args:
          image: The input RGB image.
          detection_result: The list of all "Detection" entities to be visualize.

        Returns:
          Image with bounding boxes.
        """
        _MARGIN = 10  # pixels
        _ROW_SIZE = 10  # pixels
        _FONT_SIZE = 1
        _FONT_THICKNESS = 1
        _TEXT_COLOR = (0, 0, 255)  # red

        # mocking danger detectionn with water bottles :)
        bottle_detected: bool = False

        for detection in detection_result.detections:
            # Draw bounding_box
            bbox = detection.bounding_box
            start_point = bbox.origin_x, bbox.origin_y
            end_point = bbox.origin_x + bbox.width, bbox.origin_y + bbox.height
            cv2.rectangle(image, start_point, end_point, _TEXT_COLOR, 3)

            # Draw label and score
            category = detection.categories[0]
            category_name = category.category_name
            if category_name == "bottle":
                bottle_detected = True
            probability = round(category.score, 2)
            result_text = category_name + ' (' + str(probability) + ')'
            text_location = (_MARGIN + bbox.origin_x,
                             _MARGIN + _ROW_SIZE + bbox.origin_y)
            cv2.putText(image, result_text, text_location, cv2.FONT_HERSHEY_PLAIN,
                        _FONT_SIZE, _TEXT_COLOR, _FONT_THICKNESS)

        return image, bottle_detected  # this will change to be more organized

    @staticmethod
    def run_camera(detector, height, width, camera_id):
        # Start capturing video input from the camera
        cap = cv2.VideoCapture(camera_id)
        cap.set(cv2.CAP_PROP_FRAME_WIDTH, width)
        cap.set(cv2.CAP_PROP_FRAME_HEIGHT, height)
        # Visualization parameters
        row_size = 20  # pixels
        left_margin = 24  # pixels
        text_color = (0, 0, 255)  # red
        font_size = 1
        font_thickness = 1
        fps_avg_frame_count = 10
        # Variables to calculate FPS
        counter, fps = 0, fps_avg_frame_count
        frames: List[np.ndarray] = []

        max_length_of_record_seconds: List[int] = [60, 30]
        videos_count: int = 0
        capture_starting_time = time.time()
        start_time = time.time()
        mode: int = 0
        capture_length = max_length_of_record_seconds[mode]
        # mocking danger detectionn with water bottles :)
        bottle_detected: bool = False

        fourcc = cv2.VideoWriter_fourcc(*'XVID')

        # Continuously capture images from the camera and run inference
        while cap.isOpened():
            success, image = cap.read()
            if not success:
                sys.exit(
                    'ERROR: Unable to read from webcam. Please verify your webcam settings.'
                )

            counter += 1
            image = cv2.flip(image, 1)
            # keep the size of video as intended
            if len(frames) >= max_length_of_record_seconds[mode] * fps:
                frames = frames[1:]
            frames.append(image)

            # Convert the image from BGR to RGB as required by the TFLite model.
            rgb_image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)

            # Create a TensorImage object from the RGB image.
            input_tensor = vision.TensorImage.create_from_array(rgb_image)

            # Run object detection estimation using the model.
            detection_result = detector.detect(input_tensor)

            # Draw keypoints and edges on input image
            image, detected = Camera.visualize(image, detection_result)
            if detected:
                print("\ndetected")
                bottle_detected = detected

            # Calculate the FPS
            if counter % fps_avg_frame_count == 0:
                end_time = time.time()
                fps = fps_avg_frame_count / (end_time - start_time)
                start_time = time.time()

            # Show the FPS
            fps_text = 'FPS = {:.1f}'.format(fps)
            text_location = (left_margin, row_size)
            cv2.putText(image, fps_text, text_location, cv2.FONT_HERSHEY_PLAIN,
                        font_size, text_color, font_thickness)

            # Stop the program if the ESC key is pressed.
            if cv2.waitKey(1) == 27:
                break
            cv2.imshow('object_detector', image)

            cur_time = time.time()
            print(cur_time - capture_starting_time)

            if mode == 0:
                if bottle_detected:
                    print("writing")
                    video_writer = cv2.VideoWriter('capture_{}.avi'.format(videos_count), fourcc, fps,
                                                   (int(cap.get(3)), int(cap.get(4))))
                    for i in range(len(frames)):
                        video_writer.write(frames[i])
                    frames: List[np.ndarray] = []
                    mode = 1
                    bottle_detected = False
                    capture_starting_time = time.time()

            elif mode == 1:
                if len(frames) >= fps * max_length_of_record_seconds[mode]:
                    print("writing post")
                    print(len(frames))
                    for i in range(len(frames)):
                        video_writer.write(frames[i])
                    frames: List[np.ndarray] = []
                    mode = 0
                    bottle_detected = False
                    capture_starting_time = time.time()
                    videos_count += 1

        cap.release()
        cv2.destroyAllWindows()

    @staticmethod
    def mock_event(seconds: int, start_time: float):
        cur_time = time.time()
        print(cur_time - start_time)
        return cur_time - start_time > seconds


'''
pi code for camera
camera = PiCamera()
camera.resolution = (320, 240)
camera.framerate = 24
time.sleep(2)

while True:
    image = np.empty((240 * 320 * 3,), dtype=np.uint8)
    camera.capture(image, 'bgr')
    image = image.reshape((240, 320, 3))
'''
