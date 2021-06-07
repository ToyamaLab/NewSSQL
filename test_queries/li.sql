drop table industry_research;
create table industry_research(id serial, department varchar, year int, month varchar, rate float, internship float, answer int);

insert into industry_research (department, year, month, rate, internship, answer) values ('理系全体', 2019, '5月以前', 15.8, 0, 531);
insert into industry_research (department, year, month, rate, internship, answer) values ('理系全体', 2019, '6月', 36.3, 3.7, 497);
insert into industry_research (department, year, month, rate, internship, answer) values ('理系全体', 2019, '7月', 43.8, 2.2, 463);
insert into industry_research (department, year, month, rate, internship, answer) values ('理系全体', 2019, '8月', 52.4, 23.1, 470);
insert into industry_research (department, year, month, rate, internship, answer) values ('理系全体', 2019, '9月', 57.1, 19.1, 456);
insert into industry_research (department, year, month, rate, internship, answer) values ('理系全体', 2019, '10月', 64.5, 4.6, 325);
insert into industry_research (department, year, month, rate, internship, answer) values ('理系全体', 2019, '11月', 69.7, 6.6, 431);
insert into industry_research (department, year, month, rate, internship, answer) values ('理系全体', 2019, '12月', 75.2, 9.7, 531);
insert into industry_research (department, year, month, rate, internship, answer) values ('理系全体', 2020, '1月', 84.2, 11.0, 599);
insert into industry_research (department, year, month, rate, internship, answer) values ('理系全体', 2020, '2月', 91.0, 18.3, 398);

insert into industry_research (department, year, month, rate, internship, answer) values ('機械・電気系', 2019, '5月以前', 25, 0, 127);
insert into industry_research (department, year, month, rate, internship, answer) values ('機械・電気系', 2019, '6月', 48.2, 3.1, 120);
insert into industry_research (department, year, month, rate, internship, answer) values ('機械・電気系', 2019, '7月', 52.7, 2.7, 120);
insert into industry_research (department, year, month, rate, internship, answer) values ('機械・電気系', 2019, '8月', 58.0, 26.5, 112);
insert into industry_research (department, year, month, rate, internship, answer) values ('機械・電気系', 2019, '9月', 63.4, 19.9, 120);
insert into industry_research (department, year, month, rate, internship, answer) values ('機械・電気系', 2019, '10月', 70.5, 3.8, 117);
insert into industry_research (department, year, month, rate, internship, answer) values ('機械・電気系', 2019, '11月', 76.8, 6.9, 84);
insert into industry_research (department, year, month, rate, internship, answer) values ('機械・電気系', 2019, '12月', 79.5, 8.2, 135);
insert into industry_research (department, year, month, rate, internship, answer) values ('機械・電気系', 2020, '1月', 87.5, 9.6, 113);
insert into industry_research (department, year, month, rate, internship, answer) values ('機械・電気系', 2020, '2月', 91.0, 17.5, 104);

insert into industry_research (department, year, month, rate, internship, answer) values ('情報系', 2019, '5月以前', 6.9, 0, 65);
insert into industry_research (department, year, month, rate, internship, answer) values ('情報系', 2019, '6月', 29.3, 4.6, 56);
insert into industry_research (department, year, month, rate, internship, answer) values ('情報系', 2019, '7月', 41.4, 1.9, 59);
insert into industry_research (department, year, month, rate, internship, answer) values ('情報系', 2019, '8月', 51.7, 27.8, 58);
insert into industry_research (department, year, month, rate, internship, answer) values ('情報系', 2019, '9月', 56.9, 23.1, 60);
insert into industry_research (department, year, month, rate, internship, answer) values ('情報系', 2019, '10月', 56.9, 4.6, 59);
insert into industry_research (department, year, month, rate, internship, answer) values ('情報系', 2019, '11月', 60.3, 5.6, 35);
insert into industry_research (department, year, month, rate, internship, answer) values ('情報系', 2019, '12月', 70.7, 9.3, 63);
insert into industry_research (department, year, month, rate, internship, answer) values ('情報系', 2020, '1月', 77.6, 3.7, 53);
insert into industry_research (department, year, month, rate, internship, answer) values ('情報系', 2020, '2月', 89.7, 19.4, 51);

insert into industry_research (department, year, month, rate, internship, answer) values ('化学・農学・薬学系', 2019, '5月以前', 14.7, 0, 149);
insert into industry_research (department, year, month, rate, internship, answer) values ('化学・農学・薬学系', 2019, '6月', 33.3, 1.6, 138);
insert into industry_research (department, year, month, rate, internship, answer) values ('化学・農学・薬学系', 2019, '7月', 40.3, 1.9, 122);
insert into industry_research (department, year, month, rate, internship, answer) values ('化学・農学・薬学系', 2019, '8月', 50.4, 22.4, 130);
insert into industry_research (department, year, month, rate, internship, answer) values ('化学・農学・薬学系', 2019, '9月', 54.3, 17.3, 135);
insert into industry_research (department, year, month, rate, internship, answer) values ('化学・農学・薬学系', 2019, '10月', 65.1, 5.8, 124);
insert into industry_research (department, year, month, rate, internship, answer) values ('化学・農学・薬学系', 2019, '11月', 70.5, 8.0, 94);
insert into industry_research (department, year, month, rate, internship, answer) values ('化学・農学・薬学系', 2019, '12月', 76.0, 11.8, 141);
insert into industry_research (department, year, month, rate, internship, answer) values ('化学・農学・薬学系', 2020, '1月', 81.4, 9.6, 84);
insert into industry_research (department, year, month, rate, internship, answer) values ('化学・農学・薬学系', 2020, '2月', 89.9, 19.8, 103);

insert into industry_research (department, year, month, rate, internship, answer) values ('文系', 2019, '5月以前', 11.7, 0, 731);
insert into industry_research (department, year, month, rate, internship, answer) values ('文系', 2019, '6月', 23.0, 3.6, 899);
insert into industry_research (department, year, month, rate, internship, answer) values ('文系', 2019, '7月', 28.7, 5.0, 853);
insert into industry_research (department, year, month, rate, internship, answer) values ('文系', 2019, '8月', 35.4, 24.2, 1024);
insert into industry_research (department, year, month, rate, internship, answer) values ('文系', 2019, '9月', 41.7, 16.3, 981);
insert into industry_research (department, year, month, rate, internship, answer) values ('文系', 2019, '10月', 53.2, 5.6, 653);
insert into industry_research (department, year, month, rate, internship, answer) values ('文系', 2019, '11月', 60.1, 5.7, 689);
insert into industry_research (department, year, month, rate, internship, answer) values ('文系', 2019, '12月', 67.6, 10.8, 731);
insert into industry_research (department, year, month, rate, internship, answer) values ('文系', 2020, '1月', 78.3, 8.1, 784);
insert into industry_research (department, year, month, rate, internship, answer) values ('文系', 2020, '2月', 87.5, 20.4, 844);