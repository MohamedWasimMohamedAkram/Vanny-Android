from vannypi.inputmanagement.audiomanager.audio import Audio


class DevicesManger:
    def __int__(self):
        pass

    def connect_device(self, address: str) -> None:
        pass

    def send_audio(self, audio_data: Audio) -> None:
        pass

    def receive_audio(self) -> Audio:
        pass

    def connected_devices(self) -> None:
        pass
