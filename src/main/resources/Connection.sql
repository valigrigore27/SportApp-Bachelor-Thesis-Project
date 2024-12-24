select * from sport;
-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- creare tabela 
create table sports_venues (
    id integer primary key autoincrement,
    name text not null,
    type text,
    location text,
    phone text,
    unique(name)
)

select * from sports_venues;
/*drop table sports_venues;*/
select * from sports_venues where location like '%nicolina%';
update sports_venues set type = 'Centru de �not' where id = 11;


--populare sali de fitness(+10 care nu sunt sali fitness)
insert into sports_venues(name, type, location, phone)
values 
(
"Body Line GYM � Tatarasi",
"Sal? de fitness",
"Str. Ciurchi nr 128",
"0736 211 171"
),
(
"Body Line GYM � Alexandru",
"Sal? de fitness",
"?oseaua Na?ional? 5, Ia?i 700259",
"0736 211 171"
),
(
"Body Line GYM � Dacia",
"Sal? de fitness",
"Strada Rampei 9A",
"0736 211 171"
),
(
"Let's Move",
"Sal? de fitness",
"Strada Carpa?i 11, 47.1431090 27, Ia?i 700729",
"0744 451 461"
),
(
"Weider Gym- Sal? de Fitness, Sala de Sport Ia?i",
"Sal? de fitness",
"Strada Nicolae Costin 26, Ia?i 700373",
"0770 181 023"
),
(
"Young Gym",
"Sal? de fitness",
"Strada Atelierului 14B, Ia?i 700182",
"0726 777 544"
),
(
"Ser Gym",
"Sal? de fitness",
"Strada Valea Ad�nc? 11, Ia?i 700712",
"0746 756 111"
),
(
"OanaGoldGym Fitness",
"Sal? de fitness",
"Complex Stejar, Bloc, Strada Stejar 54, Ia?i 700327",
"0743394490"
),
(
"Well Time Fitness",
"Sal? de fitness",
"Casa Leb?da, ?oseaua Bucium 23, Ia?i 700282",
"0757 142 762"
),
(
"Jade Gym",
"Sal? de fitness",
"Asociatia Art Residence, Bloc 619, Parter, Strada Ovidiu 4A, Ia?i 700676",
"0332 730 706"
),
(
"Vivertine | Sal? Fitness, Spa, Piscin?",
"Sal? de fitness",
"Strada Anastasie Panu 26, Ia?i 700025",
"0799 505 136"
),
(
"A-GYM Sala Fitness Bucium Iasi. Sala Forta, Gym, Personal Trainer Iasi",
"Sal? de fitness",
"?oseaua Bucium 55k, Ia?i 700278",
"0744 488 832"
),
(
"HORNET FIT",
"Sal? de fitness",
"?os. Nicolina 39, Ia?i 700688",
"0770 195 624"
),
(
"Fantastic Fit Metalurgie",
"Sal? de fitness",
"Bulevardul Chimiei, Nr. 8, Ia?i",
"0741 080 817"
),
(
"Oxygen Sports & SPA",
"Sal? de fitness",
"Strada Pantelimon Halipa 10, Ia?i 700614",
"0747 101 177"
),
(
"MOTIVATION GYM Royal Town",
"Sal? de fitness",
"Aleea Mihail Sadoveanu 59, Ia?i 700489",
"0746 283 908"
),
(
"ProGym",
"Sal? de fitness",
"?oseaua Na?ional? 23, Ia?i 700259",
"0771 467 242"
),
(
"EnerGym Moara de foc 15",
"Sal? de fitness",
"Sos. Moara de Foc, 15, Iasi, Iasi, 700520, Ia?i 707410",
"0745 637 244"
),
(
"Attitude Aerobic",
"Sal? de fitness",
"Strada Pictor Octav B?ncil? 1, Ia?i 700515",
"0722 379 354"
),
(
"Metalmorphose Gym",
"Sal? de fitness",
"Bloc B10, Strada Vitejilor 16, Ia?i 700650",
"0751 791 803"
),
(
"Stay Fit Gym - Palas Campus Ia?i",
"Sal? de fitness",
"Strada Iancu Bacalu, Ia?i 700029",
"0773 831 210"
),
(
"Fantastic Fit Podu Ros",
"Sal? de fitness",
"Ia?i 700213",
"0751 035 050"
),
(
"EnerGym Sfantul Lazar 04",
"Sal? de fitness",
"Bloc Pene? Curcanul, Strada Sf�ntul Laz?r 4, Ia?i 700044",
"0744 619 658"
),
(
"World Class Ia?i",
"Sal? de fitness",
"Strada Anastasie Panu 31, Ia?i 700020",
"0757 116 649"
),
(
"Flex Gym",
"Sal? de fitness",
"Strada Palat 2, Ia?i 700259",
"0332 415 415"
),
(
"Xbody Exclusive",
"Sal? de fitness",
"?oseaua S?r?rie 34, Ia?i 700765",
"0742 493 970"
),
(
"Fitness Hub",
"Sal? de fitness",
"Aleea Mihail Sadoveanu 59 A, Ia?i 707027",
"0747 938 522"
),
(
"Motivation Gym Sun City",
"Sal? de fitness",
"?oseaua Voine?ti 46F, Ia?i 700620",
"0746 283 908"
),
(
"Endorfine Gym",
"Sal? de fitness",
"Calea Chi?in?ului 29, Ia?i 700177",
"0742 279 106"
),
(
"Sala de Fitness Brainfit Iasi",
"Sal? de fitness",
"DJ248 15, Lunca Cet??uii 707085",
"0770 496 443"
),
(
"Raf Gym",
"Sal? de fitness",
"Casa Sindicatelor, Bulevardul Socola nr. 134, Ia?i 700186",
"0770 353 862"
),
(
"The Rock Gym Studis",
"Sal? de fitness",
"Strada Otilia Cazimir 10, Ia?i 700400",
"0773 960 342"
),
(
"Club D Iasi",
"Sal? de fitness",
"Bulevardul Independen?ei nr. 3, Ia?i 700106",
"0745 430 135"
),
(
"Kangoo Club Iasi",
"Sal? de fitness",
"Bl.3A+3B, Mezanin, Bulevardul Anastasie Panu 13, Ia?i 700259",
"0723 677 474"
),
(
"Symphony Exclusive Club",
"Club de spa ?i s?n?tate",
"Exclusive Residence, ?oseaua S?r?rie nr.4, Ia?i 700452",
"0741 119 706"
),
(
"Axis Gym",
"Sal? de fitness",
"Strada Petre Andrei 7, Ia?i 700496",
""
),
(
"La Playa Fitness Gym",
"Sal? de fitness",
"�n sta?ia OMW Petrom, ?oseaua Bucium 15, Ia?i",
"0745 456 583"
),
(
"Aerobic cu Dana",
"Sal? de fitness",
"Strada Dudescu 12, Ia?i 700259",
"0753 327 346"
),
(
"Stay Fit Gym Iulius Mall Iasi",
"Sal? de fitness",
"Bulevardul Tudor Vladimirescu, Ia?i 700305",
"0774 658 184"
),
(
"dojo gym",
"Sal? de fitness",
"Aleea Vasile Alecsandri, Ia?i 700259",
"0723 002 226"
),
(
"Hill Center Sport & Spa",
"Sport & Spa",
"?oseaua Bucium 79, Ia?i 700720",
"0332 408 366"
),
(
"Box Club CSM Ia?i",
"Club de box",
"Strada Teodor Burada nr 5, Ia?i 900160",
"0740 419 909"
),
(
"Metalmorphose Gym Antibiotice",
"Sal? de fitness",
"Valea Lupului 707410",
"0757 225 779"
),
(
"DAO Center Ia?i",
"Loca?ie pentru activit??i sportive",
"Strada Vasile Lupu 146a, Ia?i 700259",
"0735 183 343"
),
(
"GymBox",
"Sal? de box",
"Strada Anastasie Panu 27, Ia?i 700023",
"0750 206 363"
),
(
"Body Time Iasi",
"Sal? de fitness",
"Strada Arcu 21, Ia?i 700259",
"0731 763 024"
),
(
"Refresh Gym",
"Sal? de fitness",
"Strada Anastasie Panu 56, Ia?i",
"0760 720 770"
),
(
"ZAZEN FIT",
"Sal? de fitness",
"?oseaua Arcu 78, Ia?i 700136",
"0740 031 662"
),
(
"The Rock Gym Copou",
"Sal? de fitness",
"Strada Toma Cozma 9, Ia?i 700554",
"0773 960 327"
),
(
"Sala de atletism �Maricica Puic?� - Gr?dinari",
"Sal? de fitness",
"Bulevardul Chimiei, Ia?i",
""
),
(
"OneGym",
"Sal? de fitness",
"Strada Profesor Petru Olteanu 87, Tome?ti 707515",
"0742 224 888"
),
(
"Sala polivalenta",
"Complex sportiv",
"Strada Palat 2, Ia?i 700032",
"0232 234 857"
),
(
"Climb Again Sala de Escalada",
"Sal? de Escalad?",
"Stradela Plopii F?r? So? 18-22, Ia?i",
"0734 011 115"
),
(
"Scorpions Kick Boxing Ia?i",
"Sal? de box",
"Club Sportiv Scorpions, Strada Grigore Ureche 7B, 700022",
"0744 213 967"
),
(
"Talpiz Box Academy Ia?i",
"Sal? de box",
"?oseaua Na?ional? 23, Ia?i 700259",
"0747 978 858"
),
(
"Tornado Fight Gym",
"?coal? de kickboxing",
"Strada Han T?tar 10, Ia?i 700349",
"0743 807 927"
),
(
"Provocatores Gym",
"Sal? de fitness",
"?oseaua Bucium 21, Ia?i",
""
);

-- ------------------------------------------------------------------------------------
--populare terenuri fotbal(+Complex sportiv)
insert into sports_venues(name, type, location, phone)
values 
(
"Dribling Arena",
"Teren de fotbal",
"Dribling Arena, Ia?i",
"0743 490 053"
),
(
"Teren Fotbal",
"Teren de fotbal",
"Strada Cicoarei, Ia?i 707515",
"0757 630 506"
),
(
"Magic Arena Heineken",
"Teren de fotbal",
"Campus Tudor Vladimirescu, Fundac Mitropolit Iosif Naniescu 6, Ia?i 700562",
"0722 376 349"
),
(
"Magic Sport Center Alexandru",
"Club sportiv",
"?oseaua Na?ional? 42C, Ia?i 700605",
"0722 376 349"
),
(
"La Locomotiva - Teren de fotbal sintetic",
"Teren de fotbal",
"Bulevardul Tudor Vladimirescu, Ia?i 700259",
"0758 791 486"
),
(
"Teren sintetic de fotbal - Universitatea Tehnic? �Gheorghe Asachi� din Ia?i",
"Teren de antrenament sportiv",
"Str. Ciurchi 84, Ia?i 700313",
""
),
(
"D&C Sport",
"Complex sportiv",
"Bulevardul Poitiers 177, Ia?i 707317",
"0232 277 071"
),
(
"Fotbal Mania",
"Teren de fotbal",
"Bulevardul Chimiei 8, Ia?i 707252",
"0740 834 460"
),
(
"Baza Sportiv? Miroslava",
"Club sportiv",
"Strada Neculai Zaharia, Ia?i 707306",
"0759 655 958"
),
(
"Stadionul Tineretului",
"Teren de antrenament sportiv",
"Strada Avia?iei 27, Ia?i 707252",
""
),
(
"Teren fotbal Balciu",
"Antrenament de fotbal",
"Teren fotbal Balciu, Miroslava",
""
),
(
"Baza Sportiva Ciric",
"Complex sportiv",
"Str. Moara de Vant 171, Ia?i 707023",
"0774 933 807"
),
(
"Sportul Junior Ia?i -?oimii viole?i",
"Club de fotbal",
"Bulevardul Poitiers 177, Ia?i 707317",
"0742 463 756"
),
(
"La Mocanita - Teren de Fotbal Sintetic",
"Club sportiv",
"Calea Chi?in?ului, Ia?i 700265",
"0758 791 486"
),
(
"Arena Didi Junior",
"Club sportiv",
"Strada Voinicilor, Ia?i",
""
),
(
"ACS INTER IASI",
"Club de fotbal",
"Str. Olarilor, Lunca Cet??uii",
"0773 831 300"
),
(
"ACS INTER IASI1",
"Club de fotbal",
"Strada Hlincea nr 71 Etaj 4, Ia?i 700715",
"0773 831 300"
),
(
"PADBOL ARENA IASI",
"Club sportiv",
"Strada Niciman 2, Ia?i",
"0742 359 573"
),
(
"Arena Dragonilor",
"Complex sportiv",
"General Emanoil Dascalu, Balciu 707306",
"0762 976 304"
),
(
"Baza Sportiv? a Universit??ii de ?tiin?e Agricole",
"Complex sportiv",
"Aleea Mihail Sadoveanu, Ia?i 700489",
""
),
(
"Clubul Sportiv Politehnica Iasi",
"Club de fotbal",
"Nr. 38, Strada Lasc?r Catargi 38, Ia?i 700107",
"0232 114 731"
);

-- ------------------------------------------------------------------------------------
--populare inot
insert into sports_venues(name, type, location, phone)
values
(
"Club Sportiv JUNIOR SWIM Iasi",
"Centru de �not",
"Bloc H4B, Strada Pantelimon Halipa 6B, Ia?i 700614",
"0741 347 498"
),
(
"Club Sportiv TriSport Ia?i",
"Centru de �not",
"Strada Pantelimon Halipa, Ia?i",
"0745 730 825"
),
(
"World Class Ia?i1",
"Centru de �not",
"Strada Anastasie Panu 31, Ia?i 700020",
"0757 116 649"
),
(
"Zenity Spa",
"Club de spa ?i s?n?tate",
"la Electra, 29, Ia?i 700177 (in spate, Calea Chi?in?ului, Ia?i 700177)",
"0755 070 566"
);

-- ------------------------------------------------------------------------------------
--populare balet/dans
insert into sports_venues(name, type, location, phone)
values
(
"Add'Dance",
"?coal? de balet",
"Aleea Gr?dinari 7, Ia?i 700361",
"0747 343 021"
),
(
"Odette Academy - clase de gimnastic? ?i balet",
"?coal? de balet",
"Aleea Mihail Sadoveanu 59f, Ia?i 700489",
"0760 607 929"
),
(
"LEVITA Sal? de ballet ?i stretching",
"Sal? de dans",
"Bulevardul Carol I 4, Ia?i 700505",
"0746 908 700"
),
(
"?coala de dans Bel ART",
"?coal? de dans",
"Centrul Civic, str. Grigore Voda Ghica (langa Banc Post - Cladirea de sticla), Ia?i 700259",
"0770 195 861"
),
(
"Original Ballet & Dance",
"?coal? de dans",
"Bulevardul Socola 134, Ia?i 700187",
"0758 559 839"
),
(
"Dance Studio Iasi",
"Punct de instructaj dans",
"Strada Miroslava 8, Ia?i 700732",
"0743 939 970"
),
(
"Prime Agency-?coal? de balet&gimnastic?",
"?coal? de dans",
"Zenity Spa, Calea Chi?in?ului 29, Ia?i 700100",
"0758 533 535"
),
(
"Compania de Dans si Entertainment The Sky",
"?coal? de dans",
"Strada Vasile Conta nr. 30, Ia?i 700106",
"0740 655 797"
),
(
"Art &Passion Dance",
"?coal? de dans",
"Str.Garoafei nr.28 bl.CCR7 spatiul comercial demisol 2, Valea Lupului 707410",
"0785 130 955"
),
(
"Spirit of dance Iasi",
"Club de dans",
"Strada Vasile Conta 30, Ia?i",
"0745 810 119"
),
(
"Tazu Dance - Street Dance Iasi",
"?coal? de dans",
"Strada Vasile Conta nr. 30, Ia?i 700106",
"0743 520 481"
),
(
"Scoala de Dans The Sky",
"?coal? de dans",
"str. Oastei ( in spate la SuperCopou ) fostul Club Canabis, Ia?i 700478",
"0740 655 797"
),
(
"Sensation Dance Studio",
"?coal? de dans",
"Strada Garabet Ibr?ileanu 4, Ia?i",
"0745 480 731"
);

-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------

create table if not exists my_user (
    id integer primary key autoincrement,
    username text not null,
    password text not null
    );
ALTER TABLE my_user ADD COLUMN liked_exercises TEXT;
ALTER TABLE my_user ADD COLUMN liked_recipes TEXT;
ALTER TABLE my_user ADD COLUMN liked_venues TEXT;
update my_user set liked_exercises = '' where id = 1;
update my_user set liked_recipes = '' where id = 1;
update my_user set liked_venues = '' where id = 1;
select * from my_user;

--O cheie ext (FOREIGN KEY) este o constrangere folosita pentru a lega doua tabele
--(DELETE CASCADE) - daca un utilizator din tabela User este sters, toate relatiile de prietenie care
-- contin acel utilizator vor fi automat sterse din tabela Friend.
create table Friend (
    id integer primary key autoincrement,
    user1_id integer not null,
    user2_id integer not null,
    accepted boolean not null default 0,
    foreign key (user1_id) references User (id) on delete cascade,
    foreign key (user2_id) references User (id) on delete cascade
);

delete from "Friend" where id = 26;
select * from Friend;

create table Message (
    id integer primary key autoincrement,
    sender_id integer not null,
    receiver_id integer not null,
    content text not null,
    time datetime default current_timestamp,
    foreign key (sender_id) references User (id) on delete cascade,
    foreign key (receiver_id) references User (id) on delete cascade
);

select * from "Message";
drop table "Message";