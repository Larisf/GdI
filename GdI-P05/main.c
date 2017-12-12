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
void tabulator(int argc); //bekanntmachen der tabulator funktion
int main(int argc, char** argv) 
{
	setbuf(stdout, NULL); //Output_Buffer auf NULL setzen
	tabulator(argc); //Funktion für den Algorithmus
	return (EXIT_SUCCESS);
}
void tabulator(int argc) //Funktion für den Algorithmus
{
	char satz[MAX], satzteil[MAX]; //Arrays für die Eingabe/Inhalte der Datei
	FILE *datei; //File typ mit einem Zeiger für die Datei
	if(argc < 2) //wenn input der Argumente beim programmstart < 2 dann:
	{
		printf("Keine Datei gefunden, bitte taetigen Sie eine Eingabe:\n"); //ausgabe, wenn argumente <2
		fgets(satz,MAX,stdin); //pointer auf die stelle wo es hin kopiert wird mit einer maximalen laenger MAX aus der Standarteingabe
	}
	else if((datei = fopen("test.txt","r")) != NULL) //argc > 2 dann öffne die datei mit dem Namen "NAME" zum lesen, wenn vorhanden
	{
		fgets(satz,MAX,datei); //pointer auf die stelle wo es hin kopiert wird mit einer maximalen laenger MAX aus der Datei
		fclose(datei); //datei schließen
	}
	for(int i=0,j = 0; i <MAX;i++,j++) //algorithmus um \t durch Leerzeichen zu ersetzen
	{
		satzteil[i] = satz[j]; //eingelesenen string in zweiten stringkopieren
		if(satz[j] == '\\' && satz[j+1] == 't') //ist ein \t erreicht dann:
		{	
			j+=2; //eingelesenen string um 2stellen nach rechts rücken um das \t loszuwerden
			for(int z= 0; z<8;z++) //8 mal durchlaufen
			{
				satzteil[i] = ' '; //die i-te stelle mit einem leerzeichen belegen
				i++; //i inkrementieren
			}
		}
		satzteil[i] = satz[j]; //eingelesenen string an der stelle nach dem \t an zweiten string anfügen
	}
	printf("%s",satzteil); //ausgabe
}