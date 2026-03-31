# Primzahltester

Vergleich zweier klassischer Algorithmen zur Bestimmung aller Primzahlen bis zu einer gegebenen Grenze `max`.

## Lernziele

- Verstehen der **Probedivision** als naiver Ansatz
- Verstehen des **Siebs des Eratosthenes** als effizienter Algorithmus
- Laufzeitmessung und Vergleich: Wie stark unterscheiden sich O(n²) und O(n log log n)?
- Umgang mit `boolean`-Arrays als Datenstruktur

## Die zwei Algorithmen

### Probedivision
Für jede Zahl `n` wird geprüft, ob ein Teiler zwischen 2 und `n/2` existiert.  
→ Einfach zu verstehen, aber langsam: **O(n²)**

### Sieb des Eratosthenes
Alle Vielfachen bekannter Primzahlen werden systematisch gestrichen.  
→ Wesentlich effizienter: **O(n log log n)**

```
Beispiel (max=20):
Start: 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
p=2:   streiche 4, 6, 8, 10, 12, 14, 16, 18, 20
p=3:   streiche 9, 15
p=5:   –
Ergebnis: 2  3  5  7  11  13  17  19
```

## Erwartete Ausgabe (max = 100.000)

```
=== Laufzeitvergleich (max = 100000) ===
Laufzeit Eratosthenes-Sieb:  3 ms
Laufzeit Probedivision:     89 ms
Das Sieb ist ~29x schneller.
```
*(genaue Werte hängen vom System ab)*
