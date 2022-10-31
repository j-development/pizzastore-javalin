Planen är att bygga upp vår pizza backend från föregående kurs med TDD metodik.

Vi kommer att inleda med unittester och när varje enskild enhet fungerar kommer vi att gå över
till integrationstester. 

Strategin för integrationstesterna vi valt är Big-Bang där vi testar javalin-endpointsen.
Detta har vi valt av tre anledningar. 
1. För att javalin är vår endpoint och kör vi dess funktioner kommer
vi också köra controller, service och repo.
2. Vi har redan kört unittester på varje enskilt lager och enhet. Detta gör att integrationstestets
syfte är att testa integrationen och inte varje enskild del. Därav(i vårt mindre projekt)
är det lika bra att köra allt i ett.
3. Då vi skapar javalin-endpointsen direkt i början är dessa de enda som är otestade med hjälp av kod-test.

Steg:
1. Starta upp en javalin app samt skapa handlers
För att kunna följa TDD behöver vi ha någon typ av kravspec för att veta vilka tester vi behöver skriva. 
Därför har vi skapat ett gäng endpoints som motsvarar vår kravspecifikation. Syftet med detta utöver att de får agera
vår kravspec är också att vi vill ha ett program som är up and running. Även om vi kör TDD finns det en vinning i att snabbt
se så att våra frameworks fungerar/i detta fall javalin.
Problem/Lösning:
Det går inte att importera context. Vid snabb informationssökning fanns det inte särskilt bra information om varför
det helt enkelt inte funkade. Mindes att IntelliJ22 var nämnt i klasschatten. Uppdaterade IntelliJ. Fungerade direkt efter.