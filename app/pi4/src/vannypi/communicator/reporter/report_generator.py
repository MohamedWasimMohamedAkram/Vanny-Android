from typing import List

from vannypi.communicator.reporter.report import Report
from vannypi.inputmanagement.audiomanager.audio import Audio
from vannypi.inputmanagement.videomanager.video import Video


class ReportGenerator:
    def __int__(self):
        pass

    @staticmethod
    def generate(videos: List[Video], audios: List[Audio], title: str, details: str) -> Report:
        pass
   