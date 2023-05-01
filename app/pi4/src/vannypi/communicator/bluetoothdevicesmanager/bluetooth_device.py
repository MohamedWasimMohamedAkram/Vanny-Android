from abc import ABC, abstractmethod


class BluetoothDevice(ABC):
    name: str
    connected: bool

    @abstractmethod
    def connect(self, name: str) -> None:
        pass

    @abstractmethod
    def set_name(self, name: str) -> str:
        pass
