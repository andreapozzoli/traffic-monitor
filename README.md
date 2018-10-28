# Gestione del traffico

## Mappatura delle cartelle e dei file

. <br />
├── design <br />
├── java <br />
├── README.md [questo file]<br />
└── requisiti<br />
    &emsp;├── [Analisi_dei_requisiti.pdf](https://gitlab.com/sweng2018/group1/traffic-monitor/tree/master/requisiti/Analisi_dei_requisiti.pdf) [*Analisi dei requisiti completa*]<br />
    &emsp;├── [data dictionary.xlsx](https://gitlab.com/sweng2018/group1/traffic-monitor/tree/master/requisiti/data%20dictionary.xlsx) [*data dictionary completo*]<br />
    &emsp;├── [diagramma_istar.png](https://gitlab.com/sweng2018/group1/traffic-monitor/tree/master/requisiti/diagramma_istar.png) [*diagramma i-star del sistema*]<br />
    &emsp;└── [diagramma_istar.zip](https://gitlab.com/sweng2018/group1/traffic-monitor/tree/master/requisiti/diagramma_istar.zip) [*file di progetto del diagramma i-star*]


## Tema del progetto

_Nota: disponibile anche nel file con l'[analisi dei requisiti](https://gitlab.com/sweng2018/group1/traffic-monitor/tree/master/requisiti/Analisi_dei_requisiti.pdf)._

Realizzare un sistema per il monitoraggio e il controllo integrato del traffico cittadino, composto dai seguenti sotto-sistemi che operano in modo distribuito:
 
* __Sistema centrale:__ incaricato di memorizzare tutte le informazioni di stato, inviare notifiche a sistemi esterni in caso di specifici eventi, mostrare lo stato dell'intero sistema e sottosistemi.
Il sistema quindi include una interfaccia utente che consente di esplorare le varie informazioni attuali.
__*Opzionale*__: è possibile decidere di mostrare i dati anche in un qualche tipo di forma grafica (diagrammi, mappe. ecc.).

* __Centraline stradali:__ incaricate di monitorare il flusso di traffico del segmento stradale in cui collocate e inviarlo al sistema centrale con periodicità proporzionale all'ammontare di traffico.

* __Centraline automobilistiche:__ incaricate di inviare con periodicità fissa il dato di velocità (e posizione) del veicolo su cui sono installate.

* __Applicazioni mobili:__ installate su telefono cellulare e incaricate di inviare al sistema centrale esplicite segnalazioni di traffico (coda, con posizione GPS) da parte degli utenti / guidatori.
Le applicazioni inoltre ricevono notifiche dal sistema centrale per qualsiasi evento di traffico (coda, velocità lenta, traffico elevato) in un raggio fisso dalla posizione (ultima registrata) del telefono.


Specificare, progettare e implementare il sistema distribuito necessario, coprendo: sistema centrale, applicazione mobile, e una a scelta tra centralina stradale e centralina automobilistica.
Definire esplicitamente tutti i formati dei dati scambiati e le modalità di scambio (protocollo).

È possibile raffinare i requisiti ed aggiungere ipotesi e assunzioni sul contesto, sensate e in linea con quanto indicato nei requisiti. Tali estensioni devono essere esplicitamente riportate nella documentazione di progetto (sezione specifica requisiti).

## Consegne

* Consegna 1) Requisiti: Fattibilità + DataDictionary + Goal Diagram
	* Fattibilità (1 pagina) [*Contenuto nel documento di progetto*]
	* Progetto i* con OpenOme [*Descritto e riportato anche nel documento di progetto*, [file di progetto](https://gitlab.com/sweng2018/group1/traffic-monitor/tree/master/requisiti/diagramma_istar.zip), [immagine](https://gitlab.com/sweng2018/group1/traffic-monitor/tree/master/requisiti/diagramma_istar.png)]
	* Documento di progetto, con: testo del progetto (da questo documento), fattibilità, sintesi goal e data dictionary (non c’è un formato fisso, è a vostra discrezione) [[link](https://gitlab.com/sweng2018/group1/traffic-monitor/tree/master/requisiti/Analisi_dei_requisiti.pdf)]

* Consegna 2) Design: UML
	* Almeno 2 activity, 2 sequence e 2 state diagrams
	* Almeno 1 diagramma per ogni restante tipo di diagramma
	* File di progetto di tool UML
	* Documento PDF riassuntivo dei diagrammi fatti, commentati

* Consegna 3) Implementazione: Java 
	* Progetto Java completo e funzionante
	* Casi di test Junit
	* Documento PDF riassuntivo 

* Consegna 4) Completo
	* Progetto e documento completo e funzionante

