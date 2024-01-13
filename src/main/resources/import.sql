INSERT INTO "customer" ("id", "cpf", "email") VALUES (1, '22.758.871-01', 'eduardo@outlook.com'), (2, '45.665.643-01', 'jose@outlook.com');
INSERT INTO "enterprise" ("id", "cnpj", "balance") VALUES (1, '74.455.712-31', 100.0), (2, '61.807.074-29', 200.0);
INSERT INTO "tax" ("id", "value", "enterprise_id", "type") VALUES (1, 10.0, 1, 'DEPOSIT'), (2, 20.0, 1, 'WITHDRAW'), (3, 10, 2, 'WITHDRAW');