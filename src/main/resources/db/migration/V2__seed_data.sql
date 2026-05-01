-- USERS
INSERT INTO users (username, password, role, email) VALUES
('admin', '$2a$10$hashedpassword1', 'ADMIN', 'admin@flowers.ru'),
('ivan',  '$2a$10$hashedpassword2', 'USER',  'ivan@mail.ru'),
('anna',  '$2a$10$hashedpassword3', 'USER',  'anna@mail.ru');

-- CATEGORIES (категории цветов)
INSERT INTO categories (name) VALUES
('Букеты'),
('Розы'),
('Комнатные растения'),
('Подарочные композиции');

-- PRODUCTS (цветы)
INSERT INTO products (name, description, price, category_id) VALUES
('Букет "Весенний микс"', 'Яркий букет из тюльпанов, нарциссов и ирисов', 2490.00, 1),
('Букет "Нежность"', 'Пастельный букет из роз и эустом', 2790.00, 1),
('Букет "Яркое настроение"', 'Сборный букет из сезонных цветов', 3190.00, 1),
('Букет красных роз (15 шт)', 'Классический букет из свежих красных роз', 3990.00, 2),
('Букет белых роз (11 шт)', 'Элегантные белые розы премиум качества', 3290.00, 2),
('Розы кустовые микс', 'Микс кустовых роз разных оттенков', 2890.00, 2),
('Орхидея фаленопсис', 'Комнатное растение с длительным цветением', 1890.00, 3),
('Фикус Бенджамина', 'Неприхотливое декоративное растение', 1590.00, 3),
('Сансевиерия', 'Популярное растение для дома и офиса', 990.00, 3),
('Композиция "Праздничная"', 'Цветочная композиция в коробке с лентами', 3290.00, 4),
('Коробка "Романтика"', 'Цветы в подарочной коробке с декором', 3590.00, 4);

-- CART (корзина пользователя Иван)
INSERT INTO cart (user_id, product_id, quantity) VALUES
(2, 1, 1),
(2, 2, 1),
(3, 3, 2);

-- ORDERS (заказ Ивана)
INSERT INTO orders (user_id, created_at) VALUES
(2, NOW());

-- ORDER ITEMS
INSERT INTO order_items (order_id, product_id, quantity, price) VALUES
(1, 1, 1, 2490.00),
(1, 2, 1, 3990.00);

-- DELIVERY
INSERT INTO deliveries (
    order_id,
    delivery_address,
    city,
    postal_code,
    status,
    delivery_date
) VALUES (
    1,
    'ул. Ленская, 10',
    'Москва',
    '630000',
    'PENDING',
    NULL
);