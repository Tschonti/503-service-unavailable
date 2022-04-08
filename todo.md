# TODOS

- this should be private, but we need to use it for some tests `Virologist -> moveTo`
- 4.4.1 szerint geneticCodeokat kéne visszaadnia, de usecaseben úgysincs `Map -> createMap()`
- A virológus ne ismerje a controllert -> ne felvételkor hívja a virológus, hanem minden kör végén a controller intézze
- getAcrionsLeft nem jó while okban
    nem kell a stunhoz és a vitushoz getActionsLeft, elég ha numberOfActions -ször lefut a ciklus. 
## 7es doksi elejéről (ezeket változtattuk):
nem igazán todo, de ne vesszen el
- Class Equipment: #usesLeft: int
- Class BearDanceVirus
- Class Virologist: -dropEquipment(eq: Equipment)
- Class Controller: +removeVirologist(v: Virologist)
- Interface Effect: +infect(Virologist: infector):void – ez agentre, equipmentre is
- Class InfectedLaboratory
- BearDanceVirusImpact sequence diagram
- PlayerMove sequence diagram
- Glove counterimpact plusz opt
- Interface useableEquipment: +use(v: Virologist)
- class Axe extends Equipment implements usableEquipment
- BearDanceVirusOnTurnImpact sequence
- BearDanceImpact sequence diagram
- UseAxe sequence diagram
- MoveTo infectedLaboratory
- AxeOnTurnImpact sequence diagram

## GenCode, Inv, Controller kérdések, TODOk
Controllerben 
- akkor most nem kell addPlayer, hanem helyette van a removeVirologist?????
- attribútumokon is változtattam kicsit a remove miatt, ezeket classdiagramba?
- startGame mit fog csinálni?


GeneticCodeban
- a cloneCollectable fv nincs fent classdiagramon, doksiba gondolom akkor is kell

UsableEquipment nincs bent a kódban
