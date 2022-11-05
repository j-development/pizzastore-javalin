## Inledning & arbetsmetodik

Läs ReadMeForsmark.md

## Unittester
Unittesterna går vi i följande stycke igenom, commit efter commit. Det blir smidigt att referera till en commitlog i denna rapport då det är enkelt att gå tillbaka och minnas vad som gjorts och vad som gått fel, olika övervägningar etc. De kommande rubrikerna är motsvarande commits, där vi behandlar och återger ett sammanhang till commiten. I parentesen så finns en referens till testklassen.

(eg)
## Detta är commiten (Dettaärtestklasen.java)



## Pizzamodel added (PizzaTest.java)

Litet misstag här då vi la till Pizzamodellen först innan vi skrev testerna. I senare commits (`PizzaTest – succesfullyCreatePizzaAndTestConstructor, etc`) så testar vi pizzamodellen på två sätt, ett där pizzan skapas med framgång och ett där det misslyckas.

## testGetListOfPizzas (PizzaRepositoryTest.java)

Efter lite boilerplatekod och setupkod så skriver vi testet. Vi mockar en metod som ska returnera alla pizzor, vi har det som konvention att vi döper klassen som ska testas ”classUnderTest”. När vi skrivit testet och assertat det vi vill (att findAll() ska returnera en lista av alla pizzor) så skapar vi klassen som är under testning. Vi väljer ett interface i stället för en konkret klass för repositoryt. Det liknar förfarandet i Spring boot och det är vår övertygelse att det är smidigast att lämna det öppet för att testa olika implementationer av repositoryt.

Efter att repository-interfacet skapats kan vi går vidare i testningen. Vi annoterar testet med `@ExtendWith(MockitoExtension.class)` för att smidigt mocka och injecta vårt repository till ”classUnderTest” placeholdern.

Det andra testet i denna testklassen är deletePizzaByIdSuccessful och följer samma förfarande i princip. Vi experimenterar och sätter olika argument till Mockito.times(), en bra grej att garantera så en metod inte ska kunna köras flera gånger idempotent.

## PizzaServiceTest setup done (PizzaServiceTest.java)

Börjar med att skriva testet throwErrorWhenPizzaNameAlreadyTaken, i given sektionen så är först 2 pizzaobjekt angivna. Det visade sig sen inte vara nödvändigt så vi ändrade på det i en senare commit. I testet så använder vi assertThatThrownBy() från assertJ core biblioteket, syftet med det är att kunna testa så att en instans av ett specifikt exception kastas, med ett specifikt meddelande. Det kanske är möjligt att göra precis lika bra i det assert-bibliotek som kursen använt, men annars så är det av denna anledningen vi använt ett annat bibliotek.

I de efterföljande commitsen så skapar vi vårt custom Exception till i exceptions-paketet. I och med att vi vill testa om ett test throwar när vi lägger till en redan befintlig pizza så är det en addPizza-metod som vi först måste konstruera. Sen se till att den kastar vårt exception, när en pizza läggs till med samma namn.

Nästa test i denna testklass är successfullyUpdatePizzaWithId, vi skriver testet tills det är tillräckligt för att faila och så långt vi klarar av innan vi påbörjar den konkreta klassen. Vi vet i alla fall att repositoryt ska hitta en pizza med ett visst id så det mockar vi till en början.

I den konkreta klassen sen så kommer vi antagligen behöva två metoder för att klara testet, en updatePizza() metod och en findPizzaById(). För vi behöver ju hitta en pizza först innan vi kan uppdatera den. Här kastar vi också felet `BadRequestResponse("Bad Id Received")`, ett exception från javalin som vi tyckte kunde vara passande högre upp i projektet, så vi implementerade den felhanteringen redan här.

Åter till testklassen så prövar vi att använda ArgumentCaptor med vår Pizzaklass. Det var ett häftigt verktyg för att testa och verifiera de faktiska argument som skickas i de underliggande klasserna. Efter det så verifierar vi alla fält så de överensstämmer med objektet lokalt, och att det inte är samma objekt heller, för vi vill ju uppdatera fälten och inte skriva över med referens till samma objekt.


## PizzaControllerTest setup done (PizzaControllerTest.java)

Till en början vill vi testa så att en GET request till en controllermetod ger oss en lista till alla pizzor. Vi kallar testet ”GET_ToReceiveAllPizzasSuccessfully” och vi behöver mocka getAllPizzas() från servicen  så att får en lista över alla pizzor. I den konkreta klassen så skapar vi en controllermetod som returnerar en Handler, har sett lite olika sätt där man använder statiska implementationer men det sätts i motsats till den generella regeln att man ska undvika static till förmån för objekt för att uppnå separation of concerns och använda sig av dependency injection, för enklare testing och mer modulär kod. På grund av detta så behöver vi i ett senare skede skapa objekt och Overrida repository-interfacet i mainklassen.

Testet var inte heller fullständigt från början men vi uppdaterade med att mocka contexten (mock(Context.class))och använda den för att verifiera att controllermetoden skickar tillbaka pizzorna med json().

Vi började med att implementera unittester för följande:
POST_ToCreateNewPizzaSuccessfully
PUT_ToUpdatePizzaSuccessfully
Båda dessa tester behöver en requestbody i form utav en jsonstring. Vi hittade inget sätt att ändra bodyn för javalins context. Man skulle kunna använda javalins
test metoder och skapa en mockapp för att testa men så arbetar vi i integrationstesten och med den tidsgränsen vi har valde vi att lämna testet 
för att visa på problemet men exemplifiera lösningen i integrationstestet istället.

## Integrationstest
Läs ReadMeForsmark.md
