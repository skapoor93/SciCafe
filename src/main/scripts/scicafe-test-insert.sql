INSERT INTO `users`
(`id`,
`EMAIL`,
`ENABLED`,
`FIRST_NAME`,
`LAST_NAME`,
`PASSWORD`,
`USERNAME` , 
`TYPE`,
`UNIT`)
VALUES
(1,
"cysun@calstatela.edu",
true,
"cysun",
"sun",
"abcd",
"cysun",
"Faculty",
"Dept. of Computer Science");

INSERT INTO `users`
(`id`,
`EMAIL`,
`ENABLED`,
`FIRST_NAME`,
`LAST_NAME`,
`PASSWORD`,
`USERNAME`,
`TYPE`,
`MAJOR`)
VALUES
(2,
"skapoor4@calstatela.edu",
true,
"shivam",
"kapoor",
"abcd",
"skapoor",
"STUDENT",
"Computer Science");

INSERT INTO `events`
(`ID`,
`END_TIME`,
`DESCRIPTION`,
`NAME`,
`LOCATION`,
`POSTED`,
`START_TIME`,
`SUBMITTED_BY`)
VALUES
(1,
"2018-09-30",
"test",
"robotics",
"king hall",
true,
"2018-09-27",
1);

INSERT INTO `events`
(`ID`,
`END_TIME`,
`DESCRIPTION`,
`NAME`,
`LOCATION`,
`POSTED`,
`START_TIME`,
`SUBMITTED_BY`)
VALUES
(2,
"2018-10-10",
"abcd",
"robots",
"salazar hall",
false,
"2018-09-29",
2);

INSERT INTO `springrest`.`roles`
(`id`,
`name`)
VALUES
(1,
"regular");

commit;