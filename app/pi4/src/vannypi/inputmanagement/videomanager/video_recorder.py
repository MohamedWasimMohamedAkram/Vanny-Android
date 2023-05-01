from typing import List

from vannypi.inputmanagement.videomanager.video import Video


class VideoRecorder:
    def __int__(self):
        self._full_video: Video = Video()

    def save_video(self, name: str, data: List[List[float]]) -> Video:
        pass
