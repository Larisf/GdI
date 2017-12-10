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

/*
 * 
 */
char ersetze(char zeichen);
const int MAX=255;
int main(int argc, char** argv) 
{
	char zeichen, satz[MAX];
	FILE *datei;
	setbuf(stdout, NULL);
	if(argc < 2)
	{
		printf("Keine Datei gefunden, bitte taetigen Sie eine Eingabe:\n");
		fgets(satz,MAX,stdin);
		printf("%s",satz);
	}
	else
	{
		datei = fopen("test.txt","r");
		while((zeichen = fgetc(datei))!= EOF)
			putchar(zeichen);
	}
	return (EXIT_SUCCESS);
}