from typing import List

from vannypi.inputanalysis.objects.human import Human


class Toddler(Human):
    def __int__(self):
        self.distance_from_toddler: float = 0.
        self.dangerous: bool = True
        self.size: List[float] = [0., 0.]
