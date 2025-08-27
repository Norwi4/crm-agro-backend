-- CRM Agro Backend Database Schema
-- Создание таблиц для JDBC Template

-- Пользователи системы
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    employee_id BIGINT,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Сотрудники
CREATE TABLE IF NOT EXISTS employees (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100),
    role VARCHAR(20) NOT NULL,
    team VARCHAR(100),
    rate DECIMAL(10,2),
    phone VARCHAR(20),
    email VARCHAR(100),
    hire_date DATE,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Поля
CREATE TABLE IF NOT EXISTS fields (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    area DECIMAL(10,2) NOT NULL,
    crop VARCHAR(100),
    season VARCHAR(50),
    soil VARCHAR(100),
    geojson TEXT,
    owner_id BIGINT REFERENCES employees(id),
    description TEXT,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Техника
CREATE TABLE IF NOT EXISTS machines (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    type VARCHAR(100) NOT NULL,
    plate VARCHAR(20),
    vin VARCHAR(50),
    model VARCHAR(100),
    manufacturer VARCHAR(100),
    norm_fuel DECIMAL(8,2),
    hourly_cost DECIMAL(10,2),
    telematics_id VARCHAR(100),
    status VARCHAR(20) DEFAULT 'ACTIVE',
    last_maintenance_date DATE,
    next_maintenance_date DATE,
    total_hours INTEGER DEFAULT 0,
    total_fuel DECIMAL(10,2) DEFAULT 0,
    description TEXT,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Материалы
CREATE TABLE IF NOT EXISTS materials (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    unit VARCHAR(20) NOT NULL,
    batch VARCHAR(100),
    certificate VARCHAR(100),
    stock DECIMAL(10,2) DEFAULT 0,
    min_stock DECIMAL(10,2) DEFAULT 0,
    category VARCHAR(100),
    description TEXT,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- История цен на материалы
CREATE TABLE IF NOT EXISTS material_price_history (
    id BIGSERIAL PRIMARY KEY,
    material_id BIGINT REFERENCES materials(id),
    price DECIMAL(10,2) NOT NULL,
    valid_from DATE NOT NULL,
    valid_to DATE,
    source VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Задачи
CREATE TABLE IF NOT EXISTS tasks (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    type VARCHAR(50) NOT NULL,
    field_id BIGINT REFERENCES fields(id),
    machine_id BIGINT REFERENCES machines(id),
    assignees JSONB,
    start_plan TIMESTAMP,
    end_plan TIMESTAMP,
    start_actual TIMESTAMP,
    end_actual TIMESTAMP,
    checklist JSONB,
    status VARCHAR(20) DEFAULT 'PLANNED',
    planned_area DECIMAL(10,2),
    actual_area DECIMAL(10,2),
    planned_hours DECIMAL(8,2),
    actual_hours DECIMAL(8,2),
    planned_fuel DECIMAL(8,2),
    actual_fuel DECIMAL(8,2),
    notes TEXT,
    priority INTEGER DEFAULT 1,
    parent_task_id BIGINT REFERENCES tasks(id),
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Записи о работах
CREATE TABLE IF NOT EXISTS work_entries (
    id BIGSERIAL PRIMARY KEY,
    task_id BIGINT REFERENCES tasks(id),
    field_id BIGINT REFERENCES fields(id),
    machine_id BIGINT REFERENCES machines(id),
    employee_ids JSONB,
    area DECIMAL(10,2),
    hours DECIMAL(8,2),
    fuel_used DECIMAL(8,2),
    materials JSONB,
    photos JSONB,
    geo JSONB,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    notes TEXT,
    status VARCHAR(20) DEFAULT 'DRAFT',
    is_offline BOOLEAN DEFAULT false,
    sync_status VARCHAR(20) DEFAULT 'SYNCED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Путевые листы
CREATE TABLE IF NOT EXISTS waybills (
    id BIGSERIAL PRIMARY KEY,
    waybill_number VARCHAR(50) UNIQUE NOT NULL,
    machine_id BIGINT REFERENCES machines(id),
    driver_id BIGINT REFERENCES employees(id),
    route JSONB,
    odo_start INTEGER,
    odo_end INTEGER,
    moto_start INTEGER,
    moto_end INTEGER,
    fuel_start DECIMAL(8,2),
    fuel_end DECIMAL(8,2),
    fuel_issued DECIMAL(8,2),
    waybill_date DATE NOT NULL,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    status VARCHAR(20) DEFAULT 'DRAFT',
    driver_signature TEXT,
    mechanic_signature TEXT,
    pdf_path VARCHAR(500),
    notes TEXT,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Топливные карты
CREATE TABLE IF NOT EXISTS fuel_cards (
    id BIGSERIAL PRIMARY KEY,
    provider VARCHAR(100) NOT NULL,
    card_number VARCHAR(50) UNIQUE NOT NULL,
    holder VARCHAR(100),
    machine_id BIGINT REFERENCES machines(id),
    driver_id BIGINT REFERENCES employees(id),
    daily_limit DECIMAL(10,2),
    monthly_limit DECIMAL(10,2),
    balance DECIMAL(10,2) DEFAULT 0,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    expiry_date DATE,
    notes TEXT,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Топливные транзакции
CREATE TABLE IF NOT EXISTS fuel_transactions (
    id BIGSERIAL PRIMARY KEY,
    card_id BIGINT REFERENCES fuel_cards(id),
    datetime TIMESTAMP NOT NULL,
    liters DECIMAL(8,2) NOT NULL,
    price DECIMAL(8,2) NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    station VARCHAR(200),
    station_address TEXT,
    geo JSONB,
    matched_waybill_id BIGINT REFERENCES waybills(id),
    matched_work_entry_id BIGINT REFERENCES work_entries(id),
    status VARCHAR(20) DEFAULT 'PENDING',
    is_anomaly BOOLEAN DEFAULT false,
    anomaly_reason TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Техническое обслуживание
CREATE TABLE IF NOT EXISTS maintenance (
    id BIGSERIAL PRIMARY KEY,
    machine_id BIGINT REFERENCES machines(id),
    type VARCHAR(50) NOT NULL,
    scheduled_at TIMESTAMP,
    scheduled_by_metric JSONB,
    performed_at TIMESTAMP,
    status VARCHAR(20) DEFAULT 'PLANNED',
    parts JSONB,
    cost DECIMAL(10,2),
    labor_hours DECIMAL(8,2),
    performed_by_id BIGINT REFERENCES employees(id),
    description TEXT,
    notes TEXT,
    next_maintenance_at TIMESTAMP,
    next_maintenance_by_metric JSONB,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Документы
CREATE TABLE IF NOT EXISTS documents (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    type VARCHAR(50) NOT NULL,
    linked_object_type VARCHAR(50),
    linked_object_id BIGINT,
    file_path VARCHAR(500) NOT NULL,
    file_type VARCHAR(50),
    file_size BIGINT,
    uploaded_by_id BIGINT REFERENCES users(id),
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'DRAFT',
    description TEXT,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Фотографии
CREATE TABLE IF NOT EXISTS photos (
    id BIGSERIAL PRIMARY KEY,
    work_entry_id BIGINT REFERENCES work_entries(id),
    file_path VARCHAR(500) NOT NULL,
    file_type VARCHAR(50),
    file_size BIGINT,
    geo JSONB,
    timestamp TIMESTAMP,
    description TEXT,
    photo_type VARCHAR(50),
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Журнал аудита
CREATE TABLE IF NOT EXISTS audit_logs (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    action VARCHAR(100) NOT NULL,
    object_type VARCHAR(50),
    object_id BIGINT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    details JSONB,
    ip_address VARCHAR(45),
    user_agent TEXT,
    session_id VARCHAR(100)
);

-- Журнал интеграций
CREATE TABLE IF NOT EXISTS integration_logs (
    id BIGSERIAL PRIMARY KEY,
    system VARCHAR(50) NOT NULL,
    direction VARCHAR(20) NOT NULL,
    doc_type VARCHAR(50),
    status VARCHAR(20) DEFAULT 'PENDING',
    payload JSONB,
    payload_hash VARCHAR(64),
    details JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processed_at TIMESTAMP
);

-- Уведомления
CREATE TABLE IF NOT EXISTS notifications (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    title VARCHAR(200) NOT NULL,
    message TEXT NOT NULL,
    type VARCHAR(50) NOT NULL,
    status VARCHAR(20) DEFAULT 'UNREAD',
    object_type VARCHAR(50),
    object_id BIGINT,
    read_at TIMESTAMP,
    sent_at TIMESTAMP,
    metadata JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Индексы для оптимизации
CREATE INDEX IF NOT EXISTS idx_users_username ON users(username);
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
CREATE INDEX IF NOT EXISTS idx_users_role ON users(role);
CREATE INDEX IF NOT EXISTS idx_users_active ON users(is_active);

CREATE INDEX IF NOT EXISTS idx_employees_role ON employees(role);
CREATE INDEX IF NOT EXISTS idx_employees_active ON employees(is_active);
CREATE INDEX IF NOT EXISTS idx_employees_team ON employees(team);

CREATE INDEX IF NOT EXISTS idx_fields_crop ON fields(crop);
CREATE INDEX IF NOT EXISTS idx_fields_season ON fields(season);
CREATE INDEX IF NOT EXISTS idx_fields_active ON fields(is_active);
CREATE INDEX IF NOT EXISTS idx_fields_owner ON fields(owner_id);

CREATE INDEX IF NOT EXISTS idx_machines_type ON machines(type);
CREATE INDEX IF NOT EXISTS idx_machines_status ON machines(status);
CREATE INDEX IF NOT EXISTS idx_machines_plate ON machines(plate);
CREATE INDEX IF NOT EXISTS idx_machines_vin ON machines(vin);
CREATE INDEX IF NOT EXISTS idx_machines_active ON machines(is_active);

CREATE INDEX IF NOT EXISTS idx_tasks_type ON tasks(type);
CREATE INDEX IF NOT EXISTS idx_tasks_status ON tasks(status);
CREATE INDEX IF NOT EXISTS idx_tasks_field ON tasks(field_id);
CREATE INDEX IF NOT EXISTS idx_tasks_machine ON tasks(machine_id);
CREATE INDEX IF NOT EXISTS idx_tasks_active ON tasks(is_active);
CREATE INDEX IF NOT EXISTS idx_tasks_start_plan ON tasks(start_plan);
CREATE INDEX IF NOT EXISTS idx_tasks_end_plan ON tasks(end_plan);

CREATE INDEX IF NOT EXISTS idx_work_entries_task ON work_entries(task_id);
CREATE INDEX IF NOT EXISTS idx_work_entries_field ON work_entries(field_id);
CREATE INDEX IF NOT EXISTS idx_work_entries_machine ON work_entries(machine_id);
CREATE INDEX IF NOT EXISTS idx_work_entries_status ON work_entries(status);
CREATE INDEX IF NOT EXISTS idx_work_entries_created_at ON work_entries(created_at);

CREATE INDEX IF NOT EXISTS idx_waybills_number ON waybills(waybill_number);
CREATE INDEX IF NOT EXISTS idx_waybills_machine ON waybills(machine_id);
CREATE INDEX IF NOT EXISTS idx_waybills_driver ON waybills(driver_id);
CREATE INDEX IF NOT EXISTS idx_waybills_date ON waybills(waybill_date);
CREATE INDEX IF NOT EXISTS idx_waybills_status ON waybills(status);

CREATE INDEX IF NOT EXISTS idx_fuel_cards_number ON fuel_cards(card_number);
CREATE INDEX IF NOT EXISTS idx_fuel_cards_provider ON fuel_cards(provider);
CREATE INDEX IF NOT EXISTS idx_fuel_cards_machine ON fuel_cards(machine_id);
CREATE INDEX IF NOT EXISTS idx_fuel_cards_status ON fuel_cards(status);

CREATE INDEX IF NOT EXISTS idx_fuel_transactions_card ON fuel_transactions(card_id);
CREATE INDEX IF NOT EXISTS idx_fuel_transactions_datetime ON fuel_transactions(datetime);
CREATE INDEX IF NOT EXISTS idx_fuel_transactions_status ON fuel_transactions(status);
CREATE INDEX IF NOT EXISTS idx_fuel_transactions_anomaly ON fuel_transactions(is_anomaly);

CREATE INDEX IF NOT EXISTS idx_maintenance_machine ON maintenance(machine_id);
CREATE INDEX IF NOT EXISTS idx_maintenance_type ON maintenance(type);
CREATE INDEX IF NOT EXISTS idx_maintenance_status ON maintenance(status);
CREATE INDEX IF NOT EXISTS idx_maintenance_scheduled_at ON maintenance(scheduled_at);

CREATE INDEX IF NOT EXISTS idx_documents_type ON documents(type);
CREATE INDEX IF NOT EXISTS idx_documents_linked_object ON documents(linked_object_type, linked_object_id);
CREATE INDEX IF NOT EXISTS idx_documents_uploaded_by ON documents(uploaded_by_id);

CREATE INDEX IF NOT EXISTS idx_photos_work_entry ON photos(work_entry_id);
CREATE INDEX IF NOT EXISTS idx_photos_type ON photos(photo_type);

CREATE INDEX IF NOT EXISTS idx_audit_logs_user ON audit_logs(user_id);
CREATE INDEX IF NOT EXISTS idx_audit_logs_action ON audit_logs(action);
CREATE INDEX IF NOT EXISTS idx_audit_logs_timestamp ON audit_logs(timestamp);
CREATE INDEX IF NOT EXISTS idx_audit_logs_object ON audit_logs(object_type, object_id);

CREATE INDEX IF NOT EXISTS idx_integration_logs_system ON integration_logs(system);
CREATE INDEX IF NOT EXISTS idx_integration_logs_status ON integration_logs(status);
CREATE INDEX IF NOT EXISTS idx_integration_logs_created_at ON integration_logs(created_at);

CREATE INDEX IF NOT EXISTS idx_notifications_user ON notifications(user_id);
CREATE INDEX IF NOT EXISTS idx_notifications_type ON notifications(type);
CREATE INDEX IF NOT EXISTS idx_notifications_status ON notifications(status);
CREATE INDEX IF NOT EXISTS idx_notifications_created_at ON notifications(created_at);

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
COMMENT ON TABLE audit_logs IS 'Журнал аудита';
COMMENT ON TABLE integration_logs IS 'Журнал интеграций';
COMMENT ON TABLE notifications IS 'Уведомления';
