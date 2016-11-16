# Eispartikel
Softwarearchitektur: Eispartikelprojekt

Aktuell kann der Server auf Port 7000 neue Verbindungen aufnehmen und versendet über einen ObjectOutputStream Station-Objekte.
Wenn im Laufe eine neue Station dazukommt, wird diese auch weitergeleitet an alle angemeldeten Clients.

Insgesamt soll es aber noch schöner hinprogrammiert werden mit ein paar Interfaces um die Austauschbarkeit zu ermöglichen und einige Klassen leichter erweiterbar zu machen.
Außerdem fehlt halt noch die Schnittstelle für aktuelle Werte.

Vorschlag: Die Clients innerhalb des Projekts entwickeln. Mein TestClient kann zum kopieren von Quellcode genutzt werden, sollte aber bitte nur durch mich geändert werden.
Ich habe jetzt einfach mal eine Mavenstruktur von Eclipse anlegen lassen für das Projekt.

Für die Stationen, die empfangen werden, muss die Klasse Station aus dem Servermodelpaket genommen werden.
Vermutlich werde ich diese Klasse noch auslagern in ein anderes Paket.
