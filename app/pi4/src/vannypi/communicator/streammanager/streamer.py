from vannypi.communicator.streammanager.stream_manager import StreamManager
from vannypi.inputmanagement.videomanager.video import Video


class Streamer:  # Everything in this class is tentative

    def __int__(self):
        self._manager: StreamManager = StreamManager()
        self._current_video: Video = Video()
        self._buffer: Video = Video()

    def stream(self, video: Video) -> None:
        pass

    def clear_buffer(self) -> None:
        pass
