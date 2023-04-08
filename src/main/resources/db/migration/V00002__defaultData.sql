insert into customers values (default, 'Jack', 'Sparrow', 'Tortuga', 1234523, 'jack.tortuga@pirat.rum', default, 'jacklogin', '$2a$12$PD9hZgWMPel2Bxe3R3vwF.SDb1XQ2gbDhMXaWc9cWbDWBzl7OcmP.', default, default);
insert into customers values (default, 'Gandalf', 'the Gray', 'Middle Earth', 'i will find you myself', 'gandalf.the_white_soon@wizard.mag', default, 'gandalflogin', '$2a$12$by/TgvEkqNc4C1JgyF.rheH6pncUiPvoH80PmuOL3gHKsWmKaVQSG', default, default);
insert into customers values (default, 'Remus ', 'Lupin', 'Hogwarts', 'send me an owl', 'lupin.professor@were.wolf', default, 'remuslogin', '$2a$12$bNxx6.xzSvtt8X1.hGZ0P.uRJ31lgtq4EqUgQctwyLg5K8f4N2uU6', default, default);

insert into roles values (default, 3, 'ADMIN');

insert into roles values (default, 1, 'ADMIN', default);
insert into roles values (default, 3, 'ADMIN', default);

insert into manufacturers values (default, 'Ritter Sport', default, default);
insert into manufacturers values (default, 'Schogetten', default, default);

insert into products values (default, 'DARK', 'NUTS', 1, 'whole hazelnut', 'really tasty', 100, 5.79, default, default);
insert into products values (default, 'DARK', 'NUTS', 1, 'marzipan', 'really tasty', 100, 5.79, default, default);
insert into products values (default, 'MILK', 'MIX', 1, 'rum, raisin, walnut', 'really tasty', 100, 5.79, default, default);
insert into products values (default, 'MILK', 'CREAM', 2, 'latte macchiato', 'really tasty', 100, 7.79, default, default);
insert into products values (default, 'MILK', 'MIX', 2, 'it"s time', 'winter with almond cream and crispy almonds', 100, 8.39, default, default);

insert into orders values (default, 1000000000000000001, 1, 1, 2, default, default, default, default);
insert into orders values (default, 1000000000000000001, 2, 1, 2, default, default, default, default);
insert into orders values (default, 1000000000000000001, 3, 1, 2, default, default, default, default);
insert into orders values (default, 1000000000000000001, 4, 1, 2, default, default, default, default);
insert into orders values (default, 1000000000000000001, 5, 1, 2, default, default, default, default);

insert into orders values (default, 2000000000000000002, 1, 2, 4, default, default, default, default);
insert into orders values (default, 2000000000000000002, 2, 2, 4, default, default, default, default);
insert into orders values (default, 2000000000000000002, 3, 2, 4, default, default, default, default);
insert into orders values (default, 2000000000000000002, 4, 2, 4, default, default, default, default);
insert into orders values (default, 2000000000000000002, 5, 2, 4, default, default, default, default);

insert into orders values (default, 3000000000000000003, 1, 3, 6, default, default, default, default);
insert into orders values (default, 3000000000000000003, 2, 3, 6, default, default, default, default);
insert into orders values (default, 3000000000000000003, 3, 3, 6, default, default, default, default);
insert into orders values (default, 3000000000000000003, 4, 3, 6, default, default, default, default);
insert into orders values (default, 3000000000000000003, 5, 3, 6, default, default, default, default);