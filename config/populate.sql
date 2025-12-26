DELETE FROM resume;

DELETE FROM contact;

DELETE FROM section;

INSERT INTO resume(uuid, full_name) VALUES
    ('7de882da-02f2-4d16-8daa-60660aaf4071', 'Name1'),
    ('a97b3ac3-3817-4c3f-8a5f-178497311f1d', 'Name2'),
    ('dd0a70d1-5ed3-479a-b452-d5e04f21ca73', 'Name3');

INSERT INTO contact(resume_uuid, type, value) VALUES
    ('7de882da-02f2-4d16-8daa-60660aaf4071', 'PHONE', '9098612432'),
    ('7de882da-02f2-4d16-8daa-60660aaf4071', 'SKYPE', 'skype'),
    ('7de882da-02f2-4d16-8daa-60660aaf4071', 'MAIL', 'name1@mail.ru'),
    ('7de882da-02f2-4d16-8daa-60660aaf4071', 'LINKEDIN', 'https://linkedin.com/in/name1'),
    ('7de882da-02f2-4d16-8daa-60660aaf4071', 'GITHUB', 'https://github.com/name1'),
    ('7de882da-02f2-4d16-8daa-60660aaf4071', 'STACKOVERFLOW', 'https://stackoverflow.com/users/123456/name1'),
    ('7de882da-02f2-4d16-8daa-60660aaf4071', 'HOMEPAGE', 'https://name1.example.com'),
    ('a97b3ac3-3817-4c3f-8a5f-178497311f1d', 'PHONE', '9098613333'),
    ('a97b3ac3-3817-4c3f-8a5f-178497311f1d', 'SKYPE', 'skype2'),
    ('a97b3ac3-3817-4c3f-8a5f-178497311f1d', 'MAIL', 'name2@yndex.ru'),
    ('a97b3ac3-3817-4c3f-8a5f-178497311f1d', 'LINKEDIN', 'https://linkedin.com/in/name2'),
    ('a97b3ac3-3817-4c3f-8a5f-178497311f1d', 'GITHUB', 'https://github.com/name2'),
    ('a97b3ac3-3817-4c3f-8a5f-178497311f1d', 'STACKOVERFLOW', 'https://stackoverflow.com/users/234567/name2'),
    ('a97b3ac3-3817-4c3f-8a5f-178497311f1d', 'HOMEPAGE', 'https://name2.example.com'),
    ('dd0a70d1-5ed3-479a-b452-d5e04f21ca73', 'PHONE', '9098613333'),
    ('dd0a70d1-5ed3-479a-b452-d5e04f21ca73', 'SKYPE', 'skype2'),
    ('dd0a70d1-5ed3-479a-b452-d5e04f21ca73', 'MAIL', 'name3@inbox.ru'),
    ('dd0a70d1-5ed3-479a-b452-d5e04f21ca73', 'LINKEDIN', 'https://linkedin.com/in/name3'),
    ('dd0a70d1-5ed3-479a-b452-d5e04f21ca73', 'GITHUB', 'https://github.com/name3'),
    ('dd0a70d1-5ed3-479a-b452-d5e04f21ca73', 'STACKOVERFLOW', 'https://stackoverflow.com/users/345678/name3'),
    ('dd0a70d1-5ed3-479a-b452-d5e04f21ca73', 'HOMEPAGE', 'https://name3.example.com');


