from vannypi.communicator.reporter.report import Report


class Notifier:
    def __init__(self):
        self._connection_details: str = ""

    def receive_report(self) -> Report:
        pass

    def send_notification(self, report: Report) -> None:
        pass
