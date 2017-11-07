#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void)
{
	setbuf(stdout, NULL);
	long long int maximum,i, calcNumber = 0; //unsigned = nur positive werte, long long int = großer werte bereich = des double
							 //Zuweisung erfolgt bereits, um dem Zufall der "1" als Zuweisungswert aus dem Weg zu gehen.
	int count; //zähler variable
	char input; //input string
	while(calcNumber != 1) //endlosschleife
	{
		printf("Bitte geben Sie eine Zahl ein.\n");
		gets(&input); //Eingabe einfagen und in input speichern
		if(strchr(&input, '.') == NULL && strchr(&input, ',') == NULL && (maximum = atol(&input)) > 0) //gespeicherte eingabe auf zeichen prüfen 
		{																 //char array zu long konvertieren
			printf("Durchlaeufe || Anfangswert\n");
			for(i = 1; i <= maximum; i++) //for schleife zum durchlaufen des Algorithmus
			{
				calcNumber = i; // wird mit
				count = 0; //counter auf 0 setzen
				do
				{
					count++; //counter bei jedem durchlauf der Do-While Schleife um 1 erhöhen.
					if(calcNumber%2 == 0) //prüfen ob modulo 2 = 0
						calcNumber /= 2;
					else if(calcNumber%2 != 0 && calcNumber != 1) //falls modulo 2 != 0 hier ausführen
						calcNumber = 3*calcNumber+1;
				}
				while(calcNumber != 1); //prüfen ob die zahl != 1 ist
				printf("%d\t    || %llu\n",count,i); //ausgabe der zahlen und anzahl der durchläufe pro zahl
			}
		}
		else
			printf("Bitte eine ganze positive Zahl eingeben!\n");
	}
}