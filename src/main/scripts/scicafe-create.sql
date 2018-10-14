create table ATTENDEDBY (
       event_id bigint not null,
        user_id bigint not null,
        primary key (event_id, user_id)
    ) engine=InnoDB;

    create table authorities (
       user_id bigint not null,
        role_id bigint not null,
        primary key (user_id, role_id)
    ) engine=InnoDB;

    create table DEPARTMENT (
       ID bigint not null,
        DESCRIPTION varchar(255),
        NAME varchar(255),
        ORGANIZATIONAL_UNIT_ID bigint,
        primary key (ID)
    ) engine=InnoDB;

    create table EVENT_TAGS (
       event_id bigint not null,
        tag_id bigint not null,
        primary key (event_id, tag_id)
    ) engine=InnoDB;

    create table EVENTS (
       ID bigint not null,
        END_TIME datetime(6),
        DESCRIPTION varchar(255),
        NAME varchar(255) not null,
        LOCATION varchar(255),
        POSTED bit,
        START_TIME datetime(6),
        SUBMITTED_BY bigint,
        primary key (ID)
    ) engine=InnoDB;

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB;

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    create table ORGANIZATION (
       ID bigint not null,
        DESCRIPTION varchar(255),
        NAME varchar(255),
        primary key (ID)
    ) engine=InnoDB;

    create table ORGANIZATIONAL_UNIT (
       ID bigint not null,
        DESCRIPTION varchar(255) not null,
        NAME varchar(255) not null,
        ORGANIZATION_ID bigint,
        primary key (ID)
    ) engine=InnoDB;

    create table POSITION (
       ID bigint not null,
        DESCRIPTION varchar(255),
        NAME varchar(255) not null,
        primary key (ID)
    ) engine=InnoDB;

    create table PROGRAM_AFFILIATIONS (
       user_id bigint not null,
        program_id bigint not null,
        primary key (user_id, program_id)
    ) engine=InnoDB;

    create table PROGRAMS (
       ID bigint not null,
        DESCRIPTION varchar(255),
        FULL_NAME varchar(255),
        NAME varchar(255),
        primary key (ID)
    ) engine=InnoDB;

    create table QUALIFIED_EVENTS (
       event_id bigint not null,
        reward_id bigint not null,
        primary key (event_id, reward_id)
    ) engine=InnoDB;

    create table QUALIFIED_USERS (
       reward_id bigint,
        user_id bigint not null,
        primary key (user_id)
    ) engine=InnoDB;

    create table REWARD_TAGS (
       tag_id bigint not null,
        reward_id bigint not null,
        primary key (tag_id, reward_id)
    ) engine=InnoDB;

    create table REWARDS (
       ID bigint not null,
        APPROVED bit,
        DESCRIPTION varchar(255) not null,
        END_TIME datetime(6) not null,
        START_TIME datetime(6) not null,
        primary key (ID)
    ) engine=InnoDB;

    create table REWARDS_BY_ORGANIZATION (
       reward_id bigint,
        organization_id bigint not null,
        primary key (organization_id)
    ) engine=InnoDB;

    create table REWARDS_BY_USER (
       reward_id bigint,
        user_id bigint not null,
        primary key (user_id)
    ) engine=InnoDB;

    create table roles (
       id bigint not null,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table TAGS (
       ID bigint not null,
        DESCRIPTION varchar(255),
        NAME varchar(255),
        primary key (ID)
    ) engine=InnoDB;

    create table TITLE_OF_USER (
       user_id bigint not null,
        title_id bigint not null,
        primary key (user_id, title_id)
    ) engine=InnoDB;

    create table USER_ORGANIZATION (
       user_id bigint not null,
        organization_id bigint not null,
        primary key (user_id, organization_id)
    ) engine=InnoDB;

    create table USER_POSITION (
       user_id bigint not null,
        position_id bigint not null,
        primary key (user_id, position_id)
    ) engine=InnoDB;

    create table USER_TITLE (
       ID bigint not null,
        TITLE_NAME varchar(255) not null,
        primary key (ID)
    ) engine=InnoDB;

    create table users (
       id bigint not null,
        EMAIL varchar(255),
        ENABLED bit not null,
        FIRST_NAME varchar(255) not null,
        LAST_NAME varchar(255) not null,
        PASSWORD varchar(255) not null,
        USERNAME varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table roles 
       add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);

    alter table TITLE_OF_USER 
       add constraint UK_i0lvrydimd103euk8rely9iy5 unique (title_id);

    alter table USER_ORGANIZATION 
       add constraint UK_dhsfi2r3lno3qbe0e92nv3gl5 unique (organization_id);

    alter table USER_POSITION 
       add constraint UK_qftyrixiqjxb6r0enbx797ro8 unique (position_id);

    alter table users 
       add constraint UK_9vhgg9up1h7iho1g8i3957jgo unique (USERNAME);

    alter table ATTENDEDBY 
       add constraint FK4e6bfjm5v4taloyyiju9u3b2s 
       foreign key (user_id) 
       references users (id);

    alter table ATTENDEDBY 
       add constraint FKfwo5twpk6803pvo812nt6yf2k 
       foreign key (event_id) 
       references EVENTS (ID);

    alter table authorities 
       add constraint FK7kj74d912rytfp5su91jfv0tg 
       foreign key (role_id) 
       references roles (id);

    alter table authorities 
       add constraint FKk91upmbueyim93v469wj7b2qh 
       foreign key (user_id) 
       references users (id);

    alter table DEPARTMENT 
       add constraint FKk045s1ouh7h4bq4s7hy24ba38 
       foreign key (ORGANIZATIONAL_UNIT_ID) 
       references ORGANIZATIONAL_UNIT (ID);

    alter table EVENT_TAGS 
       add constraint FKfnlg79bw8hyg86w67s4keeh31 
       foreign key (tag_id) 
       references TAGS (ID);

    alter table EVENT_TAGS 
       add constraint FK9ffeyc2nranbmh4vmshr6r5gh 
       foreign key (event_id) 
       references EVENTS (ID);

    alter table EVENTS 
       add constraint FK4xp8jrd41q9gncjkxi8jyvvux 
       foreign key (SUBMITTED_BY) 
       references users (id);

    alter table ORGANIZATIONAL_UNIT 
       add constraint FKdmf7b0g1nmrflixr4qw2oy5aj 
       foreign key (ORGANIZATION_ID) 
       references ORGANIZATION (ID);

    alter table PROGRAM_AFFILIATIONS 
       add constraint FKpoca3a3v90pllvl7ru7sh8gkm 
       foreign key (program_id) 
       references PROGRAMS (ID);

    alter table PROGRAM_AFFILIATIONS 
       add constraint FK1lied0uohe2o8x9bssepb22dp 
       foreign key (user_id) 
       references users (id);

    alter table QUALIFIED_EVENTS 
       add constraint FK9qpuyrdkb59rq9tnw6hy5dinw 
       foreign key (reward_id) 
       references EVENTS (ID);

    alter table QUALIFIED_EVENTS 
       add constraint FKbbtc33e0918ossfn4w8r88vwj 
       foreign key (event_id) 
       references REWARDS (ID);

    alter table QUALIFIED_USERS 
       add constraint FKhygtod4whnxtlho1nqu2o3gwa 
       foreign key (reward_id) 
       references users (id);

    alter table QUALIFIED_USERS 
       add constraint FKmp2ypxvrdms6ba9o16r9k49n0 
       foreign key (user_id) 
       references REWARDS (ID);

    alter table REWARD_TAGS 
       add constraint FK9cromsb9fnly4c6dq2tnjf1bh 
       foreign key (reward_id) 
       references TAGS (ID);

    alter table REWARD_TAGS 
       add constraint FKhc4t5p8hikilhq73p64cla3nd 
       foreign key (tag_id) 
       references REWARDS (ID);

    alter table REWARDS_BY_ORGANIZATION 
       add constraint FKc4il6u7vthwpu63padfwcb1gx 
       foreign key (reward_id) 
       references ORGANIZATION (ID);

    alter table REWARDS_BY_ORGANIZATION 
       add constraint FKird5gi9ycsuce87r70oysthm3 
       foreign key (organization_id) 
       references REWARDS (ID);

    alter table REWARDS_BY_USER 
       add constraint FKnuqi8pvobv7djlr8k3h10u58c 
       foreign key (reward_id) 
       references users (id);

    alter table REWARDS_BY_USER 
       add constraint FKc0jlwtv4mp2nx670uu5tpkvk3 
       foreign key (user_id) 
       references REWARDS (ID);

    alter table TITLE_OF_USER 
       add constraint FK7chlqow56pgwx1tkkb5x8icmn 
       foreign key (title_id) 
       references users (id);

    alter table TITLE_OF_USER 
       add constraint FKi08se4dgsnx0m9b3hqdx4j98g 
       foreign key (user_id) 
       references USER_TITLE (ID);

    alter table USER_ORGANIZATION 
       add constraint FKpjun7upgut3l6fkc73428glpi 
       foreign key (organization_id) 
       references ORGANIZATION (ID);

    alter table USER_ORGANIZATION 
       add constraint FKgxurdmdcnsw3ti31yitc02f61 
       foreign key (user_id) 
       references users (id);

    alter table USER_POSITION 
       add constraint FKaitfxasvrauqf64f0hdf7ipfg 
       foreign key (position_id) 
       references users (id);

    alter table USER_POSITION 
       add constraint FK35iiaohj3jvg1g9f3ap96to9s 
       foreign key (user_id) 
       references POSITION (ID);
       
commit;