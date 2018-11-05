alter table ATTENDEDBY 

           drop 

           foreign key FK4e6bfjm5v4taloyyiju9u3b2s;


        alter table ATTENDEDBY 

           drop 

           foreign key FKfwo5twpk6803pvo812nt6yf2k;


        alter table authorities 

           drop 

           foreign key FK7kj74d912rytfp5su91jfv0tg;


        alter table authorities 

           drop 

           foreign key FKk91upmbueyim93v469wj7b2qh;


        alter table EVENT_TAGS 

           drop 

           foreign key FKfnlg79bw8hyg86w67s4keeh31;


        alter table EVENT_TAGS 

           drop 

           foreign key FK9ffeyc2nranbmh4vmshr6r5gh;


        alter table EVENTS 

           drop 

           foreign key FK4xp8jrd41q9gncjkxi8jyvvux;


        alter table PROGRAM_AFFILIATIONS 

           drop 

           foreign key FKpoca3a3v90pllvl7ru7sh8gkm;


        alter table PROGRAM_AFFILIATIONS 

           drop 

           foreign key FK1lied0uohe2o8x9bssepb22dp;


        alter table QUALIFIED_EVENTS 

           drop 

           foreign key FK9qpuyrdkb59rq9tnw6hy5dinw;


        alter table QUALIFIED_EVENTS 

           drop 

           foreign key FKbbtc33e0918ossfn4w8r88vwj;


        alter table QUALIFIED_USERS 

           drop 

           foreign key FKhygtod4whnxtlho1nqu2o3gwa;


        alter table QUALIFIED_USERS 

           drop 

           foreign key FKmp2ypxvrdms6ba9o16r9k49n0;


        alter table REWARD_TAGS 

           drop 

           foreign key FK9cromsb9fnly4c6dq2tnjf1bh;


        alter table REWARD_TAGS 

           drop 

           foreign key FKhc4t5p8hikilhq73p64cla3nd;


        alter table REWARDS_BY_USER 

           drop 

           foreign key FKnuqi8pvobv7djlr8k3h10u58c;


        alter table REWARDS_BY_USER 

           drop 

           foreign key FKc0jlwtv4mp2nx670uu5tpkvk3;


        alter table TITLE_OF_USER 

           drop 

           foreign key FK7chlqow56pgwx1tkkb5x8icmn;


        alter table TITLE_OF_USER 

           drop 

           foreign key FKi08se4dgsnx0m9b3hqdx4j98g;


        alter table USER_POSITION 

           drop 

           foreign key FKaitfxasvrauqf64f0hdf7ipfg;


        alter table USER_POSITION 

           drop 

           foreign key FK35iiaohj3jvg1g9f3ap96to9s;


        drop table if exists ATTENDEDBY;


        drop table if exists authorities;


        drop table if exists DEPARTMENT;


        drop table if exists EVENT_TAGS;


        drop table if exists EVENTS;


        drop table if exists hibernate_sequence;


        drop table if exists POSITION;


        drop table if exists PROGRAM_AFFILIATIONS;


        drop table if exists PROGRAMS;


        drop table if exists QUALIFIED_EVENTS;


        drop table if exists QUALIFIED_USERS;


        drop table if exists REWARD_TAGS;


        drop table if exists REWARDS;


        drop table if exists REWARDS_BY_USER;


        drop table if exists roles;


        drop table if exists TAGS;


        drop table if exists TITLE_OF_USER;


        drop table if exists USER_POSITION;


        drop table if exists USER_TITLE;


        drop table if exists users;
        
        commit;