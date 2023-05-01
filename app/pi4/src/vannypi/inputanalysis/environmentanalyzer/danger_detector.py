from typing import List

from vannypi.inputanalysis.objects.object import Object


class DangerDetector:
    def __int__(self):
        self.dangerous_object: List[Object] = []
        self.current_status: int = 0

    def determine_danger(self, objects: List[Object]) -> List[list]:
        pass
