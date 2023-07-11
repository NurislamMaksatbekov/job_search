create table CATEGORIES
(
    ID    BIGINT auto_increment UNIQUE not null,
    TITLE CHARACTER VARYING(50)        not null
);

alter table CATEGORIES
    add primary key (ID);

create table CONTACTS
(
    ID            BIGINT auto_increment UNIQUE not null,
    PHONE_NUMBER  CHARACTER VARYING(50)        not null,
    TELEGRAM      CHARACTER VARYING(150),
    EMAIL         CHARACTER VARYING(150),
    FACEBOOK_LINK CHARACTER VARYING(150),
    LINKEDIN_LINK CHARACTER VARYING(150)
);

alter table CONTACTS
    add primary key (ID);

create table RESPONDS
(
    ID                 BIGINT auto_increment UNIQUE not null,
    TIME_OF_RESPOND    TIMESTAMP                    not null,
    RESPOND            CHARACTER VARYING(50)        not null,
    FOR_WHAT_RESPONDED BIGINT                       not null
);

alter table RESPONDS
    add primary key (ID);

create table RESUMES
(
    ID               BIGINT auto_increment UNIQUE not null,
    JOB_TITLE        CHARACTER VARYING(50)        not null,
    REQUIRED_SALARY  DOUBLE PRECISION             not null,
    EXPERIENCE       CHARACTER VARYING(100)       not null,
    TITLE_OF_COMPANY CHARACTER VARYING(50)        not null,
    RESPONSIBILITIES CHARACTER VARYING(150)       not null,
    EDUCATION        CHARACTER VARYING(100)       not null,
    ACTIVE           BOOLEAN                      not null,
    CONTACT_ID       BIGINT                       not null,
    AUTHOR_EMAIL     CHARACTER VARYING(50)        not null,
    CATEGORY_ID      BIGINT                       not null
);

alter table RESUMES
    add primary key (ID);

alter table RESUMES
    add foreign key (CONTACT_ID) references CONTACTS;

alter table RESUMES
    add foreign key (CATEGORY_ID) references CATEGORIES;

create table TYPES_OF_ACCOUNT
(
    ID   BIGINT auto_increment UNIQUE not null,
    TYPE CHARACTER VARYING(50)        not null
);

alter table TYPES_OF_ACCOUNT
    add primary key (ID);

create table USERS
(
    EMAIL        CHARACTER VARYING(50) not null UNIQUE,
    NAME         CHARACTER VARYING(50) not null,
    SURNAME      CHARACTER VARYING(50),
    USERNAME     CHARACTER VARYING(50) not null,
    PASSWORD     CHARACTER VARYING(50) not null,
    PHOTO        CHARACTER VARYING(250),
    PHONE_NUMBER CHARACTER VARYING(50) not null,
    TYPE_ID      BIGINT                not null
);

alter table USERS
    add primary key (EMAIL);

alter table RESPONDS
    add foreign key (RESPOND) references USERS;

alter table RESUMES
    add foreign key (AUTHOR_EMAIL) references USERS;

alter table USERS
    add foreign key (TYPE_ID) references TYPES_OF_ACCOUNT;

create table VACANCY
(
    ID               BIGINT auto_increment UNIQUE not null,
    JOB_TITLE        CHARACTER VARYING(50)        not null,
    SALARY           DOUBLE PRECISION             not null,
    JOB_DESCRIPTION  CHARACTER VARYING(150)       not null,
    REQUIRED_MAX_EXP INTEGER                      not null,
    REQUIRED_MIN_EXP INTEGER                      not null,
    DATE_OF_POSTED   DATE                         not null,
    ACTIVE           BOOLEAN                      not null,
    CATEGORY_ID      BIGINT                       not null,
    AUTHOR_EMAIL     CHARACTER VARYING(50)        not null
);

alter table VACANCY
    add primary key (ID);

alter table RESPONDS
    add foreign key (FOR_WHAT_RESPONDED) references VACANCY;

alter table VACANCY
    add foreign key (CATEGORY_ID) references CATEGORIES;

alter table VACANCY
    add foreign key (AUTHOR_EMAIL) references USERS;


insert into types_of_account(type)
values ('Applicant'),
       ('Employer');


insert into users(name, surname, username, email, password, photo, phone_number, type_id)
values ('CWX.TRANSPORTATION', null, 'cwxxxx', 'cwx@mail.ru', 'qwerty', null, '+996502271004',
        (select id from types_of_account where type = 'Employer')),
       ('Azidin', 'Amankulov', 'azidinn', 'azidin@mail.ru', 'azidinmanka', null, '+996555555555',
        (select id from types_of_account where type = 'Applicant'));


insert into categories(title)
values ('Taxi'),
       ('Sergent');


insert into contacts(PHONE_NUMBER, telegram, email, facebook_link, linkedin_link)
values ('+996555555555', 'CVK.REDAN', 'REDAN.SILA', 'https://sasaeeaessaeea', 'https://xaxaaxaxxaxaxax');


insert into vacancy(job_title, salary, job_description, required_max_exp, required_min_exp, date_of_posted, active,
                    category_id, author_email)
values ('Taxi', 200.00, 'We are very friendly', 1, 5, CURRENT_DATE, true,
        (select id from categories where title = 'Taxi'), (select email from users where email = 'cwx@mail.ru')),
       ('Sergent', 3000.00, 'We need a man sergent', 3, 10, CURRENT_DATE, true,
        (select id from categories where title = 'Sergent'), (select email from users where email = 'cwx@mail.ru'));


insert into resumes(job_title, required_salary, experience, title_of_company, responsibilities, education, active,
                    contact_id, author_email, category_id)
values ('Taxi', 100.00, 'I was taxi worker in the NY', 'FastX', 'Be friendly', 'Sergent', true,
        (select id from CONTACTS where PHONE_NUMBER = '+996555555555'),
        (select email from users where email = 'azidin@mail.ru'), (select id from categories where title = 'Taxi')),
       ('Sergent', 2500.00, 'I was a sergent in the NY central hospital for a 4 year', 'NYC', 'Be carefully', 'Sergent',
        true, (select id from CONTACTS where PHONE_NUMBER = '+996555555555'),
        (select email from users where email = 'azidin@mail.ru'), (select id from categories where title = 'Sergent'));


insert into responds(time_of_respond, respond, for_what_responded)
values (CURRENT_TIMESTAMP(), (select email from users where email = 'azidin@mail.ru'),
        (select id from vacancy where job_title = 'Taxi')),
       (CURRENT_TIMESTAMP(), (select email from users where email = 'azidin@mail.ru'),
        (select id from vacancy where job_title = 'Sergent'));

