from typing import List

from vannypi.inputanalysis.objects.object import Object


class LightSource(Object):
    def __int__(self):
        self.distance_from_toddler: float = 0.
        self.dangerous: bool = True
        self.size: List[float] = [0., 0.]
        self.strength: float = 0.
        