INSERT INTO `user_title`
(`ID`,
`TITLE_NAME`)
VALUES
(1,
'Mr.');

INSERT INTO `user_title`
(`ID`,
`TITLE_NAME`)
VALUES
(2,
'Ms.');

INSERT INTO `user_title`
(`ID`,
`TITLE_NAME`)
VALUES
(3,
'Mrs.');


INSERT INTO `users`
(`id`,
`EMAIL`,
`ENABLED`,
`FIRST_NAME`,
`LAST_NAME`,
`PASSWORD`,
`USERNAME`)
VALUES
(1,
'cysun@calstatela.edu',
true,
'cysun',
'sun',
'abcd',
'cysun');

INSERT INTO `users`
(`id`,
`EMAIL`,
`ENABLED`,
`FIRST_NAME`,
`LAST_NAME`,
`PASSWORD`,
`USERNAME`)
VALUES
(2,
'skapoor4@calstatela.edu',
true,
'skapoor',
'kapoor',
'abcd',
'skapoor');

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
'2018-09-30',
'test',
'robotics',
'king hall',
true,
'2018-09-27',
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
'2018-10-10',
'abcd',
'robots',
'salazar hall',
false,
'2018-09-29',
2);