from typing import List

from vannypi.communicator.reporter.report import Report
from vannypi.communicator.reporter.report_generator import ReportGenerator
from vannypi.inputmanagement.audiomanager.audio import Audio
from vannypi.inputmanagement.videomanager.video import Video


class Reporter:
    def __int__(self):
        self._reports: List[Report] = []
        self._generator: ReportGenerator = ReportGenerator()

    def make_report(self, videos: List[Video], audios: List[Audio], title: str, details: str):
        pass

    def export_report(self, report: Report):
        pass
