/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * File:   main.c
 * Author: Bambi
 *
 * Created on 27. November 2017, 19:30
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Knoten //Struktur für die Liste
{
	int key;
	struct Knoten* next;
}*anfang, *liste; //Strukturen namen
/*Strukturen anlegen*/
	struct Knoten *anfang; //Struktur-Name *anfang
	struct Knoten *liste; //Struktur-Name *liste
/*--------------*/
/*Bekanntmachen der Funktionen*/
void qinit();
void enqeue(int element);
int deqeue(void);
int isempty(void);
void printQeue(void);
/*----------------------------*/
int main() //Menue und Switch-Case
{
	int eingabe = 0,auswahl = 0; //Deklaration der Variablen

	qinit(); //Warteschlange mit NULL initialisieren
	while(1) //endlosschleife
	{
		setbuf(stdout, NULL);
		printf("Menue zur Warteschlagenverwaltung:\n" /*Menu des Programmes */
				"1. Anfuegen eines Elementes\n"
				"2. Loeschen und Ausgeben des ersten Elementes\n"
				"3. Ausgeben der Warteschlange\n"
				"4. Beenden des Programms\n\n"
				"Bitte geben Sie die Ziffer der gewuenschten Funktion ein:");
		scanf("%d",&auswahl); //einlesen des Switch-Case Parameters
		switch(auswahl) //Switch-Case
		{
			case 1:
			{
				printf("Welches Elemnet soll angefuegt werden?\n"); //aufforderung einen Wert einzugeben
				scanf("%d",&eingabe); //einlesen des Wertes
				enqeue(eingabe); //aufruf der Funktion zum einfügen eines Elementes
				break; //schleife verlassen
			}
			case 2:
			{
				deqeue(); //aufruf der Funktion zum löschen des ersten Elementes
				break; //schleife verlassen
			}
			case 3:
			{
				printQeue(); //aufruf der Funktion zur ausgabe
				break; //schleife verlassen
			}
			case 4:
			{
				printf("Ende"); //Ausgabe bei beenden des Programmes
				return (EXIT_SUCCESS); //Abbruch Bedingung
			}
		}
	}
}
void qinit()//Erzeugen der leeren Warteschlange
{
	anfang = NULL; //*anfang auf NULL setzen
	liste = NULL; //*liste auf NULL setzen
}
void enqeue(int element) //element anhaengen
{
	liste = anfang;
	if(anfang == NULL) //Element anfügen -> Warteschlange leer
	{
		anfang = malloc(sizeof(struct Knoten)); //Speicherplatz reservieren
		anfang->key = element; //einzufügendes Element setzen
		anfang->next = NULL; // next pointer auf NULL setzen
		liste = anfang; //liste auf anfang setzen
	}
	else //Element anfügen -> Warteschlange nicht leer
	{
		while(liste->next != NULL) //solange das Ende nicht erreicht wurde
			liste = liste->next; // liste auf nächste stelle setzen
		liste->next = malloc(sizeof(struct Knoten)); //Speicherplatz reservieren
		liste = liste->next; //liste auf freie Stelle setzen
		liste->key = element; //element einfügen
		liste->next = NULL; //nächstes Element auf NULL setzen
	}
}
int deqeue() //element loeschen
{
	liste = anfang; //liste auf anfang setzen
	if(liste != NULL) //erste Element aus der Warteschlange löschen
	{
		printf("Geloescht: %d\n",liste->key); //Ausgabe des zu löschenden Elementes
		liste = anfang->next; //liste auf den nachfolger von anfang setzen
		free(anfang); //speicherplatz für anfang freigeben
		anfang = liste; //liste auf anfang setzen
	}
	else //Rückgabe, wenn die Warteschlange leer ist
		return printf("Leer: %d\n",isempty()); 
}
int isempty() //Funktion zur Überprüfung ob die Warteschlange leer ist
{
	if(liste == NULL) //wenn liste = NULL, ist die liste leer
		return 1;
	else
		return 0;
}
void printQeue() //Ausgabe der Liste
{
	liste = anfang; //liste auf anfang setzen
	if(liste != NULL) //wenn die liste nicht leer ist
	{
		while(liste != NULL) //solange die liste nicht leer ist
		{
			printf("%d\n",liste->key); // werte ausgeben an der jeweiligen position
			liste = liste->next; //liste auf den nachfolger setzen
		}
	}
	else
		return printf("Leer: %d\n",isempty()); //ausgabe, wenn die liste leer ist
}
