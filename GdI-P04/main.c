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
}*anfang, *liste;

void qinit();
void enqeue(int element);
int deqeue(void);
int isempty(void);
void printQeue(void);

int main()
{
	int eingabe = 0,auswahl = 0;

	qinit();
	while(1)
	{
		setbuf(stdout, NULL);
		printf("Menue zur Warteschlagenverwaltung:\n"
				"1. Anfuegen eines Elementes\n"
				"2. Loeschen und Ausgeben des ersten Elementes\n"
				"3. Ausgeben der Warteschlange\n"
				"4. Beenden des Programms\n\n"
				"Bitte geben Sie die Ziffer der gewuenschten Funktion ein:");
		scanf("%d",&auswahl);
		switch(auswahl)
		{
			case 1:
			{
				printf("Welches Elemnet soll angefuegt werden?\n");
				scanf("%d",&eingabe);
				enqeue(eingabe);
				break;
			}
			case 2:
			{
				deqeue();
				break;
			}
			case 3:
			{
				printQeue();
				break;
			}
			case 4:
			{
				printf("Ende");
				return (EXIT_SUCCESS);
			}
		}
	}
}
void qinit()
{
	struct Knoten *next = NULL; //Erzeugen der leeren Warteschlange
}
void enqeue(int element)
{
	liste = anfang;
	if(anfang == NULL) //Element anfügen -> Warteschlange leer
	{
		anfang = malloc(sizeof(struct Knoten));
		anfang->key = element;
		anfang->next = NULL;
		liste = anfang;
	}
	else //Element anfügen -> Warteschlange nicht leer
	{
		while(liste->next != NULL)
			liste = liste->next;
		liste->next = malloc(sizeof(struct Knoten));
		liste = liste->next;
		liste->key = element;
		liste->next = NULL;
	}
}
int deqeue()
{
	liste = anfang;
	if(liste != NULL) //erste Element aus der Warteschlange löschen
	{
		liste = anfang->next;
		free(anfang);
		anfang = liste;
	}
	else //Rückgabe, wenn die Warteschlange leer ist
		return printf("Leer: %d\n",isempty());
}
int isempty() //Funktion zur Überprüfung ob die Warteschlange leer ist
{
	if(liste == NULL)
		return 1;
	else
		return 0;
}
void printQeue() //Ausgabe der Liste
{
	liste = anfang;
	if(liste != NULL)
	{
		while(liste != NULL)
		{
				printf("%d\n",liste->key);
				liste = liste->next;
		}
	}
	else
		return printf("Leer: %d\n",isempty());
}
