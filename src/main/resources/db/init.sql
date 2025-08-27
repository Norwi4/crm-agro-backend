-- Инициализация базы данных CRM Agro Backend
-- Этот скрипт создает тестовые данные для демонстрации функциональности

-- Создание базы данных (выполнить отдельно)
-- CREATE DATABASE crm_agro;

-- Подключение к базе данных
-- \c crm_agro;

-- Таблицы будут созданы автоматически Hibernate при первом запуске
-- Здесь добавляем только тестовые данные

-- Вставка тестовых сотрудников
INSERT INTO employees (first_name, last_name, middle_name, role, team, rate, phone, email, hire_date, is_active, created_at, updated_at) VALUES
('Иван', 'Иванов', 'Иванович', 'AGROLOGIST', 'Агрономия', 50000.00, '+79001234567', 'ivanov@agro.com', '2023-01-15', true, NOW(), NOW()),
('Петр', 'Петров', 'Петрович', 'MECHANIC', 'Механики', 45000.00, '+79001234568', 'petrov@agro.com', '2023-02-01', true, NOW(), NOW()),
('Сергей', 'Сидоров', 'Сергеевич', 'DRIVER', 'Водители', 35000.00, '+79001234569', 'sidorov@agro.com', '2023-03-10', true, NOW(), NOW()),
('Анна', 'Козлова', 'Александровна', 'ACCOUNTANT', 'Бухгалтерия', 40000.00, '+79001234570', 'kozlova@agro.com', '2023-01-20', true, NOW(), NOW()),
('Михаил', 'Смирнов', 'Дмитриевич', 'MANAGER', 'Руководство', 80000.00, '+79001234571', 'smirnov@agro.com', '2022-12-01', true, NOW(), NOW());

-- Вставка тестовых пользователей (пароли: admin123, user123)
INSERT INTO users (username, email, phone, password_hash, role, is_active, created_at, updated_at) VALUES
('admin', 'admin@agro.com', '+79001234566', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'ADMIN', true, NOW(), NOW()),
('agronom', 'ivanov@agro.com', '+79001234567', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'AGROLOGIST', true, NOW(), NOW()),
('mechanic', 'petrov@agro.com', '+79001234568', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'MECHANIC', true, NOW(), NOW()),
('driver', 'sidorov@agro.com', '+79001234569', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'DRIVER', true, NOW(), NOW()),
('accountant', 'kozlova@agro.com', '+79001234570', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'ACCOUNTANT', true, NOW(), NOW()),
('manager', 'smirnov@agro.com', '+79001234571', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'MANAGER', true, NOW(), NOW());

-- Связывание пользователей с сотрудниками
UPDATE users SET employee_id = (SELECT id FROM employees WHERE email = 'ivanov@agro.com') WHERE username = 'agronom';
UPDATE users SET employee_id = (SELECT id FROM employees WHERE email = 'petrov@agro.com') WHERE username = 'mechanic';
UPDATE users SET employee_id = (SELECT id FROM employees WHERE email = 'sidorov@agro.com') WHERE username = 'driver';
UPDATE users SET employee_id = (SELECT id FROM employees WHERE email = 'kozlova@agro.com') WHERE username = 'accountant';
UPDATE users SET employee_id = (SELECT id FROM employees WHERE email = 'smirnov@agro.com') WHERE username = 'manager';

-- Вставка тестовых полей
INSERT INTO fields (name, area, crop, season, soil, description, is_active, created_at, updated_at) VALUES
('Поле №1', 150.50, 'Пшеница', '2024', 'Чернозем', 'Основное поле под пшеницу', true, NOW(), NOW()),
('Поле №2', 200.00, 'Кукуруза', '2024', 'Суглинок', 'Поле под кукурузу', true, NOW(), NOW()),
('Поле №3', 120.75, 'Подсолнечник', '2024', 'Чернозем', 'Поле под подсолнечник', true, NOW(), NOW()),
('Поле №4', 180.25, 'Соя', '2024', 'Суглинок', 'Поле под сою', true, NOW(), NOW()),
('Поле №5', 95.30, 'Рапс', '2024', 'Чернозем', 'Поле под рапс', true, NOW(), NOW());

-- Вставка тестовой техники
INSERT INTO machines (name, type, plate, vin, model, manufacturer, norm_fuel, hourly_cost, status, total_hours, total_fuel, description, is_active, created_at, updated_at) VALUES
('Трактор John Deere 7930', 'Трактор', 'А123БВ77', '1JD7930A123456789', '7930', 'John Deere', 15.5, 2500.00, 'ACTIVE', 1250.5, 19382.75, 'Основной трактор', true, NOW(), NOW()),
('Комбайн New Holland CR10.90', 'Комбайн', 'В456ГД77', '1NHCR1090A987654321', 'CR10.90', 'New Holland', 25.0, 5000.00, 'ACTIVE', 850.2, 21255.00, 'Основной комбайн', true, NOW(), NOW()),
('Сеялка Amazone D9', 'Сеялка', 'Е789ЖЗ77', '1AMZD9A456789123', 'D9', 'Amazone', 8.0, 1500.00, 'ACTIVE', 320.8, 2566.40, 'Сеялка точного высева', true, NOW(), NOW()),
('Опрыскиватель Hardi Commander', 'Опрыскиватель', 'И012КЛ77', '1HRDCMDA789123456', 'Commander', 'Hardi', 12.0, 2000.00, 'ACTIVE', 450.3, 5403.60, 'Опрыскиватель', true, NOW(), NOW()),
('Плуг Lemken VariOpal', 'Плуг', 'М345НО77', '1LMKVOPA123456789', 'VariOpal', 'Lemken', 5.5, 800.00, 'ACTIVE', 280.1, 1540.55, 'Плуг оборотный', true, NOW(), NOW());

-- Вставка тестовых материалов
INSERT INTO materials (name, unit, batch, certificate, stock, min_stock, category, description, is_active, created_at, updated_at) VALUES
('Семена пшеницы', 'кг', 'SW2024-001', 'CERT-SW-001', 5000.00, 1000.00, 'Семена', 'Семена пшеницы озимой', true, NOW(), NOW()),
('Удобрение NPK 16-16-16', 'кг', 'NPK2024-001', 'CERT-NPK-001', 10000.00, 2000.00, 'Удобрения', 'Комплексное удобрение', true, NOW(), NOW()),
('Гербицид Раундап', 'л', 'HERB2024-001', 'CERT-HERB-001', 500.00, 100.00, 'СЗР', 'Гербицид сплошного действия', true, NOW(), NOW()),
('Дизельное топливо', 'л', 'FUEL2024-001', 'CERT-FUEL-001', 50000.00, 10000.00, 'Топливо', 'Дизельное топливо', true, NOW(), NOW()),
('Моторное масло', 'л', 'OIL2024-001', 'CERT-OIL-001', 200.00, 50.00, 'Смазочные материалы', 'Моторное масло 15W-40', true, NOW(), NOW());

-- Вставка истории цен материалов
INSERT INTO material_price_history (material_id, price, valid_from, valid_to, source, created_at) VALUES
((SELECT id FROM materials WHERE name = 'Семена пшеницы'), 45.00, '2024-01-01 00:00:00', '2024-12-31 23:59:59', '1С', NOW()),
((SELECT id FROM materials WHERE name = 'Удобрение NPK 16-16-16'), 85.00, '2024-01-01 00:00:00', '2024-12-31 23:59:59', '1С', NOW()),
((SELECT id FROM materials WHERE name = 'Гербицид Раундап'), 1200.00, '2024-01-01 00:00:00', '2024-12-31 23:59:59', '1С', NOW()),
((SELECT id FROM materials WHERE name = 'Дизельное топливо'), 65.00, '2024-01-01 00:00:00', '2024-12-31 23:59:59', '1С', NOW()),
((SELECT id FROM materials WHERE name = 'Моторное масло'), 450.00, '2024-01-01 00:00:00', '2024-12-31 23:59:59', '1С', NOW());

-- Вставка тестовых задач
INSERT INTO tasks (title, description, type, field_id, machine_id, start_plan, end_plan, planned_area, planned_hours, planned_fuel, priority, status, is_active, created_at, updated_at) VALUES
('Вспашка поля №1', 'Вспашка поля под пшеницу', 'PLOWING', 1, 1, '2024-03-15 08:00:00', '2024-03-15 18:00:00', 150.50, 10.0, 155.0, 1, 'PLANNED', true, NOW(), NOW()),
('Посев пшеницы', 'Посев пшеницы на поле №1', 'SEEDING', 1, 3, '2024-03-20 08:00:00', '2024-03-20 16:00:00', 150.50, 8.0, 120.4, 1, 'PLANNED', true, NOW(), NOW()),
('Обработка гербицидом', 'Обработка поля №2 гербицидом', 'SPRAYING', 2, 4, '2024-03-25 08:00:00', '2024-03-25 14:00:00', 200.00, 6.0, 72.0, 2, 'PLANNED', true, NOW(), NOW()),
('Уборка кукурузы', 'Уборка кукурузы с поля №2', 'HARVESTING', 2, 2, '2024-09-15 08:00:00', '2024-09-20 18:00:00', 200.00, 50.0, 1250.0, 1, 'PLANNED', true, NOW(), NOW()),
('ТО трактора', 'Техническое обслуживание трактора', 'MAINTENANCE', NULL, 1, '2024-04-01 08:00:00', '2024-04-01 16:00:00', NULL, 8.0, NULL, 3, 'PLANNED', true, NOW(), NOW());

-- Вставка тестовых топливных карт
INSERT INTO fuel_cards (provider, card_number, holder, machine_id, driver_id, daily_limit, monthly_limit, balance, status, notes, is_active, created_at, updated_at) VALUES
('Лукойл', '1234567890123456', 'Иванов И.И.', 1, 3, 200.00, 5000.00, 1500.00, 'ACTIVE', 'Карта для трактора', true, NOW(), NOW()),
('Роснефть', '9876543210987654', 'Петров П.П.', 2, 3, 300.00, 8000.00, 2500.00, 'ACTIVE', 'Карта для комбайна', true, NOW(), NOW()),
('Газпромнефть', '1111222233334444', 'Сидоров С.С.', 3, 3, 100.00, 2000.00, 800.00, 'ACTIVE', 'Карта для сеялки', true, NOW(), NOW());

-- Вставка тестовых топливных транзакций
INSERT INTO fuel_transactions (card_id, datetime, liters, price, total_amount, station, station_address, status, created_at, updated_at) VALUES
(1, '2024-03-15 10:30:00', 50.00, 65.00, 3250.00, 'Лукойл №123', 'г. Москва, ул. Примерная, 1', 'PENDING', NOW(), NOW()),
(1, '2024-03-15 16:45:00', 45.00, 65.00, 2925.00, 'Лукойл №123', 'г. Москва, ул. Примерная, 1', 'PENDING', NOW(), NOW()),
(2, '2024-03-16 09:15:00', 100.00, 67.00, 6700.00, 'Роснефть №456', 'г. Москва, ул. Другая, 2', 'PENDING', NOW(), NOW());

-- Вставка тестовых записей о работах
INSERT INTO work_entries (task_id, field_id, machine_id, area, hours, fuel_used, start_time, end_time, notes, status, created_at, updated_at) VALUES
(1, 1, 1, 150.50, 10.5, 162.75, '2024-03-15 08:00:00', '2024-03-15 18:30:00', 'Вспашка выполнена качественно', 'COMPLETED', NOW(), NOW()),
(2, 1, 3, 150.50, 8.2, 131.20, '2024-03-20 08:00:00', '2024-03-20 16:12:00', 'Посев выполнен по технологии', 'COMPLETED', NOW(), NOW()),
(3, 2, 4, 200.00, 6.5, 78.00, '2024-03-25 08:00:00', '2024-03-25 14:30:00', 'Обработка гербицидом', 'COMPLETED', NOW(), NOW());

-- Вставка тестовых путевых листов
INSERT INTO waybills (waybill_number, machine_id, driver_id, odo_start, odo_end, moto_start, moto_end, fuel_start, fuel_end, fuel_issued, waybill_date, start_time, end_time, status, notes, is_active, created_at, updated_at) VALUES
('WL-2024-001', 1, 3, 1250.5, 1300.5, 1250.5, 1261.0, 100.0, 50.0, 50.0, '2024-03-15', '2024-03-15 08:00:00', '2024-03-15 18:30:00', 'COMPLETED', 'Вспашка поля №1', true, NOW(), NOW()),
('WL-2024-002', 3, 3, 320.8, 329.0, 320.8, 329.0, 80.0, 60.0, 20.0, '2024-03-20', '2024-03-20 08:00:00', '2024-03-20 16:12:00', 'COMPLETED', 'Посев пшеницы', true, NOW(), NOW()),
('WL-2024-003', 4, 3, 450.3, 456.8, 450.3, 456.8, 120.0, 42.0, 78.0, '2024-03-25', '2024-03-25 08:00:00', '2024-03-25 14:30:00', 'COMPLETED', 'Обработка гербицидом', true, NOW(), NOW());

-- Вставка тестовых ТО
INSERT INTO maintenance (machine_id, type, scheduled_at, performed_at, status, cost, labor_hours, performed_by, description, notes, is_active, created_at, updated_at) VALUES
(1, 'PREVENTIVE', '2024-04-01 08:00:00', '2024-04-01 16:00:00', 'COMPLETED', 15000.00, 8.0, 2, 'Плановое ТО трактора', 'Заменено масло, фильтры', true, NOW(), NOW()),
(2, 'INSPECTION', '2024-04-05 08:00:00', '2024-04-05 12:00:00', 'COMPLETED', 5000.00, 4.0, 2, 'Осмотр комбайна', 'Проведен осмотр перед сезоном', true, NOW(), NOW()),
(3, 'OIL_CHANGE', '2024-04-10 08:00:00', '2024-04-10 10:00:00', 'COMPLETED', 3000.00, 2.0, 2, 'Замена масла сеялки', 'Заменено масло в редукторе', true, NOW(), NOW());

-- Вставка тестовых уведомлений
INSERT INTO notifications (user_id, title, message, type, status, object_type, object_id, created_at) VALUES
(2, 'Новая задача', 'Вам назначена задача: Вспашка поля №1', 'TASK_ASSIGNED', 'UNREAD', 'TASK', 1, NOW()),
(2, 'Задача завершена', 'Задача "Вспашка поля №1" завершена', 'TASK_COMPLETED', 'UNREAD', 'TASK', 1, NOW()),
(3, 'Требуется ТО', 'Трактор John Deere 7930 требует ТО', 'MAINTENANCE_DUE', 'UNREAD', 'MACHINE', 1, NOW()),
(4, 'Низкий остаток', 'Низкий остаток семян пшеницы', 'MATERIAL_LOW', 'UNREAD', 'MATERIAL', 1, NOW());

-- Индексы уже созданы в schema.sql

-- Комментарии к таблицам
COMMENT ON TABLE users IS 'Пользователи системы';
COMMENT ON TABLE employees IS 'Сотрудники предприятия';
COMMENT ON TABLE fields IS 'Поля сельскохозяйственного назначения';
COMMENT ON TABLE machines IS 'Техника и оборудование';
COMMENT ON TABLE materials IS 'Материалы и ресурсы';
COMMENT ON TABLE material_price_history IS 'История цен на материалы';
COMMENT ON TABLE tasks IS 'Задачи и работы';
COMMENT ON TABLE work_entries IS 'Записи о выполненных работах';
COMMENT ON TABLE waybills IS 'Путевые листы';
COMMENT ON TABLE fuel_cards IS 'Топливные карты';
COMMENT ON TABLE fuel_transactions IS 'Транзакции по топливным картам';
COMMENT ON TABLE maintenance IS 'Техническое обслуживание и ремонт';
COMMENT ON TABLE documents IS 'Документы';
COMMENT ON TABLE photos IS 'Фотографии';
COMMENT ON TABLE notifications IS 'Уведомления';
COMMENT ON TABLE audit_logs IS 'Журнал аудита';
COMMENT ON TABLE integration_logs IS 'Журнал интеграций';

-- Статистика
SELECT 'База данных инициализирована успешно!' as status;
SELECT COUNT(*) as users_count FROM users;
SELECT COUNT(*) as employees_count FROM employees;
SELECT COUNT(*) as fields_count FROM fields;
SELECT COUNT(*) as machines_count FROM machines;
SELECT COUNT(*) as materials_count FROM materials;
SELECT COUNT(*) as tasks_count FROM tasks;
SELECT COUNT(*) as work_entries_count FROM work_entries;
SELECT COUNT(*) as waybills_count FROM waybills;
SELECT COUNT(*) as fuel_cards_count FROM fuel_cards;
SELECT COUNT(*) as fuel_transactions_count FROM fuel_transactions;
SELECT COUNT(*) as maintenance_count FROM maintenance;
SELECT COUNT(*) as notifications_count FROM notifications;
