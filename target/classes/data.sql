
INSERT INTO korisnik (id, adresa, email, ime, lozinka, mesto_stanovanja, potvrdjen_mail, prezime, tip_korisnika, promenjena_lozinka)
VALUES (1, 'A' , 'admin@gmail.com', 'admin', 'admin', 'A',FALSE, 'A','sistemAdministrator', null);
INSERT INTO korisnik (id, adresa, email, ime, lozinka, mesto_stanovanja, potvrdjen_mail, prezime, tip_korisnika, promenjena_lozinka)
VALUES (2, 'marija' , 'marijakukice2@gmail.com', 'marija', 'marija', 'Kukic',true, 'kukic','registrovanKorisnik', null);
INSERT INTO korisnik (id, adresa, email, ime, lozinka, mesto_stanovanja, potvrdjen_mail, prezime, tip_korisnika, promenjena_lozinka)
VALUES (3, 'A' , 'damir.kkc@gmail.com', 'a', 'marija', 'a',true, 'a','registrovanKorisnik', null);
INSERT INTO korisnik (id, adresa, email, ime, lozinka, mesto_stanovanja, potvrdjen_mail, prezime, tip_korisnika, promenjena_lozinka)
VALUES (4, 'b' , 'b@gmail.com', 'b', 'b', 'b',false, 'b','registrovanKorisnik', null);
INSERT INTO korisnik (id, adresa, email, ime, lozinka, mesto_stanovanja, potvrdjen_mail, prezime, tip_korisnika, promenjena_lozinka)
VALUES (5, 'c' , 'c@gmail.com', 'c', 'c', 'c',true, 'c','registrovanKorisnik', null);
INSERT INTO korisnik (id, adresa, email, ime, lozinka, mesto_stanovanja, potvrdjen_mail, prezime, tip_korisnika, promenjena_lozinka)
VALUES (6, 'Administrator bioskopa/pozorista' , 'bp@gmail.com', 'Admin Bioskopa/Pozorista', 'bp', 'Admin Bioskopa/Pozorista Adresa', FALSE, 'Admin Bioskopa/Pozorista prezime', 'adminBP', FALSE);
INSERT INTO korisnik (id, adresa, email, ime, lozinka, mesto_stanovanja, potvrdjen_mail, prezime, tip_korisnika, promenjena_lozinka)
VALUES (7, 'Administrator Fan Zone' , 'fz@gmail.com', 'Admin Fan Zone', 'fz', 'Admin Fan Zone Adresa', FALSE, 'Admin Fan Zone', 'adminFAN', FALSE);



insert into prijateljstvo(id, id_korisnik1, id_korisnik2, prijatelji, zahtev_poslao, poslat_zahtev)
values (1,2, 3, TRUE, 2, TRUE);
insert into prijateljstvo(id, id_korisnik1, id_korisnik2, prijatelji, zahtev_poslao, poslat_zahtev)
values (2,3, 2, TRUE, 2, TRUE);
insert into prijateljstvo(id, id_korisnik1, id_korisnik2, prijatelji, zahtev_poslao, poslat_zahtev)
values (3,2, 4, TRUE, 2, TRUE);
insert into prijateljstvo(id, id_korisnik1, id_korisnik2, prijatelji, zahtev_poslao, poslat_zahtev)
values (4,4, 2, TRUE, 2, TRUE);
insert into prijateljstvo(id, id_korisnik1, id_korisnik2, prijatelji, zahtev_poslao, poslat_zahtev)
values (5,2, 5, TRUE, 2, TRUE);
insert into prijateljstvo(id, id_korisnik1, id_korisnik2, prijatelji, zahtev_poslao, poslat_zahtev)
values (6,5, 2, TRUE, 2, TRUE);

insert into teatar(id, adresa, naziv, promotivni_opis, tip)
values (1,'Kod Zare', 'Arena Cineplex', 'Dobri filmovi', 'bioskop');
insert into teatar(id, adresa, naziv, promotivni_opis, tip)
values (2,'Kod Biga', 'Arena Cinestar', 'Ima svega', 'bioskop');
insert into teatar(id, adresa, naziv, promotivni_opis, tip)
values (3,'U centru', 'Srpsko narodno pozoriste', 'Ima dosta predstava na repertoaru', 'pozoriste');
insert into teatar(id, adresa, naziv, promotivni_opis, tip)
values (4,'Nije u centru', 'Jos jedno pozoriste', 'Promotivni opis', 'pozoriste');
insert into teatar(id, adresa, naziv, promotivni_opis, tip)
values (5,'Kod autobuske', 'Pozoriste', 'Odlican', 'pozoriste');

insert into sala (id, naziv, teatar_id)
values (1, 'Sala 1', 1);
insert into sala (id, naziv, teatar_id)
values (2, 'Sala 2', 1);

insert into segment(id, boja, naziv, sala_id)
values (1, '#ff0', 'Classic', 1);
insert into segment(id, boja, naziv, sala_id)
values (2, '#0ff', 'Balcon', 1);

insert into mesto (id, naziv, sala_id, segment_id, x, y)
values (1, '1', 1, 1, 50, 50);
insert into mesto (id, naziv, sala_id, segment_id, x, y)
values (2, '2', 1, 1, 150, 150);
insert into mesto (id, naziv, sala_id, segment_id, x, y)
values (3, '3', 1, 1, 250, 250);
insert into mesto (id, naziv, sala_id, segment_id, x, y)
values (4, '4', 1, 2, 50, 100);

insert into film(id, ime_reditelja, kratak_opis, naziv, poster, prosecna_ocena, spisak_glumaca, trajanje, zanr)
values (1, 'David Fincher', 'Film, snovi, itd.', 'Inception', 'https://ia.media-imdb.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SY1000_CR0,0,675,1000_AL_.jpg', 5, 'Leonardo DiCaprio i ekipa', '150', 'Drama, Sci-Fi');
insert into film(id, ime_reditelja, kratak_opis, naziv, poster, prosecna_ocena, spisak_glumaca, trajanje, zanr)
values (2, 'David Fincher', 'Strasno, itd.', 'Zodiac', 'http://i.idefix.com/cache/500x400-0/originals/0000000413312-1.jpg', 5, 'Neka ekipa', '150', 'Drama, Horror');


insert into projekcija (id, datum, teatar_id, film_id)
values (1, '20180419', 1, 1);
insert into projekcija (id, datum, teatar_id, film_id)
values (2, '20180411', 1, 2);

insert into termin (id, cena, sala_id, sala_naziv, vreme, projekcija_id)
values (1, 300, 1, 'Sala 1', '14:30', 1);
insert into termin (id, cena, sala_id, sala_naziv, vreme, projekcija_id)
values (2, 450, 1, 'Sala 1', '22:30', 1);

insert into rezervacija (id, korisnik_id, projekcija_id, teatar_id, mesto_id, termin_id)
values (1, 3, 1, 1, 4, 1);
insert into rezervacija (id, korisnik_id, projekcija_id, teatar_id, mesto_id, termin_id)
values (2, 2, 1, 1, 1, 1);
insert into rezervacija (id, korisnik_id, projekcija_id, teatar_id, mesto_id, termin_id)
values (3, 2, 1, 1, 2, 1);

insert into rekvizit (id, cena, datum, korisnik_id, naziv, opis, slika, stanje, teatar_id)
values (1, 400, null, null, 'Breaking Bad Glass', 'I AM THE DANGER', 'https://http2.mlstatic.com/tazas-sublimadas-souvenir-serie-breaking-bad-walter-white--D_NQ_NP_822701-MLA26467526553_112017-F.jpg', 'nov', 1);
insert into rekvizit (id, cena, datum, korisnik_id, naziv, opis, slika, stanje, teatar_id)
values (2, 1000, null, null, 'Heisenberg T-shirt', 'Heisenberg', 'https://www.serishirts.com/1269-2544-tm_large_default/breaking-bad-t-shirt-representing-heisenberg-black-white.jpg', 'nov', 1);
insert into rekvizit (id, cena, datum, korisnik_id, naziv, opis, slika, stanje, teatar_id, odobren)
values (3, null, '20180415', 2, 'Los Pollos Hermanos Notebook', 'Notebook based on popular TV Series Breaking Bad', 'http://www.delfi.rs/_img/artikli/2015/12/agenda_breaking_los_pollos_vv.jpg', 'polovan', 1, FALSE);
insert into rekvizit (id, cena, datum, korisnik_id, naziv, opis, slika, stanje, teatar_id, odobren)
values (4, null, '20180415', 3, 'Los Pollos Hermanos Top', 'Top based on popular TV Series Breaking Bad', 'https://cdn8.bigcommerce.com/s-phfhf3/images/stencil/500x659/products/5183/9140/breaking_bad_los_pollos_hermanos_logo_adult_tank_top_13__06070.1441387990.jpg', 'polovan', 1, FALSE);
insert into rekvizit (id, cena, datum, korisnik_id, naziv, opis, slika, stanje, teatar_id, odobren)
values (5, null, '20180417', 3, 'PLL Notebook', 'Svescurina', 'https://i.pinimg.com/originals/29/37/e8/2937e892a6863c317390056cf9391bf6.jpg', 'polovan', 1, FALSE);


insert into ponuda (id, cena, korisnik_id, odabrana, poslata, rekvizit_id, teatar_id)
values (1, 150, 3, null, null, 3, 1);
insert into ponuda (id, cena, korisnik_id, odabrana, poslata, rekvizit_id, teatar_id)
values (2, 250, 4, null, null, 3, 1);
insert into ponuda (id, cena, korisnik_id, odabrana, poslata, rekvizit_id, teatar_id)
values (3, 350, 5, null, null, 3, 1);

insert into skala_clanstva (id, bronzani, bronzani_popust, srebrni, srebrni_popust, zlatni, zlatni_popust, bodovi_za_posetu)
values (1, 20, 5, 50, 10, 150, 20, 15);

