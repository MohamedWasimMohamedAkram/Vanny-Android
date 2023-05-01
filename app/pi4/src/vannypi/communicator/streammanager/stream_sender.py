from vannypi.communicator.internetconnectionmanager.connection_manager import ConnectionManager
from vannypi.communicator.streammanager.streamer import Streamer
from vannypi.inputmanagement.videomanager.video import Video


class StreamSender:
    def __int__(self):
        self._connection: ConnectionManager = ConnectionManager()
        self._streamer: Streamer = Streamer()

    def send(self, video: Video) -> None:
        pass

    def is_connected_to_netwrok(self) -> bool:
        pass

    def is_connected_to_device(self) -> bool:
        pass
