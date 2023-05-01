from typing import List

from vannypi.inputanalysis.activityanalyzer.activity_analyzer import ActivityAnalyzer
from vannypi.inputanalysis.environmentanalyzer.environment_analyzer import EnvironmentAnalyzer
from vannypi.inputanalysis.environmentanalyzer.object_identifier import ObjectsIdentifier
from vannypi.inputanalysis.environmentanalyzer.pitch_analyzer import PitchAnalyzer
from vannypi.inputanalysis.objects.object import Object
from vannypi.inputmanagement.audiomanager.audio import Audio
from vannypi.inputmanagement.videomanager.video import Video


class Watcher:
    def __int__(self):
        self._identifier: ObjectsIdentifier = ObjectsIdentifier()
        self._environemnt_analyzer: EnvironmentAnalyzer = EnvironmentAnalyzer()
        self._activity_analyzer: ActivityAnalyzer = ActivityAnalyzer()
        self._detected_objects: List[Object] = []
        self._pitch_analyzer: PitchAnalyzer = PitchAnalyzer()

    def watch(self) -> None:  # everything happens here
        pass

    def is_up(self) -> bool:
        pass

    def identify_objects(self) -> None:
        pass

    def analyze(self, video: Video) -> None:
        pass

    def analyze_audio(self, audio: Audio) -> None:
        pass
