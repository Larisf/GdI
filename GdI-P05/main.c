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
void expand(FILE *datei);
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
	int c=0; 
	while((c=fgetc(datei)) != EOF) //solange bis das datei ende erreicht wurde
	{
		if(c=='\\') //ist c = '\'
			if((c=fgetc(datei))=='t') //ist der darauf folgende character = t
				for(int i=0; i<7;i++) //solange ausführen bis i = 6 (7x dank der 0)
				{
					c = ' '; //c = leerzeichen setzen
					putchar(c); //leezeichen in console schreiben
				}
		putchar(c); //character der datei in console schreiben
	}
}