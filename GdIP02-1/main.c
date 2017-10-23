#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void)
{
	setbuf(stdout, NULL);
	unsigned long long int zahl,merker,rechnung,i; //unsigned = nur positive werte, long long int = großer werte bereich = des double
	int count; //zähler variable
	char input; //input string
	while(1) //endlosschleife
	{
		printf("Bitte geben Sie eine Zahl ein.\n");
		gets(&input); //Eingabe einfagen und in input speichern
		if(strchr(&input, '.') == NULL && strchr(&input, ',') == NULL && strchr(&input, '-') == NULL) //gespeicherte eingabe auf zeichen prüfen
		{
			zahl = atol(&input); //char array zu long konvertieren
			printf("Anfangswert || Durchlaeufe\n");
			for(i = 1; i <= zahl; i++) //for schleife zum durchlaufen des Algorithmus
			{
				merker = i; //wird = i gesetzt um die aktuelle zahl nutzen zu können.
				rechnung = i; // wird mit
				count = 0; //counter auf 0 setzen
				do
				{
					count++; //counter bei jedem durchlauf der Do-While Schleife um 1 erhöhen.
					if(rechnung%2 == 0) //prüfen ob modulo 2 = 0
					{
						rechnung /= 2;
					}
					else if(rechnung%2 != 0 && rechnung != 1) //falls modulo 2 != 0 hier ausführen
						rechnung = 3*rechnung+1;
				}
				while(rechnung != 1); //prüfen ob die zahl != 1 ist
			printf("%llu\t    || %d\n",merker=i,count); //ausgabe der zahlen und anzahl der durchläufe pro zahl
			}
			return EXIT_SUCCESS;
		}
		else
			printf("Bitte eine ganze positive Zahl eingeben!\n");
	}
}
