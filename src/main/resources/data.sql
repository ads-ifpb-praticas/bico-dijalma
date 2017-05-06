INSERT INTO person (id, cpf, email, last_name, name, path_photo, telephone)
  SELECT
    1,
    '000.000.000-00',
    'admin@admin.com',
    'master',
    'admin',
    '/static/img/man.png',
    '(00) 00000-0000'
  WHERE NOT EXISTS(SELECT id
                   FROM person
                   WHERE id = 1);

INSERT INTO admin (password, username, id) SELECT
                                             'admin',
                                             'admin',
                                             1
                                           WHERE NOT exists(SELECT id
                                                            FROM admin
                                                            WHERE id = 1)