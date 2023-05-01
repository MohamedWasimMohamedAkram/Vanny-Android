from abc import ABC

from typing import List


class Object(ABC):
    distance_from_toddler: float
    dangerous: bool
    size: List[float]
