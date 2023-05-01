from typing import List

from vannypi.inputanalysis.objects.object import Object
from vannypi.inputmanagement.videomanager.video import Video

from tflite_support.task import core
from tflite_support.task import processor
from tflite_support.task import vision


class ObjectsIdentifier:
    def __int__(self):
        self._objects_to_look_for: List[Object] = []

    @staticmethod
    def digest_models(model, num_threads, enable_edgetpu) -> core.BaseOptions:
        # Initialize the object detection model
        base_options: core.BaseOptions = core.BaseOptions(
            file_name=model, use_coral=enable_edgetpu, num_threads=num_threads)
        return base_options

    def detect_and_identify(self, video: Video) -> None:
        pass

    @staticmethod
    def detect_objects(base_options: core.BaseOptions) -> vision.ObjectDetector:
        detection_options: processor.DetectionOptions = processor.DetectionOptions(
            max_results=5, score_threshold=0.3)
        options: vision.ObjectDetectorOptions = vision.ObjectDetectorOptions(
            base_options=base_options, detection_options=detection_options)
        detector: vision.ObjectDetector = vision.ObjectDetector.create_from_options(options)
        return detector
