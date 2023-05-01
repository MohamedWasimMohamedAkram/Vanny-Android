from typing import List

from vannypi.communicator.reporter.report import Report
from vannypi.inputanalysis.environmentanalyzer.danger_detector import DangerDetector
from vannypi.inputanalysis.objects.object import Object


class EnvironmentAnalyzer:
    def __int__(self):
        self.people_count: int = 0
        self.night_time: bool = False
        self._detector: DangerDetector = DangerDetector()

    def is_dangerous(self, objects: List[Object]) -> List[bool]:
        pass

    def count_people(self) -> int:
        pass

    def movement_detected(self) -> bool:
        pass

    def generate_report(self, objects: List[Object]) -> Report:
        pass

    def _generate_details(self, objects: List[Object]) -> str:
        pass
