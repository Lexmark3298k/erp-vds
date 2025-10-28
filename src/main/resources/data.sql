-- Datos de prueba para desarrollo

-- Clientes
INSERT INTO cliente (nombre, tipo_documento, numero_documento, direccion, contacto, email, fecha_registro, activo) 
VALUES 
('Juan Pérez García', 'DNI', '12345678', 'Av. Lima 123', '999888777', 'juan.perez@email.com', NOW(), true),
('María López Soto', 'DNI', '87654321', 'Jr. Ayacucho 456', '988777666', 'maria.lopez@email.com', NOW(), true),
('Empresa Tech SAC', 'RUC', '20123456789', 'Av. Industrial 789', '955444333', 'contacto@empresatech.com', NOW(), true),
('Carlos Rodríguez', 'DNI', '11223344', 'Calle Los Pinos 321', '977666555', 'carlos.rodriguez@email.com', NOW(), true);

-- Técnicos
INSERT INTO tecnico (nombre, especialidad, telefono, direccion, estado, fecha_registro, activo)
VALUES
('Luis Martínez', 'Cámaras de Seguridad', '966555444', 'Av. Tecnología 123', 'DISPONIBLE', NOW(), true),
('Ana Gómez', 'Sistemas de Alarma', '955444333', 'Jr. Electrónica 456', 'DISPONIBLE', NOW(), true),
('Pedro Sánchez', 'Cableado Estructurado', '944333222', 'Calle Central 789', 'OCUPADO', NOW(), true),
('Laura Díaz', 'Control de Acceso', '933222111', 'Av. Innovación 321', 'DISPONIBLE', NOW(), true);

-- Productos/Inventario
INSERT INTO producto (nombre, descripcion, categoria, precio, stock, stock_minimo, proveedor, fecha_registro, activo)
VALUES
('Cámara IP HD 1080p', 'Cámara de vigilancia IP con resolución 1080p', 'Cámaras', 299.99, 15, 5, 'TechSupplier SAC', NOW(), true),
('DVR 8 Canales', 'Grabador digital de video 8 canales', 'Grabadores', 499.99, 8, 3, 'SecurityTech', NOW(), true),
('Sensor de Movimiento', 'Sensor infrarrojo para alarmas', 'Sensores', 89.99, 25, 10, 'AlarmSystems', NOW(), true),
('Cable UTP Cat6', 'Cable de red categoría 6, 100 metros', 'Cableado', 45.50, 50, 20, 'CableWorld', NOW(), true),
('Fuente de Poder 12V', 'Fuente de poder 12V 2A para cámaras', 'Accesorios', 25.99, 30, 15, 'PowerTech', NOW(), true),
('NVR 16 Canales', 'Grabador de video en red 16 canales', 'Grabadores', 799.99, 5, 2, 'NetSecurity', NOW(), true);