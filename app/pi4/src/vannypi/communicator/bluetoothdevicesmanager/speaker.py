from vannypi.communicator.bluetoothdevicesmanager.bluetooth_device import BluetoothDevice


class Speaker(BluetoothDevice):
    def __int__(self):
        self.name: str = ""
        self.connected: bool = False

    def set_name(self, name: str) -> str:
        pass

    def connect(self, name: str) -> None:
        pass
