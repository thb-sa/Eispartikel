# Eispartikel
Softwarearchitektur: Eispartikelprojekt

Der Server kann auf Port 7000 neue Verbindungen aufnehmen und versendet über einen ObjectOutputStream Station-Objekte.
Wenn im Laufe eine neue Station dazukommt, wird diese auch weitergeleitet an alle angemeldeten Clients.

Der Server kann aktuelle Tageswerte abspeichern und Abweichungswerte berechnen. Die Abweichungswerte werden an alle angemeldeten Clients versendet. Sich neu anmeldende Clients bekommen die Abweichungswerte beim initialen Verbinden übertragen.
Die Kommunikation für die aktuellen Tageswerte findet über Port 7001 statt.

Für die Kommunikation zwischen Client und Server soll unbedingt die Kommunikationsbibliothek benutzt werden.
