# torbenschopshop

tl;dr

Bruger kan oprette dyr gennem post-commands, som bliver sent til en server gennem gRPC og gemt i en online Postgresql database
Bruger har mulighed for at slagte det dyr ved at referere til dyrets ID - dyret bliver hentet fra DB på samme måde som det blev sendt, 
dyrets type (ko, gris, kylling) bliver vuderet og derefter slagtet iht. hvilket slags dyr. Dette laver "parts" som også bliver gemt i databasen med reference til det originale
dyrs ID så man altid kan gå tilbage og tjekke hvor det kom fra.

Parts bliver bragt til pakkestationen igennem trays som er en carrier-klasse som kun kan have én slags part af gangen for at undgå krydskontaminering.
Pakkestationen laver en package med reference til dyret og trayets ID, så alting kan spores tilbage.
