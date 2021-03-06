package base_classes;

import java.time.LocalDate;

import base_classes.classes.AdditServices;
import base_classes.classes.ClientUsedServices;
import base_classes.classes.Clients;
import base_classes.classes.Country;
import base_classes.classes.Hotel;
import base_classes.classes.Reservation;
import base_classes.classes.ReservationForm;
import base_classes.classes.Room;
import base_classes.classes.ServiceCategory;
import base_classes.classes.User;
import base_classes.classes.emuns.SE;
import base_classes.classes.emuns.ServiceType;
import base_classes.classes.emuns.URE;

public class FillDB {
    public void filldb() {
        DBConnection db = new DBConnection();

        Hotel h1 = new Hotel("Testivile");
        Hotel h2 = new Hotel("Mehvile");

        db.saveObject(h1);
        db.saveObject(h2);

        User u = new User( "Lori.Gerlach", "wmdmbYTJmr7qq0Z", "Deondre Runte", "(447) 964-2403 x066", URE.MANAGER);
        User u1 = new User( "Lonie.Kirlin", "VHJRkcWbSiK6R29", "Betty Collier PhD", "1-262-900-8172 x13775", URE.MANAGER);
        User u2 = new User( "Ubaldo_Bergstrom71", "c_rm5jE89n4lC3m", "Jermain Hackett", "055-466-9021 x75931", URE.RECEPTIONIST);
        User u3 = new User( "Valerie92", "otAyaI1_ctLjJN_", "Paxton Schneider DVM", "191.207.1373", URE.RECEPTIONIST);
        User u4 = new User( "Ibrahim.White12", "5I9GSR6NDF8cDn3", "Heaven Powlowski", "240.503.4499", URE.OWNER);
        User u5 = new User( "Easter_Buckridge15", "27I1RQ16H5pMymJ", "Raoul Abernathy", "940.907.8393", URE.MANAGER);
        User u6 = new User( "Orland46", "HA8F1o_PND8iHRK", "Virginia Boyer", "1-297-386-7756 x8166", URE.RECEPTIONIST);
        User u7 = new User( "Aliza99", "4O3hDm0eqVL2YpT", "Name Kshlerin", "911.333.2891", URE.RECEPTIONIST);
        User u8 = new User( "Jabari.Trantow57", "ZIhyidzOxyxnPQw", "Skyla Konopelski V", "(119) 436-8299 x4321", URE.OWNER);
        User u9 = new User( "Derek.Ruecker", "5WZSc9ebJmfR2fF", "Frederique Dickinson", "1-435-753-3203 x53541", URE.MANAGER);
        User u10 = new User( "Rickie53", "5EetOEzeOznQr2R", "Carter Bahringer", "882.105.7726 x08103", URE.RECEPTIONIST);
        User u11 = new User( "Dandre.Rice", "Zopso7Hj22l92QH", "Vada Bernhard", "(846) 510-9696 x69813", URE.RECEPTIONIST);
        User u12 = new User( "Rusty79", "45OHB5CzI6OpEDd", "Lolita Runolfsdottir", "1-522-657-4451", URE.MANAGER);
        User u13 = new User( "Jerel.Hessel", "orl10tCZQTxUCAz", "Fabian McClure", "1-410-404-6852", URE.RECEPTIONIST);
        User u14 = new User( "Antwan_Kunde", "FeI1nJa9EfAXLX0", "Emmanuelle Volkman", "181.424.0196 x04920", URE.RECEPTIONIST);
        User u15 = new User( "Nadia_Abbott", "KrsWgI9DBqw0Hg0", "Gladyce Johnston Sr.", "582.887.7108 x097", URE.OWNER);

        u.addToHotel(h1);
        u1.addToHotel(h1);
        u2.addToHotel(h1);
        u3.addToHotel(h1);
        u4.addToHotel(h1);
        u5.addToHotel(h1);
        u6.addToHotel(h1);
        u7.addToHotel(h1);

        u8.addToHotel(h2);
        u9.addToHotel(h2);
        u10.addToHotel(h2);
        u11.addToHotel(h2);
        u12.addToHotel(h2);
        u13.addToHotel(h2);
        u14.addToHotel(h2);
        u15.addToHotel(h2);

        db.saveObject(u);
        db.saveObject(u1);
        db.saveObject(u2);
        db.saveObject(u3);
        db.saveObject(u4);
        db.saveObject(u5);
        db.saveObject(u6);
        db.saveObject(u7);
        db.saveObject(u8);
        db.saveObject(u9);
        db.saveObject(u10);
        db.saveObject(u11);
        db.saveObject(u12);
        db.saveObject(u13);
        db.saveObject(u14);
        db.saveObject(u15);

        ServiceCategory sc = new ServiceCategory("Electronics", ServiceType.NEGATIVE);
        ServiceCategory sc1 = new ServiceCategory("Shoes", ServiceType.PROSITIVE);
        ServiceCategory sc2 = new ServiceCategory("Jewelery", ServiceType.NEGATIVE);
        ServiceCategory sc3 = new ServiceCategory("Computers", ServiceType.NEGATIVE);
        ServiceCategory sc4 = new ServiceCategory("Home", ServiceType.NEGATIVE);
        ServiceCategory sc5 = new ServiceCategory("Shoes", ServiceType.PROSITIVE);
        ServiceCategory sc6 = new ServiceCategory("Shoes", ServiceType.NEGATIVE);
        ServiceCategory sc7 = new ServiceCategory("Music", ServiceType.PROSITIVE);
        ServiceCategory sc8 = new ServiceCategory("Baby", ServiceType.NEGATIVE);

        db.saveObject(sc);
        db.saveObject(sc1);
        db.saveObject(sc2);
        db.saveObject(sc3);
        db.saveObject(sc4);
        db.saveObject(sc5);
        db.saveObject(sc6);
        db.saveObject(sc7);
        db.saveObject(sc8);

        AdditServices ads = new AdditServices("Rustic Fresh Fish", sc2, 400.00, h1);
        AdditServices ads1 = new AdditServices("Sleek Soft Salad", sc7, 735.00, h1);
        AdditServices ads2 = new AdditServices("Refined Concrete Pants", sc4, 14.00, h1);
        AdditServices ads3 = new AdditServices("Gorgeous Steel Computer", sc6, 275.00, h1);
        AdditServices ads4 = new AdditServices("Ergonomic Wooden Shoes", sc, 620.00, h1);
        AdditServices ads5 = new AdditServices("Generic Metal Cheese", sc8, 761.00, h1);
        AdditServices ads6 = new AdditServices("Handcrafted Rubber Gloves", sc, 245.00, h1);
        AdditServices ads7 = new AdditServices("Rustic Rubber Shirt", sc3, 713.00, h1);
        AdditServices ads8 = new AdditServices("Incredible Cotton Gloves", sc3, 401.00, h1);
        AdditServices ads9 = new AdditServices("Handcrafted Plastic Table", sc3, 979.00, h1);
        AdditServices ads10 = new AdditServices("Generic Granite Computer", sc7, 102.00, h1);
        AdditServices ads11 = new AdditServices("Practical Cotton Shoes", sc4, 756.00, h1);
        AdditServices ads12 = new AdditServices("Rustic Fresh Bike", sc4, 136.00, h1);

        AdditServices ads13 = new AdditServices("Incredible Metal Pants", sc2, 752.00, h2);
        AdditServices ads14 = new AdditServices("Small Frozen Ball", sc5, 639.00, h2);
        AdditServices ads15 = new AdditServices("Unbranded Steel Chips", sc7, 52.00, h2);
        AdditServices ads16 = new AdditServices("Incredible Fresh Pizza", sc2, 950.00, h2);
        AdditServices ads17 = new AdditServices("Awesome Granite Towels", sc7, 245.00, h2);
        AdditServices ads18 = new AdditServices("Licensed Frozen Fish", sc6, 962.00, h2);
        AdditServices ads19 = new AdditServices("Handmade Metal Salad", sc2, 791.00, h2);
        AdditServices ads20 = new AdditServices("Gorgeous Soft Chips", sc2, 818.00, h2);
        AdditServices ads21 = new AdditServices("Intelligent Steel Chips", sc8, 267.00, h2);
        AdditServices ads22 = new AdditServices("Unbranded Steel Pizza", sc3, 438.00, h2);
        AdditServices ads23 = new AdditServices("Awesome Steel Tuna", sc2, 963.00, h2);
        AdditServices ads24 = new AdditServices("Refined Soft Bacon", sc1, 684.00, h2);
        AdditServices ads25 = new AdditServices("Licensed Wooden Gloves", sc5, 6.00, h2);

        db.saveObject(ads);
        db.saveObject(ads1);
        db.saveObject(ads2);
        db.saveObject(ads3);
        db.saveObject(ads4);
        db.saveObject(ads5);
        db.saveObject(ads6);
        db.saveObject(ads7);
        db.saveObject(ads8);
        db.saveObject(ads9);
        db.saveObject(ads10);
        db.saveObject(ads11);
        db.saveObject(ads12);
        db.saveObject(ads13);
        db.saveObject(ads14);
        db.saveObject(ads15);
        db.saveObject(ads16);
        db.saveObject(ads17);
        db.saveObject(ads18);
        db.saveObject(ads19);
        db.saveObject(ads20);
        db.saveObject(ads21);
        db.saveObject(ads22);
        db.saveObject(ads23);
        db.saveObject(ads24);
        db.saveObject(ads25);

        Country c1 = new Country("Gabon");
        Country c2 = new Country("Iran");
        Country c3 = new Country("Gabon");
        Country c4 = new Country("Guadeloupe");
        Country c5 = new Country("Taiwan");
        Country c6 = new Country("Togo");
        Country c7 = new Country("Seychelles");
        Country c8 = new Country("Kenya");
        Country c9 = new Country("Cuba");
        Country c10 = new Country("Mongolia");
        Country c11 = new Country("Oman");

        db.saveObject(c1);
        db.saveObject(c2);
        db.saveObject(c3);
        db.saveObject(c4);
        db.saveObject(c5);
        db.saveObject(c6);
        db.saveObject(c7);
        db.saveObject(c8);
        db.saveObject(c9);
        db.saveObject(c10);
        db.saveObject(c11);

        Clients cl1 = new Clients("Rashad Spencer", LocalDate.now(), true, "84747", LocalDate.now(), "29809", c9, "KHVympYPbG", "LBrkFmwnnR", h1);
        Clients cl2 = new Clients("Reggie Osinski", LocalDate.now(), false, "81060", LocalDate.now(), "93894", c3, "kQlAn", "BFenlGGjWviwTJackCk", h1);
        Clients cl3 = new Clients("Lupe Hilpert", LocalDate.now(), true, "87221", LocalDate.now(), "91440", c2, "tDcRbyKB", "LwxzNkujnVIJRGfCJx", h1);
        Clients cl4 = new Clients("Eula Senger", LocalDate.now(), false, "73171", LocalDate.now(), "73395", c5, "URYtzZyNdEfq", "icYOPuMUMXZ", h1);
        Clients cl5 = new Clients("Marguerite Bruen", LocalDate.now(), false, "77612", LocalDate.now(), "15799", c7, "sJfkxJzgJuTEh", "lDwphpGNC", h1);
        Clients cl6 = new Clients("Will Orn", LocalDate.now(), false, "74853", LocalDate.now(), "64694", c7, "HYwvJYvsGFjwBSLrLbq", "MTgmdjNcZNzIsUNyf", h1);
        Clients cl7 = new Clients("Raphaelle Flatley", LocalDate.now(), true, "1300", LocalDate.now(), "39386", c1, "VktFfIPs", "PflnECckFYEKSy", h1);
        Clients cl8 = new Clients("Dr. Llewellyn Rath", LocalDate.now(), true, "39139", LocalDate.now(), "77129", c4, "WuaiMtddCKenQpzyxzgJ", "hDbiHOPw", h1);
        Clients cl9 = new Clients("Anibal Schowalter", LocalDate.now(), false, "16977", LocalDate.now(), "80030", c2, "kNXmzCCRoes", "eSixIWo", h1);
        Clients cl10 = new Clients("Joesph Kirlin I", LocalDate.now(), false, "7321", LocalDate.now(), "95467", c6, "sGryBMFI", "GQShBoYVBpzeIcwYtrq", h1);
        Clients cl11 = new Clients("Elenora Weimann", LocalDate.now(), true, "18012", LocalDate.now(), "50724", c9, "EHERAxgkmXfZgYWqmzMH", "qHuYp", h1);
        Clients cl12 = new Clients("Ms. Valentina Howe", LocalDate.now(), false, "32462", LocalDate.now(), "47952", c6, "jsffpTgNhrkL", "TOKwaLoY", h1);

        Clients cl13 = new Clients("Matilda Bosco", LocalDate.now(), false, "86927", LocalDate.now(), "68997", c1, "aANhxRGdXIraMqKUrG", "PLaNjhzFG", h2);
        Clients cl14 = new Clients("Miss Chelsey Harvey", LocalDate.now(), false, "44640", LocalDate.now(), "12019", c2, "vraSIXI", "UXpqfhxNIxX", h2);
        Clients cl15 = new Clients("Rey Abernathy", LocalDate.now(), true, "93909", LocalDate.now(), "69423", c8, "ZTTLAhchZDz", "CfNZYhJFVwmN", h2);
        Clients cl16 = new Clients("Craig Pagac", LocalDate.now(), true, "40194", LocalDate.now(), "69936", c6, "NNkTXyjsIxrHhcjqStl", "FkZwXU", h2);
        Clients cl17 = new Clients("Wayne Buckridge", LocalDate.now(), false, "60328", LocalDate.now(), "62294", c10, "LDprdHjefVMeoWQIxemc", "aUuRu", h2);
        Clients cl18 = new Clients("Toy Keebler", LocalDate.now(), false, "54182", LocalDate.now(), "33336", c11, "DsbkwUUtX", "kPxKgrrKJLHOwOIX", h2);
        Clients cl19 = new Clients("Urban Brown", LocalDate.now(), true, "21958", LocalDate.now(), "56961", c2, "JTQxAonPciodSSv", "lfNLYbpXUGrRhyLmIw", h2);
        Clients cl20 = new Clients("Miss Zachariah Schiller", LocalDate.now(), true, "42069", LocalDate.now(), "16147", c4, "WMvCyIUvGW", "PYgUEBeOwnEz", h2);

        ClientUsedServices cus1 = new ClientUsedServices(ads7, 3, "AQVHnlfyGgG", h1);
        ClientUsedServices cus2 = new ClientUsedServices(ads4, 10, "uYcME", h1);
        ClientUsedServices cus3 = new ClientUsedServices(ads7, 4, "QCXJctFfeoGlplSEyzI", h1);
        ClientUsedServices cus4 = new ClientUsedServices(ads10, 8, "KrjeMp", h1);
        ClientUsedServices cus5 = new ClientUsedServices(ads8, 4, "pMrRKoacQnMzqrGyaO", h1);
        ClientUsedServices cus6 = new ClientUsedServices(ads, 7, "RtaARdaHUbN", h1);
        ClientUsedServices cus7 = new ClientUsedServices(ads10, 2, "UBEdNRWINEokcxH", h1);
        ClientUsedServices cus8 = new ClientUsedServices(ads2, 10, "uTpDqiTc", h1);
        ClientUsedServices cus9 = new ClientUsedServices(ads2, 6, "sgVIapNK", h1);
        ClientUsedServices cus10 = new ClientUsedServices(ads6, 10, "BJxkkbSlIVEyUfDdi", h1);
        ClientUsedServices cus11 = new ClientUsedServices(ads6, 6, "aGhTwLNnpRVPkXrcUNZG", h1);
        ClientUsedServices cus12 = new ClientUsedServices(ads2, 10, "iOivYqGPxSlK", h1);
        ClientUsedServices cus13 = new ClientUsedServices(ads10, 3, "qfdzAdN", h1);
        ClientUsedServices cus14 = new ClientUsedServices(ads, 9, "tGSTsNe", h1);
        ClientUsedServices cus15 = new ClientUsedServices(ads6, 2, "LYhSkThqtszZ", h1);
        ClientUsedServices cus16 = new ClientUsedServices(ads10, 10, "IiElfAo", h1);
        ClientUsedServices cus17 = new ClientUsedServices(ads7, 3, "HgOLyJfjnYnGLnxydzn", h1);
        ClientUsedServices cus18 = new ClientUsedServices(ads6, 10, "QPiQDczJMUpiBpVjPtr", h1);
        ClientUsedServices cus19 = new ClientUsedServices(ads10, 6, "BhAGRcQ", h1);
        ClientUsedServices cus20 = new ClientUsedServices(ads1, 5, "hgptAWZt", h1);
        ClientUsedServices cus21 = new ClientUsedServices(ads10, 6, "ZhaCXMBuMKohvzKDNZp", h1);
        ClientUsedServices cus22 = new ClientUsedServices(ads2, 1, "HmqCwavSXr", h1);
        ClientUsedServices cus23 = new ClientUsedServices(ads6, 9, "DIrST", h1);
        ClientUsedServices cus24 = new ClientUsedServices(ads2, 3, "lZpNzyIPobfLnXE", h1);
        ClientUsedServices cus25 = new ClientUsedServices(ads8, 7, "nwCSquWZlsiEwywgPCCH", h1);
        ClientUsedServices cus26 = new ClientUsedServices(ads1, 4, "OvLfyOxsL", h1);
        ClientUsedServices cus27 = new ClientUsedServices(ads10, 7, "QpIDkiN", h1);
        ClientUsedServices cus28 = new ClientUsedServices(ads12, 10, "gxXpBusWjUuCnKKVX", h1);
        ClientUsedServices cus29 = new ClientUsedServices(ads8, 1, "dIRIP", h1);

        ClientUsedServices cus30 = new ClientUsedServices(ads18, 9, "iOuLDagdfBrhpxeyt", h2);
        ClientUsedServices cus31 = new ClientUsedServices(ads16, 6, "CPrpOsq", h2);
        ClientUsedServices cus32 = new ClientUsedServices(ads14, 5, "oVmydIzvo", h2);
        ClientUsedServices cus33 = new ClientUsedServices(ads13, 7, "bGWxsmxaLWlDCZ", h2);
        ClientUsedServices cus34 = new ClientUsedServices(ads13, 2, "mqtsFDGeewi", h2);
        ClientUsedServices cus35 = new ClientUsedServices(ads22, 10, "EGAtSxbf", h2);
        ClientUsedServices cus36 = new ClientUsedServices(ads21, 7, "XLlWE", h2);
        ClientUsedServices cus37 = new ClientUsedServices(ads16, 3, "MhAEYZlYIGMSEBfZKnh", h2);
        ClientUsedServices cus38 = new ClientUsedServices(ads23, 9, "QVOFKghkbm", h2);
        ClientUsedServices cus39 = new ClientUsedServices(ads25, 9, "ytvLlZn", h2);
        ClientUsedServices cus40 = new ClientUsedServices(ads17, 7, "KTixryfBzpseR", h2);
        ClientUsedServices cus41 = new ClientUsedServices(ads19, 6, "DqdhaliZ", h2);
        ClientUsedServices cus42 = new ClientUsedServices(ads14, 8, "kwmHaxJRlIVAyWtNfH", h2);
        ClientUsedServices cus43 = new ClientUsedServices(ads20, 5, "eLlQyDdprjRaE", h2);
        ClientUsedServices cus44 = new ClientUsedServices(ads19, 5, "tHrvZGSjf", h2);
        ClientUsedServices cus45 = new ClientUsedServices(ads13, 1, "rJsTWcy", h2);
        ClientUsedServices cus46 = new ClientUsedServices(ads15, 7, "vibCjohNZoxXMknWKgj", h2);
        ClientUsedServices cus47 = new ClientUsedServices(ads18, 5, "ZHoUvksUPxmqR", h2);
        ClientUsedServices cus48 = new ClientUsedServices(ads14, 4, "dFaFclq", h2);
        ClientUsedServices cus49 = new ClientUsedServices(ads13, 8, "yvsRRR", h2);
        ClientUsedServices cus50 = new ClientUsedServices(ads24, 10, "isSKmKgzaxpEx", h2);
        ClientUsedServices cus51 = new ClientUsedServices(ads19, 6, "KFRffySLOSbX", h2);

        db.saveObject(cus1);
        db.saveObject(cus2);
        db.saveObject(cus3);
        db.saveObject(cus4);
        db.saveObject(cus5);
        db.saveObject(cus6);
        db.saveObject(cus7);
        db.saveObject(cus8);
        db.saveObject(cus9);
        db.saveObject(cus10);
        db.saveObject(cus11);
        db.saveObject(cus12);
        db.saveObject(cus13);
        db.saveObject(cus14);
        db.saveObject(cus15);
        db.saveObject(cus16);
        db.saveObject(cus17);
        db.saveObject(cus18);
        db.saveObject(cus19);
        db.saveObject(cus20);
        db.saveObject(cus21);
        db.saveObject(cus22);
        db.saveObject(cus23);
        db.saveObject(cus24);
        db.saveObject(cus25);
        db.saveObject(cus26);
        db.saveObject(cus27);
        db.saveObject(cus28);
        db.saveObject(cus29);
        db.saveObject(cus30);
        db.saveObject(cus31);
        db.saveObject(cus32);
        db.saveObject(cus33);
        db.saveObject(cus34);
        db.saveObject(cus35);
        db.saveObject(cus36);
        db.saveObject(cus37);
        db.saveObject(cus38);
        db.saveObject(cus39);
        db.saveObject(cus40);
        db.saveObject(cus41);
        db.saveObject(cus42);
        db.saveObject(cus43);
        db.saveObject(cus44);
        db.saveObject(cus45);
        db.saveObject(cus46);
        db.saveObject(cus47);
        db.saveObject(cus48);
        db.saveObject(cus49);
        db.saveObject(cus50);
        db.saveObject(cus51);

        cl4.addToUsedServices(cus1);
        cl6.addToUsedServices(cus2);
        cl12.addToUsedServices(cus3);
        cl10.addToUsedServices(cus4);
        cl8.addToUsedServices(cus5);
        cl6.addToUsedServices(cus6);
        cl4.addToUsedServices(cus7);
        cl12.addToUsedServices(cus8);
        cl1.addToUsedServices(cus9);
        cl10.addToUsedServices(cus10);
        cl1.addToUsedServices(cus11);
        cl9.addToUsedServices(cus12);
        cl5.addToUsedServices(cus13);
        cl1.addToUsedServices(cus14);
        cl2.addToUsedServices(cus15);
        cl2.addToUsedServices(cus16);
        cl2.addToUsedServices(cus17);
        cl4.addToUsedServices(cus18);
        cl9.addToUsedServices(cus19);
        cl9.addToUsedServices(cus20);
        cl1.addToUsedServices(cus21);
        cl8.addToUsedServices(cus22);
        cl2.addToUsedServices(cus23);
        cl8.addToUsedServices(cus24);
        cl7.addToUsedServices(cus25);
        cl1.addToUsedServices(cus26);
        cl5.addToUsedServices(cus27);
        cl9.addToUsedServices(cus28);
        cl11.addToUsedServices(cus29);

        cl18.addToUsedServices(cus30);
        cl15.addToUsedServices(cus31);
        cl17.addToUsedServices(cus32);
        cl15.addToUsedServices(cus33);
        cl15.addToUsedServices(cus34);
        cl20.addToUsedServices(cus35);
        cl17.addToUsedServices(cus36);
        cl18.addToUsedServices(cus37);
        cl19.addToUsedServices(cus38);
        cl16.addToUsedServices(cus39);
        cl16.addToUsedServices(cus40);
        cl19.addToUsedServices(cus41);
        cl13.addToUsedServices(cus42);
        cl18.addToUsedServices(cus43);
        cl17.addToUsedServices(cus44);
        cl20.addToUsedServices(cus45);
        cl17.addToUsedServices(cus46);
        cl20.addToUsedServices(cus47);
        cl13.addToUsedServices(cus48);
        cl13.addToUsedServices(cus49);
        cl20.addToUsedServices(cus50);
        cl20.addToUsedServices(cus51);


        db.saveObject(cl1);
        db.saveObject(cl2);
        db.saveObject(cl3);
        db.saveObject(cl4);
        db.saveObject(cl5);
        db.saveObject(cl6);
        db.saveObject(cl7);
        db.saveObject(cl8);
        db.saveObject(cl9);
        db.saveObject(cl10);
        db.saveObject(cl11);
        db.saveObject(cl12);
        db.saveObject(cl13);
        db.saveObject(cl14);
        db.saveObject(cl15);
        db.saveObject(cl16);
        db.saveObject(cl17);
        db.saveObject(cl18);
        db.saveObject(cl19);
        db.saveObject(cl20);

        Room r1 = new Room("73", "Single", 633.00, SE.FREE, h1);
        Room r2 = new Room("64", "Double", 52.00, SE.FREE, h1);
        Room r3 = new Room("3", "Double", 680.00, SE.FREE, h1);
        Room r4 = new Room("63", "Triple", 469.00, SE.FREE, h1);
        Room r5 = new Room("69", "Triple", 547.00, SE.FREE, h1);
        Room r6 = new Room("42", "Suite", 976.00, SE.FREE, h1);
        Room r7 = new Room("25", "Double", 232.00, SE.FREE, h1);
        Room r8 = new Room("18", "Single", 782.00, SE.FREE, h1);
        Room r9 = new Room("73", "Triple", 227.00, SE.FREE, h1);
        Room r10 = new Room("87", "Double", 716.00, SE.FREE, h1);
        Room r11 = new Room("17", "Double", 764.00, SE.FREE, h1);
        Room r12 = new Room("42", "Triple", 818.00, SE.FREE, h1);
        Room r13 = new Room("100", "Triple", 880.00, SE.FREE, h1);
        Room r14 = new Room("82", "Single", 780.00, SE.FREE, h1);
        Room r15 = new Room("96", "Suite", 810.00, SE.FREE, h1);
        Room r16 = new Room("10", "Double", 801.00, SE.FREE, h1);
        Room r17 = new Room("24", "Triple", 830.00, SE.FREE, h1);
        Room r18 = new Room("98", "Single", 394.00, SE.FREE, h1);
        Room r19 = new Room("70", "Suite", 569.00, SE.FREE, h1);

        Room r20 = new Room("99", "Triple", 442.00, SE.FREE, h2);
        Room r21 = new Room("49", "Double", 537.00, SE.FREE, h2);
        Room r22 = new Room("17", "Triple", 411.00, SE.FREE, h2);
        Room r23 = new Room("4", "Single", 739.00, SE.FREE, h2);
        Room r24 = new Room("79", "Suite", 356.00, SE.FREE, h2);
        Room r25 = new Room("52", "Double", 274.00, SE.FREE, h2);
        Room r26 = new Room("29", "Single", 106.00, SE.FREE, h2);
        Room r27 = new Room("45", "Double", 166.00, SE.FREE, h2);
        Room r28 = new Room("23", "Suite", 503.00, SE.FREE, h2);
        Room r29 = new Room("37", "Single", 849.00, SE.FREE, h2);
        Room r30 = new Room("62", "Suite", 445.00, SE.FREE, h2);
        Room r31 = new Room("73", "Double", 889.00, SE.FREE, h2);
        Room r32 = new Room("9", "Suite", 754.00, SE.FREE, h2);
        Room r33 = new Room("63", "Single", 640.00, SE.FREE, h2);
        Room r34 = new Room("36", "Triple", 647.00, SE.FREE, h2);
        Room r35 = new Room("89", "Triple", 958.00, SE.FREE, h2);
        Room r36 = new Room("67", "Single", 47.00, SE.FREE, h2);
        Room r37 = new Room("22", "Suite", 990.00, SE.FREE, h2);
        Room r38 = new Room("70", "Double", 219.00, SE.FREE, h2);
        Room r39 = new Room("11", "Triple", 666.00, SE.FREE, h2);

        r1.addToClients(cl1);
        r7.addToClients(cl2);
        r6.addToClients(cl3);
        r10.addToClients(cl4);
        r3.addToClients(cl5);
        r10.addToClients(cl6);
        r5.addToClients(cl7);
        r8.addToClients(cl8);
        r9.addToClients(cl9);
        r9.addToClients(cl10);
        r3.addToClients(cl11);
        r8.addToClients(cl12);

        r20.addToClients(cl13);
        r21.addToClients(cl14);
        r22.addToClients(cl15);
        r24.addToClients(cl16);
        r25.addToClients(cl17);
        r20.addToClients(cl18);
        r25.addToClients(cl19);
        r23.addToClients(cl20);


        db.saveObject(r1);
        db.saveObject(r2);
        db.saveObject(r3);
        db.saveObject(r4);
        db.saveObject(r5);
        db.saveObject(r6);
        db.saveObject(r7);
        db.saveObject(r8);
        db.saveObject(r9);
        db.saveObject(r10);
        db.saveObject(r11);
        db.saveObject(r12);
        db.saveObject(r13);
        db.saveObject(r14);
        db.saveObject(r15);
        db.saveObject(r16);
        db.saveObject(r17);
        db.saveObject(r18);
        db.saveObject(r19);
        db.saveObject(r20);
        db.saveObject(r21);
        db.saveObject(r22);
        db.saveObject(r23);
        db.saveObject(r24);
        db.saveObject(r25);
        db.saveObject(r26);
        db.saveObject(r27);
        db.saveObject(r28);
        db.saveObject(r29);
        db.saveObject(r30);
        db.saveObject(r31);
        db.saveObject(r32);
        db.saveObject(r33);
        db.saveObject(r34);
        db.saveObject(r35);
        db.saveObject(r36);
        db.saveObject(r37);
        db.saveObject(r38);
        db.saveObject(r39);


        ReservationForm rf1 = new ReservationForm("reservation_type", "Double", "cancel_type", LocalDate.parse("2020-01-11"), LocalDate.parse("2020-11-13"), 4, 3, 0, "food_type", 552.00, "status", "notes", "Einar Koss");
        ReservationForm rf2 = new ReservationForm("reservation_type", "Suite", "cancel_type", LocalDate.parse("2020-07-17"), LocalDate.parse("2020-11-20"), 2, 2, 0, "food_type", 16.00, "status", "notes", "Dr. Arlo Hegmann");
        ReservationForm rf3 = new ReservationForm("reservation_type", "Single", "cancel_type", LocalDate.parse("2020-07-14"), LocalDate.parse("2020-12-16"), 1, 4, 0, "food_type", 339.00, "status", "notes", "Claire Moen");
        ReservationForm rf4 = new ReservationForm("reservation_type", "Double", "cancel_type", LocalDate.parse("2020-05-15"), LocalDate.parse("2020-10-19"), 3, 2, 0, "food_type", 218.00, "status", "notes", "Lelah Mann DVM");
        ReservationForm rf5 = new ReservationForm("reservation_type", "Suite", "cancel_type", LocalDate.parse("2020-02-12"), LocalDate.parse("2020-11-20"), 3, 5, 0, "food_type", 581.00, "status", "notes", "Reyna Nienow");
        ReservationForm rf6 = new ReservationForm("reservation_type", "Double", "cancel_type", LocalDate.parse("2020-09-16"), LocalDate.parse("2020-10-17"), 1, 2, 0, "food_type", 875.00, "status", "notes", "Mrs. Destinee Hermann");
        ReservationForm rf7 = new ReservationForm("reservation_type", "Single", "cancel_type", LocalDate.parse("2020-06-15"), LocalDate.parse("2020-10-15"), 4, 5, 0, "food_type", 353.00, "status", "notes", "Nathanael Weber");
        ReservationForm rf8 = new ReservationForm("reservation_type", "Suite", "cancel_type", LocalDate.parse("2020-09-12"), LocalDate.parse("2020-12-11"), 2, 2, 0, "food_type", 117.00, "status", "notes", "Rosalyn Thiel");
        ReservationForm rf9 = new ReservationForm("reservation_type", "Double", "cancel_type", LocalDate.parse("2020-05-17"), LocalDate.parse("2020-11-14"), 4, 2, 0, "food_type", 820.00, "status", "notes", "Laverna Carroll");
        ReservationForm rf10 = new ReservationForm("reservation_type", "Single", "cancel_type", LocalDate.parse("2020-02-14"), LocalDate.parse("2020-10-14"), 3, 3, 0, "food_type", 570.00, "status", "notes", "Katrine Little");
        ReservationForm rf11 = new ReservationForm("reservation_type", "Suite", "cancel_type", LocalDate.parse("2020-05-15"), LocalDate.parse("2020-11-19"), 4, 5, 0, "food_type", 53.00, "status", "notes", "Norberto Baumbach");
        ReservationForm rf12 = new ReservationForm("reservation_type", "Double", "cancel_type", LocalDate.parse("2020-04-19"), LocalDate.parse("2020-12-15"), 3, 2, 0, "food_type", 986.00, "status", "notes", "Riley Rolfson");



        Reservation res1 = new Reservation(u, rf1, r12, h1);
        Reservation res2 = new Reservation(u3, rf2, r18, h1);
        Reservation res3 = new Reservation(u4, rf3, r3, h1);
        Reservation res4 = new Reservation(u5, rf4, r8, h1);
        Reservation res5 = new Reservation(u4, rf5, r16, h1);
        Reservation res6 = new Reservation(u3, rf6, r16, h1);
        Reservation res7 = new Reservation(u3, rf7, r18, h1);

        Reservation res8 = new Reservation(u14, rf8, r20, h2);
        Reservation res9 = new Reservation(u9, rf9, r22, h2);
        Reservation res10 = new Reservation(u8, rf10, r23, h2);
        Reservation res11 = new Reservation(u12, rf11, r24, h2);
        Reservation res12 = new Reservation(u12, rf12, r25, h2);


        db.saveObject(res1);
        db.saveObject(res2);
        db.saveObject(res3);
        db.saveObject(res4);
        db.saveObject(res5);
        db.saveObject(res6);
        db.saveObject(res7);
        db.saveObject(res8);
        db.saveObject(res9);
        db.saveObject(res10);
        db.saveObject(res11);
        db.saveObject(res12);
    }

    public static void main(String[] args) {
        // FillDB fdb = new FillDB();
        // fdb.filldb();
    }
}
