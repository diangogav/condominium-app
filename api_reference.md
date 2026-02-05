# API Reference - Admin Panel Backend

**Base URL**: `http://localhost:3000` (Development)  
**Authentication**: Bearer Token (JWT)

---

## 📑 Índice

1. [Autenticación](#autenticación)
2. [Usuarios](#usuarios)
3. [Edificios y Apartamentos](#edificios-y-apartamentos)
4. [Pagos](#pagos)
5. [Facturación](#facturación)
6. [Modelos de Datos](#modelos-de-datos)

---

## 🔐 Autenticación

### POST `/auth/register`
Registrar nuevo residente

**Permisos**: Público

**Request Body**:
```json
{
  "name": "Juan Pérez",
  "email": "juan@example.com",
  "password": "SecurePass123",
  "unit_id": "uuid-del-apartamento",
  "building_id": "uuid-del-edificio"
}
```

**Response**:
```json
{
  "access_token": "eyJhbGciOiJI...",
  "refresh_token": "...",
  "expires_in": 3600,
  "user": {
    "id": "uuid",
    "email": "juan@example.com",
    "role": "RESIDENT"
  }
}
```

### POST `/auth/login`
Iniciar sesión

**Permisos**: Público

**Request Body**:
```json
{
  "email": "juan@example.com",
  "password": "SecurePass123"
}
```

**Response**:
```json
{
  "access_token": "eyJhbGciOiJI...",
  "refresh_token": "...",
  "expires_in": 3600,
  "user": {
    "id": "uuid",
    "email": "juan@example.com",
    "role": "board",
    "units": [
      {
        "unit_id": "uuid",
        "building_id": "uuid",
        "building_role": "board",
        "is_primary": true
      }
    ]
  }
}
```

---

## 👥 Usuarios

### GET `/users/me`
Obtener perfil del usuario actual

**Permisos**: Usuario autenticado

**Response**:
```json
{
  "id": "uuid",
  "email": "juan@example.com",
  "name": "Juan Pérez",
  "role": "board",
  "status": "active",
  "phone": "+507 6000-0000",
  "units": [...],
  "created_at": "2026-01-15T10:00:00Z",
  "updated_at": "2026-01-15T10:00:00Z"
}
```

### PATCH `/users/me`
Actualizar perfil propio

**Permisos**: Usuario autenticado

**Request Body**:
```json
{
  "name": "Juan Pérez Actualizado",
  "phone": "+507 6111-1111"
}
```

### GET `/users`
Listar todos los usuarios

**Permisos**: Admin o Board

**Query Parameters**:
- `building_id` (opcional): Filtrar por edificio
- `unit_id` (opcional): Filtrar por apartamento
- `role` (opcional): Filtrar por rol (admin, board, resident)
- `status` (opcional): Filtrar por estado (active, pending, rejected)

**Response**:
```json
[
  {
    "id": "uuid",
    "email": "user@example.com",
    "name": "Usuario Ejemplo",
    "role": "resident",
    "status": "active",
    "phone": "+507 6000-0000",
    "units": [...]
  }
]
```

### GET `/users/:id`
Obtener usuario por ID

**Permisos**: Admin, Board (mismo edificio), o usuario mismo

**Response**: Ver estructura en GET `/users/me`

### PATCH `/users/:id`
Actualizar usuario

**Permisos**: Admin (todos los campos), Board (sin role), Usuario mismo (solo nombre/teléfono)

**Request Body**:
```json
{
  "name": "Nombre Actualizado",
  "phone": "+507 6222-2222",
  "role": "board"   // Solo admin puede cambiar
}
```

### POST `/users`
Crear nuevo usuario

**Permisos**: Solo Admin

**Request Body**:
```json
{
  "email": "nuevo@example.com",
  "password": "SecurePass123",
  "name": "Nuevo Usuario",
  "role": "board",             // admin | board | resident
  "building_id": "uuid",
  "unit_id": "uuid",           // Opcional
  "phone": "+507 6000-0000"    // Opcional
}
```

### DELETE `/users/:id`
Eliminar usuario

**Permisos**: Solo Admin

**Response**:
```json
{
  "success": true
}
```

### POST `/users/:id/approve`
Aprobar registro de usuario pendiente

**Permisos**: Admin o Board (mismo edificio)

**Response**:
```json
{
  "success": true
}
```

---

## 🏢 Gestión de Unidades (Usuario)

### GET `/users/:id/units`
Obtener unidades asignadas a un usuario

**Permisos**: Admin, Board, Usuario mismo

**Response**:
```json
[
  {
    "unit_id": "uuid",
    "building_id": "uuid",
    "building_role": "board",
    "is_primary": true
  }
]
```

### POST `/users/:id/units`
Asignar/Actualizar unidad de usuario

**Permisos**: Admin, Board (solo en sus edificios)

> ⚠️ **Importante**: Si la unidad ya existe, actualiza el `building_role`. Si no existe, la crea.

**Request Body**:
```json
{
  "unit_id": "uuid-del-apartamento",
  "building_role": "board",    // board | resident | owner
  "is_primary": true
}
```

**Casos de uso**:
- ✅ Asignar nueva unidad
- ✅ Promover residente a board
- ✅ Degradar board a resident
- ✅ Cambiar unidad primaria

**Response**:
```json
{
  "success": true
}
```

---

## 🏗️ Edificios y Apartamentos

### GET `/buildings`
Listar todos los edificios

**Permisos**: Público

**Response**:
```json
[
  {
    "id": "uuid",
    "name": "Edificio Central",
    "address": "Av. Principal #123",
    "created_at": "2026-01-01T00:00:00Z"
  }
]
```

### GET `/buildings/:id`
Obtener edificio por ID

**Permisos**: Público

### POST `/buildings`
Crear nuevo edificio

**Permisos**: Solo Admin

**Request Body**:
```json
{
  "name": "Edificio Norte",
  "address": "Calle 5 #456"
}
```

### PATCH `/buildings/:id`
Actualizar edificio

**Permisos**: Solo Admin

**Request Body**:
```json
{
  "name": "Edificio Norte Actualizado",
  "address": "Nueva Dirección"
}
```

### GET `/buildings/:id/units`
Listar apartamentos de un edificio

**Permisos**: Público

**Response**:
```json
[
  {
    "id": "uuid",
    "building_id": "uuid",
    "name": "1-A",
    "floor": "1",
    "aliquot": 0.05,
    "created_at": "2026-01-01T00:00:00Z"
  }
]
```

### GET `/buildings/units/:id`
Obtener apartamento por ID

**Permisos**: Público

### POST `/buildings/:id/units`
Crear apartamento individual

**Permisos**: Solo Admin

**Request Body**:
```json
{
  "name": "2-B",
  "floor": "2",
  "aliquot": 0.05    // Opcional
}
```

### POST `/buildings/:id/units/batch`
Crear apartamentos en lote

**Permisos**: Solo Admin

**Request Body**:
```json
{
  "floors": ["1", "2", "3"],
  "unitsPerFloor": ["A", "B", "C"]
}
```

**Response**:
```json
{
  "count": 9,
  "units": [
    { "id": "uuid", "name": "1-A", ... },
    { "id": "uuid", "name": "1-B", ... },
    ...
  ]
}
```

---

## 💰 Pagos

### GET `/payments`
Historial de pagos del usuario

**Permisos**: Usuario autenticado

**Query Parameters**:
- `year` (opcional): Filtrar por año
- `unit_id` (opcional): Filtrar por apartamento
- `building_id` (opcional): Filtrar por edificio

**Response**:
```json
[
  {
    "id": "uuid",
    "amount": 50.00,
    "currency": "USD",
    "payment_date": "2026-01-15",
    "status": "APPROVED",
    "method": "PAGO_MOVIL",
    "reference": "123456",
    "bank": "Banesco",
    "unit_id": "uuid",
    "building_id": "uuid",
    "proof_url": "https://...",
    "notes": "Pago de enero",
    "periods": ["2026-01"],
    "allocations": [...],
    "user": {
      "id": "uuid",
      "name": "Juan Pérez"
    }
  }
]
```

### GET `/payments/summary`
Resumen de pagos con estado de solvencia

**Permisos**: Usuario autenticado

**Response**:
```json
{
  "solvency_status": "SOLVENTE",
  "last_payment_date": "2026-01-15",
  "pending_periods": ["2026-02"],
  "paid_periods": ["2026-01"],
  "recent_transactions": [...]
}
```

### GET `/payments/:id`
Detalles de un pago

**Permisos**: Admin, Board (mismo edificio), Residente (mismo apartamento)

### POST `/payments`
Reportar nuevo pago

**Permisos**: Usuario autenticado

**Request** (multipart/form-data):
```json
{
  "amount": 50.00,
  "date": "2026-01-15",
  "method": "PAGO_MOVIL",      // PAGO_MOVIL | TRANSFER | CASH
  "reference": "123456",        // Opcional
  "bank": "Banesco",            // Opcional
  "proof_image": File,          // Opcional
  "periods": ["2026-01"],       // Opcional
  "building_id": "uuid",        // Opcional
  "unit_id": "uuid",            // Opcional
  "notes": "Pago de enero",     // Opcional
  "allocations": [              // Opcional
    {
      "invoice_id": "uuid",
      "amount": 50.00
    }
  ]
}
```

**Response**: Ver estructura de pago arriba

### GET `/payments/admin/payments`
Listar todos los pagos (Admin/Board)

**Permisos**: Admin (todos), Board (solo su edificio)

**Query Parameters**:
- `building_id` (opcional)
- `status` (opcional): PENDING | APPROVED | REJECTED
- `period` (opcional): "2026-01"
- `year` (opcional)
- `unit_id` (opcional)

### PATCH `/payments/admin/payments/:id`
Aprobar/Rechazar pago

**Permisos**: Admin, Board (mismo edificio)

**Request Body**:
```json
{
  "status": "APPROVED",          // APPROVED | REJECTED
  "notes": "Confirmado",         // Opcional
  "approved_periods": ["2026-01"] // Opcional
}
```

---

## 📊 Facturación

### GET `/billing/invoices`
Listar todas las facturas

**Permisos**: Admin o Board

**Query Parameters**:
- `unit_id` (opcional)
- `building_id` (opcional)
- `status` (opcional): PENDING | PAID | PARTIAL
- `period` (opcional): "2026-01"
- `year` (opcional)
- `month` (opcional)
- `user_id` (opcional)

**Response**:
```json
[
  {
    "id": "uuid",
    "amount": 100.00,
    "paid_amount": 50.00,
    "status": "PARTIAL",
    "period": "2026-01",
    "year": 2026,
    "month": 1,
    "issue_date": "2026-01-01",
    "created_at": "2026-01-01T00:00:00Z",
    "unit": {
      "id": "uuid",
      "name": "1-A"
    },
    "user": {
      "id": "uuid",
      "name": "Juan Pérez"
    }
  }
]
```

### POST `/billing/debt`
Cargar deuda a un apartamento

**Permisos**: Admin o Board

**Request Body**:
```json
{
  "unit_id": "uuid",
  "amount": 100.00,
  "period": "2026-01",
  "description": "Condominio enero 2026",
  "due_date": "2026-01-31"      // Opcional
}
```

**Response**:
```json
{
  "id": "uuid",
  "unit_id": "uuid",
  "amount": 100.00,
  "period": "2026-01",
  "description": "Condominio enero 2026",
  "status": "PENDING",
  "paid_amount": 0.00,
  "due_date": "2026-01-31",
  "created_at": "2026-01-01T00:00:00Z"
}
```

### GET `/billing/units/:id/balance`
Obtener balance de un apartamento

**Permisos**: Admin, Board, Residente (mismo apartamento)

**Response**:
```json
{
  "unit": "1-A",
  "totalDebt": 150.00,
  "pendingInvoices": 2,
  "details": [
    {
      "invoiceId": "uuid",
      "amount": 100.00,
      "paid": 50.00,
      "remaining": 50.00,
      "period": "2026-01",
      "status": "PARTIAL"
    },
    {
      "invoiceId": "uuid",
      "amount": 100.00,
      "paid": 0.00,
      "remaining": 100.00,
      "period": "2026-02",
      "status": "PENDING"
    }
  ]
}
```

### GET `/billing/units/:id/invoices`
Listar facturas de un apartamento

**Permisos**: Admin, Residente (mismo apartamento)

**Response**: Array de facturas

### GET `/billing/invoices/:id`
Detalles de una factura

**Permisos**: Admin, Board, Residente (mismo apartamento)

### GET `/billing/invoices/:id/payments`
Listar pagos aplicados a una factura

**Permisos**: Admin o Board

**Response**:
```json
[
  {
    "id": "payment-uuid",
    "amount": 50.00,
    "status": "APPROVED",
    "payment_date": "2026-01-15",
    "method": "PAGO_MOVIL",
    "reference": "123456",
    "allocated_amount": 50.00,
    "allocation_id": "uuid",
    "allocated_at": "2026-01-15T10:00:00Z",
    "user": {
      "id": "uuid",
      "name": "Juan Pérez"
    }
  }
]
```

---

## 📋 Modelos de Datos

### User
```typescript
{
  id: string;
  email: string;
  name: string;
  role: 'admin' | 'board' | 'resident';  // Rol global
  status: 'active' | 'pending' | 'rejected';
  phone?: string;
  units: UserUnit[];
  created_at: Date;
  updated_at: Date;
}
```

### UserUnit
```typescript
{
  unit_id: string;
  building_id?: string;
  building_role: 'board' | 'resident' | 'owner';  // Rol por edificio
  is_primary: boolean;
}
```

### Building
```typescript
{
  id: string;
  name: string;
  address: string;
  created_at: Date;
  updated_at?: Date;
}
```

### Unit
```typescript
{
  id: string;
  building_id: string;
  name: string;
  floor?: string;
  aliquot?: number;
  created_at?: Date;
  updated_at?: Date;
}
```

### Payment
```typescript
{
  id: string;
  amount: number;
  currency?: string;
  payment_date: Date;
  status: 'PENDING' | 'APPROVED' | 'REJECTED';
  method: 'PAGO_MOVIL' | 'TRANSFER' | 'CASH';
  reference?: string;
  bank?: string;
  unit_id: string;
  building_id?: string;
  proof_url?: string;
  notes?: string;
  periods?: string[];
  allocations?: Allocation[];
  created_at?: Date;
  updated_at?: Date;
  user?: {
    id: string;
    name: string;
  };
}
```

### Invoice
```typescript
{
  id: string;
  unit_id: string;
  amount: number;
  paid_amount: number;
  period: string;        // "2026-01"
  description?: string;
  status: 'PENDING' | 'PARTIAL' | 'PAID';
  due_date?: Date;
  created_at?: Date;
  updated_at?: Date;
}
```

---

## 🔑 Autenticación

Todos los endpoints (excepto `/auth/*` y algunos públicos de `/buildings`) requieren autenticación mediante Bearer Token en el header:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

## 🚨 Códigos de Error

| Código | Descripción |
|--------|-------------|
| 200 | OK - Éxito |
| 201 | Created - Recurso creado |
| 400 | Bad Request - Datos inválidos |
| 401 | Unauthorized - No autenticado |
| 403 | Forbidden - Sin permisos |
| 404 | Not Found - Recurso no encontrado |
| 500 | Internal Server Error - Error del servidor |

---

## 📌 Notas Importantes

1. **Separación de Responsabilidades**:
   - Editar perfil de usuario: `PATCH /users/:id`
   - Gestionar unidades de usuario: `POST /users/:id/units`

2. **Permisos por Rol**:
   - **Admin**: Acceso total
   - **Board**: Solo recursos de sus edificios
   - **Resident**: Solo sus propios recursos

3. **Building Role vs Role Global**:
   - `user.role`: Rol global del sistema
   - `user.units[].building_role`: Rol específico en cada edificio

4. **Multipart Form Data**:
   - `POST /payments` usa `multipart/form-data` para subir archivos

5. **Filtros Dinámicos**:
   - La mayoría de endpoints GET soportan filtros opcionales por query params

---

**Última actualización**: 2026-02-05  
**Versión del API**: v2.1.0
