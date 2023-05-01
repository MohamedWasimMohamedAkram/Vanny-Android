from typing import List

from vannypi.inputmanagement.audiomanager.audio import Audio
from vannypi.inputmanagement.videomanager.video import Video


class Report:
    def __int__(self):
        self._videos: List[Video] = []
        self._audios: List[Audio] = []
        self._details: str = ""
        self._title: str = ""
