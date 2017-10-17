/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * File:   main.c
 * Author: Bambi
 *
 * Created on 12. Oktober 2017, 08:48
 */
//Standart includes
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
/*
 *
 */
//Enum zur besseren Lesbarkeit des Codes.
enum eError{FALSE,TRUE};
enum eCurrency{EURO,DOLLAR=6,POUND=16};
enum eInput{eurDol=1,dolEur,eurPou,pouEur,end};
//Konstanten: werden einmal initialisiert und dann nie wieder, der Compiler verhindert zudem eine Neuzuweisung.
const float euDo = 1.13;
const float euPo = 1.11;
//Anfang des eigentlichen Programms
int main(void)
{
	//Variablen deklarationen
	int error = FALSE;
	int input,arrayPos,checkSpace;
	char whiteSpace;
	float value;
	//Array fuer die existierenden Waehrungen
	char currency[] = {'E','u','r','o',' ','\0'
    /*6*/				,'U','S','-','D','o','l','l','a','r','\0'
	/*16*/				,'P','f','u','n','d','\0'};
	//array mit der Groesse der Anzahl der in currency[] gegebenen Elemente.
	//Division durch Typen des Arrays noetig, da sizeof() die Bytes zurueckgibt.
	char word[sizeof(currency)/sizeof(char)];
	//Endlosschleife
	while(1)
	{
		/*setbuf um das buffering des Standart-Outputs zu unterbinden. NULL = Ungebuffert, ansonsten durch BUFSIZ bestimmt.
		 * memset(*void adres, int zeichen, size_t n) Diese Funktion schreibt den Wert "0"
		 *an die naechsten "n"-stellen des Speichers von "b"
		*/
	//	setbuf(stdout,NULL);
	//	setbuf(stdin,NULL);
		memset(word, 0, sizeof(word));
		//Menu mit moeglichen Eingaben.
		printf("1: EURO ------> US-Dollar\n"
				"2: US-Dollar -> EURO\n"
				"3: EURO ------> Pfund\n"
				"4: Pfund -----> EURO\n"
				"5: Beenden\n"
				"Bitte geben Sie die gewuenschte Aktion ein:\n");
		if(error == FALSE)
			//Abfrage des Standart-Input Streams, Rueckgabe-Wert wird in checkSpace gespeichert. Hier: Wenn richtig = 2, ansonsten <= 0
			checkSpace = scanf("%d%c",&input,&whiteSpace);
		//Ueberpruefung ob die Rueckgabe korrekt ist. 2 Zeichen = korrekt.
		//Die issppace()-Funktion Ueberprueft, ob es sich um ein "Whitespace" handelt.
		if((checkSpace == 2 && isspace(whiteSpace)) || error == TRUE)
		{
			//switch-case handling fuer diverse Moeglichkeiten.
			switch(input)
			{
				case eurDol:
				{
					//Case-Abfragen: Nach einem Betrag fragen, Eingabe ueberpruefen und berechnen, oder weitergehen zur Fehlerbehandlung.
					printf("Sie haben den Wechsel von EURO zu US-Dollar gewaehlt. "
							"Der Wechselkurs beträgt: %.2f\nBitte geben Sie einen Betrag ein: ",euDo);
					checkSpace = scanf("%f%c",&value,&whiteSpace);
					if(checkSpace == 2 && isspace(whiteSpace))
					{
						value *= euDo;
						arrayPos = DOLLAR;
						error = FALSE;
						break;
					}
					else
					{
						error = TRUE;
						break;
					}
				}
				case dolEur:
				{
					printf("Sie haben den Wechsel von US-Dollar zu EURO gewaehlt. "
							"Der Wechselkurs beträgt: %.2f\nBitte geben Sie einen Betrag ein: ",euDo);
					checkSpace = scanf("%f%c",&value,&whiteSpace);
					if(checkSpace == 2 && isspace(whiteSpace))
					{
						value /= euDo;
						arrayPos = EURO;
						error = FALSE;
						break;
					}
					else
					{
						error = TRUE;
						break;
					}
				}
				case eurPou:
				{
					printf("Sie haben den Wechsel von EURO zu Pfund gewaehlt. "
							"Der Wechselkurs beträgt: %.2f\nBitte geben Sie einen Betrag ein: ",euPo);
					checkSpace = scanf("%f%c",&value,&whiteSpace);
					if(checkSpace == 2 && isspace(whiteSpace))
					{
						value *= euPo;
						arrayPos = POUND;
						error = FALSE;
						break;
					}
					else
					{
						error = TRUE;
						break;
					}
				}
				case pouEur:
				{
					printf("Sie haben den Wechsel von Pfund zu EURO gewaehlt. "
							"Der Wechselkurs beträgt: %.2f\nBitte geben Sie einen Betrag ein: ",euPo);
					checkSpace = scanf("%f%c",&value,&whiteSpace);
					if(checkSpace == 2 && isspace(whiteSpace))
					{
						value /= euPo;
						arrayPos = EURO;
						error = FALSE;
						break;
					}
					else
					{
						error = TRUE;
						break;
					}
				}
				case end:
					//return EXIT_SUCCESS zum beenden des Programms
					return EXIT_SUCCESS;
				default:
				{
					//Handling bei Eingabe einer Zahl > 5
					printf("\nBitte gueltige Auswahl treffen!22\n\n");
					break;
				}
			}
			//Ueberpruefung ob die Auswahl korrekt war, bevor es zur Verarbeitung mit ungueltigen Werten kommt.
			if(input < 6 && checkSpace == 2 && error == FALSE)
			{
				//for-Schleife zum zusammenbauen des gewuenschten Strings, mithilfe der oben erstellten Arrays.
				for(int i=0; (currency[arrayPos+i]) != '\0'; i++)
					word[i]  = currency[i+currency];
				//Ausgabe des Betrages und der Waehrung.
				printf("\nSie erhalten: %.2f %s\n\n",value,word);
			}
			else if(input < 6)
				printf("\nBitte geben Sie einen gueltigen Betrag ein!\n\n");
		}
		//Bei einer Eingabe, die zu einem Rueckgabewert != 2 fuehrt, wird dieser Teil ausgefuehrt.
		else
			printf("\nBitte gueltige Auswahl treffen!1\n\n");
	}
}
