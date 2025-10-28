-- Datos de prueba para desarrollo
INSERT INTO cliente (nombre, tipo_documento, numero_documento, direccion, contacto, email, fecha_registro, activo) 
VALUES 
('Juan Pérez García', 'DNI', '12345678', 'Av. Lima 123', '999888777', 'juan.perez@email.com', NOW(), true),
('María López Soto', 'DNI', '87654321', 'Jr. Ayacucho 456', '988777666', 'maria.lopez@email.com', NOW(), true),
('Empresa Tech SAC', 'RUC', '20123456789', 'Av. Industrial 789', '955444333', 'contacto@empresatech.com', NOW(), true);