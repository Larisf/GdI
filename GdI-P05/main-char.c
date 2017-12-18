/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.c
 * Author: Bambi
 *
 * Created on 8. Dezember 2017, 12:53
 */

#include <stdio.h>
#include <stdlib.h>
void expand(FILE *datei); //Funktion bekannt machen
int main_a(int argc, char** argv) 
{
	setbuf(stdout, NULL); //Output_Buffer auf NULL setzen !!!NUR IN IDES NÖTIG, WELCHE DIE STANDARTAUSGABE PUFFERN!!!
	FILE *datei; //File typ mit einem Zeiger für die Datei
	if(argc < 2 || (datei = fopen(argv[1],"r")) == NULL) //wenn input der Argumente beim programmstart < 2 dann:
	{
		printf("Keine Datei gefunden! Bitte eingabe taetigen:\n"); //Ausgabe, wenn keine Datei angegeben oder gefunden wurde
		expand_a(stdin); //rufe expand auf
	}
	else if((datei = fopen(argv[1],"r")) != NULL) //argc > 2 dann öffne die datei mit dem Namen "NAME" zum lesen, wenn vorhanden
		expand_a(datei); //rufe expand auf
	return (EXIT_SUCCESS);
}
void expand_a(FILE *datei) //expand funktion convertiere \t zu leerzeichen
{
	int counter=0,c,cTemp,control=0; //laeufer variable
	while((c = fgetc(datei)) != EOF) //Solange ausführen bis das Dateiende erreicht ist
	{
		if(c == '\\')
		{
			cTemp = c; //temporaere variable anlegen.
			if((c=fgetc(datei)) == 't')//ist die position mit einem \ erreicht, nächste stelle prüfen ob die = t ist
			{
				{
					if((counter%8)==0) //ist z%8=0, 8 leerzeichen einfuegen
						for(int k=0; k<8;k++) //8x durchlaufen
						{
							c = ' '; // leerzeichen setzen und inkrementieren
							fputc(c,stdout); //ausgabe
							control = 1; //kontrolle auf 1
						}
					while((counter%8) != 0) //ist z != 8, solange ausfuerhen bis z%8 = 0 ist
					{
						c = ' '; //leerzeichen an stell i setzen und inkrementieren
						fputc(c,stdout); //ausgabe
						counter++; //z inkrementieren
						control= 1; //kontrolle auf 1
					}
				}
			}
			else if(c != 't') //wenn c != 't' der vorgaenger aber ein \ war.
				fputc(cTemp,stdout); //ausgabe des temporaer gespeicherten wertes.
		}
		if(control == 0) //kontrolle ob vorher leerzeichen waren
		{
			fputc(c,stdout); //zeile ausgeben und von vorne anfangen.
			counter++; //zaehler inkrementieren
		}
		if(control==1 || c ==10) //10 = integer für \n 
		{
			control=0; //kontroll var auf 0
			counter=0; // zaehlher auf 0
		}
	}
}