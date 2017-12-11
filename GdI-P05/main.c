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
const int MAX=255;
void kopiere(FILE *datei);
int main(int argc, char** argv) 
{
	char satz[MAX],satzteil[MAX];
	FILE *datei;
	setbuf(stdout, NULL);
	if(argc > 2)
	{
		printf("Keine Datei gefunden, bitte taetigen Sie eine Eingabe:\n");
		fgets(satz,MAX,stdin);
		printf("%s",satz);
	}
	else if((datei = fopen("test.txt","r")) != NULL)
	{
		fgets(satz,MAX,datei);
		//kopiere(datei);
		fclose(datei);
		for(int i=0,j = 0; i <MAX;i++,j++)
		{
			satzteil[i] = satz[j];
			if(satz[j] == '\\' && satz[j+1] == 't')
			{	
				j+=2;
				for(int z= 0; z<8;z++)
				{
					satzteil[i] = ' ';
					i++;
				}
			}
			satzteil[i] = satz[j];			
		}
		printf("%s",satzteil);
	}
	return (EXIT_SUCCESS);
}
void kopiere(FILE *datei)
{
	char zeichen;
	while((zeichen = getc(datei)) != EOF)
		putchar(zeichen);
}
