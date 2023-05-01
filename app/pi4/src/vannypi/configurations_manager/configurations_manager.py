from typing import List


class ConfigurationsManger:
    def __int__(self):
        self.mode: int = 0
        self.encoding: str = ""
        self.max_frame_rate: int = 0
        self.models_path: str = ""
        self.streaming_resolution: List[int] = [0, 0]
        self.recording_resolution: List[int] = [0, 0]
