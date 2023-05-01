from typing import List

from vannypi.inputmanagement.audiomanager.audio import Audio


class AudioRecorder:
    def __int__(self):
        pass

    def recorder_audio(self, bitrate_audio: List[float]) -> Audio:
        pass

    def export_audio(self, audio: List[Audio]) -> Audio:
        pass

    def save_audio(self, name: str, audio_data: Audio) -> None:
        pass
