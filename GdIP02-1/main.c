#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int hcp(int i);
int main(void)
{
	int i, maximum;
	char input; //input string
	setbuf(stdout, NULL);
	printf("Bitte geben Sie eine Zahl ein.\n");
	gets(&input); //Eingabe einfagen und in input speichern
	if(strchr(&input, '.') == NULL && strchr(&input, ',') == NULL && (maximum = atoi(&input)) > 0) //gespeicherte eingabe auf zeichen prüfen 
	{																 //char array zu long konvertieren
		printf("Durchlaeufe || Anfangswert\n");
		for(i = 1; i <= maximum; i++) //for schleife zum durchlaufen des Algorithmus
		{
			printf("%d\t    || %d\n",hcp(i),i); //ausgabe der zahlen und anzahl der durchläufe pro zahl
		}
	}
	else
		printf("Bitte eine ganze positive Zahl eingeben!\n");
}
int hcp(int i)
{
	int count, calcNumber;
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
	return count;
}