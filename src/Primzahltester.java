import java.util.Arrays;

/**
 * Vergleich zweier klassischer Algorithmen zum Finden aller Primzahlen bis zu einer Grenze:
 * <ol>
 *   <li><b>Probedivision</b>: Testet jede Zahl durch alle möglichen Teiler.</li>
 *   <li><b>Sieb des Eratosthenes</b>: Streicht systematisch alle Vielfachen
 *       bereits bekannter Primzahlen.</li>
 * </ol>
 *
 * <p>Beide Algorithmen arbeiten auf einem {@code boolean[]}-Array der Länge {@code max+1}.
 * Index {@code i} steht für die Zahl {@code i}: {@code true} bedeutet "noch als Primzahl
 * gehandelt", {@code false} bedeutet "bereits als zusammengesetzt erkannt".</p>
 *
 * <p>Das Programm misst die Laufzeit beider Methoden und gibt sie vergleichend aus.
 * Das Sieb des Eratosthenes ist für große {@code max}-Werte wesentlich schneller.</p>
 *
 * @author hibbes
 * @version 1.0
 */
public class Primzahltester {

    /** Obergrenze: Es werden alle Primzahlen von 2 bis {@code max} gesucht. */
    static int max = 100000;

    /**
     * Hauptmethode: Führt beide Algorithmen aus, misst ihre Laufzeit und vergleicht sie.
     *
     * @param args Kommandozeilenargumente (nicht verwendet)
     */
    public static void main(String[] args) {
        long timeprobe;       // Laufzeit der Probedivision in Millisekunden
        long timeerasto;      // Laufzeit des Siebs in Millisekunden

        // --- Probedivision testen ---
        long timeStart = System.currentTimeMillis();
        boolean[] primzahlen = probedivision(erzeugeArray(max));
        long timeEnd = System.currentTimeMillis();
        timeprobe = timeEnd - timeStart;

        // --- Sieb des Eratosthenes testen ---
        timeStart = System.currentTimeMillis();
        primzahlen = erastothenes(erzeugeArray(max));
        timeEnd = System.currentTimeMillis();
        timeerasto = timeEnd - timeStart;

        // Ergebnisausgabe: Laufzeitvergleich
        System.out.println("=== Laufzeitvergleich (max = " + max + ") ===");
        System.out.println("Laufzeit Eratosthenes-Sieb: " + timeerasto  + " ms");
        System.out.println("Laufzeit Probedivision:      " + timeprobe   + " ms");
        System.out.println("Das Sieb ist ~" + (timeprobe / Math.max(1, timeerasto)) + "x schneller.");
    }

    /**
     * Sieb des Eratosthenes – findet alle Primzahlen bis {@code max}.
     *
     * <p><b>Idee:</b> Beginne bei 2. Wenn eine Zahl noch nicht gestrichen ist,
     * ist sie eine Primzahl. Streiche dann alle ihre Vielfachen (2p, 3p, 4p, …)
     * als zusammengesetzt. Wiederhole für die nächste ungestrichene Zahl.</p>
     *
     * <p><b>Beispiel für max=20:</b>
     * <pre>
     *   Start: [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
     *   p=2: streiche 4,6,8,10,12,14,16,18,20
     *   p=3: streiche 6,9,12,15,18
     *   p=5: streiche 10,15,20
     *   → Primzahlen: 2, 3, 5, 7, 11, 13, 17, 19
     * </pre></p>
     *
     * <p><b>Zeitkomplexität:</b> O(n log log n) – sehr effizient für große n.</p>
     *
     * @param primzahlen Array, das zu Beginn alle Zahlen als "prim" markiert (true)
     * @return dasselbe Array, nach dem Sieben: true = prim, false = zusammengesetzt
     */
    private static boolean[] erastothenes(boolean[] primzahlen) {
        for (int i = 2; i <= max; i++) {
            int momentanerWert = i;   // Startpunkt: die Zahl selbst

            if (primzahlen[momentanerWert]) {
                // i ist eine Primzahl → streiche alle Vielfachen ab 2*i
                momentanerWert += i;
                while (momentanerWert <= max) {
                    primzahlen[momentanerWert] = false;  // Vielfaches → nicht prim
                    momentanerWert += i;                 // nächstes Vielfaches
                }
            }
            // Ist primzahlen[i] == false, wurde i bereits durch eine kleinere Primzahl gestrichen
        }
        return primzahlen;
    }

    /**
     * Probedivision – prüft jede Zahl einzeln durch Teilen.
     *
     * <p><b>Idee:</b> Eine Zahl {@code number} ist prim, wenn kein Teiler
     * zwischen 2 und {@code number/2} existiert. Wird ein Teiler gefunden,
     * wird die Zahl sofort als zusammengesetzt markiert (Short-Circuit durch
     * die Schleifenbedingung {@code primzahlen[number]}).</p>
     *
     * <p><b>Optimierungspotenzial:</b> Es würde genügen, nur bis √{@code number}
     * zu testen – da ein Teiler > √n immer einen Partnerteil < √n hätte.
     * Hier wird zur Vereinfachung bis {@code number/2} getestet.</p>
     *
     * <p><b>Zeitkomplexität:</b> O(n²) – deutlich langsamer als das Sieb.</p>
     *
     * @param primzahlen Array, das zu Beginn alle Zahlen als "prim" markiert (true)
     * @return dasselbe Array nach der Prüfung: true = prim, false = zusammengesetzt
     */
    private static boolean[] probedivision(boolean[] primzahlen) {
        for (int number = 2; number <= max; number++) {
            // Teste alle möglichen Teiler von 2 bis number/2
            // Die zweite Bedingung (primzahlen[number]) bricht ab, sobald ein Teiler gefunden wurde
            for (int i = 2; i <= number / 2 && primzahlen[number]; i++) {
                if (number % i == 0) {
                    // i teilt number ohne Rest → number ist zusammengesetzt
                    primzahlen[number] = false;
                }
            }
        }
        return primzahlen;
    }

    /**
     * Gibt alle gefundenen Primzahlen auf der Konsole aus.
     * (Nicht in main aufgerufen, um die Laufzeitmessung nicht zu verfälschen.)
     *
     * @param primzahlen Ergebnis-Array aus {@link #erastothenes} oder {@link #probedivision}
     */
    private static void ausgabe(boolean[] primzahlen) {
        System.out.println("Primzahlen bis " + max + ":");
        for (int i = 1; i <= max; i++) {
            if (primzahlen[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    /**
     * Erzeugt ein boolesches Array der Länge {@code max+1} und initialisiert alle
     * Einträge auf {@code true} – alle Zahlen gelten zunächst als Primzahlen.
     * Anschließend werden 0 und 1 auf {@code false} gesetzt, da sie per Definition
     * keine Primzahlen sind.
     *
     * @param max die größte zu prüfende Zahl
     * @return initialisiertes Array für die Primzahlsuche
     */
    private static boolean[] erzeugeArray(int max) {
        boolean[] primzahlen = new boolean[max + 1];
        // Alle Einträge auf true setzen (alle Zahlen zunächst als prim annehmen)
        for (int i = 0; i <= max; i++) {
            primzahlen[i] = true;
        }
        // 0 und 1 sind per Definition keine Primzahlen
        primzahlen[0] = false;
        primzahlen[1] = false;
        return primzahlen;
    }
}
