INSERT INTO "customer" ("id", "cpf", "email") VALUES (1, '22.758.871-01', 'eduardo@outlook.com'), (2, '45.665.643-01', 'jose@outlook.com');
INSERT INTO "enterprise" ("id", "cnpj", "balance") VALUES (1, '74.455.712/0001-31', 100.0), (2, '61.807.074/0001-29', 200.0);
INSERT INTO "tax" ("id", "value", "type") VALUES (1, 2.0, 'DEPOSIT'), (2, 3.0, 'WITHDRAW'), (3, 4.0, 'WITHDRAW');
INSERT INTO "enterprise_taxes" ("enterprise_id", "taxes_id") VALUES (1, 1), (1, 2), (2, 3);