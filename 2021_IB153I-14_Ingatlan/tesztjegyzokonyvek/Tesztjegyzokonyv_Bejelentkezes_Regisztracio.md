# Tesztjegyzőkönyv: Ingatlan2

Az alábbi tesztdokumentum az Ingatlan2 projekthez tartozó `9.3.11. Regisztráció és bejelentkezés GUI` funkcióihoz készült. Felelőse: Mindenki

## 1. Teszteljárások (TP)

### 1.1. Bejelentkezés

- Azonosító: TP-01
- Tesztesetek: TC-01
- Leírás: bejelentkezés funkció tesztelése 0. lépés: Nyissuk meg az alkalmazást, és lépjünk a bejelentkezés oldalra
  1. lépés: A `Felhasználónév` szövegbeviteli mezőbe írjunk be az `admin` szöveget
  2. lépés: A `Jelszó` szövegbeviteli mezőbe írjunk be a `password123` szöveget
  3. lépés: Nyomjuk meg a `Bejelentkezés` gombot
  4. lépés: Ellenőrizzük az eredményt. Elvárt eredmény: `Az alkalmazás átirányít minket a főoldalra, és a felhasználónevünk (admin) megjelenik a jobb fenti felhasználó ikon mellett`

### 1.2. Regisztráció

- Azonosító: TP-02
- Tesztesetek: TC-02
- Leírás: regisztráció funkció tesztelése 0. lépés: Nyissuk meg az alkalmazást, és lépjünk a regisztráció oldalra
  1. lépés: A `Teljes Név` szövegbeviteli mezőbe írjunk be a `felh1` szöveget
  2. lépés: A `E-mail cím` szövegbeviteli mezőbe írjunk be a `2021ib153i14ingatlan2@gmail.com` szöveget
  3. lépés: A `Jelszó` szövegbeviteli mezőbe írjunk be az `asdf` szöveget
  4. lépés: A `Telefonszám` szövegbeviteli mezőbe írjunk be az `1234` szöveget
  5. lépés: A `Születési dátum` szövegbeviteli mezőbe írjunk be az `1970` szöveget
  6. lépés: Nyomjuk meg az `Regisztráció` gombot
  7. lépés: Ellenőrizzük az eredményt. Elvárt eredmény: `Az alkalmazás átirányít minket a bejelentkezés oldalra`

## 2. Teszesetek (TC)

### 2.1. Bejelentkezés funkció tesztelése

#### 2.1.1. TC-01

- TP: TP-01
- Leírás: bejelentkezés funkció tesztelése
- Bemenet: `Felhasználónév` = admin ; `Jelszó` = jelszo123
- Művelet: nyomjuk meg az `Bejelentkezés` gombot
- Elvárt kimenet: Az alkalmazás átirányít minket a főoldalra, és a felhasználónevünk (admin) megjelenik a jobb fenti felhasználó ikon mellett

### 2.2. Regisztráció funkció tesztelése

#### 2.2.1. TC-02

- TP: TP-02
- Leírás: regisztráció funkció tesztelése
- Bemenet: `Teljes Név` = felh1, `E-mail cím` = 2021ib153i14ingatlan2@gmail.com, `Jelszó` = jelszo123, `Telefonszám` = 1234, `Születési dátum` = 1970
- Művelet: nyomjuk meg az `Regisztráció` gombot
- Elvárt kimenet: Az alkalmazás átirányít minket a bejelentkezés oldalra

## 3. Tesztriportok (TR)

### 3.1. Bejelentkezés funkció tesztriportjai

#### 3.1.1. TR-01 (TC-01)

- TP: TP-01
  1. lépés: admin-t beírtam
  2. lépés: jelszo123-t beírtam
  3. lépés: megnyomtam a bejelentkezés gombot
  4. lépés: helyes eredményt kaptam (Az alkalmazás helyesen átirányított)

### 3.2. Regisztráció funkció tesztriportjai

#### 3.2.1. TR-01 (TC-01)

- TP: TP-02
  1. lépés: felh1-t beírtam
  2. lépés: 2021ib153i14ingatlan2@gmail.com-t beírtam
  3. lépés: asdf-t beírtam
  4. lépés: 1234-t beírtam
  5. lépés: 1970-t beírtam
  6. lépés: a regisztráció gombot megnyomtam
  7. lépés: helyes eredményt kaptam (az alkalmazás helyesen átirányított)
