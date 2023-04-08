# ProjectOOP : SMOL

## Descrizione del progetto

Il gruppo si pone come obiettivo quello di realizzare un videogioco 2D con visuale dall’alto che prende spunto dal famoso “Schiaccia la Talpa”.
L’obbiettivo del gioco è difendere i campi di ortaggi dalle talpe che invadono la mappa di gioco, schiacciandole con un martello.
Il giocatore controllerà il personaggio attraverso i tasti direzionali per muoversi all’interno della mappa, usando il mouse invece, controllerà la direzione del martello che attraverso una pressione prolungata potrà allungarsi aumentando il raggio di azione. Il gioco termina quando le talpe riescono a mangiare tutti gli ortaggi.

## Collaboratori

Il software è stato sviluppato da:
- [Ettore Farinelli](https://github.com/TheDarkRuler) 
- [Marco Galeri](https://github.com/Fre0Grella)
- [Giovanni Paradisi](https://github.com/gioviheyz<>)
- [Mounir Samite](https://github.com/muni106) 

Per il corso di programmazione a oggetti.

## Build
Puoi buildare il progetto usando Gradlew, prima di tutto bisogna clonare la repository:

```bash
git clone git@github.com:TheDarkRuler/OOP22-SMOL.git
```

Puoi buildarlo su linux o mac OS con il comando:

```bash
cd "OOP22-SMOL"
./gradlew build
```

Se invece usi Windows

```ps
cd "OOP22-SMOL"
gradlew.bat build
```

## Run
Per avviare il gioco si può utilizzare Gradle:
- Su Linux o MacOS il comando:
```bash
cd "OOP22-SMOL"
./gradlew run
```
- Su Windows il comando:
```ps
cd "OOP22-SMOL"
gradlew.bat run
```

o in alternativa si può direttamente avviare il gioco con il file .jar messo a disposizione

## Istruzioni

All’avvio dell’applicazione l’utente si ritroverà nel menu di gioco composto
da diversi bottoni:
```
- Start: per avviare la partita.
- Instructions: per visionare le istruzioni di gioco in un’altra schermata
(compresa di bottone menu per tornare nella scena iniziale).
- Quit: per chiudere la finistra.
- ListBox: per la scelta della grafica di gioco.
```

I comandi di gioco sono i seguenti:
```
- F11: Attiva/disattiva la visuale a tutto schermo.
- W: Movimento verso l’alto.
- A: Movimento verso sinistra.
- S: Movimento verso il basso.
- D: Movimento verso destra
- Movimento del mouse: Spostamento del mirino.
- LMB (tasto sinistro mouse): Colpo del martello (a seguito di una
pressione prolungata di questo tasto il raggio di azione dell’arma sarà
maggiore).
```