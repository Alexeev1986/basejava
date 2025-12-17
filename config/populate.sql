DELETE FROM resume;

DELETE FROM contact;


INSERT INTO resume(uuid, full_name) VALUES
    ('uuid1', 'Курочкин Евгений Николаевич'),
    ('uuid2', 'Иванов Никита Николаевич'),
    ('uuid3' , 'Полохов Алексей Владимирович');

INSERT INTO contact(resume_uuid, type, value) VALUES
    ('uuid1', 'PHONE', '9098612432'),
    ('uuid1', 'SKYPE', 'skype'),
    ('uuid2', 'PHONE', '9098613333'),
    ('uuid2', 'SKYPE', 'skype2');

SELECT *
FROM resume r
         RIGHT JOIN contact c
              ON r.uuid=c.resume_uuid
