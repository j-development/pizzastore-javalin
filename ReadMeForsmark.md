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


Main Integrationstest
I MainTest testar vi alla units integrerade med varandra.
Vi testar genom att skicka in olika request och mäter vad responsen blir. Flödet består utav själva appen, 
pizzacontroller, pizzaservice och en InMemoryDatabas.

Vi har valt att använda oss utav de faktiska klasserna och manuellt mocka dom istället för att skapa en mock
av ett interface. Detta är för att vi vill använda oss av hela flödet och försäkra oss om att allt fungerar som det ska,
inte förutbestämma en respons typ som nedan:
Mockito.when(repository.getByName(pizza.getName())).thenReturn(Optional.of(pizza));
Fördelarna med att använda mockito som ovan är att man utan att veta hur själva "databasen" ser ut, och utan att behöva oroa sig om det kan
vara något fel i databasconnectionen, kan bestämma vad utfallet ska bli vad som ska hända om man kallar en metod med en viss parameter. Man plockar alltså bort osäkerheten och 
felrisken från själva databas connectionen.
Detta är av större vikt då man har en yttre databas som "inte tillhör" programflödet. Men då vi vill testa hela flödet och vår in-memorydatabas tillhör flödet
så har vi istället valt att använda oss utav dependency injection direkt i InMemory databasen. Alltså skickar vi in en hashmap när vi skapar mockobjektet repository. 
Då kan vi utifrån(från testklassen faktiskt styra exakt vad databasen ska innehålla).
T.ex. kör vi en databas restet inför varje test så att vi på ett enkelt sätt kan räkna ut vad vi bör förvänta oss.
Vi har också en comparisionStorage som vi innför alla test sätter så den är likvärdig med vår testStorage. Detta för att vi inte vill testa
en respons direkt mot den faktiska databsen. T.ex. om getPizza metoden råkar radera en pizza och vi testar responsen mot den faktiskta databasen
kommer vi tro att testet är rätt men den faktiskt har raderat en pizza.

Ibland pratas det om att man endast ska ha en assertion i varje test. Kanske framförallt i unittests. Vi har valt att använda oss
utav flera assertions i ett test. JUnit ger tydliga felmeddelanden så att det är lätt att utläsa vad som gått fel. Detta
gör att vi slipper upprepa kod alltför mycket och får mindre testkod att underhålla. Nackdelen är att om en assertion blir fel så ser vi endast
det felmeddelandet. Det kan finnas en stor vinning i att t.ex. se om respons bodyn har blivit rätt fastän statusen är fel. Detta gör det enklare att
få reda på vart man ska börja leta efter felet.

En svårighet med att göra både unittester och integrationstester är att det är svårt att veta vart uppdelningen ska vara.
Det blir lätt att man kanske testar status på flera olika unittester och i integrationstestet. När blir det ett överflöd av tester? När
är dom relevanta?