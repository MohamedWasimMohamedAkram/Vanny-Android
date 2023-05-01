from typing import List


class VideoEncoder:
    def __int__(self):
        self._frame: List[float] = []
        self._audio_frame: List[float] = []

    def _import_raw_data(self, raw_data: List[float]) -> None:
        pass

    def _set_frame_rate(self, rate: int) -> None:
        pass

    def _set_compression_ratio(self, ratio: float) -> None:
        pass

    def encode(self, data: List[float]) -> List[float]:
        pass
