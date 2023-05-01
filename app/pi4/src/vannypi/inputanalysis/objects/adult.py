from typing import List

from vannypi.inputanalysis.objects.human import Human


class Adult(Human):
    def __int__(self):
        self.distance_from_toddler: float = 0.
        self.dangerous: bool = True
        self.size: List[float] = [0., 0.]
        self.age_group: int = 0
