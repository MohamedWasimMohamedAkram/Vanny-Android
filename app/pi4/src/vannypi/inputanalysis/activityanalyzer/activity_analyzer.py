from typing import List

from vannypi.communicator.reporter.report import Report
from vannypi.inputanalysis.objects.object import Object


class ActivityAnalyzer:
    def __int__(self):
        self.people_count: int = 0
        self.type: int = 0

    def movement_detected(self) -> bool:
        pass

    def record_activity_details(self) -> None:
        pass

    def generate_report(self) -> Report:
        pass

    def _generate_details(self, objects: List[Object]) -> str:
        pass

