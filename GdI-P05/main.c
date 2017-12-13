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
const int MAX=32767; //Maximale länge des strings
void expand(FILE *datei); //Funktion bekannt machen
int main(int argc, char** argv) 
{
	setbuf(stdout, NULL); //Output_Buffer auf NULL setzen
	FILE *datei; //File typ mit einem Zeiger für die Datei
	if(argc < 2 || (datei = fopen(argv[1],"r")) == NULL) //wenn input der Argumente beim programmstart < 2 dann:
	{
		printf("Keine Datei gefunden! Bitte eingabe taetigen:\n"); //Ausgabe, wenn keine Datei angegeben oder gefunden wurde
		expand(stdin); //rufe expand auf
	}
	else if((datei = fopen(argv[1],"r")) != NULL) //argc > 2 dann öffne die datei mit dem Namen "NAME" zum lesen, wenn vorhanden
		expand(datei); //rufe expand auf
	return (EXIT_SUCCESS);
}
void expand(FILE *datei) //expand funktion convertiere \t zu leerzeichen
{
	int z;
	char satz[MAX],satzteil[MAX]; 
	while(fgets(satz,MAX,datei)) //Solange ausführen bis das Dateiende erreicht ist
	{
		for(int j=0,i=0; i<MAX;i++,j++) //for-schleife zum iterieren
		{
			satzteil[i] = satz[j]; //string auf string setzen
			if(satz[j] == '\\' && satz[j+1] == 't') //ist die position mit einem \ erreicht, nächste stelle prüfen ob die = t ist
			{
				z = j; //z = j setzen
				j+=2; //j inkrementieren um hinter das \t zu kommen
				if((z%8)==0) //ist z%8=0, 8 leerzeichen einfuegen
					for(int k=0; k<8;k++) //
						satzteil[i++] = ' '; //
				while((z%8) != 0) //ist z != 8, solange ausfuerhen bis z%8 = 0 ist
				{
					satzteil[i++] = ' ';
					z++;
				}
			}
			satzteil[i] = satz[j]; //stringsaneinanderhängen
		}
	  fputs(satzteil,stdout); //zeile ausgeben und von vorne anfangen.
	}
}