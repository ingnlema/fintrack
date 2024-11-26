### Endpoint: `GET /transactions/{filters}`

#### Descripción:
Recupera una lista de transacciones para una cuenta específica, permitiendo filtrar por rango de fechas y tipo de transacción. **Las fechas deben estar en formato ISO (`YYYY-MM-DDTHH:mm:ss`) y siempre se pasarán como strings entre el frontend y el backend.**

---

#### Entrada

**URL:** `/transactions`

**Método:** `GET`

**Parámetros de Entrada (query params):**
- **idaccount** (String) - ID de la cuenta a consultar. (Obligatorio)
- **fechadesde** (String, formato ISO: `YYYY-MM-DDTHH:mm:ss`) - Fecha de inicio para el filtro de transacciones. (Opcional)
- **fechahasta** (String, formato ISO: `YYYY-MM-DDTHH:mm:ss`) - Fecha de fin para el filtro de transacciones. (Opcional)
- **tipo** (String) - Define el tipo de transacciones a recuperar:
    - `"entrada"` para transacciones de entrada.
    - `"salida"` para transacciones de salida.
    - `"todos"` para ambos tipos. (Opcional, por defecto: `"todos"`)

**Ejemplo de Request:**
```http
GET /transactions?idaccount=12345&fechadesde=2024-01-01T00:00:00&fechahasta=2024-12-31T23:59:59&tipo=entrada
```

#### Salida

**Formato de Response:** `application/json`

**Ejemplo de Response:**
```json
{
  "idaccount": "12345",
  "transactions": [
    {
      "nameaccount": "Cuenta Principal",
      "amount": 1500.00,
      "currency": "UYU",
      "type": "entrada",
      "descripcion": "Ingreso por venta",
      "fecha": "2024-05-01T12:00:00",
      "category": "Comida",
      "notas": "Transacción recurrente"
    },
    {
      "nameaccount": "Cuenta Principal",
      "amount": 200.00,
      "currency": "USD",
      "type": "entrada",
      "descripcion": "Ropa",
      "fecha": "2024-05-15T15:30:00",
      "category": "Reembolsos",
      "notas": "Nota adicional para referencia"
    }
  ]
}
```

### Endpoint: `GET /transactions`

#### Descripción:
Recupera una lista de todas las transacciones registradas en el sistema, sin aplicar filtros específicos.

---

#### Entrada

**URL:** `/transactions`

**Método:** `GET`

**Ejemplo de Request:**
```http
GET /transactions
```

#### Salida
**Formato de Response:** `application/json`

```json
{
  "transactions": [
    {
      "idaccount": "1234",
      "nameaccount": "Cuenta Principal",
      "amount": 1500.00,
      "currency": "USD",
      "type": "entrada",
      "descripcion": "Ingreso por venta",
      "fecha": "2024-05-01T12:00:00",
      "category": "Ventas",
      "notas": "Transacción recurrente"
    },
    {
      "idaccount": "5678",
      "nameaccount": "Cuenta de Ahorros",
      "amount": 200.00,
      "currency": "UYU",
      "type": "salida",
      "descripcion": "Pago de servicios",
      "fecha": "2024-06-15T08:30:00",
      "category": "Gastos Fijos",
      "notas": "Pago mensual de electricidad"
    }
  ]
}
```

### Endpoint: `GET /balance`

#### Descripción:
Obtiene el balance actual de una cuenta específica.

---

#### Entrada

**URL:** `/balance`

**Método:** `GET`

**Parámetros de Entrada (query params):**
- **idaccount** (String) - ID de la cuenta para la cual se desea obtener el balance. (Obligatorio)

**Ejemplo de Request:**
```http
GET /balance?idaccount=12345
```

#### Salida
**Formato de Response:** `application/json`

```json
{
  "idaccount": "12345",
  "currentBalance": 1500.00
}
```

### Endpoint: `POST /transaction`

#### Descripción:
Registra una nueva transacción para una cuenta específica.

---

#### Entrada

**URL:** `/transaction`

**Método:** `POST`

**Body (JSON):**
```json
{
  "idaccount": "1234",
  "nameaccount": "Cuenta Principal",
  "amount": 200.00,
  "currency": "USD",
  "type": "entrada",
  "descripcion": "Ropa",
  "fecha": "2024-05-15T15:30:00",
  "category": "Reembolsos",
  "notas": "Nota adicional para referencia"
}
```

#### Salida
**Formato de Response:** `application/json`

POST /transaction
Content-Type: application/json
```json
{
  "idaccount": "1234",
  "nameaccount": "Cuenta Principal",
  "amount": 200.00,
  "currency": "USD",
  "type": "entrada",
  "descripcion": "Ropa",
  "fecha": "2024-05-15T15:30:00",
  "category": "Reembolsos",
  "notas": "Nota adicional para referencia"
}
```

### Endpoint: `POST /account`

#### Descripción:
Crea una nueva cuenta con un balance inicial y la información básica del usuario.

---

#### Entrada

**URL:** `/account`

**Método:** `POST`

**Body (JSON):**
```json
{
  "initialbalance": 1000.00,
  "currency": "USD",
  "username": "johndoe",
  "nameaccount": "Cuenta Principal"
}
```

#### Salida
**Formato de Response:** `application/json`

POST /account
Content-Type: application/json

```json
{
  "initialbalance": 1000.00,
  "currency": "USD",
  "username": "johndoe",
  "nameaccount": "Cuenta Principal"
}
```

### Endpoint: `GET /account/{idaccount}`

#### Descripción:
Recupera la información de una cuenta específica, incluyendo el balance actual y la información básica del usuario.

---

#### Entrada

**URL:** `/account/{idaccount}`

**Método:** `GET`

**Parámetros de Ruta:**
- **idaccount** (String) - ID de la cuenta que se desea consultar. (Obligatorio)

**Ejemplo de Request:**
```http
GET /account/1234
```

#### Salida
**Formato de Response:** `application/json`

```json
{
  "idaccount": "1234",
  "balance": 1000.00,
  "currency": "USD",
  "username": "johndoe",
  "nameaccount": "Cuenta Principal"
}
```

### Endpoint: `GET /accounts`

#### Descripción:
Recupera una lista de todas las cuentas, incluyendo información básica de cada una.

---

#### Entrada

**URL:** `/accounts`

**Método:** `GET`

**Ejemplo de Request:**
```http
GET /accounts
```

#### Salida
**Formato de Response:** `application/json`

```json
{
  "accounts": [
    {
      "idaccount": "1234",
      "balance": 1000.00,
      "currency": "USD",
      "username": "johndoe",
      "nameaccount": "Cuenta Principal"
    },
    {
      "idaccount": "5678",
      "balance": 500.00,
      "currency": "UYU",
      "username": "janedoe",
      "nameaccount": "Cuenta de Ahorros"
    }
  ]
}
```

### Endpoint: `DELETE /account/{idaccount}`

#### Descripción:
Elimina una cuenta específica mediante su ID.

---

#### Entrada

**URL:** `/account/{idaccount}`

**Método:** `DELETE`

**Parámetros de Ruta:**
- **idaccount** (String) - ID de la cuenta que se desea eliminar. (Obligatorio)

**Ejemplo de Request:**
```http
DELETE /account/1234
```

#### Salida
**Formato de Response:** `application/json`

```json
{
  "message": "Account successfully deleted",
  "accountId": "1234"
}

```

